package learn1;

import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * 검색 기능을 강화시킨 컬렉션
 * TreeSet, TreeMap
 */

public class Hello35 {
    public static <T> void p(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        /* TreeSet */
        // Set 인터페이스에 대입해도 되지만,
        // TreeSet만의 메소드를 사용하기 위해 TreeSet에 대입
        // Set<Integer> treeSet = new TreeSet<>();
        TreeSet<Integer> treeSet1 = new TreeSet<>();
        Integer[] arr = new Integer[] { 40, 90, 70, 2, 3, 4, -102, 0, 99 };
        for (Integer i : arr)
            treeSet1.add(i);

        Integer num = null;
        p("가장 작은 수 : " + treeSet1.first()); // -102
        p("가장 큰 수 : " + treeSet1.last()); // 99
        p("99보다 처음으로 작은 수 : " + treeSet1.lower(99)); // 90
        p("70보다 처음으로 큰 수 : " + treeSet1.higher(70)); // 90
        p("3보다 처음으로 같거나 작은 수 : " + treeSet1.floor(3)); // 3
        p("69보다 처음으로 같거나 큰 수 : " + treeSet1.ceiling(69)); // 70

        // 오름차순 순으로 꺼내오기
        p("\n오름차순");
        while (!treeSet1.isEmpty()) {
            System.out.printf("%d ", treeSet1.pollFirst());
        }
        p("\n");
        // 다시 채워넣기
        for (Integer i : arr)
            treeSet1.add(i);

        // descendingIterator()
        // 내림차순으로 정렬된 iterator 반환
        // NavigableSet
        // TreeSet과 마찬가지로, fisrt, last, lower, higher, floor, ceiling 메소드 제공

        // 내림차순 iterator 반환
        Iterator<Integer> desIter1 = treeSet1.descendingIterator(); // 내림차순
        // treeSet1의 내림차순 정렬된 집합을 반환
        NavigableSet<Integer> desSet1 = treeSet1.descendingSet(); // 내림차순
        // treeSet1의 내림차순을 내림차순(반대로)한 (== 오름차순한) 집합 반환
        NavigableSet<Integer> ascSet1 = desSet1.descendingSet(); // 오름차순

        p("내림차순");
        while (desIter1.hasNext()) {
            System.out.printf("%d ", desIter1.next());
        }
        System.out.println();

        p("내림차순");
        desIter1 = desSet1.iterator();
        while (desIter1.hasNext()) {
            System.out.printf("%d ", desIter1.next());
        }
        System.out.println();

        p("오름차순");
        for (Integer val : ascSet1) {
            System.out.printf("%d ", val);
        }
        p("\n");

        // TreeSet의 범위 검색
        NavigableSet<Integer> underSet1 = treeSet1.headSet(4, true); // 4 이하 원소들
        NavigableSet<Integer> upperSet1 = treeSet1.tailSet(4, false); // 4 초과 원소들
        NavigableSet<Integer> midSet1 = treeSet1.subSet(-102, true, 70, false); // -102이상 70미만 원소들
        for (int val : underSet1) {
            System.out.printf("%d ", val);
        }
        System.out.println();
        for (int val : upperSet1) {
            System.out.printf("%d ", val);
        }
        System.out.println();
        for (int val : midSet1) {
            System.out.printf("%d ", val);
        }
        p("\n");

        /* TreeMap */
        // TreeSet과 마찬가지로, TreeMap에 대입
        // 값이 아닌 키값 정렬
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        String[] name = new String[] { "홍길동", "고준표", "박지성", "손흥민", "이명박" };
        for (String str : name) {
            treeMap.put(str, new Random().nextInt(50));
        }

        // treeSet과 마찬가지로, first, last, lower, higher, floor, ceiling Entry 메소드 제공
        Map.Entry<String, Integer> entry = null;
        // 오름차순 출력
        p("이름 오름차순");
        while (!treeMap.isEmpty()) {
            entry = treeMap.pollFirstEntry();
            p(entry.getKey() + " " + entry.getValue());
        }
        p("");
        // 다시 채워넣기
        for (String str : name) {
            treeMap.put(str, new Random().nextInt(50));
        }

        p("이름 내림차순");
        NavigableSet<String> desKeySet = treeMap.descendingKeySet();
        for (String key : desKeySet) {
            p(key + " " + treeMap.get(key));
        }
        p("");

        p("이름 오름차순");
        NavigableMap<String, Integer> desEntry = treeMap.descendingMap();
        NavigableMap<String, Integer> ascEntry = desEntry.descendingMap();
        for (Map.Entry<String, Integer> ent : ascEntry.entrySet()) {
            p(ent.getKey() + " " + ent.getValue());
        }
        p("");

        NavigableMap<String, Integer> underEntry = treeMap.headMap("손흥민", true);    // 손흥민 이하
        NavigableMap<String, Integer> upperEntry = treeMap.tailMap("손흥민", false);   // 손흥민 이상
        NavigableMap<String, Integer> midEntry = treeMap.subMap("박지성", true, "이명박", true);// 박지성,이명박 사이
        for (Map.Entry<String, Integer> ent : underEntry.entrySet()) {
            p(ent.getKey() + " " + ent.getValue());
        }
        p("");

        for (Map.Entry<String, Integer> ent : upperEntry.entrySet()) {
            p(ent.getKey() + " " + ent.getValue());
        }
        p("");
        for (Map.Entry<String, Integer> ent : midEntry.entrySet()) {
            p(ent.getKey() + " " + ent.getValue());
        }
        p("\n");
    }

}
