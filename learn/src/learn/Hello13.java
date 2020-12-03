package learn;

import java.util.Arrays;

// java.lang.Objects

import java.util.Comparator;
import java.util.Objects;

class Student {
    int num;

    Student(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return String.valueOf(num);
    }
}

class StudentCmp implements Comparator<Student> {
    // compare(T a, T b, Comparator<T> c);
    @Override
    public int compare(Student a, Student b) {
        // a가 작으면 음수, 같으면 0, a가 크면 양수 반환
        return Integer.compare(a.num, b.num);
    }
}

class HashClass {
    int num;
    String name;

    HashClass(int num, String name) {
        this.num = num;
        this.name = name;
    }

    // Objects.hashCode(Object o)
    // Object.hashCode() 리턴값과 동일하지만, 매개값이 null이면 0을 반환한다는 점이 다름

    // Objects.hash(Object ... values)
    // 여러 필드로 해쉬값을 만듬
    @Override
    public int hashCode() {
        return Objects.hash(num, name);
    }

}

public class Hello13 {
    public static <T> void print(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        int ret;

        // 객체 비교
        // compare(T a, T b, Comparator<T> c);
        Student s1 = new Student(1);
        Student s2 = new Student(2);
        ret = Objects.compare(s1, s2, new StudentCmp());
        print(ret + "\n");

        // 동등 비교
        // equals, deepEquals
        Integer n1 = 1000;
        Integer n2 = 1000;
        print(Objects.equals(n1, n1)); // t
        print(Objects.equals(null, n2)); // f
        print(Objects.equals(null, null)); // t
        print(Objects.deepEquals(n1, n2) + "\n"); // t

        Integer[] arr1 = { 2, 3 };
        Integer[] arr2 = { 2, 3 };
        print(Objects.equals(arr1, arr2)); // f
        print(Objects.deepEquals(arr1, arr2)); // t
        print(Arrays.deepEquals(arr1, arr2)); // t
        print(Objects.deepEquals(null, arr2)); // f
        print(Objects.deepEquals(null, null) + "\n"); // t

        // hash
        // Objects.hashCode(Object o)
        // o.hashCode() 리턴값과 동일하지만, 매개값이 null이면 0을 반환한다는 점이 다름
        HashClass hc1 = new HashClass(1, "가나다");
        HashClass hc2 = new HashClass(1, "가나다");
        print(hc1.hashCode());
        print(Objects.hashCode(hc2));
        print(Objects.hashCode(null) + "\n");

        // null 여부 조사, isNull, nonNull
        print(Objects.isNull(null)); // T
        print(Objects.nonNull(null) + "\n"); // F

        // requireNonNull(T obj, [String msg | Supplier<String> msgSupplier])
        // obj가 nonNull 이면 obj 반환, null이면 예외 발생
        String str1 = "가나다";
        String str2 = null;

        print(Objects.requireNonNull(str1)); // 가나다
        try {
            String tmp = Objects.requireNonNull(str2);
        } catch (NullPointerException e) {
            print(e.getMessage());
        }

        try {
            String tmp = Objects.requireNonNull(str2, "null 값임");
        } catch (NullPointerException e) {
            print(e.getMessage());
        }

        try {
            String tmp = Objects.requireNonNull(str2, () -> "null값이라니까");
        } catch (NullPointerException e) {
            print(e.getMessage());
        }
        print("");

        // 객체 문자 정보
        // toString(Object o, [String nullDefault])
        Student tmp = new Student(3234566);
        print(Objects.toString("123"));
        print(Objects.toString(tmp));
        print(Objects.toString(null)); // "null"
        print(Objects.toString(null, "this is null"));

    }

}
