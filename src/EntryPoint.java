import java.util.logging.Level;

import ctr.MainCtrl;
import model.User;
import view.CrashNotifier;
import view.MainMenu;

public class EntryPoint {
    public static void main(String[] args) {
        try {
            User user = new User();
            MainMenu view = new MainMenu(user.getLangData(), user.isSudoMode());
            MainCtrl controller = new MainCtrl(view, user);
            while (true) {
            	try {
            		controller.run();
            	} catch (InterruptedException e) {
            		MainCtrl.logger.log(Level.INFO, "Close program with code: " + e.getMessage());
            		throw e;
            	} catch (Exception e) {
            		MainCtrl.logger.log(Level.SEVERE, "Interrupt program by cause:  " + e.getMessage());
            		throw e;
            	}
            	
            }
        } catch (Exception e) {
            new CrashNotifier(e);
        }
    }
}