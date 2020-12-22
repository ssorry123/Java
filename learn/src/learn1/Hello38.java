package learn1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
 * 동기화된 컬렉션(멀티 스레드 사용)
 * Vector, Hashtable 등
 * 
 * 비동기화된 컬렉션(싱글스레드 사용)
 * ArrayList, HashSet, HashMap 등
 * 
 * 비동기화된 컬렉션 -> 동기화된 컬렉션으로 변환
 * synchronizedXXX 메소드
 * 
 */

/*
 * 병렬 처리를 위한 컬렉션
 * 동기화된 컬렉션은 멀티 스레드 환경에서
 * 하나의 스레드가 요소를 안전하게 처리할 수 있지만,
 * 전체 요소를 빠르게 처리하지는 못함.
 * 전체 잠금이 발생하기 때문
 * 
 * 멀티스레드가 컬렉션의 요소를 병렬적으로 처리할 수 있도록 특별한 컬렉션 제공
 * 전체 잠금을 하지 않고, 부분 잠금을 함
 * java.util.concurrent 패키지
 * 
 */

public class Hello38 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 비동기화된 컬렉션 -> 동기화된 컬렉션으로 변환
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        Set<Integer> set = Collections.synchronizedSet(new HashSet<Integer>());
        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<Integer, String>());

        // 병렬 처리를 위한 컬렉션
        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        Map<String, Integer> map1 = new ConcurrentHashMap<>();
    }

}
