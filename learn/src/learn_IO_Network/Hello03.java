package learn_IO_Network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;

/*
 * File 클래스
 * 파일에 대한 다양한 정보를 얻고, 생성 및 삭제 할 수 있다.
 * 파일의 데이터를 읽고 쓰는 기능은 지원하지 않는다.
 * 파일의 입출력은 스트림을 사용해야 한다.
 */

public class Hello03 {

    public static void main(String[] args) throws IOException {
        String testFile = "D:/Java/learn/src/learn_IO_Network/Temp";
        // 1. 파일 클래스
        // 파일 생성 및 디렉토리 정보 출력
        File dir = new File(testFile + "/Dir");
        File file1 = new File(testFile + "/file1.txt");
        File file2 = new File(testFile + "/file2.txt");

        if (!dir.exists())
            dir.mkdirs(); // 경로상의 모든 존재하지 않는 디렉토리를 만든다.
        if (!file1.exists())
            file1.createNewFile(); // 파일이 실제로 존재하지 않으면 만든다.
        if (!file2.exists())
            file2.createNewFile();

        File tmp = new File(testFile);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\taHH:mm");
        File[] contents = tmp.listFiles(); // 디렉토리의 모든 파일 및 디렉토리를 File 배열로 리턴
        System.out.println("--------------------------------------------------------------");
        System.out.println("날짜\t\t시간\t\t형태\t크기\t이름");
        System.out.println("--------------------------------------------------------------");
        for (File file : contents) {
            System.out.print(sdf.format(file.lastModified())); // 마지막 수정 날짜
            if (file.isDirectory()) {
                System.out.print("\t\t<DIR>\t\t" + file.getName());
            } else {
                System.out.print("\t\t\t" + file.length() + "\t" + file.getName());
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------\n");

        // 2. 입출력은 FileInputStream, FileOutputStream을 사용한다.
        // output 두가지 방법 (String 전달, File 객체 전달)
        // 두번째 인자 -> 이어쓰기 여부
        FileOutputStream fos1 = new FileOutputStream(file1);
        FileOutputStream fos2 = new FileOutputStream(testFile + "/file2.txt", true);

        fos1.write("가나다abc\n".getBytes());
        fos2.write("abc가나다\n".getBytes());
        fos1.flush();
        fos2.flush();
        fos1.close();
        fos2.close();

        // 문자를 읽어야하므로 Reader를 사용한다.
        FileReader fis1 = new FileReader(testFile + "/file1.txt");
        FileReader fis2 = new FileReader(file2);

        char[] readChar = new char[100];
        while (fis1.read(readChar) != -1) {
            System.out.println(new String(readChar));
        }
        System.out.println("\n");

        int data;
        while ((data = fis2.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println("\n");

        fis1.close();
        fis2.close();
    }

}
