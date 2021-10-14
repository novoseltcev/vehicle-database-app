package utils;

import java.io.IOException;

public class CrashNotifier {
    private void print(String msg){
        System.out.print(msg);
    }
    public void handler(Exception e){
        if (e instanceof IOException) {
            print("File integrity is broken: " + e.getMessage());
        } else if(e instanceof InterruptedException) {
            print("Program close with code " + e.getMessage());
        } else {
            print("Handling unexpected error: " + e.getMessage());
        }
    }
}
