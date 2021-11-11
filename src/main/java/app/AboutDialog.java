package app;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class AboutDialog{
    public AboutDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setMinWidth(380);
        stage.setMaxWidth(380);
        stage.setMinHeight(280);
        stage.setMaxHeight(280);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
