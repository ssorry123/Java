package learn_IO_Network;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/*
 * 자바에서 데이터는 스트림을 통해 입출력된다.
 * 
 * 스트림은 크게 바이트 기반 스트림과, 문자 기반 스트림
 * 
 * - 최상위 클래스
 *      - 하위 클래스
 * 
 *  1. 바이트 기반 스트림 :: 모든 종류
 *      - InputStream
 *          - FileInputStream
 *          - BufferedInputStream
 *          - DataInputStream
 *      - OutputStream
 *          - FileOutputStream
 *          - PrintStream
 *          - BufferedOutputStream
 *          - DataOutputStream
 *          
 *          
 *  2. 문자 기반 스트림 :: 문자만
 *      - Reader
 *          - FileReader
 *          - BufferedReader
 *          - InputStreamReader
 *      - Writer
 *          - FileWriter
 *          - BufferedWriter
 *          - PrintWriter
 *          - OutputStreamWriter
 *  
 */

public class Hello00 {

    public static void main(String[] args) throws IOException {
        String testFile = "D:/Java/learn/src/learn_IO_Network";

        // 1. InputStream
        // 1-1. read() : 입력스트림으롭터 1바이트를 읽고, 읽은 바이트를 리턴
        // 1-2. read(byte[] b) : 읽은 바이트들을 배열에 저장하고, 읽은 바이트 수를 리턴
        // 1-3. read(byte[] b, int off, int len)
        // : len개의 바이트를 읽고, b[off]부터 저장, 모두 읽지 못하면 실제로 읽은 바이트 수 리턴

        // 1. 더이상 읽을 수 없을 경우 -1을 리턴한다
        // 1-1. read()
        // 1바이트를 읽고, 4바이트 int타입으로 리턴, 끝에 1바이트만 데이터가 들어 있음
        InputStream is = new FileInputStream(testFile + File.separator + "test.dat");
        int readByte;
        while ((readByte = is.read()) != -1) {
            System.out.print(readByte);
        }
        System.out.println();

        // 1-2. read(byte[] b)
        // 매개값으로 주어진 바이트 배열 길이만큼 읽는다.
        // 위에서 한번 읽었고, 스트림이므로 재활용 할 수 없다.
        is = new FileInputStream(testFile + File.separator + "test.dat");
        byte[] b = new byte[100];
        int readByteNum;
        while ((readByteNum = is.read(b)) != -1) {
            System.out.println(readByteNum);
        }

        // 1-3. read(byte[] b, int off, int len)
        // 아래 둘은 같은 의미이다.
        is.read(b);
        is.read(b, 0, b.length);

        System.out.println();
        is.close();

        // 2. OutputStream
        // 2-1. write(int i) : 출력스트림으로 1바이트를 보낸다(i의 끝 1바이트)
        // 2-2. write(byte[] b) : 바이트 배열의 모든 바이트를 보낸다
        // 2-3. write(byte[] b, int off, int len)
        // : b[off] 부터 len개의 바이트 출력 스트림으로 보낸다.
        File outFile = new File(testFile + File.separator + "out.txt");
        // outFile.createNewFile();
        OutputStream os = new FileOutputStream(outFile);
        // 2-1. write(int i)
        byte[] data = "ABC".getBytes();
        for (int i = 0; i < data.length; ++i) {
            os.write(data[i]);
        }
        // 2-2. write(byte[] b)
        byte[] data1 = "DEF".getBytes();
        os.write(data1);
        // 2-3. write(byte[] b, int off, int len)
        byte[] data2 = "1XYZ2".getBytes();
        os.write(data2, 1, 3);

        os.flush();
        os.close();

        // 3. Reader
        // 3-1. read() : 한개의 문자(2바이트)를 읽고, 4바이트 int 리턴 (끝 2바이트만 데이터 존재)
        Reader reader = new FileReader(testFile + File.separator + "out.txt");
        int readData;
        while ((readData = reader.read()) != -1) {
            System.out.print((char) readData);
        }
        System.out.println();

        // 3-2. read(char[] buf)
        reader = new FileReader(testFile + File.separator + "out.txt");
        int readDataNum;
        char[] cbuf = new char[100];
        while ((readDataNum = reader.read(cbuf)) != -1) {
            System.out.println(readDataNum + " " + String.valueOf(cbuf));
        }
        System.out.println();

        // 3-3. read(char[] buf, int off, int len)
        // 아래 둘은 같다.
        reader.read(cbuf);
        reader.read(cbuf, 0, cbuf.length);

        reader.close();

        // 4. Writer
        // 4-1. write(int c) : int값 끝 2바이트를 출력 스트림으로 보냄
        Writer writer = new FileWriter(testFile + File.separator + "out1.txt");
        char[] cArr = "홍길동".toCharArray();
        for (char c : cArr) {
            writer.write(c);
        }

        // 4-2. write(char[] buf)
        writer.write(cArr);
        // 4-3. write(char[] buf, int off, int len)
        writer.write("a정우성a".toCharArray(), 1, 3);
        writer.flush();
        writer.close();
    }

}
