package learnStream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
 * 중간처리, 최종처리
 * 리덕션 : 대량의 데이터를 가공해서 축소하는 것
 * 
 * 합계, 평균값, 카운팅, 최대값, 최ㅗ값 등이 대표적인 리덕션의 결과물
 * 컬렉션 요소를 결과물로 바로 집계할 수 없을 경우 집계하기 좋도록
 * 필터링, 맵핑, 정렬, 그룹핑 등 중간 처리가 필요
 * 
 * 파이프 라인 : 여러개의 스트림이 연결되어 있는 구조
 * 파이프라인에서 최종처리를 제외하고 모두 중간 처리 스트림
 * 
 * 중간 처리 메소드는 스트림을 반환
 * 최종 처리 메소드는 기본타입 or OptionalXXX 타입 반환
 * 
 * 중간처리 메소드는 최종처리 메소드가 호출될때까지 지연됨
 */

class Member {
    public static final int MALE = 0;
    public static final int FEMALE = 1;

    private String name;
    private int sex;
    private int age;

    Member(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}

public class Hello02 {
    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<Member> list = Arrays.asList(
                new Member("홍길동", 0, 25),
                new Member("이효리", 1, 30),
                new Member("김철수", 0, 12),
                new Member("김유리", 1, 38)
        );

        // version 1
        Stream<Member> stream = list.stream(); // 컬렉션 스트림 얻기
        Stream<Member> maleStream = stream.filter((m) -> (m.getSex() == Member.MALE)); // 남자 필터링
        IntStream ageStream = maleStream.mapToInt(Member::getAge); // 남자의 나이 스트림 만들기
        OptionalDouble optionalDouble = ageStream.average(); // 남자의 나이스트림의 평균값 구하기
        double avgAge = optionalDouble.getAsDouble(); // Opional -> double
        p(avgAge);

        // version 2 : 로컬변수 생략
        avgAge = list.stream()
                .filter(m -> m.getSex() == Member.MALE)
                .mapToInt(Member::getAge)
                .average()
                .getAsDouble();
        p(avgAge);
    }

}
