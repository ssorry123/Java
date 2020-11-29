package learn;

class Animal {
    void method1() {
        System.out.println("parent's method1");
    }

    void method2() {
        System.out.println("parent's method2");
    }
}

class Cat extends Animal {
    @Override
    void method2() {
        System.out.println("child's method2");
    }

    void method3() {
        System.out.println("child's method3");
    }
}

/*
 * 추상 클래스는 객체들의 부모이다 추상 클래스는 new를 이용하여 객체를 생성할 수 없다
 */
abstract class AbstractClass {
    public String name;

    public AbstractClass(String name) {
        this.name = name;
    }

    public void method1() {
        System.out.println("AbstractClass's method");
    }

    // 추상 메소드
    public abstract void method2();
}

class AbsChildClass extends AbstractClass {
    // 생성자, 부모 생성자를 호출해주어야 하
    public AbsChildClass(String name) {
        super(name);
    }

    // 추상 메소드 정의
    @Override
    public void method2() {
        System.out.printf("%s'soverrided method2\n", this.name);
    }
}

public class Hello05 {
    public static void main(String args[]) {
        // 자동 타입 변환
        // Cat cat = new Cat();
        // Animal animal = cat;
        // or
        Animal animal = new Cat();
        animal.method1(); // 부모 타입이므로, 부모 멤버만 접근 가능
        animal.method2(); // 자식이 오버라이딩 한 경우, 자식 멤버 접근 가능
        // animalmal.method3(); // 부모 타입에서 자식 타입 참조 불가
        System.out.println();

        // 강제 타입 변환
        // 자식이 부모 타입이 되었다가, 다시 자식이 되는 경우만 가능하다.
        // 자식 타입에만 있는 메소드를 사용하고자 할때 사용한다.
        // instanceof -> 자식 타입으로 강제 형변환 가능한지 알려준다
        boolean result = animal instanceof Cat;
        if (result) {
            Cat cat = (Cat) animal;
            cat.method3(); // 이제 사용 가능
            System.out.println(cat == animal); // true (같은 주소값에 있는 같은 객체임)
        }
        
        // 추상 클래스
        AbsChildClass abs = new AbsChildClass("abdef");
        abs.method1();  // 재정의 되지 않으므로 부모 타입 메소드가 실행
        abs.method2();  // 추상 메소드이고 오버라이딩 되었으므로 자식 타입 메소드가 실행
    }
}
