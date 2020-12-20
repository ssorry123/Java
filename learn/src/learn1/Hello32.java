package learn1;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;

/*
 * 메소드 참조
 * 람다식에서 불필요한 매개변수를 제거하는 것이 목적.
 */

@FunctionalInterface
interface MyUtil<T> {
    public T max(T t1, T t2);
}

class MyUtilClass {

    private String str;
    private Integer val;

    public static int staticMethod(int x, int y) {
        return x + y;
    }

    public int instanceMethod(int x, int y) {
        return x + y;
    }

    MyUtilClass() {

    }

    MyUtilClass(String str) {
        this.str = str;
    }

    MyUtilClass(String str, Integer val) {
        this.str = str;
        this.val = val;
    }
}

public class Hello32 {
    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // 다소 불필요해보이는 표현
        MyUtil<Integer> util = (a, b) -> Math.max(a, b);
        int ret = util.max(3, 4);
        p(ret);

        // 1. 메소드 참조
        util = Math::max;
        ret = util.max(12, 23);
        p(ret);

        // 정적 메소드 참조
        IntBinaryOperator operator;
        operator = MyUtilClass::staticMethod;
        ret = operator.applyAsInt(3, 4);
        p(ret);

        // 인스턴스 메소드 참조
        MyUtilClass myUtilClass = new MyUtilClass();
        operator = myUtilClass::instanceMethod;
        // operator = myUtilClass::staticMethod;
        ret = operator.applyAsInt(4, 7);
        p(ret + "\n");

        // 2. 매개변수의 메소드 참조
        ToIntBiFunction<String, String> function;
        function = (a, b) -> a.compareToIgnoreCase(b); // String의 인스턴트 메소드 compareToIgnoreCase
        p(function.applyAsInt("java", "JAVA"));
        function = String::compareToIgnoreCase; // <클래스, 메소드의 매개변수 타입>
        p(function.applyAsInt("java", "python"));

        
        // 3. 생성자 참조
        // String -> MyUtilClass 맵핑
        Function<String, MyUtilClass> makeInstance = (s) -> new MyUtilClass(s);
        MyUtilClass tmp = makeInstance.apply("abcde");

        makeInstance = MyUtilClass::new;
        tmp = makeInstance.apply("Abcde");

        // String, Integer -> MyUtilClass 맵핑
        BiFunction<String, Integer, MyUtilClass> makeInstance2 = (s, i) -> new MyUtilClass(s, i);
        tmp = makeInstance2.apply("qwe", 123);

        makeInstance2 = MyUtilClass::new;
        tmp = makeInstance2.apply("zxc", 321);

    }

}
