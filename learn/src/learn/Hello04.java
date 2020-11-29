package learn;

class Parent {
    // private 필드와 메소드는 상속 대상에서 제외된다.
    // 부모와 자식이 다른 클래스에 존재한다면, default 필드와 메소드도 상속 대상에서 제외

    // protected
    protected int a;

    protected void methodA() {
        System.out.println("Parent's methodA");
    }

    // default
    int b;

    void methodB() {
        System.out.println("Parent's methodB");
    }

    // public
    public int c;

    public void methodC() {
        System.out.println("Parent's methodC");
    }

    public void methodD(boolean mode) {
        System.out.println("Parent's methodD");
    }

    // 생성자
    public Parent() {
        this.a = this.b = this.c = 0;
    }

    // 자바는 default 인자가 가능하지 않은 듯
    // public Parent(int a = 1, int b = 2, int c = 3) {
    public Parent(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

// extends 키워드로 상속, java는 다중상속 불허, 자식이 부모 선택
class Child extends Parent {
    // parent로부터 필드와 메소드를 상속받음

    // 생성자를 명시적으로 선언하지 않는 다면, 다음과 같은 기본 생성자 생성
    /*
     * public Child() { super(); }
     */

    // 생성자를 명시적으로 선언하고, 부모 생성자를 호출하지 않아도
    // 컴파일러가 자동으로 super(); 추가
    public Child() {
        // super(); 명시하지 않아도 컴파일러가 자동 추가
    }

    public Child(int a, int b, int c) {
        // super는 생성자의 가장 위에 위치해야 함
        // 부모가 기본 생성자가 없고 인자가 있는 변수만 있다면
        // 부모 생성자를 명시적으로 반드시 호출해야 함
        // (이 경우에는 부모가 기본 생성자가 있음)
        super(a, b, c);

    }

    // 메소드 오버라이딩
    // 자식 클래스에서 부모 메소드 재정의, 부모 객체의 메소드는 숨겨짐
    // 자식 객체의 메소드가 호출됨
    // 접근 제한을 더 강하게 오버라이딩 할 수 없다.
    // (public을 default나 private으로 수정 불가)
    // (default를 dafault나 public은 가능)
    @Override // 어노테이션 입력시 잘 체크해줌, public 없으면 오류 발생
    public void methodC() {
        System.out.println("Child's methodC");
    }

    @Override
    public void methodD(boolean mode) {
        if (mode)
            System.out.println("Child's methodD");
        else
            super.methodD(mode); // super는 부모 객체를 참조한다.
    }
}

// final 클래스는 자식을 만들 수 없다
final class FinalClass {
    // ...
}

class tmp {
    // 자식들이 재정의(오버라이딩) 할 수 없다
    public final void method() {
        // ...
    }
}

// 다른패키지에 있는 ClassB를 상속받는다
class Ref extends learn2.ClassB {
    Ref() {
        // new로 직접 부모 생성자를 호출할 수 없고
        // super()로 부모 생성자를 호출한다.
        super(); // 상속 받기위해 생성자 호출
        this.method();
    }

}

public class Hello04 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Child c1 = new Child();
        Child c2 = new Child(5, 6, 7); // 자식 클래스에서 생성자 정의해야 함
        c2.methodB(); // parent
        c2.methodC(); // child
        c2.methodD(true); // child
        c2.methodD(false); // parent

        // 다른 패키지의 클래스를 상속받는 클래스
        Ref ref = new Ref(); // 출력 : ClassB's method
        // ref.method(); // 클래스 내부 메소드에서 접근 가능(객체로 상속받은 protected 접근 불가)

    }

}
