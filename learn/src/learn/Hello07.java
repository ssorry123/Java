package learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Hello07 {

    // 버퍼리더를 사용할때는 throws IOExeption 또는 예외 처리를 해주어야 함
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        // 버퍼리더로 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("abcde\n"); // 문자열 출력
        bw.write(str);
        bw.newLine(); // 줄바꿈
        bw.flush();// 버퍼에 있는 데이터 모두 출력, 마지막에 한번만 IO
        br.close();
        bw.close();// 닫아주어야함
    }

}
