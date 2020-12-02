package learn;

import java.io.FileInputStream;
import java.io.IOException;

class MyFileInputStream implements AutoCloseable {
    private String file;

    public MyFileInputStream(String file) {
        this.file = file;
    }

    public void read() {
        System.out.println(file + " 을 읽습니다");
    }

    @Override
    public void close() throws Exception {
        System.out.println(file + " 을 닫습니다");
    }
}

// 사용자 정의 예외
// 일반예외 설정 또는 실행 예외 설정
// class MyException extends [Exception | RuntimeException]
class MyException extends RuntimeException {
    // 보통 두개의 생성자를 만듬
    public MyException() {

    }

    public MyException(String mes) {
        // 상속받은 클래스에 전달
        super(mes);
    }
}

public class Hello11 {

    static void method1() {
        try {
            method2();
        } catch (Exception e) {
            String msg = e.getMessage();
            System.out.println(msg); // 저장되어있는 메세지 출력
            e.printStackTrace(); // 스택 트레이서 출력
            System.out.println(e + " method2의 예외 method1에서 처리"); // learn.MyException: ~~~
        } finally {

        }
    }

    // 예외 발생 시 메소드를 호출한 곳으로 예외를 떠넘김
    static void method2() throws Exception {
        System.out.println("method2");
        throw new MyException("user exception");
    }

    // main 에서 예외를 넘기면 JVM에서 처리, JVM은 콘솔에 출력하고 마무리
    // public static void main(String[] args) throws Exception {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 다중 catch 블록을 작성할 때는
        // 상위 예외 클래스가 하위 예외 클래스보다 아래쪽에 위치해야 한다.
        try {

        } catch (NullPointerException e) {

        } catch (ArrayIndexOutOfBoundsException e) {

        } catch (NumberFormatException | ClassCastException e) {
            // 멀티 캐치 가능

        } catch (Exception e) {

        } finally {
        }
        // 항상 실행

        // try-with-resources
        // 정상적으로 실행을 완료 했거나 도중에 예외가 발새앟게 되면
        // 자동으로 FileInputStream의 close(); 메소드가 호출된다
        // 리소스 객체는 java.lang.AutoCloseable 인터페이스를 구현하고 있어야 한다.
        try (

                FileInputStream fis = new FileInputStream("file1.txt");
                FileInputStream fis2 = new FileInputStream("file2.txt")) {

        } catch (IOException e) {

        } finally {

        }

        try (MyFileInputStream fis = new MyFileInputStream("file3.txt")) {
            fis.read();
        } catch (Exception e) {

        } finally {

        }

        // 사용자 정의 예외, 예외 넘기기 확인
        method1();
    }

}
