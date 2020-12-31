package learn;

// 동적 객체 생성
// 런타임 시에 클래스 이름이 결정되는 경우에 매우 유용하게 사용

interface Action {
    public void execute();
}

class SendAction implements Action {
    @Override
    public void execute() {
        System.out.println("메세지를 보냅니다.");
    }
}

class ReceiveAction implements Action {
    @Override
    public void execute() {
        System.out.println("메세지를 받습니다.");
    }
}

public class Hello15 {

    @SuppressWarnings({ "deprecation", "rawtypes" })
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Class clazz = Class.forName("learn.SendAction");
            Action action = (Action) clazz.newInstance();
            action.execute();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Class clazz = Class.forName("learn.ReceiveAction");
            Action action = (Action) clazz.newInstance();
            action.execute();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
