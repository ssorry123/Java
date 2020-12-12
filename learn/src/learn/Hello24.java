package learn;

import java.util.Map;
import java.util.Set;

// 데몬쓰레드(보조 쓰레드)로 설정할 쓰레드
// 일반 쓰레드와 다른 점은, 이 스레드를 호출한 스레드가 종료되면
// 자동으로 종료된다.
class AutoSave extends Thread {
    public void save() {
        System.out.println("자동저장");
    }

    @Override
    public void run() {
        // 쓰레드 그룹 이름 얻기
        // 명시적으로 설정하지 않으면, 자신을 생성한 스레드와 같은 그룹
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        String str = group.getName();
        System.out.println(str);

        try {
            while (true) {
                Thread.sleep(1000);
                save();
            }
        } catch (Exception e) {
            System.out.println("예외 캐치");
        }

        System.out.println("데몬쓰레드 종료");
    }
}

class SameThread extends Thread {
    // 쓰레드 생성자중에서, 그룹을 매개로 가지는 생성자중에서, 한가지
    SameThread(ThreadGroup tg, String ThreadName) {
        super(tg, ThreadName);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(getName() + " interrupted");
                break;
            }
        }
        System.out.println(getName() + " 종료됨");

    }

}

public class Hello24 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AutoSave as = new AutoSave();
        as.setDaemon(true); // start 하고 설정하면 예외 발생
        as.setName("AutoSave");
        as.start();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }

        // 쓰레드 이름 얻기
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        String str = group.getName();
        System.out.println(str);

        // 프로세스에 존재하는 모든 스레드 정보 얻기
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        Set<Thread> threads = map.keySet();
        for (Thread thread : threads) {
            System.out.println("Name : " + thread.getName() + ((thread.isDaemon() ? "(데몬)" : "(주)")));
            System.out.println("\t" + "소속 그룹 : " + thread.getThreadGroup().getName());
        }

        System.out.println("\n");

        // 스레드 그룹 생성
        // 스레드에 그룹을 지정하면 많은 스레드를 한번에 컨트롤할 수 있다.
        ThreadGroup myGroup = new ThreadGroup("myGroup");
        // 그룹에 스레드 추가
        SameThread stA = new SameThread(myGroup, "stA");
        stA.start();
        new SameThread(myGroup, "stB").start();

        // 메인 스레드 그룹의 list 메소드 출력
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        mainGroup.list(); // 디버깅용 출력함수

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        System.out.println("myGroup에 인터럽트");
        myGroup.interrupt(); // 그룹에 인터럽트

        System.out.println("메인 쓰레드 종료");
    }

}
