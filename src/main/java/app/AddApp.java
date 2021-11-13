package app;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Path;

public class AddApp<T> extends App{

    private T enteredResult;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        this.changeScene(Path.of("add-view.fxml"), "Create");
        this.setBoundary(400, 400, 500, 500);

        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.showAndWait();
    }

    public void setObject(T obj) {
        enteredResult = obj;
    }

    public T getObject() {
        return enteredResult;
    }
}
