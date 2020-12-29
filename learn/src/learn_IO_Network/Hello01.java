package learn_IO_Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/*
 * 콘솔 입출력
 * 유닉스 계열 : 터미널
 * 윈도우 계열 : 명령 프롬프트
 * 자바 : 콘솔 뷰
 * 
 * 1. System.in, System.out, System.err
 * 2. Scanner 클래스
 * 
 */

public class Hello01 {

    public static void main(String[] args) throws IOException {
        // System.in 필드
        InputStream is = System.in;
        // read, 캐릭터 하나 받기
        char inputChar = (char) is.read(); // return Ascii code
        System.out.println(inputChar);
        is.read(); // \r, 캐리지 리턴
        is.read(); // \n, 라인 피드

        // read(byte[] b) or read(byte[] b, int off, int len)
        // read()는 아스키 코드이므로, 한글, 유니코드등을 위해 read(byte[]) 메소드 사용
        byte[] inputBytes = new byte[100];
        System.out.print("이름 : ");
        int inputBytesNum = is.read(inputBytes); // 실제입력
        // 출력, -2 => Enter키에 해당하는 캐리지리턴(13)과 라인피드(10) 제거
        System.out.println(new String(inputBytes, 0, inputBytesNum - 2));

        // System.out 필드
        OutputStream os = System.out; // 상위 타입으로 변환, 공통 메소드만 사용 가능
        // 아스키 코드를 문자로 콘솔에 출력
        for (byte b = 48; b < 58; ++b)
            os.write(b);
        os.write(10); // \n

        for (int i = 97; i < 123; ++i)
            os.write(i);
        os.write(10);

        // 한글도 가능하기 위해
        os.write("홍길동\n".getBytes());
        os.flush();
        // os.close();

        // System.out은 OutputStream의 하위 클래스 PrintStream
        PrintStream ps = System.out;
        ps.println("우리가 보통 쓰는거 가능");

        // Scanner 클래스
        // System.in 뿐만 아니라, File, InputStream, Path등도 가능
        Scanner scanner = new Scanner(System.in);
        System.out.print("문자열 입력 : ");
        String str = scanner.nextLine();
        System.out.println(str);

        System.out.print("정수 입력 : ");
        int i = scanner.nextInt();
        System.out.println(i);

        System.out.print("실수 입력 ");
        double d = scanner.nextDouble();
        System.out.println(d);
    }

}
