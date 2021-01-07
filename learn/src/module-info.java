module learn {
    requires java.desktop;

    exports learn_IO_Network.chat;
    exports learn_NIO_Network;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
}