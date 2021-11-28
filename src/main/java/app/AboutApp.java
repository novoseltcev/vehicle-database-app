package app;


import javafx.stage.Modality;


public class AboutApp extends App {
    public AboutApp() {
        super("about-view.fxml", "About", 380, 280, 380, 280);
    }

    @Override
    protected void show() {
        this.setPositionToCentral();

        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.showAndWait();
    }
}
