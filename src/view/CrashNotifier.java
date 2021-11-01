package view;

import java.io.IOException;

public class CrashNotifier extends BaseMenu {
    final Class<?> errorType;
    final String msg;
    public CrashNotifier(Exception e) {
        errorType = e.getClass();
        msg = e.getMessage();
        show();
    }

    @Override
    public void show() {
        showTitle();
        if (errorType.equals(IOException.class)) {
            display("File integrity is broken: " + msg);
        } else if (errorType.equals(InterruptedException.class)) {
            display("Program close with code " + msg);
        } else {
            display("Handling unexpected error: " + errorType);
        }
    }
}
