package app.controller;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.WriteAbortedException;
import java.util.Arrays;

public class Crasher extends Alert {
    public Crasher() {
        super(AlertType.ERROR);
    }

    public void handle(Exception error) {
        Class<? extends Exception> errorType = error.getClass();
        String msg = error.getMessage();
        String errorText = "Handling unexpected error";
        String handlingText = Arrays.toString(error.getStackTrace());
        if (errorType.equals(InterruptedException.class)) {
            errorText = "WriteAbortedException";
            handlingText = msg;
        } else if (errorType.equals(WriteAbortedException.class)) {
            errorText = "WriteAbortedException";
            handlingText = msg;
        } else if (errorType.equals(IOException.class)) {
            errorText = "File integrity is broken";
            handlingText = msg;
        }


        this.setHeaderText(errorText);
        this.setContentText(handlingText);
    }
}
