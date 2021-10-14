import java.io.IOException;

public class EntryPoint {
    public static void main(String[] args) throws InterruptedException {
        try {
            User user = new User();
            Menu view = new Menu(user.getLangData());
            Main controller = new Main(view, user);
            while (true) {
                controller.run();
            }
        } catch (IOException | InterruptedException e) {
            CrashNotifier crashNotifier = new CrashNotifier();
            crashNotifier.handler(e);
        }
    }
}