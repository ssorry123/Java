package learnStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
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
    public enum Sex { MALE, FEMALE }
    // public static final int MALE = 0;
    // public static final int FEMALE = 1;

    private String name;
    private Sex sex;
    private int age;

    Member(String name, Sex sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    
    public Sex getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}

// 남학생이 저장되는 컨테이너
class MaleMember{
    private List<Member> list;
    
    // 수집될 컨테이너를 생성
    public MaleMember() {
        list = new ArrayList<Member>();
        System.out.println(Thread.currentThread().getName() + " MaleMember()");
    }
    
    // 컨테이너에 요소를 수집하는 역할
    public void accumulate(Member member) {
        list.add(member);
        System.out.println(Thread.currentThread().getName() + " accumulate()");
    }
    
    // 병렬처리시에만 호출되는 결합 메소드
    public void combine(MaleMember other) {
        list.addAll(other.getList());
        System.out.println(Thread.currentThread().getName() + " combine()");
    }
    
    public List<Member> getList(){
        return list;
    }
    
}

public class Hello02 {
    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<Member> list = Arrays.asList(
                new Member("홍길동", Member.Sex.MALE, 25),
                new Member("이효리", Member.Sex.FEMALE, 30),
                new Member("김철수", Member.Sex.MALE, 12),
                new Member("김유리", Member.Sex.FEMALE, 38)
        );

        // version 1
        Stream<Member> stream = list.stream(); // 컬렉션 스트림 얻기
        Stream<Member> maleStream = stream.filter((m) -> (m.getSex() == Member.Sex.MALE)); // 남자 필터링
        IntStream ageStream = maleStream.mapToInt(Member::getAge); // 남자의 나이 스트림 만들기
        OptionalDouble optionalDouble = ageStream.average(); // 남자의 나이스트림의 평균값 구하기
        double avgAge = optionalDouble.getAsDouble(); // Opional -> double
        p(avgAge);

        // version 2 : 로컬변수 생략
        avgAge = list.stream()
                .filter(m -> m.getSex() == Member.Sex.MALE)
                .mapToInt(Member::getAge)
                .average()
                .getAsDouble();
        p(avgAge);
        p("");
        
        /* 수집, collect */
        // Collectors.정적메소드
        // collect(Collector<T, A, R> collector)
        // T요소를 A누적기가 R 컬렉션에 저장한다
        
        // 1. 남자만 List에 저장
        List<Member> maleList;
        maleList = list.stream()
                .filter(m -> m.getSex() == Member.Sex.MALE)
                .collect(Collectors.toList());
        maleList.stream().map(male -> male.getName()).forEach(System.out::println);
        
        // 2. 여자만 Set에 저장
        Set<Member> femaleSet;
        femaleSet = list.stream()
                .filter(m->m.getSex() == Member.Sex.FEMALE)
                .collect(Collectors.toCollection(HashSet::new));
        femaleSet.stream().map(female -> female.getName()).forEach(Hello02::p);
        p("");
        
        // 3. 사용자 정의 컨테이너에 수집, 
        MaleMember maleMember = list.stream()
                .filter(m -> m.getSex() == Member.Sex.MALE)
                .collect(
                    () -> new MaleMember(),     // r을 만듬
                    (r, t) -> r.accumulate(t),  // t를 r에 수집
                    (r1, r2) -> r1.combine(r2)  // 병렬 실행 되는 경우 합침
                );
        maleMember.getList().stream().forEach(m -> p(m.getName()));
        p("");
        
        MaleMember femaleMember = list.parallelStream()
                .filter(m -> m.getSex() == Member.Sex.FEMALE)
                .collect(MaleMember::new, MaleMember::accumulate, MaleMember::combine);
        femaleMember.getList().stream().map(m -> m.getName()).forEach(System.out::println);
        
        // 4. 그룹핑
        Map<Member.Sex, List<Member>> mapBySex;
        mapBySex = list.stream()
                .collect(Collectors.groupingBy(Member::getSex));
        p("\n남자");
        mapBySex.get(Member.Sex.MALE).stream()
        .forEach(m->p(m.getName()));
        
        p("\n여자");
        mapBySex.get(Member.Sex.FEMALE).stream()
        .forEach(m->p(m.getName()));
        p("");
        
        // 5. 그룹핑 후 집계
        // (그룹별 집계)
        Map<Member.Sex, Double> sumBySex;
        sumBySex = list.stream()
                .collect(Collectors.groupingBy(
                        Member::getSex, // 성별로 그룹핑 후 
                        Collectors.summingDouble(Member::getAge) // 그룹별 나이의 합 구함
                        ));
        p(sumBySex.get(Member.Sex.MALE));
        p(sumBySex.get(Member.Sex.FEMALE));
        
        
    }

}
