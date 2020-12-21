package learn1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

// 컬렉션 프레임워크

/*
 * Collection :: List, Set
 *  List
 *      ArrayList
 *      Vector
 *      LinkedList
 *  Set
 *      HashSet
 *      TreeSet
 *      
 */

/*
 * Map
 *    HashMap
 *    Hashtable
 *    TreeMap
 *    Properties
 */

class MyString {
    private String str;
    private int age;

    MyString(String str) {
        this.str = str;
        this.age = str.length();
    }

    @Override
    public String toString() {
        return str;
    }

    /*
     * Set에서 동일 객체임을 판단하기 위해
     * 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyString) {
            MyString myString = (MyString) obj;
            return (str.equals(myString.str)) && (age == myString.age);

        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return str.hashCode() + age;

    }
}

public class Hello33 {

    public static void main(String[] args) {
        /* List */
        // Arrays.asList(T... a); // ArrayList
        List<String> list1 = Arrays.asList("가나다", "홍길동", "고길동");

        // Vector
        // ArrayList 와 다른점 :
        // Thread Safe, 동기화된 메소드로 구성되어 있음
        // 멀티스레드 환경에서 안전하게 객체를 추가, 삭제할 수 있음.
        List<Integer> list2 = new Vector<>();

        /* Set */
        // HashSet 동일 객체 판단
        // hashCode -> equals -> 동일 객체
        // hashCode와 equals 메소드를 오버라이딩
        Set<MyString> set1 = new HashSet<>();
        set1.add(new MyString("123"));
        set1.add(new MyString("qwe"));
        set1.add(new MyString("zxcqwe123"));

        // 향상된 for문 사용 조회
        for (MyString myStr : set1) {
            System.out.println(myStr);
        }
        System.out.println();

        // iterator 조회
        Iterator<MyString> iterator = set1.iterator();
        while (iterator.hasNext()) {
            MyString str = iterator.next();
            System.out.println(str);

            // iterator가 가리키는 객체 삭제
            if (str.equals(new MyString("123"))) {
                iterator.remove();
            }
        }
        System.out.println();

        List<MyString> willDel = new ArrayList<>();
        for (MyString str : set1) {
            // "qwe"에 해당하는 객체 삭제
            // for문 내에서 삭제 ㄴㄴ
            if (str.equals(new MyString("qwe"))) {
                willDel.add(str);
            }
        }
        for (MyString del : willDel) {
            set1.remove(del);
        }
        willDel.clear();

        for (MyString str : set1) {
            System.out.println(str);
        }
        System.out.println();
    }

}
