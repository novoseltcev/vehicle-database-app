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
    }

    @Override
    public void show() {
        showTitle();
        if (errorType.equals(IOException.class)) {
            critical("File integrity is broken: " + msg);
        } else if (errorType.equals(InterruptedException.class)) {
            critical("Program close with code " + msg);
        } else {
            critical("Handling unexpected error: " + errorType + "\n" + Arrays.toString(error.getStackTrace()));
        }
    }
}
