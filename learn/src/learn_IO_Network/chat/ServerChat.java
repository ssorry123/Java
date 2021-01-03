package learn_IO_Network.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// JavaFX 메인클래스로 만들기 위해 Application 상속
public class ServerChat extends Application {
    ExecutorService executorService; // 서버 과부하 방지를 위한 스레드풀
    ServerSocket serverSocket; // 클라이언트 연결 수락 담당
    // 연결된 스레드 저장 List, Thread safe한 vector
    List<ServerChatClient> connections = new Vector<>();

    void startServer() {
        // cpu코어 수만큼 스레드 생성 허용
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        System.out.println(executorService);
        // ServerSocket 생서 및 포트 바인딩
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5001));
        } catch (Exception e) {
            System.out.println("예외 발생");
            if (!serverSocket.isClosed())
                stopServer();
            return;
        }

        // 연결 수락
        // 작업 스레드 생성
        Runnable runnable;
        runnable = new Runnable() {
            @Override
            public void run() {
                // 작업 쓰레드는 UI를 변경하지 못하므로
                // UI 관리 메소드를 사용
                Platform.runLater(() -> {
                    displayText("[서버 시작]");
                    btnStartStop.setText("stop");
                });

                while (true) {
                    try {
                        // 연결 수락
                        Socket socket = serverSocket.accept();
                        String msg = "[연결수락 : ";
                        msg += socket.getRemoteSocketAddress();
                        msg += ": ";
                        msg += Thread.currentThread().getName();
                        msg += "]\n";

                        // 클라이언트 객체 저장
                        ServerChatClient client = new ServerChatClient(socket, ServerChat.this);
                        connections.add(client);
                        
                        final String str = msg + "연결개수:" + connections.size() + "]";

                        Platform.runLater(() -> {
                            displayText(str);
                        });

                    } catch (Exception e) {
                        System.out.println(e);
                        e.getStackTrace();
                        if (!serverSocket.isClosed())
                            stopServer();
                        break;
                    }
                }
            }
        };

        // 쓰레드 풀에서 처리
        executorService.submit(runnable);
    }

    void stopServer() {
        // 모든 Socket 닫기, ServerSocket 닫기, ExecutorService 종료
        try {
            // 모든 소켓 닫기
            Iterator<ServerChatClient> iter = connections.iterator();
            while (iter.hasNext()) {
                ServerChatClient c = iter.next();
                c.socket.close();
                iter.remove();
            }
            // 서버 소켓 닫기
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            // 스레드 풀 종료
            if (executorService != null && !executorService.isShutdown()) {
                executorService.shutdown();
            }
            Platform.runLater(() -> {
                displayText("[서버 멈춤]");
                btnStartStop.setText("start");
            });
        } catch (Exception e) {

        }
    }

    //////////////////////////////////////////////////////////////////
    // UI 관련
    TextArea txtDisplay;
    Button btnStartStop;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        BorderPane root = new BorderPane();
        root.setPrefSize(500, 300);

        txtDisplay = new TextArea();
        txtDisplay.setEditable(false);
        BorderPane.setMargin(txtDisplay, new Insets(0, 0, 2, 0));
        root.setCenter(txtDisplay);

        btnStartStop = new Button("start");
        btnStartStop.setPrefHeight(30);
        btnStartStop.setMaxWidth(Double.MAX_VALUE);

        // start와 stop버튼을 클릭했을때 이벤트 처리 코드
        btnStartStop.setOnAction(e -> {
            if (btnStartStop.getText().equals("start")) {
                System.out.println("서버 시작");
                startServer();
            } else if (btnStartStop.getText().equals("stop")) {
                System.out.println("서버 종료");
                stopServer();
            }
        });
        root.setBottom(btnStartStop);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("app.css").toString());
        primaryStage.setScene(scene);
        primaryStage.setTitle("server");
        primaryStage.setOnCloseRequest(e -> stopServer()); // 닫기 버튼
        primaryStage.show();
    }

    void displayText(String text) {
        txtDisplay.appendText(text + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
