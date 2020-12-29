package learn_IO_Network;

import java.io.Console;

/*
 * Console 클래스
 * 
 * 주의
 * 
 * 이클립스에서 System.console()은 null을 리턴함
 * 반드시 명령 프롬포트에서 실행해야 함.
 * 
 * 실행 방법.
 * D:\Java\learn\bin>java learn_IO_Network.Hello02
 */

public class Hello02 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Console console = System.console();

        if (console == null) {
            System.out.println(Hello02.class.getName());
            System.out.println("자바 콘솔뷰에서 실행 불가");
            return;
        }

        System.out.print("아이디 : ");
        // 평범하게 입력
        // 입력 내용이 echo된다.
        String id = console.readLine();

        System.out.print("비밀번호 : ");
        // 입력내용이 프롬포트에 표시되지 않는다.
        char[] charPass = console.readPassword();

        System.out.println("...");
        System.out.println(id);
        System.out.println(new String(charPass));
    }

}
