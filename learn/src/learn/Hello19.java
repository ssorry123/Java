package learn;

import java.awt.Toolkit;

// 멀티 쓰레드

// 1. java.lagn.Thread 클래스로부터 직접 생성
class BeepTask implements Runnable {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("run1");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 0; i < 5; ++i) {
            toolkit.beep(); // 소리를 낸다
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}

// 2. Thread 하위 클래스로부터 생성
class WorkerThread extends Thread {
    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("run4");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for (int i = 0; i < 5; ++i) {
            toolkit.beep(); // 소리를 낸다
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}

public class Hello19 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        System.out.println("java.lang.Thread 클래스로부터 직접 생성");
        // 1. java.lang.Thread 클래스로부터 직접 생성
        Runnable beepTask = new BeepTask();
        // 1-1. 클래스 구현 후 생성
        Thread thread1 = new Thread(beepTask);
        // 1-2. 익명 객체 사용
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("run2");
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                for (int i = 0; i < 5; ++i) {
                    toolkit.beep(); // 소리를 낸다
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                }
            }
        });
        // 1-3. 람다식 이용
        Thread thread3 = new Thread(() -> {
            System.out.println("run3");
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            for (int i = 0; i < 5; ++i) {
                toolkit.beep(); // 소리를 낸다
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println();

        System.out.println("2. Thread 하위 클래스로부터 생성");
        // 2. Thread 하위 클래스로부터 생성
        // 2-1. 클래스 구현 후 생성
        Thread thread4 = new WorkerThread();
        // 2-2. 익명 자식 객체
        Thread thread5 = new Thread() {
            @Override
            public void run() {
                System.out.println("run5");
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                for (int i = 0; i < 5; ++i) {
                    toolkit.beep(); // 소리를 낸다
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }
                }
            }
        };

        thread4.start();
        thread5.start();

        // 3. 쓰레드 이름
        System.out.println("메인 스레드 이름 : " + Thread.currentThread().getName());
        System.out.println("변경 전 thread1 이름 : " + thread1.getName());
        thread1.setName("헤헤헤헤");
        System.out.println("변경 후 thread1 이름 : " + thread1.getName() + "\n");

        // 4. 쓰레드 우선순위
        // 기본 5, 전체 1~10, 10이 가장 높은 우선순위
        // Thread.MAX_PRIORITY==10,THREAD.NORM_PRIORITY==5,THREAD.MIN_PRIORITY==1



    }

}
