import controller.MainCtrl;
import model.User;
import view.BaseMenu;
import view.CrashNotifier;
import view.MainMenu;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntryPoint {
    public static void main(String[] args) {
        try {
            User user = new User();
            Logger logger = setLogger(user);
            BaseMenu view = new MainMenu(user.getLangData(), user.isSudoMode());
            MainCtrl controller = new MainCtrl(view, user, logger);
            controller.loop();
//            	} catch (InterruptedException e) {
//            		MainCtrl.logger.log(Level.INFO, "Close program with code: " + e.getMessage());
//            		throw e;
//            	} catch (Exception e) {
//            		MainCtrl.logger.log(Level.SEVERE, "Interrupt program by cause:  " + e.getMessage());
//            		throw e;
//            	}
        } catch (Exception e) {
            new CrashNotifier(e);
        }
    }

    private static Logger setLogger(User user) throws IOException {
        Logger logger = Logger.getLogger(EntryPoint.class.getCanonicalName());
        logger.setUseParentHandlers(false);
        logger.setLevel(
                user.isDebug() ? Level.ALL : Level.INFO
        );
        Handler handler = new FileHandler("app.log", true);
        logger.addHandler(handler);
        return logger;
    }
}