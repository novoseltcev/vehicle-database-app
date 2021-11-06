import controller.BaseCtrl;
import controller.MainCtrl;
import controller.UserCtrl;
import model.User;
import view.BaseMenu;
import view.CrashNotifier;
import view.MainMenu;
import view.UserMenu;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EntryPoint {
    static Logger logger;
    public static void main(String[] args) {
        logger = Logger.getLogger(EntryPoint.class.getCanonicalName());
        setLogger();
        try {
            logger.config("Loading user config...");
            User user = new User();
            logger.config("Successful loaded user config");
            if (user.isDebug()) {
                logger.setLevel(Level.ALL);
                logger.fine("Logger change self level to \"ALL\"");
            }

            logger.info("Starting Application...");
            BaseMenu userMenu = new UserMenu(user.getLangData());
            UserCtrl userCtrl = new UserCtrl(userMenu, user, logger);
            userCtrl.welcome();
            logger.fine(String.format("User %s successfully authorized", user.getName()));


            BaseMenu mainMenu = new MainMenu(user.isSudoMode());
            BaseCtrl mainCtrl = new MainCtrl(mainMenu);
            logger.info("Application has been started");
            mainCtrl.loop();
        } catch (InterruptedException ignored) {
            logger.info("Application has been stopped");
        } catch (Exception e) {
            new CrashNotifier(logger, e);
            logger.severe("Application has been interrupted");
        }
    }

    private static void setLogger() {
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.CONFIG);
        try {
            Handler handler = new FileHandler("app.log", true);
            logger.addHandler(handler);
        } catch (IOException ignored) {}
    }
}