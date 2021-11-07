module com.example.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens app to javafx.fxml;
    exports app;
    exports app.controller;
    opens app.controller to javafx.fxml;
}