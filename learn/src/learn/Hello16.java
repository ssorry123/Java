package learn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

// String 클래스

public class Hello16 {
    public static <T> void print(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) throws IOException {
        String str;
        // TODO Auto-generated method stub
        byte[] bytes = { 72, 101, 108, 108, 111, 32, 74, 97, 118, 97 };
        // 바이트 배열을 String으로 변환
        String str1 = new String(bytes);
        String str2 = new String(bytes, 6, 4); // 6번 부터 4개
        print(str1);
        print(str2 + "\n");

        // System.in.read
        byte[] tmp = new byte[100];
        print("입력 : ");
        int readByteNum = System.in.read(tmp); // 읽은 바이트 수를 반환한다
        str = new String(tmp, 0, readByteNum - 2); // '/r'과 '/n'은 제외한다
        print(str + "\n");

        // charAt
        str = new String("가나다라마바사");
        print(str.charAt(4)); // 마

        // eqauls
        print(str1.equals(str2) + "\n");

        // getBytes (바이트 배열로 변환), valueOf
        str1 = new String("abcde");
        str2 = new String("가나다라마");
        try {
            byte[] tmp1 = str1.getBytes();
            byte[] tmp2 = str2.getBytes();
            byte[] tmp3 = str1.getBytes("UTF-8");
            byte[] tmp4 = str2.getBytes("UTF-8");
            byte[] tmp5 = str1.getBytes("EUC-KR");
            byte[] tmp6 = str2.getBytes("EUC-KR");
            print(tmp1 + " " + String.valueOf(tmp1.length));
            print(tmp2 + " " + String.valueOf(tmp2.length));
            print(tmp3 + " " + String.valueOf(tmp3.length));
            print(tmp4 + " " + String.valueOf(tmp4.length));
            print(tmp5 + " " + String.valueOf(tmp5.length));
            print(tmp6 + " " + String.valueOf(tmp6.length) + " \n");
        } catch (UnsupportedEncodingException e) {

        }

        // indexOf 문자열 찾기
        // 주어진 문자열이 시작되는 인덱스를 리턴, 없으면 -1
        str = new String("abcdefgh");
        print(str.indexOf("cde")); // 2

        // replace, 문자열을 바꿔 새로운 문자열을 만듬
        String newStr = str.replace("abc", "xyz");
        print(newStr); // xyzdefgh

        // substring, 문자열 잘라내기
        print(newStr.substring(2)); // zdefgh
        print(newStr.substring(3, 5)); // de

        // toLowerCase, toUpperCase, 모두 변경
        String upStr = newStr.toUpperCase();
        String downStr = newStr.toLowerCase();
        print(upStr);
        print(downStr);

        // equalsIgnoreCase, 대소문자 상관없이 같은지 비교
        print(upStr.equalsIgnoreCase(downStr)); // true

        // trim, 문자열 양쪽 공백 제거, 중간을 제거하지는 않음
        str = new String("    \t\t 가나   다라 마 \t  \t ");
        print(str.trim() + "\n");

        // 문자열 연산 성능 개선
        // StringBuffer 멀티스레드 동기화 적용
        // StringBuilder 단일 스레드
        StringBuilder sb = new StringBuilder(); // sb의 크기는 중요하지 않다
        sb.append("java ");
        sb.append("study\n");
        print(sb.toString());
        sb.insert(1, "가카");// 1 위치에 "가" 삽입
        print(sb.toString());
        sb.setCharAt(6, '나'); // 3 위치 변경
        print(sb.toString());
        sb.replace(0, 4, "넌뭐냐?");// 0~4 위치 변경
        print(sb.toString());
        sb.delete(0, 2); // 삭제
        print(sb.toString() + " " + sb.length()); // 출력, 총 문자 수
    }

}
