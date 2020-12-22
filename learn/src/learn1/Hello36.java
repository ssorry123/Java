package learn1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*
 * TreeSet, TreeMap의 키에 사용자 정의 데이터타입을 이용하기 위해
 * 
 * 1. Comparable 인터페이스의 compareTo()메소드를 구현
 * Integer, Double, String등은 구현되어 있음
 * 
 * 2. TreeSet, TreeMap 생성자의 매개값으로
 * Comparator를 제공
 */

// Comparable 구현 객체
class PersonComparable implements Comparable<PersonComparable> {
    String name;
    Integer age;

    PersonComparable(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(PersonComparable o) {
        if (!name.equals(o.name))
            return name.compareTo(o.name);
        else
            return age.compareTo(o.age);
    }
}

// Comparable 비구현 객체
class PersonNoComparable {
    String name;
    Integer age;

    PersonNoComparable(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

public class Hello36 {
    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Comparable 구현 객체
        TreeSet<PersonComparable> treeSet1 = new TreeSet<>();
        treeSet1.add(new PersonComparable("홍길동", 123));
        treeSet1.add(new PersonComparable("고길동", 100));
        treeSet1.add(new PersonComparable("박서원", 223));
        treeSet1.add(new PersonComparable("박서원", 123));
        Iterator<PersonComparable> iter1 = treeSet1.iterator();
        while (iter1.hasNext()) {
            PersonComparable tmp = iter1.next();
            p(tmp.name + " " + tmp.age);
        }
        p("");

        // Comparable 비구현 객체
        // Comparator 구현 객체를 넘겨주어 해결
        // compare(o1, o2)
        Comparator<PersonNoComparable> desComp = (person1, person2) -> {
            if (!person1.name.equals(person2.name))
                return -person1.name.compareTo(person2.name);
            else
                return -person1.age.compareTo(person2.age);
        };
        TreeSet<PersonNoComparable> treeSet2 = new TreeSet<>(desComp);
        treeSet2.add(new PersonNoComparable("홍길동", 123));
        treeSet2.add(new PersonNoComparable("고길동", 100));
        treeSet2.add(new PersonNoComparable("박서원", 223));
        treeSet2.add(new PersonNoComparable("박서원", 123));
        Iterator<PersonNoComparable> iter2 = treeSet2.iterator();
        while (iter2.hasNext()) {
            PersonNoComparable tmp = iter2.next();
            p(tmp.name + " " + tmp.age);
        }
        p("");
    }

}
