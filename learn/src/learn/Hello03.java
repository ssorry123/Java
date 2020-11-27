package learn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.security.Provider.Service;

// 어노테이션(Annotation)
// 아무런 동작을 하지 않는 단지 표식일 뿐
// 메타데이터, 컴파일과정과 실행과정에서 코드를 어떻게 컴파일하고 처리할 것인지 알려주는 정보
// - 컴파일러가 문법 에러를 체크하도록
// - 개발 툴이 빌드나 배치 시 코드를 자동으로 생성할수 있도록 정보를 제공
// - 실행시(런타임시) 특정 기능을 실행하도록 정보 제공

// 어노테이션 선언
// 타겟 종류 : TYPE, ANNOTATION_TYPE, FIELD, CONSTRUCTOR, METHOD, LOCAL_VARIABLE, PACKAGE
// 유지정책 : SOURCE, CLASS, RUNTIME
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME) // (리플렉션 이용 어노테이션 정보 얻기 가능)
@interface Annotation1 {
    String str(); // 생략 불가

    int value() default 5; // 생략가능
}

public class Hello03 {

    @Annotation1(str = "-")
    public void method1() {
        System.out.println("method1");
    }

    @Annotation1(str = "*", value = 10)
    public void method2() {
        System.out.println("method2");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Hello03 object = new Hello03();
        object.method1();
        object.method2();

        // 클래스로부터 메소드 정보를 얻어옴. 순서를 보장하지 않는다.
        Method[] declaredMethods = Hello03.class.getDeclaredMethods();

        for (Method method : declaredMethods) {
            // 어노테이션이 적용되었는지 확인
            if (method.isAnnotationPresent(Annotation1.class)) {

                // 메소드에서 어노테이션 객체 얻기
                Annotation1 annotation = method.getAnnotation(Annotation1.class);

                // 메소드 이름 출력
                System.out.println("[" + method.getName() + "]");

                // 메소드의 어노테이션의 수만큼 문자 출력
                for (int i = 0; i < annotation.value(); ++i) {
                    System.out.print(annotation.str());
                }
                System.out.println();

                // 해당 메소드 호출
                try {
                    method.invoke(new Hello03());
                    System.out.println("\n\n");
                } catch (Exception e) {
                    System.out.println();
                }

            }
        }

    }

}
