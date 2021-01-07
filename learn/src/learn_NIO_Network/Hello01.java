package learn_NIO_Network;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * 와치 서비스
 * 변경 통지 메커니즘.
 * 
 */

public class Hello01 extends Application {

    class WatchServiceThread extends Thread {
        public void printPlatform(String str) {
            Platform.runLater(() -> textArea.appendText(str));
        }

        @Override
        public void run() {
            try {
                // 감시 서비스 생성
                WatchService watchService = FileSystems.getDefault().newWatchService();
                // 감시할 디렉토리 설정
                // 테스트 결과
                // 0단계 까지는 감지(파일 내부 변경도)
                // 1단계 부터는 파일 명으로 감지
                Path dir = Paths.get("C:/Temp");
                // 감시할 이벤트 설정
                dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
                        StandardWatchEventKinds.ENTRY_MODIFY);

                while (true) {
                    // 블로킹
                    WatchKey watchKey = watchService.take();
                    // WatchEvent 목록 얻기
                    List<WatchEvent<?>> list = watchKey.pollEvents();

                    // 변화 목록 출력
                    for (WatchEvent watchEvent : list) {
                        Kind kind = watchEvent.kind(); // 종류
                        Path path = (Path) watchEvent.context(); // 경로

                        if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                            printPlatform("파일 생성됨 -> " + path.getFileName() + "\n");
                        } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                            printPlatform("파일 삭제됨 -> " + path.getFileName() + "\n");
                        } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                            printPlatform("파일 변경됨 -> " + path.getFileName() + "\n");
                        } else if (kind == StandardWatchEventKinds.OVERFLOW) {
                            // 운영체제에서 이벤트가 소실됐거나 버려진 경우.
                        }
                    }

                    // 감시하는 디렉토리 유효 검사
                    boolean valid = watchKey.reset();
                    if (!valid)
                        break;

                }

            } catch (Exception e) {

            }
        }
    }

    TextArea textArea;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 300);

        textArea = new TextArea();
        textArea.setEditable(false);
        root.setCenter(textArea);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("WatchServiceExample");
        primaryStage.show();

        // 와치 서비스 스레드 생성 및 시작
        WatchServiceThread wst = new WatchServiceThread();
        wst.start();

    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

}
