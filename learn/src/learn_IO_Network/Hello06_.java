package learn_IO_Network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * 네트워크
 * 클라이언트 - 연결 요청
 * 서버 - 연결 수락
 * 클라이언트 - 처리 요청 [request]
 * 서버 - 처리
 * 서버 - 응답(처리 결과)   [ response]
 */

/*
 * 한 대의 컴퓨터에는 다양한 서버 프로그램이 실행될 수 있다.
 * 웹, DBMS, FTP 서버 등이 하나의 IP 주소를 갖는 컴퓨터에서 동시에 실행될 수 있다.
 * 클라이언트는 어떤 서버와 통신해야 할지 결정해야 한다.
 * IP는 컴퓨터의 네트워크 어댑터까지만 갈 수 있는 정보이기 때문에
 * 컴퓨터 내에서 실행하는 서버를 선택하기 위해 추가적인 정보가 필요하다
 * 이 정보를 포트 번호라고 한다.
 * 
 * 포트 바인딩 : 서버는 시작할 때 고정적인 포트 번호를 가지고 실행한다.
 * 기본적으로
 * 웹서버 80번
 * FTP서버 21번
 * DBMS 1521번
 * 
 */
/*
 * 클라이언트도 서버에서 보낸 정보를 받기 위해 포트번호가 필요하다
 * 서버와 같이 고정적인 포트 번호가 아니라 운영체제가 자동으로 부여하는 동적 포트 번호를 사용한다.
 * 이 동적 포트는 클라이언트가 서버로 연결 요청을 할 때 전송되어 서버가 클라이언트로 데이터를 보낼때 사용된다.
 */

public class Hello06_ {
    public static void p() {
        System.out.println();
    }

    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) throws IOException {
        // 1. java.net.InetAddress
        // 자바는 IP주소를 InetAddress 객체로 표현한다.
        // IP주소 뿐만 아니라 DNS에서 도메인을 검색한 후 IP 주소를 가져오는 기능을 제공한다.

        // 로컬 컴퓨터의 InetAddress 얻기
        InetAddress ia = InetAddress.getLocalHost();
        p(ia.getHostName());
        p(ia.getHostAddress());
        p();

        // 외부 컴퓨터의 InetAddress 얻기
        String host = "www.naver.com";
        ia = InetAddress.getByName(host);
        p(ia.getHostName()); // www.naver.com
        p(ia.getHostAddress()); // 도메인에 등록된 하나의 ip주소
        p();

        // 하나의 도메인에 여러 개의 컴퓨터IP가 등록되어 있을 수 있다
        // (서버의 과부하를 막기 위해)
        InetAddress[] iaArr = InetAddress.getAllByName(host);
        for (InetAddress ina : iaArr) {
            p(ina.getHostAddress());
        }

        // 2. TCP네트워킹, 연결 지향적 프로토콜
        // Transmission Control Protocol
        // 클라이언트와 서버가 연결된 상태에서 데이터를 주고받는 프로토콜

        // TCP 서버
        // java.net.ServerSocket : 클라이언트의 연결 요청을 기다림, 연결 수락 담당
        // java.net.Socket : 연결된 클라이언트와 통신을 담당

        // 서버를 개발하려면 우선 ServerSocket 객체를 얻어야 한다.
        // 이미 포트가 사용중이라면 BindException이 발생한다.
        // 다른 포트로 바인딩 하거나, 다른 프로그램을 종료한고 다시 실행한다.

        // 첫번째 방법
        ServerSocket ss1 = new ServerSocket(5001); // 5001번 포트에 바인딩
        // 두번째 방법
        ServerSocket ss2 = new ServerSocket();
        ss2.bind(new InetSocketAddress(5002));
        // 세번째 방법
        // 서버 pc에 멀티 IP가 할당되어 있는 경우,
        // 특정 IP로 접속할 때만 연결 수락을 하고 싶은 경우
        String aIP = InetAddress.getLocalHost().getHostAddress();
        ServerSocket ss3 = new ServerSocket();
        ss3.bind(new InetSocketAddress(aIP, 5003));

        // 블로킹되어 있을때 ServerSocket을 닫으면 예외가 발생한다.
        try {
            // accept메소드는 클라이언트가 연결요청하기 전가지 블로킹된다.(스레드가 대기상태 됨)
            Socket socket1 = ss1.accept();
            p("소켓 블로킹 해제");
            
            
        } catch (Exception e) {

        }
    }

}
