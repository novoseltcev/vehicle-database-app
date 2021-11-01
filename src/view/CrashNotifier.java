package view;

import java.io.IOException;
import java.util.Arrays;

public class CrashNotifier extends BaseMenu {
    final Exception error;
    final Class<?> errorType;
    final String msg;
    public CrashNotifier(Exception e) {
        error = e;
        errorType = error.getClass();
        msg = error.getMessage();
        show();
//        throw error;
    }

    void exception(String msg) {
        showTitle();
        display("|\t" + msg);
    }

    @Override
    public void show() {
        if (errorType.equals(IOException.class)) {
            exception("File integrity is broken: " + msg);
        } else if (errorType.equals(InterruptedException.class)) {
            display("Program close with code " + msg);
        } else {
            exception("Handling unexpected error: " + errorType + "\n");
            display_ln(Arrays.toString(error.getStackTrace()));
        }
    }
}
