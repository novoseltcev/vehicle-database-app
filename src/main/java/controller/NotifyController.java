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
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            error.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

//            this.getDialogPane().setExpandableContent(expContent);
        }

//        this.setHeaderText(errorText);
//        this.setContentText(msg);
    }

    @Override
    protected void setLang() {}
}
