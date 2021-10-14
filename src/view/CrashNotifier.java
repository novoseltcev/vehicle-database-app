package view;

import java.io.IOException;

public class CrashNotifier extends BaseMenu {
    public void handler(Exception e){
        if (e instanceof IOException) {
            display_ln("File integrity is broken: " + e.getMessage());
        } else if(e instanceof InterruptedException) {
            display_ln("Program close with code " + e.getMessage());
        } else {
            display_ln("Handling unexpected error: " + e.toString());
        }
    }
}
