package learn;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Set;

/*
 * java.lang.System 클래스
 * JVM 위에서 돌아가는 Java에서, 운영체제의 일부 기능을 이용할 수 있다.
 * 모든 필드와 메소드는 static 으로 이루어져 있다.
 */

class Robot {
    int id;

    Robot(int id) {
        this.id = id;
    }

    // 소멸자
    public void finalize() {
        System.out.println(id + " Robot객체가 메모리에서 제거됨");
    }
}

class Obo extends Robot {
    int intObo;
    public int intObo2;

    Obo(int id) {
        super(id);
        this.intObo = id * 2;
    }

    public void methodObo() {

    }
}

public class Hello14 {
    private static void printParameters(Class[] parameters) {
        for (int i = 0; i < parameters.length; ++i) {
            System.out.print(parameters[i].getName());
            if (i < (parameters.length - 1)) {
                System.out.print(",");
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Class 클래스, getClass(), Class.getName("");
        Robot r = new Robot(1);
        Class rClass = r.getClass();
        System.out.println(rClass.getName());
        System.out.println(rClass.getSimpleName());
        System.out.println(rClass.getPackageName());
        System.out.println(rClass.getPackage().getName() + "\n");
        try {
            Class clazz = Class.forName("learn.Robot");
            System.out.println(clazz.getName() + "\n");
        } catch (ClassNotFoundException e) {

        }

        // 리플렉션
        // 선언된 멤버만 가져옴
        // getDeclaredConstructors(), getDeclareFields(), getDeclaredMethods();
        // 상속된 멤버도 가져옴(public만)
        // getFields, getMethods
        Robot rr = new Robot(2);
        Class rrC = rr.getClass();
        //
        Constructor[] rrCCons = rrC.getDeclaredConstructors();
        Field[] rrCField = rrC.getDeclaredFields();
        Method[] rrCMethod = rrC.getDeclaredMethods();
        //
        Field[] ooCField = rrC.getFields();
        Method[] ooCMethod = rrC.getMethods();
        //
        System.out.println("[생성자 정보]");
        for (Constructor ct : rrCCons) {
            System.out.print(ct.getName() + "(");
            Class[] parameters = ct.getParameterTypes();
            printParameters(parameters);
            System.out.println(")\n");
        }
        //
        System.out.println("[필드 정보]");
        for (Field f : rrCField) {
            System.out.println(f.getType().getSimpleName() + " " + f.getName());
        }
        System.out.println();
        //
        System.out.println("[메소드 정보]");
        for (Method m : rrCMethod) {
            System.out.print(m.getName() + "(");
            Class[] parameters = m.getParameterTypes();
            printParameters(parameters);
            System.out.println(")");
        }

        System.out.println();
        // 환경 변수 읽기
        System.out.println(System.getenv("JAVA_HOME"));
        System.out.println();

        // 시스템 프로퍼티 읽기 getProperty()
        String[] str = { "os.name", "user.name", "user.home", "java.version", "java.home", "file.separator",
                "user.dir" };
        for (String s : str) {
            System.out.println(System.getProperty(s));
        }
        // 모든 속성의 키와 값을 출력
        Properties props = System.getProperties();
        Set keys = props.keySet();
        for (Object objKey : keys) {
            String key = (String) objKey;
            String value = System.getProperty(key);
            System.out.println(key + " : " + value);
        }

        System.out.println();

        long timeMilli = System.currentTimeMillis(); // 1/1000초
        long timeNano = System.nanoTime(); // 1/10**9 초
        System.out.println(timeMilli + " " + timeNano + "\n");

        // System.gc() // 갈비지 컬렉션을 수행하도록 요청한다
        Robot r1 = new Robot(1);
        Robot r2 = new Robot(2);
        r1 = r2 = null;
        System.gc();
        System.out.println();

        // System.exit(val);
        // 보안 관리자 설정
        System.setSecurityManager(new SecurityManager() {
            @Override
            public void checkExit(int status) {
                // 종료 값이 5가 아니면, 예외를 일으켜서 종료되지 않게 한다
                if (status != 5) {
                    throw new SecurityException();
                }
            }
        });
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
            try {
                // JVM 종료 요청
                System.exit(i);
            } catch (SecurityException e) {

            }
        }

    }

}
