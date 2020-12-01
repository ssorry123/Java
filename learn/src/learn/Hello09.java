package learn;

// 중첩 인터페이스
class Button {
    // 중첩 인터페이스
    interface OnClickListener {
        public void onClick();
    }

    // 인터페이스 타입 필드
    OnClickListener listener;

    // 매개 변수의 다형성
    void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    // 구현 객체의 onClick 메소드 호출
    void touch() {
        listener.onClick();
    }
}

class CallListener implements Button.OnClickListener {
    @Override
    public void onClick() {
        System.out.println("전화를 건다");
    }
}

class MessageListener implements Button.OnClickListener {
    @Override
    public void onClick() {
        System.out.println("메세지를 보낸다");
    }
}

class Window {
    Button btn1 = new Button();
    Button btn2 = new Button();
    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick() {
            System.out.println("전화를 켭니다");
        }
    };

    Window() {
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("전화를 끊습니다");
            }
        });
    }
}

public class Hello09 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 1.
        Button btn = new Button(); // 버튼 만들기

        btn.setOnClickListener(new CallListener());
        btn.touch();

        btn.setOnClickListener(new MessageListener());
        btn.touch();
        System.out.println();

        // 2.
        Window w = new Window();
        w.btn1.touch();
        w.btn2.touch();
    }

}
