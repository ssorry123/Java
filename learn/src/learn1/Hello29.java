package learn1;

/*
 * 람다식 : (함수적 프로그래밍)
 * 병렬 처리와 이벤트 지향 프로그래밍에 적합
 * 람다식 -> 매개 변수를 가진 코드 블록 -> 익명 구현 객체
 * 
 * ( 매개 변수 ... ) -> {실행 문};
 * 매개 변수 타입은 런타임 시에 대입되는 값에 다라 자동으로 인식 될 수 있기 때문에
 * 람다식에서는 일반적으로 매개 변수의 타입을 언급하지 않는다
 * 
 * 하나의 추상 메소드가 선언된 인터페이스(함수적 인터페이스)만이 람다식의 타겟 타입이 될 수 있다.
 */

// 두개 이상의 추상 메소드가 선언되면 오류를 발생시킨다.
// 해당 어노테이션이 없어도 추상메소드가 한개이면 FunctionalInterface이다.
@FunctionalInterface
interface MyInterfaceVoid1 {
    // 매개변수가 없는 메소드
    public void method(); // 추상 메소드라 함은 public이고 구현되지 않은 메소드를 말한다.

    // default와 static 메소드는 FunctionalInterface라도 선언할 수 있다.
    default void method1() {
    }
    static void method2() {
    }
}

@FunctionalInterface
interface MyInterfaceVoid2 {
    // 매개변수가 있는 메소드
    public void method(int a);
}

@FunctionalInterface
interface MyInterfaceInt {
    public int sum(int a, int b);
}

class Outer {
    private int outVal = 13;
    private int outVal1 = 3;
    public int outVal2 = 10;

    public void method() {
        System.out.println("Outer's method");
    }

    class Inner {
        int inVal = 10;
        int outVal = 23;

        // 람다식
        // 1. 클래스의 멤버 사용, 제약이 없음
        // 2. 로컬 변수 사용, 제약이 있음
        void method(int arg) {
            int localVal = 10;

            MyInterfaceVoid1 my = () -> {
                System.out.println(outVal); // Inner의 outVal
                System.out.println(outVal1); // Outer의 outVal1
                System.out.println(outVal2); // Outer의 outVal2
                System.out.println(Outer.this.outVal); // Outer의 outVal (바깥 객체의 참조 얻기)
                System.out.println(this.outVal); // Inner의 outVal (람다식을 실행한 객체의 참조)
                // 클래스 멤버, 제약 없음.
                outVal = outVal1 = outVal2 = Outer.this.outVal = this.outVal = -111;

                // 로컬 변수, 매게 변수, 읽을 수는 있지만 수정은 불가
                System.out.println("매개변수 : " + arg);
                System.out.println("지역변수 : " + localVal);
                // arg = localVal;
                // localVal = arg;
            };
            my.method();
        }
    }
}

public class Hello29 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1. 보통의 익명 구현 객체 생성
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
            }
        };
        // 2. 익명 구현 객체를 람다식으로 표현
        // Ruunable 변수에 대입되므로 람다식은 Runnable의 익명 구현 객체를 생성
        Runnable run2 = () -> {
            // TODO Auto-generated method stub
        };

        // 매개변수가 없는 메소드
        MyInterfaceVoid1 my1 = () -> {
            System.out.println("매개변수가 없는 메소드");
        };
        my1.method();

        // 매개변수가 있는 메소드
        MyInterfaceVoid2 my2 = (x) -> {
            System.out.println("매개변수가 있는 메소드 " + x);
        };
        my2.method(123);

        // 리턴값과 매개변수가 있는 메소드
        MyInterfaceInt my3 = (x, y) -> x + y; // 한 줄만 있고, 리턴값인 경우 생략 가능
        MyInterfaceInt my4 = (x, y) -> {
            System.out.printf("%d와 %d를 더함 = %d", x, y, x + y);
            return x + y;
        };
        System.out.println(my3.sum(3, 5));
        my4.sum(5, 10);
        System.out.println();

        // 3. 클래스의 멤버 사용
        Outer.Inner oi = new Outer().new Inner();
        oi.method(3);
        System.out.println();

    }

}
