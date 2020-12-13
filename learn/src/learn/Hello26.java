package learn;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Store {
    int val = 0;
}

// 콜백방식의 작업 완료 통보
// ExecutorService에서는 제공하지 않지만
// Runnable 구현 클래스를 작성할대 콜백 기능 구현 가능
class Callback {
    private ExecutorService executorService;

    public Callback() {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void finish() {
        executorService.shutdown();
    }

    // Integer 결과타입, Void 첨부타입
    private CompletionHandler<Integer, Void> callback = new CompletionHandler<Integer, Void>() {
        @Override
        public void completed(Integer result, Void attachment) {
            System.out.println("정상적으로 완료됨 : " + result);
        }

        @Override
        public void failed(Throwable exc, Void attachment) {
            System.out.println("예외 발생 " + exc.toString());
        }
    };

    public void doWork(final String x, final String y) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    int intX = Integer.parseInt(x);
                    int intY = Integer.parseInt(y);
                    int result = intX + intY;
                    callback.completed(result, null);
                } catch (Exception e) {
                    callback.failed(e, null);
                }
            }
        };
        executorService.submit(task);
    }
}

public class Hello26 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1. 작업 처리 결과를 외부 객체에 저장
        class Task implements Runnable {
            Store store;

            Task(Store store) {
                this.store = store;
            }

            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 50; ++i) {
                    sum += i;
                }
                store.val += sum;
            }
        }
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Store store = new Store();
        Task task1 = new Task(store);
        Task task2 = new Task(store);
        Future<Store> future1 = executorService.submit(task1, store);
        Future<Store> future2 = executorService.submit(task2, store);

        try {
            Store tmp1 = future1.get();
            Store tmp2 = future2.get();
            System.out.println(tmp1 == tmp2);
            System.out.println(tmp1.hashCode());
            System.out.println(tmp2.hashCode());
            System.out.println(tmp1.val);
            System.out.println(store.val);
        } catch (Exception e) {

        }

        // 2. 작업 완료순으로 통보
        // CompletionService 생성
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);
        System.out.println("작업완료순으로통보 작업 요청");
        for (int i = 0; i < 3; ++i) {
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 0; i < 1000; ++i)
                        sum += i;
                    return sum;
                }
            });
        }
        // 스레드풀의 스레드에서 작업이 완료됬는지 확인하자
        executorService.submit(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Future<Integer> future = completionService.take(); // 끝난 작업이 없으면 블로킹
                        int ret = future.get();
                        System.out.println("처리 결과 : " + ret);
                    } catch (Exception e) {

                    }
                }
            }
        });

        // 3초후 스레드풀 종료
        try {
            Thread.sleep(3000);
        } catch (

        Exception e) {

        }
        executorService.shutdownNow();

        // 3. 콜백방식의 작업완료 통보
        // 작업처리를 요청하고, 다른일을 하고있으면, 작업이 완료되면 신호가 온다.
        Callback callback = new Callback();
        callback.doWork("3", "-5");
        callback.doWork("5", "이");
        callback.finish();
    }

}
