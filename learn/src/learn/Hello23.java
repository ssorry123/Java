package learn;

// 쓰레드간 협업, wait, notify, notifyAll

// 생산자-소비자
// 공유 객체
// 생산자,소비자 쓰레드가 활동하는 장판 같은 느낌
class DataBox {
    private String data;

    public synchronized String getData() {
        // 데이터가 없으면 데이터를 얻지 못하고 잠든다.
        if (this.data == null) {
            try {
                System.out.println("데이터가 없어 잠이듭니다.");
                wait();
            } catch (InterruptedException e) {

            }
        }

        String ret = data;
        data = null; // 읽고 소비
        System.out.println("데이터가 생겨서 깨어났거나 처음부터 데이터가 있었습니다.");
        System.out.println("읽은 데이터 : " + ret);
        notify(); // 잠들어있을 수 있는 생산자를 깨운다.
        // (소비자도 깨우는가?) (소비자가 여럿인경우..?)
        // (synchronized 메소드이므로, 소비자가 여럿이더라도
        // 한 소비자만 이 메소드를 실행할 것인가?)
        return ret;

    }

    public synchronized void setData(String data) {
        // 데이터가 이미 존재하면, 데이터를 쓸 수 없으므로 잠든다.
        if (this.data != null) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }

        this.data = data; // 데이터 쓰기
        System.out.println("쓴 데이터 : " + this.data);
        // 데이터가 없어서 잠들었던 소비자를 깨운다.
        notify();
        // 데이터가 있어서 잠들었던 다른 생산자도 깨우는가?
        // (synchronized 메소드이므로, 생산자가 여럿이더라도
        // 한 생산자만 이 메소드를 실행할 것인가?)
    }
}

class Consumer extends Thread {
    private DataBox dataBox;

    Consumer(DataBox dataBox) {
        this.dataBox = dataBox;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; ++i) {
            dataBox.getData();
        }
    }
}

class Producer extends Thread {
    private DataBox dataBox;

    Producer(DataBox dataBox) {
        this.dataBox = dataBox;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; ++i) {
            dataBox.setData("data ::: " + i);
        }
    }
}

// interrupt
// sleep일때 인터럽트 받으면 예외 발생
class InterruptA extends Thread {
    @Override
    public void run() {
        // 쓰레드가 실행 대기, 실행 상태일때는 인터럽트를 받아도
        // 예외가 발생하지 않는다.
        // 쓰레드가 잠시 대기(block)되었을때 인터럽트를 받았어야 예외가 발생한다.

        try {
            while (true) {
                System.out.println("A실행중");
                Thread.sleep(1); // 잠시 대기

            }
        } catch (InterruptedException e) {
            System.out.println("A인터럽트 받음, whlie 탈출");
        }

        System.out.println("A자원정리");
        System.out.println("A쓰레드 종료");
    }
}

// sleep 하지 않고도 인터럽트 받는 법
class InterruptB extends Thread {
    @Override
    public void run() {
        // 쓰레드가 잠시대기(block)되지 않더라도
        // 자신에게 인터럽트가 걸렸는지 확인해서 인터럽트를 받을 수 있다.
        while (true) {
            System.out.println("B실행중");
            if (Thread.interrupted()) {
                System.out.println("B인터럽트 받음, whlie 탈출");
                break;
            }
        }

        System.out.println("B자원정리");
        System.out.println("B쓰레드 종료");
    }
}

public class Hello23 {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        DataBox db = new DataBox();
        Consumer cs = new Consumer(db);
        Producer pd = new Producer(db);
        cs.start();
        pd.start();
        cs.join();
        pd.join();
        System.out.println("\n");

        // interrupt 메소드
        InterruptA a = new InterruptA();
        a.start();
        Thread.sleep(10);
        a.interrupt();
        a.join();
        System.out.println("\n");

        InterruptB b = new InterruptB();
        b.start();
        Thread.sleep(5);
        b.interrupt();
        b.join();
    }

}
