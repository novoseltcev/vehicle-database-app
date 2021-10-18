package view;

import java.io.IOException;

public class CrashNotifier extends BaseMenu {
    public void handler(Exception e){
        if (e instanceof IOException) {
            display("File integrity is broken: " + e.getMessage());
        } else if(e instanceof InterruptedException) {
            display("Program close with code " + e.getMessage());
        } else {
            display("Handling unexpected error: " + e.toString());
        }
    }
}
