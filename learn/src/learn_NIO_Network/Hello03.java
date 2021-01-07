package learn_NIO_Network;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

public class Hello03 {

    public static void main(String[] args) {
        // 1. Non-direct 버퍼
        // 1-1. allocate 메소드
        ByteBuffer byteBuffer = ByteBuffer.allocate(100); // 바이트 100개 저장 버퍼
        CharBuffer charBuffer = CharBuffer.allocate(100); // 캐릭터 100가 저장 버퍼

        // 1-2. wrap 메소드
        // 이미 생성된 자바 배열을 래핑해서 버퍼 객체 생성
        byte[] byteArr = new byte[100];
        byteBuffer = ByteBuffer.wrap(byteArr);
        char[] charArr = new char[100];
        charBuffer = CharBuffer.wrap(charArr);
        charBuffer = CharBuffer.wrap(charArr, 0, 50); // 50개만 버퍼로

        // 2. direct 버퍼
        // ByteBuffer 클래스에서만 제공함
        byteBuffer = ByteBuffer.allocateDirect(100);
        System.out.println("저장용량: " + byteBuffer.capacity() + "개 바이트"); // 100개
        charBuffer = ByteBuffer.allocateDirect(100).asCharBuffer();
        System.out.println("저장용량: " + charBuffer.capacity() + "개 캐릭터"); // 50개
        IntBuffer intBuffer = ByteBuffer.allocateDirect(100).asIntBuffer();
        System.out.println("저장용량: " + intBuffer.capacity() + "개 정수\n"); // 25개

        // 3. byte 해석 순서
        // OS 마다 차이가 있을 수 있다.
        // 고려해야 한다.
        // Big endian : 앞쪽 바이트부터 처리 : 왼쪽부터 순서대로 메모리에 배치
        // Little endian : 뒤쪽 바이트부터 처리 : 오른쪽부터 순서대로 메모리에 배치
        // JVM도 일조의 독립된 운영체제이므로 이런 문제를 취급한다.

        // JRE가 설치된 어떤 환경이든 JVM은 무조건 [Big endian]으로 동작한다.
        // 운영체제와 JVM의 바이트 해석 순석 다른 경우 JVM이 자동적으로 처리해주기 대문에 문제는 없다.
        // 하지만 다이렉트 버퍼일 경우 운영체제의 기본 해석 순서로 JVM의 해석 순서를 맞추는 것이 성능에 도움이 된다.
        System.out.println("운영체제 종류: " + System.getProperty("os.name"));
        System.out.println("네이티브의 바이트 해석 순서: " + ByteOrder.nativeOrder() + "\n"); // LITTLE_ENDIAN
        byteBuffer = ByteBuffer.allocateDirect(100).order(ByteOrder.nativeOrder()); // OS의 기본해석 순서로 맞춰줌.

        
    }

}
