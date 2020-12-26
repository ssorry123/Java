package learnStream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 병렬처리
 * 
 * 동시성 : 멀티 작업을 위해 멀티 스레드가 번갈아가며 실행
 * 병렬성 : 멀티 작업을 위해 멀티 코어를 이용해서 동시에 실행
 * 
 * 데이터 병렬성 : 전체 데이터를 쪼개어 서브 데이터들로 만들고
 *  이 서브 데이터들을 병렬 처리해서 작업을 빨리 끝내는 것,
 *  (자바 8에서 지원하는 병렬 스트림)
 *  
 *  작업 병렬성 : 서로 다른 작업을 병렬 처리
 */

/*
 * FrokJoin 프레임워크 : 병렬 스트림이 요소들을 병렬 처리하기 위해 사용하는 프레임워크
 * 예를 들어 쿼드코어인 경우, N개의 요소를 4등분하고 처리한후 결합한다
 * 개발자가 직접 사용할 수는 없다
 * 
 * 병렬스트림 얻기
 * java.util.Collection : parallelStream()
 * java.util.Stream : parallel()
 */

/*
 * ArrayList, 배열은 분리가 쉬어 병렬 처리 시간이 절약
 * HashSet, TreeSet, LinkedList등은 분리가 쉽지 않음
 * 
 * 싱글 코어 CPU인 경우 순차처리가 더 빠를 수 있음
 * 코어의 수가 많으면 많을수록 병렬 작업 처리 속도는 빨라짐.
 * 
 * 작업 요소당 시간이 짧으면 오히려 순차처리가 더 빠를 수 있음
 */

class Test {

    public static void work(int val, long workTime) {
        try {
            Thread.sleep(workTime);
        } catch (Exception e) {

        }
    }

    // 순차 처리
    public static long testSequencial(List<Integer> list, long workTime) {
        long start = System.nanoTime();
        list.stream().forEach(a -> work(a, workTime));
        long end = System.nanoTime();
        return end - start;
    }

    // 병렬 처리
    public static long testParallel(List<Integer> list, long workTime) {
        long start = System.nanoTime();
        list.parallelStream().forEach(a -> work(a, workTime));
        long end = System.nanoTime();
        return end - start;
    }

}

public class Hello05 {

    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int a : arr) {
            arrayList.add(a);
            linkedList.add(a);
        }

        p(Test.testSequencial(arrayList, 10));  // 170017500
        p(Test.testParallel(arrayList, 10));    // 62030700
        p("1");

        p(Test.testSequencial(linkedList, 10)); // 153450900
        p(Test.testParallel(linkedList, 10));   // 62485100
        p("2");

        p(Test.testSequencial(arrayList, 100)); // 1096359300
        p(Test.testParallel(arrayList, 100));   // 330968000
        p("3");

        p(Test.testSequencial(linkedList, 100)); // 1088650300
        p(Test.testParallel(linkedList, 100));   // 433756900
        p("4");

        p(Test.testParallel(linkedList, 50)); // 187017000
        p(Test.testParallel(arrayList, 50));  // 188221500
        p("5");

    }

}
