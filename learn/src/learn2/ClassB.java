package learn2;

// 프로텍티드 클래스 (다른 패키지에서 참조할때, 자식만 가능)
public class ClassB {
    protected ClassB() {
        System.out.println("ClassB's constructor");
    }

    protected int a;

    protected void method() {
        System.out.println("ClassB's method");
    };
}
