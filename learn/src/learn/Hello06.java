package learn;

/*
 * Interface
 * 
 *  View           View2
 *  |   \         /     
 *  |    ViewChild       
 *  |             \       
 * Class06(C)      ClassViewChild(C) 
 * 
 * 
 *  View    View2
 *      \   /
 *    DoubleInterface(C)
 *    
 * */

interface View {
    // 상수 필드는 가능
    // public static final은 자동으로 컴파일 과정에서 붙음
    // static 블록으로 초기화 불가능
    double PI = 3.14;

    // 추상 메소드
    // 자동으로 public abstract 붙음
    void method1();

    // default 메소드, java 8이후에 가능
    // 자동으로 public이 붙음
    // instance 메소드 이므로 객체로 사용 가능
    // 이 메소드를 인터페이스가 상속받는 경우
    //  -1 단순히 상속만 받는다
    //  -2 재정의한다
    //  -3 추상 메소드로 재선언한다.
    public default void method2() {
        System.out.println("Interface's default method");
    }

    // static 메소드, java 8이후에 가능
    // 자동으로 public이 붙음, 인터페이스에서 바로 사용
    public static void method3() {
        System.out.println("Interface's static method");
    }
}

interface View2 {
    public abstract void method4();
}

// 인터페이스 상속
// 이 인터페이스를 구현하는 클래스는 모든 추상 메소드를 구현해야 함
interface ViewChild extends View, View2 {
    public abstract void method5();
}

class ClassViewChild implements ViewChild {
    @Override
    public void method1() {
        System.out.println("ClassViewChild's method1");
    }

    @Override
    public void method4() {
        System.out.println("ClassViewChild's method4");
    }

    @Override
    public void method5() {
        System.out.println("ClassViewChild's method5");
    }
}

class Class06 implements View {
    // 여기서 구현하지 않으면, 이 클래스는 추상 클래스가 되야 한다.
    @Override
    public void method1() {
        System.out.println("Class06's override method from interface");
    }

    // 클래스에서만 선언된 메소드
    public void localMethod() {
        System.out.println("Class06's local method");
    }
}

// 다중 인터페이스 구현 클래스
class DoubleInterface implements View, View2 {
    @Override
    public void method1() {
        System.out.println("double method1");
    }

    @Override
    public void method4() {
        System.out.println("double method4");
    }

    // 클래스에서만 선언된 메소드
    // 클래스 타입일때만 사용 가능
    public void localMethod() {
        System.out.println("DoubleInterface's local method");
    }
}

public class Hello06 {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(View.PI); // 상수
        View.method3(); // static 메소드
        System.out.println();

        // 클래스 타입으로 클래스 사용하기
        Class06 obj = new Class06();
        System.out.println(obj.PI);
        obj.method1();
        obj.method2();
        obj.localMethod(); // 로컬 메소드 사용 가능
        System.out.println();

        // 정석 사용법
        // interface타입에 대입하여 사용
        // 자동 타입 변환
        // 인터페이스 타입으로 클래스 사용하기
        View view = obj; // new Class06();
        System.out.println(obj.PI);
        view.method1();
        view.method2();
        // view.localMethod(); // 로컬 메소드 사용 불가
        System.out.println();

        // 익명 구현 객체
        View noName = new View() {
            // 추상 메소드 구현해야 함
            @Override
            public void method1() {
                System.out.println("익명 구현 객체's method");
            }
        };
        System.out.println(noName.PI);
        noName.method1();
        noName.method2();
        System.out.println();

        // 다중 인터페이스 객체 사용, 대입되는 타입의 인터페이스 함수만 사용 가능
        View di = new DoubleInterface();
        di.method1();
        di.method2();
        // di.method4();
        System.out.println();

        View2 di2 = new DoubleInterface();
        // di2.method1();
        // di2.method2();
        di2.method4();
        System.out.println();

        // di2.localMethod(); 사용 불가
        // 사용하기 위해서 강제 타입 변환을 해야함
        if (di2 instanceof DoubleInterface) {
            DoubleInterface tmp = (DoubleInterface) di2;
            tmp.localMethod();
        }
        System.out.println();

        // 상위 인터페이스가 있는 인터페이스(하위 인터페이스)를 구현한 클래스
        // 1. 하위 인터페이스 타입일 경우, 하위(선언 타입), 상위 메소드 모두 사용 가능
        ViewChild vchild1 = new ClassViewChild();
        vchild1.method1();// View
        vchild1.method2();// View
        vchild1.method4();// View2
        vchild1.method5();// ViewChild
        System.out.println();

        // 2. 상위 인터페이스 타입일 경우, 상위(선언 타입) 메소드만 사용 가능
        View vchild2 = new ClassViewChild();
        vchild2.method1();// View
        vchild2.method2();// View

        View2 vchild3 = new ClassViewChild();
        vchild3.method4();// View2

    }

}
