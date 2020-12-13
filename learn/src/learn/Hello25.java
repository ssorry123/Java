package learn;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/*
 * 쓰레드 풀
 * 작업 처리에 사용되는 스레드를 제한된 개수만큼 정해놓음(큐에 매달아서 조절)
 * 작업 처리 요청이 폭증되어도 스레드의 전체 개수가 늘어나지 않으므로
 * 앱의 성능이 급격히 저하되지 않도록 한다.
 */

class Task1 implements Runnable {
    ThreadPoolExecutor threadPoolExecutor;

    Task1(ThreadPoolExecutor tPE) {
        this.threadPoolExecutor = tPE;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int poolSize = threadPoolExecutor.getPoolSize();
        String threadName = Thread.currentThread().getName();
        System.out.println("총 스레드 수 : " + poolSize + " 현재 스레드 이름 : " + threadName);

        // 일부로 예외 발생 시키기
        int a = 3 / 0;
    }
}

public class Hello25 {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        // 1. 작업 생성
        // Runnable 구현 클래스 : 리턴값이 없다.
        // Callable 구현 클래스 : 리턴값이 있다.
        // 2. 작업 처리 요청
        // 이왕이면 submit을 사용하자
        // execute : Runnable을 작업 큐에 저장, 작업 처리 결과 받지 못함
        // submit : Runnable, Callable을 작업 큐에 저장, 리턴값 Future
        // execute : 예외 발생시 스레드 종료, 스레드풀에서 제거, 새로운 스레드 생성
        // submit : 예외가 발생해도 다음작업을 위해 재사용

        // 최대 쓰레드 개수가 2인 스레드풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; ++i) {
            Runnable runnable = new Task1((ThreadPoolExecutor) executorService);

            // executorService.execute(runnable); // 작업 처리 요청, 예외가 발생할때마다 쓰레드가 바뀜
            executorService.submit(runnable); // 스레드 재사용

            Thread.sleep(10);
        }

        System.out.println("\n");

        // 3. 블로킹 방식의 작업 완료 통보
        // submit(Runnable task), 리턴값 == future.get() -> null
        // submit(Runnable task, Integer result), 리턴값 == future.get() -> int
        // submit(Callable<String> task), 리턴값 == future.get() -> String

        // get메소드를 호출하면 작업을 완료하기 전까지 다른 코드를 실행할 수 없으므로
        // get메소드를 호출하는 스레드는 새로운 스레드이거나, 스레드풀의 또다른 스레드이어야 한다.

        // 3-1. 리턴값이 없는 작업 통보
        ExecutorService ES = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println("작업처리요청1");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                int sum = 0;
                for (int i = 0; i < 1000000; ++i) {
                    sum += i;
                }
                System.out.println("작업 처리 1 결과 : " + sum);
            }
        };
        Future future = ES.submit(runnable);

        Thread tmp = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    future.get();
                    System.out.println("작업처리 1 완료");
                } catch (Exception e) {

                }
            }
        });
        tmp.start();


        // 3-2. 리턴값이 있는 작업 통보
        System.out.println("작업처리요청2");
        Callable<Integer> task = new Callable<>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i < 100; ++i) {
                    sum += i;
                }
                return sum;
            }
        };
        Future<Integer> futureInteger = ES.submit(task);

        new Thread() {
            @Override
            public void run() {
                try {
                    Integer ret = futureInteger.get();
                    System.out.println("작업처리 2 완료 : " + ret);
                } catch (Exception e) {

                }
            }
        }.start();
        
    }

}
