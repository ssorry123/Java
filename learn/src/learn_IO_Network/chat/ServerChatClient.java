package learn_IO_Network.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.application.Platform;

/**
 * 서버에 연결된 클라이언트를 표현 클라이언트 별로 고유한 데이터를 저장할 필요가 있음. ServerChat을 상속받지 않고, 이 객체 생성시
 * 부모 객체(ServerChat)를 저장하는 방식
 * 
 * @author 26060
 *
 */
class ServerChatClient {
    ServerChat server;
    Socket socket;

    ServerChatClient(Socket socket, ServerChat server) {
        this.socket = socket;
        this.server = server;
        receive();
    }

    // 데이터 받기
    void receive() {
        // 받기 작업 스레드 생성
        Runnable runnable;
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        byte[] byteArr = new byte[100];
                        InputStream is = socket.getInputStream();

                        // 클라이언트가 비정상 종료를 하면 IOException이 발생한다.
                        int readByteCount = is.read(byteArr);

                        // 클라이언트가 정상적으로 Socket의 close를 호출할 경우, -1 리턴
                        if (readByteCount == -1) {
                            throw new IOException();
                        }

                        //
                        final String msg = "[받은 요청 처리:" + socket.getRemoteSocketAddress() + ": "
                                + Thread.currentThread().getName() + "]";

                        Platform.runLater(() -> {
                            server.displayText(msg);
                        });

                        // 받은 데이터 문자열로 변환
                        String data = new String(byteArr, 0, readByteCount, "UTF-8");
                        // 모든 클라이언트에게 데이터를 보냄
                        for (ServerChatClient client : server.connections) {
                            client.send(data);
                        }

                    }
                } catch (Exception e) {
                    exception(e);
                }
            }
        };

        server.executorService.submit(runnable);
    }

    // 데이터 보내기
    void send(String data) {
        // 보내기 작업 스레드 생성
        Runnable runnable;
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] byteArr = data.getBytes("UTF-8");
                    OutputStream os = socket.getOutputStream();
                    os.write(byteArr);
                    os.flush();
                } catch (Exception e) {
                    exception(e);
                }
            }
        };
        server.executorService.submit(runnable);
    }

    void exception(Exception e) {
        server.connections.remove(this);
        // 내부 객체인 클라이언트를 제거한다.
        server.connections.remove(ServerChatClient.this);
        final String msg = "[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": " + Thread.currentThread().getName()
                + "]";

        Platform.runLater(() -> server.displayText(msg));
        try {
            socket.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}
