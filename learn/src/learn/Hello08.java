package learn;

class A {
    int a;
    static int b;

    void methodA() {
        System.out.println("instance  A.methodA");
    }

    static void methodB() {
        System.out.println("static  A.methodB");
    }

    A() {
        a = 3;
        b = 5;
    }

    // instance 멤버 클래스
    // A 객체를 생성해야만 사용할 수 있는 중첩 클래스
    class B {
        B() { // 생성자
            field1 = 0;
        }

        int field1; // 인스턴스 필드
        // static int field2; // 정적 필드 (X)

        void method1() {// 인스턴스 메소드
            System.out.println(this.field1 + " instance A.B.method1");
            // 바깥 클래스 필드와 메소드 모두 접근 가능
            A.this.a = 3;
            A.b = 5;
            A.this.methodA();
            A.methodB();
        }
        // static method2() {} // 정적 메소드 (X)
    }

    // 정적 멤버 클래스
    // A 클래스로 바로 접근할 수 있는 중첩 클래스
    static class C {
        // 모든 종류의 필드와 메소드 생성 가능
        C() {// 생성자
        };

        int field1 = 3; // 인스턴스 필드
        static int field2 = 10; // 정적 필드

        void method1() { // 인스턴스 메소드
            System.out.println(field1 + " instance A.C.method1");
            // instance 메소드 같지만, 정적 클래스의 메소드 이므로
            // 바깥 클래스의 instance 필드, 메소드 접근 불가
            // a = 3;
            // b = 5;
            // methodA();
            methodB();
        }

        static void method2() { // 정적 메소드
            System.out.println(field2 + " static A.C.method2");
            // 바깥 클래스의 instance 필드, 메소드 접근 불가
            // a = 3;
            b = 5;
            // methodA();
            methodB();
        }
    }

    // 로컬 클래스
    // method가 실행할 때만 사용할 수 있는 D 중첩 클래스
    // 접근제한자 public, private, default등을 붙일 수 없다
    // 주로 비동기 처리를 위해 스레드 객체를 만들 때 사용
    void method() {
        // 두 변수는 classD로 복사된다
        // classD에서는 수정할 수 없다.
        final int var1 = 1; // classD의 메소드로 복사된다
        int var2 = 2; // classD의 필드로 복사된다

        // 로컬 클래스, 인스턴스 필드, 메소드만 가능
        class D {
            int field1;

            // int var2
            D() { // 생성자
                field1 = 3;
            }

            void method1() {
                // int var1
                field1 = 10;
                // var1, var2 등 은 로컬 클래스에서 수정할 수 없다
                // var1 = 10;
                // var2 = 10;

                // 참조는 할 수 있다
                System.out.println(field1 + " local A.method.D.method1 " + (var1 + var2));
            };
            // static method2() {};
        }

        D d = new D();
        d.field1 = 1;
        d.method1();
    }

}



public class Hello08 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // B 객체를 생성하려면, A를 생성하고 B를 생성해야 한다.
        A a = new A();
        A.B b = a.new B();
        b.method1();
        System.out.println();

        A.C c = new A.C();
        c.method1();
        A.C.method2();
        System.out.println();

        a.method();
    }

}
