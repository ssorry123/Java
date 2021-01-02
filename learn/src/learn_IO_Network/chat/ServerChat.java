package learn_IO_Network.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// JavaFX 메인클래스로 만들기 위해 Application 상속
public class ServerChat extends Application {
    ExecutorService executorService; // 서버 과부하 방지를 위한 스레드풀
    ServerSocket serverSocket; // 클라이언트 연결 수락 담당
    // 연결된 스레드 저장 List, Thread safe한 vector
    List<Client> connections = new Vector<Client>();

    // 연결된 클라이언트를 표현
    // 클라이언트 별로 고유한 데이터를 저장할 필요가 있음
    class Client {
        Socket socket;

        Client(Socket socket) {
            this.socket = socket;
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
                                displayText(msg);
                            });

                            // 받은 데이터 문자열로 변환
                            String data = new String(byteArr, 0, readByteCount, "UTF-8");
                            // 모든 클라이언트에게 데이터를 보냄
                            for (Client client : connections) {
                                client.send(data);
                            }

                        }
                    } catch (Exception e) {
                        // 내부 객체인 클라이언트를 제거한다.
                        final String msg = "[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": "
                                + Thread.currentThread().getName() + "]";
                        Platform.runLater(() -> {
                            displayText(msg);
                        });
                        connections.remove(Client.this);
                        try {
                            socket.close();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            };

            executorService.submit(runnable);
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
                        // 내부 객체인 클라이언트를 제거한다.
                        connections.remove(Client.this);
                        final String msg = "[클라이언트 통신 안됨: " + socket.getRemoteSocketAddress() + ": "
                                + Thread.currentThread().getName() + "]";

                        Platform.runLater(() -> displayText(msg));
                        try {
                            socket.close();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            };
            executorService.submit(runnable);
        }
    }

    void startServer() {
        // cpu코어 수만큼 스레드 생성 허용
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        // ServerSocket 생서 및 포트 바인딩
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5001));
        } catch (Exception e) {
            System.out.println("예외 발생");
            if (!serverSocket.isClosed())
                stopServer();
            return;
        }

        // 연결 수락
        // 작업 스레드 생성
        Runnable runnable;
        runnable = new Runnable() {
            @Override
            public void run() {
                // 작업 쓰레드는 UI를 변경하지 못하므로
                // UI 관리 메소드를 사용
                Platform.runLater(() -> {
                    displayText("[서버 시작]");
                    btnStartStop.setText("stop");
                });

                while (true) {
                    try {
                        // 연결 수락
                        Socket socket = serverSocket.accept();
                        String msg = "[연결수락 : ";
                        msg += socket.getRemoteSocketAddress();
                        msg += ": ";
                        msg += Thread.currentThread().getName();
                        msg += "]";

                        // 클라이언트 객체 저장
                        Client client = new Client(socket);
                        connections.add(client);

                        Platform.runLater(() -> {
                            displayText("연결개수: " + connections.size() + "]");
                        });

                    } catch (Exception e) {
                        if (!serverSocket.isClosed())
                            stopServer();
                        break;
                    }
                }
            }
        };

        // 쓰레드 풀에서 처리
        executorService.submit(runnable);
    }

    void stopServer() {
        // 모든 Socket 닫기, ServerSocket 닫기, ExecutorService 종료
        try {
            // 모든 소켓 닫기
            Iterator<Client> iter = connections.iterator();
            while (iter.hasNext()) {
                Client c = iter.next();
                c.socket.close();
                iter.remove();
            }
            // 서버 소켓 닫기
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            // 스레드 풀 종료
            if (executorService != null && !executorService.isShutdown()) {
                executorService.shutdown();
            }
            Platform.runLater(() -> {
                displayText("[서버 멈춤]");
                btnStartStop.setText("start");
            });
        } catch (Exception e) {

        }
    }

    //////////////////////////////////////////////////////////////////
    // UI 관련
    TextArea txtDisplay;
    Button btnStartStop;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 300);

        txtDisplay = new TextArea();
        txtDisplay.setEditable(false);
        BorderPane.setMargin(txtDisplay, new Insets(0, 0, 2, 0));
        root.setCenter(txtDisplay);

        btnStartStop = new Button("start");
        btnStartStop.setPrefHeight(30);
        btnStartStop.setMaxWidth(Double.MAX_VALUE);

        // start와 stop버튼을 클릭했을때 이벤트 처리 코드
        btnStartStop.setOnAction(e -> {
            if (btnStartStop.getText().equals("start")) {
                System.out.println("서버 시작");
                startServer();
            } else if (btnStartStop.getText().equals("stop")) {
                System.out.println("서버 종료");
                stopServer();
            }
        });
        root.setBottom(btnStartStop);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("app.css").toString());
        primaryStage.setScene(scene);
        primaryStage.setTitle("server");
        primaryStage.setOnCloseRequest(e -> stopServer()); // 닫기 버튼
        primaryStage.show();
    }

    void displayText(String text) {
        txtDisplay.appendText(text + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
