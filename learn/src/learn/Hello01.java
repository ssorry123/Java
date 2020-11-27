package learn;

public class Hello01 {
    // 객체 변수
    int num1;
    double num2;
    double num3;
    
    // 클래스 변수(정적)
    static int sint1 = 1;
    static int sint2 = 2;
    static int sint3;
    // 정적 변수 초기화 블록
    static {
        sint3 = sint1 + sint2;
    }

    // 생성자, 선언된경우 명시적으로 호출해야함
    public Hello01(int num) {
        this.num1 = num;
        this.num2 = 0.0;
        this.num3 = 0.0;
    }

    // 생성자 오버로딩(매개변수를 달리함)
    public Hello01(double num) {
        this.num1 = 0;
        this.num2 = num;
        this.num3 = 0.0;
    }

    public Hello01(int num1, double num2) {
        this(num1, num2, 3.0);
    }

    public Hello01(int num1, double num2, double num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    // 단순 멤버 프린트
    public void getField() {
        System.out.println(num1 + " " + num2 + " " + num3);
    }

    // 인자로 배열을 사용
    public int getSum(int[] values) {
        int sum = 0;

        for (int v : values)
            sum += v;

        return sum;
    }

    // 인자로 배열이 아닌 매개변수 ... 사용
    public int getSum1(int... values) {
        int sum = 0;

        for (int v : values)
            sum += v;

        return sum;
    }

    // 정적 함수는, 객체 변수와 객체 메소드를 직접 참조할 수 없음
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // 1. 현재 함수 이름 얻기
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(name);

        // 2. 생성자 오버로딩
        Hello01 obj = new Hello01(3);
        obj.getField();

        obj = new Hello01(3.0);
        obj.getField();

        obj = new Hello01(123, 34.0);
        obj.getField();

        // 3. 함수 여러 인자 두가지 방법
        int ret;
        ret = obj.getSum(new int[] { 1, 2, 3 });
        System.out.println(ret);

        ret = obj.getSum1(1, 2, 3);
        System.out.println(ret);
        
        // 4. 클래스 정적 변수 확인
        System.out.println(Hello01.sint3);

    }

}
