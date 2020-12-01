package learn;

class Person {
    void wake() {
        System.out.println("7시에 일어나기");
    }
}

class Anonymous {
    // 익명 자식 객체 만들기
    Person field = new Person() {
        // 자식의 메소드
        void work() {
            System.out.println("8시에 출근하기");
        }

        @Override
        void wake() {
            System.out.println("6시에 일어나기");
            work(); // 부모 객체에서는 접근할 수 없지만, 같은 객체에서는 접근 가능
        }
    };

    // 메소드
    void method1() {
        Person localVar = new Person() {
            void work() {
                System.out.println("산책하기");
            }

            @Override
            void wake() {
                System.out.println("5시에 일어나기");
                work(); // 부모 객체에서는 접근할 수 없지만, 같은 객체에서는 접근 가능
            }
        };
        localVar.wake();

    }

    void method2(Person person) {
        // 자식이 오버라이딩 한 경우 자식의 메소드
        // 오버라이딩 하지 않은 경우 person(부모)의 메소드
        person.wake();
    }
}

interface RemoteControl {
    public void turnOn();

    public void turnOff();
}

class RAnonymous {
    RemoteControl remote = new RemoteControl() {
        @Override
        public void turnOn() {
            System.out.println("TV를 킨다");
        }

        @Override
        public void turnOff() {
            System.out.println("TV를 끈다");
        }
    };

    void audio() {
        RemoteControl localVal = new RemoteControl() {
            @Override
            public void turnOn() {
                System.out.println("오디오를 킨다");
            }

            @Override
            public void turnOff() {
                System.out.println("오디오를 끈다");
            }
        };
        localVal.turnOn();
        localVal.turnOff();
    }

    void fax(RemoteControl rc) {
        rc.turnOn();
        rc.turnOff();
    }

}

public class Hello10 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1.
        Anonymous an = new Anonymous();
        an.field.wake();
        System.out.println();

        // 2.
        an.method1();
        System.out.println();

        // 3.
        an.method2(new Person() {

        });
        System.out.println();

        an.method2(new Person() {
            @Override
            void wake() {
                System.out.println("12시에 일어나기");
            }
        });

        System.out.println("\n");

        // 4.
        RAnonymous ra = new RAnonymous();
        ra.remote.turnOn();
        ra.remote.turnOff();
        System.out.println();

        // 5.
        ra.audio();
        System.out.println();

        // 6.
        ra.fax(new RemoteControl() {
            @Override
            public void turnOn() {
                System.out.println("팩스를 킨다");
            }

            @Override
            public void turnOff() {
                System.out.println("팩스를 끈다");
            }
        });

    }

}
