package app;

import javafx.stage.Modality;


public class AddApp<T> extends App{
    private T enteredResult;

    public AddApp() {
        super("add-view.fxml", "Create", 400, 500, 400, 500);
    }

    @Override
    protected void show() {
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
