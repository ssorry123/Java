package learn;
/*
 * 제네릭 타입 : 타입을 파라미터로 가지는 클래스와 인터페이스
 */

// 싱글 타입 파라미터 <T>
class Box<T> {
    private T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }

    // 제네릭 메소드(메소드에서 사용할 파라미터 정의)
    public <K> Box<K> setAndPrint(K k) {
        Box<K> ret = new Box<>();
        ret.t = k;
        System.out.println(ret.t);
        return ret;
    }
}

class ComparePair {
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
        // 키가 같고, 값도 같으면 논리적으로 같은 Pair
        boolean keyCompare = p1.getKey().equals(p2.getKey());
        boolean valCompare = p1.getValue().equals(p2.getValue());
        return keyCompare && valCompare;
    }
}

class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.setKey(key);
        this.setValue(value);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}

// 멀티 타입 파라미터 <A, B, C, ... >
class MultiBox<A, B, C, D> {
    A a;
    B b;
    C c;
    D d;

    MultiBox(A a, B b, C c, D d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void print() {
        System.out.println(a + " " + a.getClass().getName());
        System.out.println(b + " " + b.getClass().getName());
        System.out.println(c + " " + c.getClass().getName());
        System.out.println(d + " " + d.getClass().getName());
    }
}

public class Hello27 {

    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Box box1 = new Box();
        box1.set(10);
        System.out.println(box1.get());
        System.out.println(box1.get().getClass().getName());

        Box<String> box2 = new Box();
        box2.set("abcde");
        System.out.println(box2.get());
        System.out.println(box2.get().getClass().getName());
        System.out.println();

        MultiBox multiBox = new MultiBox(1, 3.14, "aaa", new Box<MultiBox>());
        multiBox.print();
        System.out.println();

        // 메소드에 명시적 파라미터 표시
        Box<String> box3 = box1.<String>setAndPrint("asdf");
        // 메소드의 타입 파라미터를 Integer로 추정
        Box<Integer> box4 = box3.setAndPrint(123);
        System.out.println();

        Pair<Integer, String> pair1 = new Pair<>(1, "감자");
        Pair<Integer, String> pair2 = new Pair<>(1, "감자");
        System.out.println(ComparePair.compare(pair1, pair2));
    }

}
