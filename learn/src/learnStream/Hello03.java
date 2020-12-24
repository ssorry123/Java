package learnStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

// 중간처리 메소드
// 1. 필터링
// 2. 매핑
// 3. 정렬
// 4. 루핑

public class Hello03 {
    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1. 필터링

        // distinct :중복값 제거: 공통
        // Stream -> equals 가 true인 경우
        // Int, Long, DoubleStream -> 동일값인 경우

        // filter :조건 부합:공통

        // distinct
        List<String> names = Arrays.asList("가", "나나 나", "다", "가", "다 다", "다다", "가 가가");
        names.stream().distinct().forEach(s -> p(s));
        p("");

        // filter
        names.stream().filter(s -> s.startsWith("가")).forEach(s -> p(s));
        p("");

        // distinct + filter
        names.stream().distinct().filter(s -> s.startsWith("가")).forEach(s -> p(s));
        p("");

        // 2. 매핑
        // 스트림의 요소를 다른 요소로 대체

        // 띄어쓰기가 있다면 분리해서 각각의 스트림으로 만듬
        Function<String, Stream<String>> mapFunction = str -> Arrays.stream(str.split(" "));
        names.stream().flatMap(mapFunction).forEach(s -> p(s));
        p("");

        // flatMapXXX
        // 하나의 요소를 복수개의 요소로 대체
        // 숫자로 변환할 수 있으면 변환
        List<String> nums = Arrays.asList("홍길동", "60", "-12", "30L", "abcsd", "Ox33", "123, 3456,789");
        nums.stream().flatMap((str) -> {
            String[] strArr = str.split(","); // ,이 있다면 분리
            List<Integer> tmpList = new ArrayList<>();
            for (String s : strArr) {
                // int로 바꿀 수 있으면 바꿈
                try {
                    tmpList.add(Integer.parseInt(s.trim()));
                } catch (NumberFormatException e) {
                    p("cant change");
                }

            }
            return tmpList.stream(); // Integer들을 Stream<Integer>으로 바꾼 후 리턴
        }).forEach(i -> p(i)); // 60, -12, 123, 3456, 789
        p("");

        // mapXXX
        // 하나의 요소를 하나의 요소로 매핑
        nums.stream().map(str -> str).forEach(str -> p(str));
        p("");

        // asDoubleStream, asLongStream, boxed
        // double이 가능한 것을 double로
        // long이 가능한 것을 long으로
        // Integer, Long, Double로 가능한 것들을 변환
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        Arrays.stream(arr).asDoubleStream().forEach(d -> p(d));
        Arrays.stream(arr).boxed().forEach(I -> p(I));
        p("");

        // 3. 정렬
        // sorted, :: 객체 Stream + Int, Long, Double Stream 가능
        // Comparable::compareTo 구현 방법에 따라 정렬
        // sorted(Comparator<T>) :: 객체 Stream만 가능 (IntStream등 불가능)
        // Comparator<T>::compare 구현방법에 따라 정렬
        Arrays.stream(arr).sorted().forEach(i -> p(i));
        p("");

        // Comparable 구현한 객체 Stream인 경우
        // 순방향
        // sorted(), sorted((a,b)->a.compareTo(b)), sorted(Comparator.naturalOrder())
        // 역방향
        // sorted((a,b)->b.compareTo(a)), sorted(Comparator.reverseOrder())

        // 4. 루핑
        // peek : forEach와 같지만, 중간처리 메소드임
        // 최종처리 메소드가 없으므로, peek은 실행되지 않음
        Arrays.stream(arr).filter(i -> i % 2 == 0).peek(i -> p(i)); // 실행되지 않음

        // 최종처리 메소드가 있으므로 실행됨
        int sum;
        sum = Arrays.stream(arr).filter(i -> i % 2 == 1).peek(i -> p(i)).sum();
        p(sum);
        p("");

    }

}
