package learn;

import java.util.Arrays;

class Util {
    // 타입 파라미터의 타입을 Number 타입 및, 하위 또는 구현 클래스로 제한한다.
    // (Byte, Short, Integer, Long, Double)
    // 제한 : 하위타입에만 있는 필드와 메소드는 사용할 수 없다.
    public static <T extends Number> int compare(T t1, T t2) {
        double d1 = t1.doubleValue();
        double d2 = t2.doubleValue();
        return Double.compare(d1, d2);
    }
}

// 와일드카드 타입
//
// <?> 제한 없음
// <? extends 상위타입> 상위 클래스 제한
// <? super 하위 타입> 하위 클래스 제한
//
//         _Person
//       /          \
// _Worker         _Student
//                   |
//              _HighStudent

class Course<T> {
    private String name;
    private T[] student;

    public Course(String name, int capacity) {
        this.name = name;
        // 타입 파라미터로 배열을 생성하려면, Object로 생성후 타입 변환을 해야 한다
        this.student = (T[]) (new Object[capacity]);
    }

    public String getName() {
        return name;
    }

    public T[] getStudent() {
        return student;
    }

    public void add(T t) {
        // 배열에 비어있는 부분을 찾아서 수강생을 추가하는 메소드
        for (int i = 0; i < student.length; ++i) {
            if (student[i] == null) {
                student[i] = t;
                break;
            }
        }
    }

}

class _Person {
    String name;

    _Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class _Worker extends _Person {
    _Worker(String name) {
        super(name);
    }
}

class _Student extends _Person {
    _Student(String name) {
        super(name);
    }
}

class _HighStudent extends _Student {
    _HighStudent(String name) {
        super(name);
    }
}

// 제네릭 클래스, 인터페이스 상속
interface _Storage<T> {
    public void add(int index, T item);

    public T get(int index);
}

class _Parent<T> {
    T parent;
}

// P : 부모의 제네릭 타입, I 인터페이스의 제네릭 타입, T : 자식이 추가한 제네릭 타입
class _Child<P, I, T> extends _Parent<P> implements _Storage<I> {
    private I[] i;
    private T t;

    @Override
    public void add(int index, I item) {
        if (i == null) {
            i = (I[]) (new Object[index + 1]);
        }
        if (index >= 0 && index < i.length) {
            i[index] = item;
            System.out.println(Arrays.toString(i));
        }
    }

    @Override
    public I get(int index) {
        if (i != null && index >= 0 && index < i.length) {
            System.out.println(i[index]);
            return i[index];
        }
        return null;
    }

    public void setParent(P p) {
        parent = p;
        System.out.println("set Parent : " + this.parent);
    }

    public void setChild(T t) {
        this.t = t;
        System.out.println("set Child : " + this.t);
    }

}

public class Hello28 {

    public static void showCourse(Course<?> course) {
        System.out.println(course.getName() + " 수강생 :" + Arrays.toString(course.getStudent()));
    }

    public static void showCourseStudent(Course<? extends _Student> course) {
        System.out.println(course.getName() + " 수강생 :" + Arrays.toString(course.getStudent()));
    }

    public static void showCourseWorker(Course<? super _Worker> course) {
        System.out.println(course.getName() + " 수강생 :" + Arrays.toString(course.getStudent()));
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s1 = "1";
        String s2 = "2";
        // Util.compare(s1, s2);
        System.out.println(Util.compare(3, 4));
        System.out.println(Util.compare(3.11, 2) + "\n");

        Course<_Person> personCourse = new Course<>("일반인과정", 5);
        personCourse.add(new _Person("일반인"));
        personCourse.add(new _Worker("직장인"));
        personCourse.add(new _Student("학생"));
        personCourse.add(new _Student("고등학생"));

        Course<_Worker> workerCourse = new Course<>("직장인과정", 5);
        workerCourse.add(new _Worker("직장인1"));

        Course<_Student> studentCourse = new Course<>("학생과정", 5);
        studentCourse.add(new _Student("학생1"));
        studentCourse.add(new _HighStudent("학생2"));

        Course<_HighStudent> highStudentCourse = new Course<>("고등학생 과정", 5);
        highStudentCourse.add(new _HighStudent("고등학생2"));

        showCourse(personCourse);
        showCourse(workerCourse);
        showCourse(studentCourse);
        showCourse(highStudentCourse);
        System.out.println();

        // showCourseStudent(personCourse);
        // showCourseStudent(workerCourse);
        showCourseStudent(studentCourse);
        showCourseStudent(highStudentCourse);
        System.out.println();

        showCourseWorker(personCourse);
        showCourseWorker(workerCourse);
        // showCourseWorker(studentCourse);
        // showCourseWorker(highStudentCourse);
        System.out.println();

        _Child<Double, String, Integer> child = new _Child<>();
        child.add(3, "홍길동");
        child.get(3);
        child.setChild(3);
        child.setParent(3.14);
    }

}
