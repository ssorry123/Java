package learn;

/* 
 쓰레드 상태 getState 메소드
 1. 객체 생성
 NEW, 아직 start 메소드가 호출되지 않음
 2. 실행 대기
 RUNNABLE, 언제든지 실행상태로 갈 수 있는 상태
 3. 일시정지
 WAITING, 다른 스레드가 통지할 때가지 기다리는 상태
 TIMED_WAITING, 주어진 시간동안 기다리는 상태
 BLOCKED, 사용하고자 하는 객체의 락이 풀릴 때까지 기다리는 상태
 4. 종료
 TERMINATED, 실행을 마친 상태
 */

// 타깃 쓰레드의 상태를 조사하는 쓰레드
class StatePrintThread extends Thread {
    private Thread targetThread;

    public StatePrintThread(Thread targetThread) {
        this.targetThread = targetThread;
    }

    public void run() {
        while (true) {
            // 스레드 상태 얻고 출력
            Thread.State state = targetThread.getState();
            System.out.println(state);

            // NEW 상태일 경우 실행대기상태(RUNNABLE)로 만듬
            if (state == Thread.State.NEW) {
                targetThread.start();
            }

            // 종료 상태일 경우 지금 쓰레드를 종료하기위해
            // while문 탈출
            if (state == Thread.State.TERMINATED) {
                break;
            }

            try {
                Thread.sleep(500);
            } catch (Exception e) {

            }
        }
    }
}

// 조사당할 쓰레드
class TargetThread extends Thread {
    public void run() {
        // RUNNABLE 상태 유지
        for (long i = 0; i < 1000000000; ++i) {

        }

        // 1.5초간 일시 정지
        // TIMED_WAITING 상태 유지
        try {
            Thread.sleep(1500);
        } catch (Exception e) {

        }

        // RUNNABLE 상태 유지
        for (long i = 0; i < 1000000000; ++i) {

        }

    }
}

public class Hello21 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StatePrintThread statePrintThread = new StatePrintThread(new TargetThread());
        statePrintThread.start(); // 쓰레드 실행
    }

}
