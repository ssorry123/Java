package learn_IO_Network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 다중 클라이언트 연결을 수락하는 서버 기본적인 코드
 * 
 * @author 26060
 *
 */

public class Hello06_Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5001));

            while (true) {
                System.out.println("\n--클라이언트의 연결 기다리는 중...");
                Socket socket = serverSocket.accept(); // blocking
                System.out.println("클라이언트와 연결 됨...");

                // 연결된 클라이언트의 IP와 포트 정보등을 얻기
                InetSocketAddress isa;
                isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println(isa.getHostName()); // 클라이언트 IP
                System.out.println(isa.getPort()); // 클라이언트 포트번호
                System.out.println(isa.toString() + "\n"); // IP:포트번호

                byte[] bytes;
                String msg;

                InputStream is = socket.getInputStream();
                bytes = new byte[100];
                int readByteCount = is.read(bytes); // blocking
                // blocking이 해제되는 경우
                // 1. 상대방이 데이터 보냄 : readByteCount
                // 2. 상대방이 정상적으로 소켓의 close()호출 : -1
                // 3. 상대방이 비정상적으로 종료 : IOException
                if (readByteCount == -1) {
                    // do something
                }

                msg = new String(bytes, 0, readByteCount, "UTF-8");
                System.out.println("받은 메세지 : " + msg);

                OutputStream os = socket.getOutputStream();
                msg = "Hello Client";
                bytes = msg.getBytes();
                os.write(bytes);
                os.flush();
                System.out.println("데이터 보내기 성공");

                is.close();
                os.close();
                socket.close();

            }
        } catch (Exception e) {
        }

        // 서버가 닫혀있지 않으면
        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch (Exception e) {

            }
        }

    }

}
