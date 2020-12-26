package learnProcess;

import java.io.IOException;

/*
 * 프로세스
 */

public class Hello00 {
    public static void main(String[] args) throws IOException, InterruptedException {

        // 1. Runtime 객체 사용
        System.out.println("start1");
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("notepad.exe");
        process.waitFor(); // 끝날때까지 대기

        // 2. ProcessBuilder 사용
        System.out.println("start2");
        ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
        Process process2 = processBuilder.start();
        // 2초후 종료시킴
        Thread.sleep(2000);
        process2.destroy();
        System.out.println(process.exitValue()); // 정상종료시 0

        System.out.println("main process end");
    }
}
