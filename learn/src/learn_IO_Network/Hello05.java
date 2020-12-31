package learn_IO_Network;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
 * 객체 입출력 보조 스트림
 * 객체를 파일 또는 네트워크에 출력할 수 있다.
 * 객체는 문자가 아니기 때문에 바이트 기반 스트림으로 출력해야 한다.
 * 
 * 직렬화 (serialization) : 객체의 데이터를 연속적인 바이트로 변경하는 것
 * 역직렬화 (deserialization) : 연속적인 바이트를 객체로 복원하는 것
 */

/*
 * Serializable 인터페이스
 * 비어있는 인터페이스지만, 객체를 직렬화할때 private 필드를 포함 모든 필드를 변환해도 좋다는 의미
 * 생성자 및 메소드는 포함되지 않음.
 * static, transient 필드도 포함되지 않음
 */

/*
 * 부모 클래스가 Serializable 인터페이스를 구현할 경우
 * 자식 클래스는 구현하지 않아도, 자식객체를 직렬화하면
 * 부모 필드 및 자식 필드가 모두 직렬화 됨
 * 
 * 부모가 구현하지 않고, 자식만 구현한 경우
 * 자식을 직렬화 할때, 부모 필드는 제외됨.
 * 
 * 방법
 * 1. 부모 클래스가 Serializable 인터페이스를 구현
 * 2. 자식 클래스에서 writeObject, readObject 메소드를 선언해서
 *      부모 객체의 필드를 직접 출력시킴
 * 
 */

@SuppressWarnings("serial")
class Test implements Serializable {
    // 컴파일 시 정해짐, 클래스 구분
    // 사용자가 정의할 수 있음, 같아야 직렬화, 역직렬화 가능, 권장x
    // static final long serialVersionUID;
    public int f1;
    protected int f2;
    int f3;
    private int f4;
    Test1 test1;
    public static int f5; // 직렬화 불가
    transient int f6; // 직렬화 불가

    Test() {
        f1 = 1;
        f2 = 2;
        f3 = 3;
        f4 = 4;
        test1 = new Test1();
        f5 = 5;
        f6 = 6;
    }
}

@SuppressWarnings("serial")
class Test1 implements Serializable {
    // 컴파일 시 정해짐, 클래스 구분
    // 사용자가 정의할 수 있음, 같아야 직렬화, 역직렬화 가능, 권장x
    // static final long serialVersionUID;
    public int f1;

    Test1() {
        f1 = 1;
    }
}

// 직렬화 불가능한 부모 직렬화 시키기
class Parent {
    String f1;
}

@SuppressWarnings("serial")
class Child extends Parent implements Serializable {
    String f2;

    // 아래 두 메소드는 직렬화, 역직렬화 할 때 자동으로 호출된다.
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF(f1); // 부모 출력
        out.defaultWriteObject(); // 자기 자신
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        f1 = in.readUTF();
        in.defaultReadObject();
    }
}

public class Hello05 {

    @SuppressWarnings({ "resource", "deprecation", "static-access" })
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String testFile = "D:/Java/learn/src/learn_IO_Network";
        /* 파일에 객체 입출력 */
        // output 스트림 정의
        FileOutputStream fos = new FileOutputStream(testFile + "/Object.dat");
        ObjectOutputStream oosFos = new ObjectOutputStream(fos);

        oosFos.writeObject(new Double(3.14));
        oosFos.writeObject(new String("홍길동"));

        oosFos.flush(); // dat파일을 보면 알아먹을 수 없다
        oosFos.close();
        fos.close();

        FileInputStream fis = new FileInputStream(testFile + "/Object.dat");
        ObjectInputStream oisFis = new ObjectInputStream(fis);
        Double dTemp = (Double) oisFis.readObject();
        String strTemp = (String) oisFis.readObject();
        oisFis.close();
        fis.close();
        System.out.println(dTemp);
        System.out.println(strTemp + "\n");

        /* 직렬화 가능 테스트 */
        Test test = new Test();
        fos = new FileOutputStream(testFile + "/Object1.dat");
        oosFos = new ObjectOutputStream(fos);
        oosFos.writeObject(test);
        oosFos.flush();
        oosFos.close();
        fos.close();

        // 역직렬화 할떄 같은 클래스를 사용해야 한다.
        fis = new FileInputStream(testFile + "/Object1.dat");
        oisFis = new ObjectInputStream(fis);
        test = (Test) oisFis.readObject();
        System.out.println(test.f1); // 1
        System.out.println(test.f2); // 2
        System.out.println(test.f3); // 3
        // System.out.println(test.f4); // private
        System.out.println(test.test1.f1); // 1
        System.out.println(test.f5); // 5, 정적 변수라 읽을 수는 있지만, dat파일에는 없다
        System.out.println(test.f6); // 6아닌 0이 출력된다(복원되지 않음)
        System.out.println();
        oisFis.close();
        fis.close();

        /* Serializable을 구현하지 않은 부모도 직렬, 역직렬화 */
        fos = new FileOutputStream(testFile + "/Object2.dat");
        oosFos = new ObjectOutputStream(fos);

        Child child = new Child();
        child.f1 = "parent";
        child.f2 = "child";
        oosFos.writeObject(child);
        oosFos.flush();
        oosFos.close();
        fos.close();

        fis = new FileInputStream(testFile + "/Object2.dat");
        oisFis = new ObjectInputStream(fis);
        Child tmp = (Child) oisFis.readObject();
        oisFis.close();
        fis.close();

        System.out.println(tmp.f1);
        System.out.println(tmp.f2);
    }

}
