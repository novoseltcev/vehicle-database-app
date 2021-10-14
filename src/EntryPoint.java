import ctr.MainCtrl;
import model.User;
import view.CrashNotifier;
import view.MainMenu;

public class EntryPoint {
    public static void main(String[] args) {
        try {
            User user = new User();
            MainMenu view = new MainMenu(user.getLangData());
            MainCtrl controller = new MainCtrl(view, user);
            while (true) {
                controller.run();
            }
        } catch (Exception e) {
            CrashNotifier crashNotifier = new CrashNotifier();
            crashNotifier.handler(e);
        }
    }
}