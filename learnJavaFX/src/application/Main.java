package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

// JavaFX 애플리케이션을 시작시키는 메인 클래스 형식
// Application을 상속받아야 하고
// start() 메소드를 재정의해야한다.

public class Main extends Application {
    public static <T> void p(T t) {
        System.out.println(t);
    }

    // 기본 생성자
    public Main() {
        p(Thread.currentThread().getName() + " Main 기본생성자 호출");
    }

    @Override
    public void init() throws Exception {
        p(Thread.currentThread().getName() + " init() 호출");
        super.init();
    }

    // App이 종료되기 전에 정리할 기회를 준다.
    @Override
    public void stop() throws Exception {
        p(Thread.currentThread().getName() + " stop() 호출");
        super.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        p(Thread.currentThread().getName() + " start() 호출");

        // javafx.scene.VBOX 이용 예제
        // VBox = 수직 방향으로 자식 컨트롤 배치
        // 루트 정의
        VBox root = new VBox();
        root.setPrefWidth(350); // 폭
        root.setPrefHeight(150); // 넓이
        root.setAlignment(Pos.CENTER); // 컨트롤 중앙 정렬
        root.setSpacing(30); // 컨트롤 수직 간격

        // 라벨
        Label label = new Label();
        label.setText("Hello, JavaFX");
        label.setFont(new Font(50));

        // 버튼
        Button btn = new Button();
        btn.setText("확인");
        btn.setOnAction(event -> Platform.exit());

        // 루트에 추가
        root.getChildren().add(label);
        root.getChildren().add(btn);

        // Scene 만들기
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("처음 만들어본 예제");
        primaryStage.show(); // 윈도우(Stage) 보여주기

    }

    public static void main(String[] args) throws Exception {
        // launch 메소드는 메인클래스의 객체를 생성하고 메인 윈도우를 생성한 다음
        // start() 메소드를 호출하는 역할을 한다.
        launch(args);
        // launch 메소드는 다음 두가지 스레드를 생성하고 시작시킨다
        // 1. JavaFX-Launcher : init() 실행
        // 2. JavaFX Application Thread : 기본생성자, start(), stop() 실행
        // -> 윈도우를 비롯한 UI 생성, 입력 이벤트 처리등 모든 관리는 JavaFX Application Thread가 함.
        // -> 다른 쓰레드가 UI에 접근하면 예외가 발생한다.

        // JavaFX 라이프 사이클
        // - 생성
        // Application.launch()
        // 1. 메인 클래스 기본 생성자 호출, 객체 생성
        // 2. init() 호출
        // 3. start() 호출
        //
        // - 종료
        // Platform.exit() or System.exit(0) 호출
        // 윈도우(Stage)의 닫기 버튼
        // close() 메소드 호출

    }
}
