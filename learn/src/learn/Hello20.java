package learn;

// 공유 객체
class Calculator {
    private int mem;

    public int getMem() {
        return mem;
    }

    // public void setMemory(int mem) { // 비동기화 버전
    public synchronized void setMemory(int mem) { // 동기화 버전
        this.mem = mem;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName() + "'s mem :" + this.mem);
    }

}

interface User {
    void setCal(Calculator cal);
}

// 사용자 객체 두개
class User1 extends Thread implements User {
    private Calculator cal;

    @Override
    public void run() {
        cal.setMemory(10);
    }

    @Override
    public void setCal(Calculator cal) {
        this.setName("User1");
        this.cal = cal;
    }
}

class User2 extends Thread implements User {
    private Calculator cal;

    @Override
    public void run() {
        cal.setMemory(50);
    }

    @Override
    public void setCal(Calculator cal) {
        // TODO Auto-generated method stub
        this.setName("User2");
        this.cal = cal;
    }

}

public class Hello20 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1. 동기화 블록, 동기화 메소드
        // synchronized
        // 동기화 메소드 : 객체에 잠금이 일어남
        // 메소드를 선언할 때, synchronized를 붙여줌
        // 동기화 블록 : 일부 영역만 임계영역으로
        // synchronized(공유객체) ( 자기 자신인 경우 this를 넣음)

        User1 user1 = new User1();
        User2 user2 = new User2();
        Calculator cal = new Calculator();
        user1.setCal(cal);
        user2.setCal(cal);

        user1.start();
        user2.start();
    }

}
