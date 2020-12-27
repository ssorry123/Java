package application01;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Hello01 extends Application {

    public static <T> void p(T t) {
        System.out.println(t);
    }

    static boolean programLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (programLayout)
            start_Program_Layout(primaryStage);
        else
            start_FXML_Layout(primaryStage);
    }

    public void start_Program_Layout(Stage primaryStage) throws Exception {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10)); // 안쪽 여백 설정
        hbox.setSpacing(10); // 컨트롤간 수평 간격 설정

        TextField textField = new TextField();
        textField.setPrefWidth(200); // 폭 설정

        Button btn = new Button();
        btn.setText("확인");

        ObservableList list = hbox.getChildren();
        list.add(textField);
        list.add(btn);

        Scene scene = new Scene(hbox);
        primaryStage.setTitle("프로그램적 레이아웃");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // 안드로이드 앱 개발 방법과 유사
    // 태그에 익숙한 디자이너와 협업 가능
    // FXML 코드 작성을 위한 JavaFX Scene Builder 제공
    public void start_FXML_Layout(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(getClass().getResource("root.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("FXML 레이아웃");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
//        programLayout = true;
//        p("프로그램적 레이아웃");
//        launch(args);

        programLayout = false;
        p("FXML 레이아웃");
        launch(args);

    }

}
