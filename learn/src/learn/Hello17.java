package learn;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;

// 사용자 정의 클래스를 정렬할 경우, Comparable 인터페이스를 구현해야 함
class Member implements Comparable<Member> {
    String name;

    Member(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Member m) {
        // 이름을 기준으로, 오름차순 정렬
        return name.compareTo(m.name);
    }

    @Override
    public String toString() {
        return name;
    }
}

public class Hello17 {
    public static <T> void print(T t) {
        System.out.println(t);
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1. 정규 표현식과 Pattern 클래스
        // [ 02 or 010 ] [ - ] [ 숫자 3~4자리 ] [ - ] [ 숫자 4자리 ]
        String regExp = "(02|010)-\\d{3,4}-\\d{4}";
        String data = "010-2223-1234";
        boolean ret = Pattern.matches(regExp, data);
        print(ret);

        // [ 한개 이상 알파벳 또는 숫자 ] [ @ ] [ 한개 이상 알파벳 또는 숫자 ] [.] [ 한개 이상 알파벳 또는 숫자 ]
        // [ 한개 이상 알파벳 또는 숫자 ] 가 한 개 있거나 없음.
        regExp = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
        data = "adflv@navercom";
        ret = Pattern.matches(regExp, data);
        print(ret + "\n");

        // 2. Arrays 클래스
        // index :: binarySearch(arr, val)
        // destArr :: copyOf(orgArr, len)
        // destArr :: copyOfRange(orgArr, start, end)
        // boolean :: deepEquals(arr1, arr2) 깊은 비교
        // boolean :: equals(arr1, arr2) 얕은 비교
        // void :: fill(arr, val) 배열 모든 값을 val로 설정
        // void :: fill(arr, start, end, val)
        // void :: sort(arr) 오름차순 정렬
        // String :: toString(arr), 배열을 문자열로 리턴

        String[] arr1 = { "abcd", "zxddqwe", "cdweqe", "가나다", "321", "121" };
        print(Arrays.toString(arr1));
        Member[] ms = new Member[arr1.length];
        for (int i = 0; i < arr1.length; ++i)
            ms[i] = new Member(arr1[i]);

        Arrays.sort(arr1);
        Arrays.sort(ms);
        print(Arrays.toString(arr1));
        print(Arrays.toString(ms) + "\n");

        // 배열 항목 검색 : 특정 값이 위치한 인덱스를 얻는 것
        // Array.sort로 정렬한 후, binarySearch 사용
        print(String.valueOf(Arrays.binarySearch(ms, ms[2])) + "\n");

        // 3. Wrapper 클래스
        // Boxing
        Integer obj1 = new Integer(100);
        Integer obj2 = new Integer("200");
        Integer obj3 = Integer.valueOf("300");
        // UnBoxing
        int value1 = obj1.intValue();
        int value2 = obj2.intValue();
        int value3 = obj3.intValue();
        print(value1 + " " + value2 + " " + value3 + "\n");

        // 자동 언박싱
        value1 = obj1;
        value2 = obj1 + obj2 + obj3 + 2;
        // 자동 박싱
        obj1 = value2;
        print(value1 + " " + value2 + " " + obj1 + "\n");

        // 문자열을 기본 타입 값을 이용
        byte b1 = Byte.parseByte("10");
        byte b2 = Byte.valueOf("20");
        float f1 = Float.parseFloat("3.14F");
        float f2 = Float.valueOf("3.14f");
        print(b1 + " " + b2 + " " + f1 + " " + f2 + "\n");

        // 객체 비교
        // == , != 번지 비교
        // .equals -> 내부 내용 비교

        // 4. Math 클래스
        double rd = Math.random(); // 0~1
        double rdInt = Math.rint(rd); // 가장 가까운 실수의 정수값
        print(rd + " " + rdInt + "\n");

        // 5. Random 클래스
        // Random() 호출시마다 다른 종자값이 자동 설정 된다.
        // Random(long seed) 주어진 종자값이 설정된다.
        int[] numArr = new int[6];
        Random random = new Random();
        for (int i = 0; i < numArr.length; ++i) {
            numArr[i] = random.nextInt(45) + 1; // [0,45) + 1;
        }
        print(Arrays.toString(numArr) + "\n");

        // 6. Date, SimpleDateFormat 클래스
        Date now = new Date();
        String str1 = now.toString();
        print(str1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년, MM월, dd일, hh시, mm분, ss초");
        str1 = sdf.format(now);
        print(str1 + "\n");

        // 7. Calendar 클래스
        Calendar cnow = Calendar.getInstance(); // 객체 생성 불가능
        int year = cnow.get(Calendar.YEAR);
        int month = cnow.get(Calendar.MONTH) + 1;
        int day = cnow.get(Calendar.DAY_OF_MONTH);
        int week = cnow.get(Calendar.DAY_OF_WEEK); // 요일
        int amPm = cnow.get(Calendar.HOUR);
        int hour = cnow.get(Calendar.HOUR);
        int minute = cnow.get(Calendar.MINUTE);
        int second = cnow.get(Calendar.SECOND);
        print(year + " " + month + " " + day + " " + week + " " + amPm + " " + hour + " " + minute + " " + second
                + "\n");

        // 다른 지역의 시간대 얻기
        TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
        cnow = Calendar.getInstance(timeZone);

        // 가능한 시간대 알아보기
        String[] availableIDs = TimeZone.getAvailableIDs();
        for (String id : availableIDs) {
            print(id);
        }
    }

}
