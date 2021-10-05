
import java.io.IOException;

public class EntryPoint {
    public static void main(String[] args) throws InterruptedException, IOException {
        //try {
            User user = new User();
            Menu view = new Menu();
            Main controller = new Main(view, user);
            Boolean run_flag = true;
            while (run_flag) {
                run_flag = controller.run();
            }
        //} catch (IOException e) {}  // TODO
    }
}