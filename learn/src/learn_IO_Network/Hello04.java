package learn_IO_Network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;

/*
 * 보조 스트림, 성능향상 스트림 --> sub stream
 * 
 * mainInputStream -> subStream1 -> subStream2 -> ... -> Program
 * -> ... -> subStream -> mainOutputStream
 */

/*
 * 문자 변환 보조 스트림
 *      InputStreamReader
 *      OutputStreamReader
 *  
 * 성능 향상 보조 스트림
 *      BufferedInputStream
 *      BufferedReader
 *      
 *      BufferedOutputStream
 *      BufferedWriter
 *     
 * 기본 타입 입출력 보조 스트림
 *      DataInputStream
 *      DataOutputStream
 *     
 * 프린터 보조 스트림
 *      PrintStream
 *      PrintWriter
 */

public class Hello04 {

    public static void main(String[] args) throws IOException {
        String testFile = "D:/Java/learn/src/learn_IO_Network/Temp";
        // TODO Auto-generated method stub

        /* 문자 변환 보조 스트림, 성능 향상 보조 스트림 */
        // 바이트(콘솔) -> 문자 스트림 변환 -> 버퍼 -> 프로그램
        InputStream is = System.in; // 최초
        InputStreamReader isreader = new InputStreamReader(is); // 보조 ( 바이트 -> 문자)
        BufferedReader br = new BufferedReader(isreader); // 보조(성능향상, 버퍼 사용)
        int readCharNo;
        char[] cbuf = new char[100];

        // 바이트 입력 스트림이지만 한글도 가능
        System.out.print("입력 : ");
        while ((readCharNo = br.read(cbuf)) > 2) {
            System.out.print(readCharNo + "\t");
            System.out.println(new String(cbuf, 0, readCharNo - 2));
        }
        br.close();
        isreader.close();
        is.close();

        System.out.println("next");

        // 프로그램 -> 버퍼 -> 문자 출력 스트림 -> 바이트 출력 스트림 -> 파일
        File file = new File(testFile + "/file3.txt");
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file); // 최종 바이트 스트림
        Writer writer = new OutputStreamWriter(fos); // 보조 문자 스트림
        BufferedWriter bw1 = new BufferedWriter(writer);

        String data = "파일 출력 스트림으로, 바이트 스트림이지만 문자를 보낼 수 있게 됨";
        bw1.write(data);
        bw1.flush();
        bw1.close();
        writer.close();
        fos.close();

        /* 기본 타입 입출력 보조 스트림 */
        // 프로그램 -> 데이터 타입 -> 바이트 변환 -> 파일
        File myData = new File(testFile + "/myData.dat");
        myData.createNewFile();
        FileOutputStream fos2 = new FileOutputStream(myData); // 최종 ( 바이트 출력 스트림 )
        BufferedOutputStream bo2 = new BufferedOutputStream(fos2); // 버퍼
        DataOutputStream dos2 = new DataOutputStream(bo2); // 데이터 타입
        dos2.writeUTF("홍길동");
        dos2.writeInt(30);
        dos2.writeUTF("정우성");
        dos2.writeInt(40);
        dos2.flush();
        dos2.close();
        fos2.close();

        // 입력한 스트림 순서대로 가져와야 한다!!!!!!!!!!!!!!!!!
        // 파일 -> 바이트 -> 버퍼 -> 데이터타입 변환 -> 프로그램
        FileInputStream fis2 = new FileInputStream(myData); // 최초
        BufferedInputStream br2 = new BufferedInputStream(fis2); // 버퍼
        DataInputStream dis2 = new DataInputStream(br2); // 데이터 타입

        for (int i = 0; i < 2; ++i) {
            String name = dis2.readUTF();
            int age = dis2.readInt();
            System.out.println(name + " " + age);
        }
        dis2.close();
        

        /* 프린트 보조 스트림 */
        // PrintStream, PrintWriter
        // System.out은 바로 PrintStream의 한 종류이다
        // printf, print, println 메소드를 사용할 수 있다.
        File file4 = new File(testFile + "/file4.txt");
        file4.createNewFile();

        FileOutputStream fos4 = new FileOutputStream(file4);
        BufferedOutputStream bos4 = new BufferedOutputStream(fos4);
        PrintStream ps4 = new PrintStream(bos4);
        ps4.println("가나다");
        ps4.flush();
        ps4.close();
        fos4.close();

        FileWriter fw4 = new FileWriter(file4, true);
        BufferedWriter bw4 = new BufferedWriter(fw4);
        PrintWriter pw4 = new PrintWriter(bw4);
        pw4.println("라마바사");
        pw4.flush();
        pw4.close();
        fw4.close();

        // printf
        // % [argument_index$] [flags] [width] [.precision] conversion
        // argument_index$ : 매개값의 순번 (ex) 1$
        // flags : '생략' 또는 '-' 또는 '0' -> 왼쪽공백채움, 오른쪽공백채움, 0으로 채움
        // width : 전체 자릿수
        // .precision : 소수자릿수
        // conversion : 변환문자
    }

}
