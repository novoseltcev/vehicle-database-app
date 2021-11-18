package controller;

import app.NotifyApp;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.WriteAbortedException;

public class NotifyController extends Controller {

    public Label errorLabel;

    @Override
    protected void initialize() throws Exception {
        Exception error = ((NotifyApp)app).getException();

        Class<? extends Exception> errorType = error.getClass();
        String msg = error.getMessage();
        String errorText = "Handling unexpected error";
        if (errorType.equals(InterruptedException.class)) {
            errorText = "WriteAbortedException";
        } else if (errorType.equals(WriteAbortedException.class)) {
            errorText = "WriteAbortedException";
        } else if (errorType.equals(IOException.class)) {
            errorText = "File integrity is broken";
        }
        errorLabel.setText(errorText);
    }

    @Override
    protected void setLang() {}
}
