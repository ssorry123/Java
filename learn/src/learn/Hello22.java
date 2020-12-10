package learn;

/* 
 * 쓰레드 상태 제어
 * deprecated
 * resume, suspend, stop
 * 
 * 1. interrupt
 * 일시정지 상태의 스레드에서 InterruptedException을 발생시켜
 * 예외처리 코드에서 실행대기 상태나 종료상태로 갈 수 있게 한다.
 * 
 * 2. wait
 * synchronized 블록 내에서 스레드를 일시 정지 상태로 만든다.
 * 매개값으로 시간이 주어지면 시간이 지난 후 실행 대기 상태가 된다.
 * 시간이 주어지지 않으면 notify, notifyAll 메소드에 의해 실행 대기 상태가 된다.
 * 
 * 3. sleep
 * 주어진 시간동안 스레드를 일시 정지 상태로 만든다.
 * 시간이 지나면 자동으로 실행 대기 상태가 된다.
 * 
 * 4. join
 * 이 메소드를 호출한 스레드는 일시정지상태가 된다.
 * 실행대기 상태가 되려면 join 메소드를 가진 스레드가 종료되거나,
 * 매개값으로 주어진 시간이 지나야 한다.
 * 
 * 5. notify, notifyAll
 * 동기화 블록 내에서 wait 메소드에 의해 일시정지상태에 있는 스레드를
 * 실행 대기 상태로 만든다
 * 

 * 
 * 6. yield
 * 실행 중에 우선순위가 동일한 다른 스레드에게 실행을 양보하고 실행 대기 상태가 된다.
 * 
 */

// 쓰레드 실행 양보 예제
class W_Thread extends Thread {
    static int a = 0;
    static int b = 0;
    String name;
    boolean stop = false;
    boolean work = true;

    W_Thread(String name) {
        this.name = name;
    }

    public void run() {
        while (!stop) {
            if (work) {
                System.out.println(name + " ");
                if (name.equals("a")) {
                    ++a;
                } else {
                    ++b;
                }
            } else {
                Thread.yield();
            }
        }
        System.out.println(name + " 종료 ");
    }
}

class CalThread extends Thread {
    int ret;

    CalThread() {
        ret = 0;
    }

    public void run() {
        for (int i = 0; i < 1234; ++i) {
            ret++;
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
}

public class Hello22 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1. yield 예제
        W_Thread a = new W_Thread("a");
        W_Thread b = new W_Thread("b");
        a.start();
        b.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        // b는 잠시 양보한다
        b.work = false; // b의 yield 실행

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        b.work = true;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        a.stop = b.stop = true;
        System.out.println(W_Thread.a + " " + W_Thread.b);

        // 2. join 예제
        CalThread calThread = new CalThread();

        calThread.start();

        try {
            // 현재 쓰레드가 종료될때까지 main 쓰레드가 기다림
            calThread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(calThread.ret);
    }

}
