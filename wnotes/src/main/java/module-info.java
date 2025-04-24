module notes.app {
    requires javafx.controls;
    requires javafx.fxml;

    opens notes.app to javafx.fxml;
    exports notes.app;
}
