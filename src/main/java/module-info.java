module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires com.fasterxml.jackson.core;


    opens app to javafx.fxml;
    exports app;
    exports app.controller;
    opens app.controller to javafx.fxml;
}