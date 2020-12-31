package learn1;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/*
 * java.util.function 인터페이스의 default 메소드들
 * 
 * Consumer, Function, Operator : andThen, compose 
 * 두개의 함수적 인터페이스를 순차적으로 연결
 *  
 * Predicate : and, or, negate
 * 
 * BinaryOperator : minBy, maxBy
 * 최대 T와 최소 T를 얻는 인터페이스를 반환
 * 
 */

@SuppressWarnings("rawtypes")
class Member implements Comparable {
    private String name;
    private String id;
    private Address address;

    public Member(String name, String id, Address address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Member)) {
            return false;
        }

        Member t = (Member) obj;
        if (t.name.equals(name))
            if (t.id.equals(id))
                return true;
        return false;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Member) {
            Member m = (Member) o;
            return this.name.compareTo(m.name);
        }
        return 0;
    }

}

class Address {
    private String country;
    private String city;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
}

public class Hello31 {

    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1. Consumer의 순차적 연결
        // 소비만 하는 인터페이스이므로 실행 순서만을 결정
        Consumer<Member> cA = (m) -> {
            System.out.println(m.getName());
        };
        Consumer<Member> cB = (m) -> {
            System.out.println(m.getId());
        };
        Consumer<Member> cAB = cA.andThen(cB);
        cAB.accept(new Member("홍길동", "123", null));
        p("");

        Function<Member, Address> fA;
        Function<Address, String> fB;
        Function<Member, String> fAB1;
        Function<Member, String> fAB2;

        fA = (m) -> m.getAddress();

        fB = (a) -> {
            return a.getCountry() + " : " + a.getCity();
        };
        fAB1 = fA.andThen(fB);
        fAB2 = fB.compose(fA);

        String ret = fAB1.apply(new Member("홍길동", "ssorry123", new Address("대한민국", "서울")));
        p(ret);
        ret = fAB2.apply(new Member("홍길동", "ssorry123", new Address("미국", "워싱턴")));
        p(ret + "\n");

        IntPredicate isEven = (i) -> i % 2 == 0;
        IntPredicate is3N = (i) -> i % 3 == 0;

        IntPredicate aAndB = isEven.and(is3N);
        IntPredicate aOrB = isEven.or(is3N);
        IntPredicate notEven = isEven.negate();
        p(aAndB.test(12));
        p(aOrB.test(3));
        p(notEven.test(2) + "\n");

        Predicate<Member> predicate = Predicate.isEqual(new Member("가길동", "123", null));
        p(predicate.test(new Member("가길동", "123", null)));
        p(predicate.test(new Member("가길동", "321", null)) + "\n");

        BinaryOperator<Member> binaryOperator;
        Member m;
        Member m1 = new Member("abc", null, null);
        Member m2 = new Member("bcd", null, null);

        binaryOperator = BinaryOperator.maxBy((x1, x2) -> x1.getName().compareTo(x2.getName()));
        m = binaryOperator.apply(m1, m2);
        p(m.getName()); // bcd

        binaryOperator = BinaryOperator.maxBy((x1, x2) -> x1.compareTo(x2));
        m = binaryOperator.apply(m1, m2);
        p(m.getName()); // bcd

        binaryOperator = BinaryOperator.minBy((x1, x2) -> x1.getName().compareTo(x2.getName()));
        m = binaryOperator.apply(m1, m2);
        p(m.getName()); // abc

        binaryOperator = BinaryOperator.minBy((x1, x2) -> x1.compareTo(x2));
        m = binaryOperator.apply(m1, m2);
        p(m.getName()); // abc

    }

}
