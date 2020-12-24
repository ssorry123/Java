package learnStream;

import java.util.Arrays;
import java.util.OptionalDouble;

// 최종처리 메소드

// 1. 루핑
// 2. 매칭
// 3. 기본집계
// 4. Optional 클래스
// 5. 커스텀 집계

public class Hello04 {
    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1. 루핑
        // forEach

        // 2. 매칭
        // 최종 처리 단계에서 특정 조건에 만족하는지 조사
        // allMatch : 모두 만족?
        // anyMatch : 최소 하나라도 만족?
        // noneMatch : 모두 불만족?
        int[] arr = { 2, 4, 6 };
        boolean ret;
        // 모두 짝수인가?
        ret = Arrays.stream(arr).allMatch(i -> i % 2 == 0);
        p(ret);
        // 3의 배수가 하나라도 있는가?
        ret = Arrays.stream(arr).anyMatch(i -> i % 3 == 0);
        p(ret);
        // 3의 배수가 하나도 없는가?
        ret = Arrays.stream(arr).noneMatch(i -> i % 3 == 0);
        p(ret);
        p("");

        // 3. 기본집계
        // sum, count, average, max, min
        long tmpLong;
        tmpLong = Arrays.stream(arr).filter(i -> i % 4 == 0).count();
        p(tmpLong);

        tmpLong = Arrays.stream(arr).sum();
        p(tmpLong);

        double tmpDouble;
        tmpDouble = Arrays.stream(arr).average().getAsDouble();
        p(tmpDouble);

        tmpLong = Arrays.stream(arr).max().getAsInt();
        p(tmpLong);

        tmpLong = Arrays.stream(arr).min().getAsInt();
        p(tmpLong);
        p("");

        // 4. OptionalXXX 클래스
        // 값을 저장한다, 값이 없을 경우 여러 메소드를 제공한다.
        // isPresent() : 값이 저장되어 있는지 여부
        // orElse(T) : 값이 저장되어 있지 않은 경우 디폴트값 지정
        // ifPresent(XXXCounumer) : 값이 저장되어 있을 경우 Consumer에서 처리

        // 4.1
        OptionalDouble opt = Arrays.stream(arr).average();
        if (opt.isPresent())
            p(opt.getAsDouble());

        // 4.2
        Double avg = Arrays.stream(arr).average().orElse(0.0);

        // 4.3
        Arrays.stream(arr).average().ifPresent(d -> p(d));
        p("");

        // 5. reduce
        // 커스텀 집계
        int sum = Arrays.stream(arr).sum();
        p(sum); // 12

        // 첫번째 인자 == 기본 값
        sum = Arrays.stream(arr).reduce(0, (a, b) -> a + b); // sum
        p(sum); // 12

        sum = Arrays.stream(arr).reduce(-2, (a, b) -> a + b); // sum
        p(sum); // 10

        sum = Arrays.stream(new int[] {}).reduce(-1, (a, b) -> a + b);
        p(sum); // -1
    }

}
