package app;

import javafx.stage.Modality;
import javafx.stage.Stage;
import java.nio.file.Path;

public class AutotestsApp extends App {
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        this.changeScene(Path.of("autotest-chart-view.fxml"), "Autotest");
        this.setBoundary(600, 1920, 450, 1080);
        this.setPositionToCentral();

        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.showAndWait();
    }
}