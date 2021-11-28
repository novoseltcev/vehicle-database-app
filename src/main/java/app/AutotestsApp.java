package app;

import javafx.stage.Modality;

public class AutotestsApp extends App {
    public AutotestsApp() throws Exception {
        super("autotest-chart-view.fxml", "Autotest", 600, 400);
    }

    @Override
    protected void show() {
        this.setPositionToCentral();

        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.showAndWait();
    }
}