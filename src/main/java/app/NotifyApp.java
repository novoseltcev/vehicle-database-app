package app;

import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class NotifyApp extends App {
    private final Exception exception;

    private static String getTitle(Alert.AlertType alertType) {
        String title;
        switch (alertType) {
            case ERROR        -> title="Program will be crashed";
            case WARNING      -> title="Warning";
            case INFORMATION  -> title="Info";
            case CONFIRMATION -> title="Confirm";
            default           -> title="None";
        }
        return title;
    }

    public NotifyApp(Exception exception, Alert.AlertType alertType) throws Exception {
        super("notify-view.fxml", getTitle(alertType), 380, 280, 380, 280);
        this.exception = exception;
        start(new Stage());
    }

    @Override
    protected void show() {
        this.setPositionToCentral();

        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.showAndWait();
    }

    public Exception getException() {
        return exception;
    }


}
