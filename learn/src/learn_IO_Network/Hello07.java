package learn_IO_Network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/*
 * UDP ( User Datagram Protocol )
 * 비연결 지향적 프로토콜
 * TCP 보다는 빠른 전송을 할 수 있지만 전달의 신뢰성은 떨어진다.
 * 
 * java.net.DatagramSocket : 발신점, 수신점
 * java.net.DatagramPacket : 주고받는 패킷 클래스
 */

class Sender {
    void main() throws IOException {
        // 발신자는 DatagramSocket을 생성할때 포트 번호가 필요 없다.
        // 메세지를 보낼때 정한다.
        DatagramSocket datagramSocket = new DatagramSocket();

        System.out.println("발신 시작");

        for (int i = 0; i < 5; ++i) {
            String data = "메세지" + i;
            byte[] byteArr = data.getBytes("UTF-8");

            // 패킷 생성
            DatagramPacket packet = new DatagramPacket(byteArr, byteArr.length,
                    new InetSocketAddress("localhost", 5001));
            // 패킷 전송
            datagramSocket.send(packet);
            System.out.println("[보낸 바이트 수]:" + byteArr.length + "bytes");

        }

        System.out.println("발신 종료\n");

        datagramSocket.close();
    }
}

class Receiver {
    void main() throws SocketException, InterruptedException {
        // 포트를 지정해서 생성한다
        DatagramSocket datagramSocket = new DatagramSocket(5001);

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("수신 시작");
                try {
                    while (true) {
                        // 받은 패킷 저장소
                        DatagramPacket packet = new DatagramPacket(new byte[100], 100);
                        // receive는 블로킹된다.
                        // datagramSocket이 close되면 예외가 발생한다.
                        datagramSocket.receive(packet); // blocking

                        String data = new String(packet.getData(), 0, packet.getLength(), "UTF-8");
                        System.out.println("[받은 내용: " + data + "]" + " from: " + packet.getSocketAddress());

                    }
                } catch (Exception e) {
                    // datagramSocket.close()를 호출하면 예외가 발생해 이쪽으로 온다.
                    System.out.println("수신 종료");
                }

            }
        };

        thread.start();

        // 수신자는 10초후 종료되게 한다.
        Thread.sleep(10000);
        datagramSocket.close();

    }
}

public class Hello07 {

    public static void main(String[] args) {

        Thread receiver = new Thread(() -> {
            try {
                new Receiver().main();
            } catch (Exception e) {

            }
        });
        Thread sender = new Thread(() -> {
            try {
                new Sender().main();
            } catch (Exception e) {

            }
        }
        );

        // 발신자를 먼저 실행하면 수신자가 실행하기 전에 보낸 데이터는 받을 수 없다.
        receiver.start();
        sender.start();
    }

}
