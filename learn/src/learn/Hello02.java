package learn;

// 다른 패키지의 클래스 사용
import learn2.ClassA;   // 다른패키지에서 public으로 설정되있을때만 가능

class Singleton{
    private static Singleton singleton = new Singleton();
    
    private Singleton() {}
    
    public static Singleton getSingletonInstance() {
        return singleton;
    }
}

// final 변수 => 한번 값이 저장되면 절대로 변하지 않음
// static final => 처음부터 정해진 값
class Final{
    final int a = 1;
    final int b;
    static final int c = 3;
    
    public Final(int b) {
        this.b = b;
    }
}

public class Hello02 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        // 싱글톤
        Singleton obj1 = Singleton.getSingletonInstance();
        Singleton obj2 = Singleton.getSingletonInstance();
        System.out.println(obj1 == obj2);   // 같은 객체면 true
        
        // 외부 클래스 사용(임폴트 사용후)
        ClassA.printStatic();
        // 외부 클래스 사용(임폴트 없이)(패키지.패키지...클래스이름)
        learn2.ClassA.printStatic();
        
        ClassA a = new ClassA();
        a.printStatic();    // warning, 객체를 통한 정적 메소드 접근
        a.printInstance();
        
        
    }
}
