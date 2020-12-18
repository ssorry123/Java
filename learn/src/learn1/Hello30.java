package learn1;

import java.util.function.*;

/*
 * 표준 API 패키지
 * java.util.function 에서 제공하는 함수적 인터페이스
 * 
 * Consumer : accept : 매개값은 있고, 리턴값은 없음
 * Supplier : get : 매개값은 없고, 리턴값은 있음
 * Function : apply : 매개값과 리턴값이 있음 ( 주로 매핑, 타입 변환 )
 * Operator : apply : 매개값도 있고 리턴값도 있음 ( 주로 연산, 결과 리턴 )
 * Predicate : test : 매개값은 있고, 리턴 타입은 boolean
 */

public class Hello30 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1. Consumer
        Consumer<String> c1 = (s) -> {
        };
        c1.accept("");

        BiConsumer<String, Integer> c2 = (s, i) -> {
        };
        c2.accept("", 1);

        DoubleConsumer c3 = (d) -> {
        };
        IntConsumer c4 = (i) -> {
        };
        LongConsumer c5 = (l) -> {
        };
        c3.accept(0.0);
        c4.accept(3);
        c5.accept(0L);

        ObjDoubleConsumer<String> c6 = (s, d) -> {
        };
        ObjIntConsumer<String> c7 = (s, i) -> {
        };
        ObjLongConsumer<String> c8 = (s, i) -> {
        };
        c6.accept("", 0.0);
        c7.accept("", 0);
        c8.accept("", 0L);

        // 2. Supplier
        Supplier<String> s1 = () -> {
            return "";
        };
        String sRet = s1.get();

        BooleanSupplier s2 = () -> {
            return true;
        };
        Boolean bRet = s2.getAsBoolean();

        DoubleSupplier s3 = () -> {
            return 0L;
        };
        Double dRet = s3.getAsDouble();

        IntSupplier s4 = () -> {
            return 0;
        };
        Integer iRet = s4.getAsInt();

        LongSupplier s5 = () -> {
            return 0L;
        };
        Long lRet = s5.getAsLong();

        // 3. Function
        Function<String, Integer> f1 = (s) -> {
            return 0;
        };
        iRet = f1.apply("");

        BiFunction<Integer, Double, String> f2 = (i, d) -> {
            return "";
        };
        sRet = f2.apply(0, 0.0);

        DoubleFunction<String> f3 = (d) -> {
            return "";
        };
        sRet = f3.apply(0.0);

        IntFunction<String> f4 = (i) -> {
            return "";
        };
        sRet = f4.apply(0);

        IntToDoubleFunction f5 = (i) -> {
            return 3.14;
        };
        dRet = f5.applyAsDouble(3);

        IntToLongFunction f6 = (i) -> {
            return 3L;
        };
        lRet = f6.applyAsLong(0);

        LongToDoubleFunction f7 = (l) -> {
            return 3.14;
        };
        dRet = f7.applyAsDouble(3L);

        LongToIntFunction f8 = (l) -> {
            return 3;
        };
        iRet = f8.applyAsInt(3L);

        ToDoubleBiFunction<String, Integer> f9 = (s, i) -> {
            return 3.14;
        };
        dRet = f9.applyAsDouble("", 3);

        ToDoubleFunction<String> f10 = (s) -> {
            return 3.14;
        };
        dRet = f10.applyAsDouble("as");

        ToIntBiFunction<String, Object> f11 = (s, obj) -> {
            return 0;
        };
        iRet = f11.applyAsInt(" ", new Object());

        ToIntFunction<String> f12 = (s) -> {
            return 0;
        };
        iRet = f12.applyAsInt("");

        ToLongBiFunction<String, Double> f13 = (s, d) -> {
            return 3L;
        };
        lRet = f13.applyAsLong(" ", 3.14);

        ToLongFunction<Object> f14 = (obj) -> {
            return 3L;
        };
        lRet = f14.applyAsLong(new Object());

        // 4. Operator
        BinaryOperator<String> o1 = (i1, i2) -> i1 + i2;
        UnaryOperator<Integer> o2 = (i) -> {
            return i * i;
        };
        sRet = o1.apply("abc", "def");
        iRet = o2.apply(4);

        DoubleBinaryOperator o3 = (d1, d2) -> d1 + d2;
        DoubleUnaryOperator o4 = d -> {
            return d * d;
        };
        dRet = o3.applyAsDouble(3.14, 2.12);
        dRet = o4.applyAsDouble(1.4);

        IntBinaryOperator o5 = (i1, i2) -> {
            return i1 * i2 * 3;
        };
        IntUnaryOperator o6 = (i) -> i * i;
        iRet = o5.applyAsInt(3, -1);
        iRet = o6.applyAsInt(123);

        LongBinaryOperator o7 = (l1, l2) -> l1 - l2;
        LongUnaryOperator o8 = l -> l * l;
        lRet = o7.applyAsLong(1L, 2L);
        lRet = o8.applyAsLong(1234567891234L);

        // 5. Predicate
        Predicate<Object> p1 = (obj) -> {
            return true;
        };
        bRet = p1.test(new Object());

        BiPredicate<String, Integer> p2 = (s, i) -> true;
        bRet = p2.test("123", 123);

        DoublePredicate p3 = (d) -> false;
        IntPredicate p4 = (i) -> {
            return true;
        };
        LongPredicate p5 = l -> {
            return false;
        };
        bRet = p3.test(123.123);
        bRet = p4.test(123);
        bRet = p5.test(123L);
    }

}
