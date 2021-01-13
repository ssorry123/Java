package learn1;

import java.util.Scanner;

/*
 * CallBack 예제
 * 
 * Button 클래스에서만 동작할 수 있는 메소드를
 * 다른 클래스에서 인터페이스로 정의하고 Button이 호출하게 함.
 */

class Button {
    //////////////////////////////////////
    // CallBack 구현 부분
    @FunctionalInterface
    public interface CallBack {
        public void method(Button btn);
    }

    private CallBack callBack;

    /**
     * 외부에서 Button이 할 일을 정의한다.
     * 
     * @param callBack
     */
    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    //////////////////////////////////////
    // 기존
    public Button() {
        callBack = null;
    }

    /**
     * 외부에서 정의해준 메소드를 실행한다.
     */
    public void run() {
        if (callBack != null) {
            callBack.method(this);
        }
    }

}

public class Hello39 {

    public static void main(String[] args) {
        Button button = new Button();
        // 인터페이스 구현
        button.setCallBack((btn) -> {
            System.out.println("이거 해라 짜샤");
        });

        button.run();
    }

}
