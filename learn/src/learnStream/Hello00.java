package learnStream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/*
 * Stream은 자바 8부터 추가된 컬렉션(배열 포함)의 저장요소를 하나씩 참조해서
 * 람다식(함수적 스타일)로 처리할 수 있도록 해주는 반복자이다.
 */

class Student {
    private String name;
    private Integer score;

    Student(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }
}

public class Hello00 {
    public static void print(String str) {
        System.out.println(str + " : " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // 외부 반복자
        // 1. Iterator 반복자 사용 순차적 처리(자바 7이전까지)
        List<String> list1 = Arrays.asList("이제동", "홍길동", "고준표", "정우성");
        Iterator<String> iterator1 = list1.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        System.out.println();

        // 내부 반복자, 병렬 처리가 쉬움(멀티코어 CPU를 최대한 활용하게 도와줌)
        // ( 병렬 처리가 컬렉션 내부에서 일어남)
        // 2. Stream사용 순차적 처리
        Stream<String> stream1 = list1.stream(); // Stream 객체 얻기
        // forEach는 Consumer 함수적 인터페이스 타입의 매개값을 가짐
        stream1.forEach(str -> System.out.println(str));
        System.out.println();

        // 3. 병렬 처리 예제
        // Hello00 print함수와 같음
        // forEach의 제네릭 타입,(하위타입 제한)(안해도 됌)
        Consumer<? super String> consumer1 = (str) -> {
            System.out.println(str + " : " + Thread.currentThread().getName());
        };

        // 순차처리
        Stream<String> sequentialStream = list1.stream();
        // 아래 두개의 forEach는 같음
        sequentialStream.forEach(consumer1);
        System.out.println();

        sequentialStream = list1.stream();
        sequentialStream.forEach(str -> System.out.println(str + " : " + Thread.currentThread().getName()));
        System.out.println();

        // 병렬 처리, 여러 스레드가 생겨나서 처리될 수 있음
        Stream<String> parallelStream = list1.parallelStream();
        parallelStream.forEach(Hello00::print); // 람다식 메소드 참조 방식
        System.out.println();

        // 4. 중간처리와 최종 처리를 할 수 있다.
        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 100),
                new Student("정우성", 50),
                new Student("둘리", 70)
        );

        
        Double avg = studentList.stream().
                // Student 객체를 Int로 매핑
                mapToInt(Student::getScore)
                // 평균 구하기
                .average()  // return OptionalDouble
                .getAsDouble();
        System.out.println(avg);
    }

}
