package app;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Path;

public class NotifyApp extends App {
    private final Exception exception;
    private final Alert.AlertType alertType;

    public NotifyApp(Exception exception, Alert.AlertType alertType) throws Exception {
        super();
        this.alertType = alertType;
        this.exception = exception;
        start(new Stage());
    }

    public Exception getException() {
        return exception;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        String title;
        switch (alertType) {
            case ERROR        -> title="Program will be crashed";
            case WARNING      -> title="Warning";
            case INFORMATION  -> title="Info";
            case CONFIRMATION -> title="Confirm";
            default           -> title="None";
        }
        this.changeScene(Path.of("notify-view.fxml"), title);
        this.setBoundary(380, 380, 280, 280);
        this.setPositionToCentral();

        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.showAndWait();
    }
}
