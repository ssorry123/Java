package learn_IO_Network;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 클라이언트 기본
 * 
 * @author 26060
 *
 *
 */
public class Hello06_Client {

    public static void main(String[] args) {
        Socket socket = null;

        try {
            socket = new Socket();
            System.out.println("연결 요청");
            socket.connect(new InetSocketAddress("localhost", 5001));
            System.out.println("연결 성공");

            // 보내는 스트림
            byte[] bytes = null;
            String msg = null;

            OutputStream os = socket.getOutputStream();
            msg = "Hello Server";
            bytes = msg.getBytes("UTF-8");
            os.write(bytes);
            os.flush();
            System.out.println("데이터 보내기 성공");

            // 받는 스트림
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
            System.out.println("데이터 받기 성공 : " + msg);

            os.close();
            is.close();

        } catch (Exception e) {

        }

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (Exception e) {

            }
        }

    }

}
