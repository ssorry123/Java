package learn1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/*
 * LIFO, FIFO 컬렉션
 */

public class Hello37 {
    public static <T> void printQueueAndDel(Queue<T> queue) {
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Stack
        Stack<Integer> stack = new Stack<>();
        stack.add(3);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty() + "\n");

        // Queue
        int[] arr = new int[] { 3, 2, 1, 4, 5, 6, 1, 2, 3, 9, 8, 7 };
        Queue<Integer> queue = new LinkedList<>();
        for (int a : arr)
            queue.add(a);
        printQueueAndDel(queue);

        // PriorityQueue
        Queue<Integer> pq = new PriorityQueue<>();
        for (int a : arr)
            pq.add(a);
        printQueueAndDel(pq);

        // PriorityQueue with userComp
        Comparator<Integer> myComp = (i1, i2) -> -i1.compareTo(i2);
        Queue<Integer> myPq = new PriorityQueue<>(myComp);
        for (int a : arr)
            myPq.add(a);
        printQueueAndDel(myPq);

        // 추가 정보
        // arr -> 컬렉션
        // int같은 primitive 타입 배열은 불가능
        Integer[] arr1 = new Integer[] { 3, 2, 1, 8, 6, 5, 33, 1 };
        List<Integer> list1 = new ArrayList<>(Arrays.asList(arr1));
        List<Integer> list2 = new LinkedList<>(Arrays.asList(arr1));
        Queue<Integer> queue1 = new LinkedList<>(Arrays.asList(arr1));
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Arrays.asList(arr1));
        printQueueAndDel(queue1);
        printQueueAndDel(pq1);

    }

}
