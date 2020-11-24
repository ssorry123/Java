package learn;

import java.util.Calendar;

public class Hello {
    // 열거타입
    public enum BigAlpha{
        A,
        B,
        C,
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println("Hello, world!");
        
        // 최댓값, 최솟값 확인
        int M = Integer.MAX_VALUE;
        int m = Integer.MIN_VALUE;
        System.out.println(M + " " + m);
        
        // 객체 비교
        String str1 = "abcde", str2 = "abcde";
        String str3 = new String("abcde");
        System.out.println(str1 == str2);	// true
        System.out.println(str1 == str3);	// false
        // 문자열 비교
        System.out.println(str1.equals(str3));	// true
        
        // 배열
        int[] arr1 = {1,2,3};
        int[] arr2;
        int[] arr3 = new int[3];
        arr2 = new int[] {4,5,6};
        System.out.println(arr2.length);	// 읽기 전용 필드
        // 배열 복사
        int[] arr4 = new int[3];
        System.arraycopy(arr1, 0, arr4, 0, arr1.length);
        
        int[][] mat = new int[2][];	// 계단식 가능
        mat[0] = new int[4];
        mat[1] = new int[2];
        
        // 커맨드 라인 입력
        if (args.length != 2) {
            System.out.println("java Hello num1, num2");
        }
        else {
            int num1 = Integer.parseInt(args[0]);	// string to int
            int num2 = Integer.parseInt(args[1]);
        }
        
        // 향상된 for문
        int sum = 0;
        int[] score = {1,2,3,4,5,6,7,8,9};
        for (int s : score) {
            sum += s;
        }
        System.out.println(sum);
        

        // 열거 타입, 열거 객체 메소드
        BigAlpha a = BigAlpha.A;
        System.out.println(a);	// A
        String aName = a.name();
        int ordinal = a.ordinal();  // 순서
        int ret = a.compareTo(BigAlpha.C); // 상대 순서
        BigAlpha c = BigAlpha.valueOf("C"); // 문자열과 동등한 열거 객체 리턴
        // A 0 -2 C
        System.out.println(aName + " " + ordinal + " " + ret + " " + c);
        
        BigAlpha[] alphas = BigAlpha.values();  // 모든 열거 타입 배열로 리턴
        for (BigAlpha cc : alphas) {    // A B C
            System.out.print(cc +" ");
        }System.out.println();
        
        c = null;
        System.out.println(c);	// null
        
        // 날짜 얻기
        Calendar cal = Calendar.getInstance();
        int day = cal.WEEK_OF_MONTH;
        System.out.println(day);
        
        
    }

}
