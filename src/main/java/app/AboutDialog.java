package app;


import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Path;


public class AboutDialog extends App {
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        this.changeScene(Path.of("about-view.fxml"), "About");
        this.setBoundary(380, 380, 280, 280);
        this.setPositionToCentral();

        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.showAndWait();
    }
}
