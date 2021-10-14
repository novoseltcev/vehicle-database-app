import java.io.Console;
import java.io.IOException;

public class CrashNotifier {
    private void print(String msg){
        System.out.print(msg);
    }
    public void handler(Exception e){
        if (e instanceof IOException) {
            print("File integrity is broken");
        } else{
            print("Handling unexpected error: " + e.getMessage());
        }
    }
}
