package learn;

import java.util.Arrays;
import java.util.HashMap;

/*
 * java.lang 패키지
 * - 임폴트 없이 사용 가능
 * 
 * Object, System, Class, String, StringBuffer, StringBuilder
 * Math, Wrapper
 * 
 */

/*
 * java.util 패키지
 * 
 * Arrays, Calendar, Date, Objects, StringTokenizer, Random
 */

class Member1 {
    String id;
    int val;

    Member1(String id) {
        this.id = id;
        this.val = 0;
    }

    public Member1 setVal(int val) {
        this.val = val;
        return this;
    }

    // Object 클래스의 equals 메소드
    // 논리적 동일 검사
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member1) {
            Member1 tmp = (Member1) obj;
            if (id == tmp.id)
                return true;
        }
        return false;
    }

    // finalize 재정의
    @Override
    protected void finalize() throws Throwable {
        System.out.println(val + " 번 객체 finalize 실행");
    }

}

class Member2 {
    String id;

    Member2(String id) {
        this.id = id;
    }

    // Object 클래스의 equals 메소드
    // 논리적 동일 검사
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member2) {
            Member2 tmp = (Member2) obj;
            if (id == tmp.id)
                return true;
        }
        return false;
    }

    // 객체 동등 검사
    @Override
    public int hashCode() {
        // String hashCode는 같은 String은 같은 해쉬 값을 반환
        return id.hashCode();
    }

    // toString 메소드
    // 기본적으로 클래스명@16진수해쉬코드
    @Override
    public String toString() {
        return "Member2's " + id + " instance";
    }
}

// 객체 복사(얕은 복사)
// 객체 복사를 허용하려면, Cloneable 인터페이스를 구현하고 있어야 함.
class Member3 implements Cloneable {
    int val;
    int[] arr;

    Member3() {
        val = 3;
        arr = new int[5];
    }

    public void print() {
        System.out.println("\nMember3\n" + val);
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public Member3 getClone() {
        Member3 ret = null;
        try {
            // clone 메소드의 리턴타입은 Object이므로 캐스팅 해야함
            ret = (Member3) clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

}

//객체 복사(깊은 복사)
//객체 복사를 허용하려면, Cloneable 인터페이스를 구현하고 있어야 함.
//깊은 복사를 하려면 clone 메소드를 재정의 해야함
class Member4 implements Cloneable {
    int val;
    int[] arr;

    Member4() {
        val = 3;
        arr = new int[5];
    }

    public void print() {
        System.out.println("\nMember4\n" + val);
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // 우선 얕은 복사를 하자
        // Object의 클론 메소드를 호출해야 한다
        Member4 ret = (Member4) super.clone();
        // Member4 ret = (Member4) Object.clone();

        // 깊은 복제 대상 깊은 복제 하기
        ret.arr = Arrays.copyOf(this.arr, this.arr.length);

        // 깊은 복사 완료된 객체 반환
        return ret;
    }

    public Member4 getClone() {
        Member4 ret = null;
        try {
            // clone 메소드의 리턴타입은 Object이므로 캐스팅 해야함
            ret = (Member4) clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

}

public class Hello12 {
    public static void main(String[] args) {

        // 1. Object 클래스
        // 자바의 모든 클래스는 Object의 자식이거나 자손이다
        // Object는 자바의 최상위 부모 클래스에 해당한다

        // equals 메소드
        // 논리적 동등 비교
        Member1 m1 = new Member1("123");
        Member1 m2 = new Member1("123");
        Member1 m3 = new Member1("234");
        System.out.println(m1.equals(m2)); // true
        System.out.println(m1.equals(m3)); // false
        System.out.println();

        // hashCode 메소드
        // 객체 동등 비교
        // 객체의 메모리 번지를 이용해서, 객체마다 다른 값을 가지고 있음
        // (hashCode == true) => (equals == true) => 같은 객체

        // 두 객체는 논리적으로 같지만, 동등 객체가 아니다
        // hashCode 메소드를 오버로딩 하지 않음
        HashMap<Member1, String> hashMap1 = new HashMap<>();
        System.out.println((new Member1("abc")).equals(new Member1("abc"))); // true
        hashMap1.put(new Member1("abc"), "홍길동");
        System.out.println(hashMap1.get(new Member1("abc"))); // null

        // 두 객체가 논리적으로도 같고, 동등 객체로 판단하도록 만든 Member2
        HashMap<Member2, String> hashMap2 = new HashMap<>();
        System.out.println((new Member2("abc")).equals(new Member2("abc"))); // true
        hashMap2.put(new Member2("abc"), "고길동");
        System.out.println(hashMap2.get(new Member2("abc"))); // 고길동
        System.out.println();

        // toString 메소드
        System.out.println((new Member2("kii")));// Member2's kii instance
        System.out.println((new Member2("kii")).toString());// Member2's kii instance
        System.out.println();

        // 얕은 복제
        Member3 a1 = new Member3();
        Member3 a2 = a1.getClone();
        a1.print();
        a2.print();
        a2.val = 5;
        a2.arr[3] = 100;
        a1.print();
        a2.print();
        System.out.println();

        // 깊은 복제
        Member4 a3 = new Member4();
        Member4 a4 = a3.getClone();
        a3.print();
        a4.print();
        a3.val = 5;
        a4.arr[3] = 100;
        a3.print();
        a4.print();
        System.out.println();

        // finalize 확인
        Member1 tmp = null;
        for (int i = 0; i < 50; ++i) {
            // 객체 생성
            tmp = (new Member1("")).setVal(i);

            // 쓰레기 객체로 만들기
            tmp = null;

            // garbage collection 실행시켜달라고 쪼르기
            System.gc();
        }
    }
}
