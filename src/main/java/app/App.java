package app;

import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class App extends CustomApp {
    protected static Logger logger;
    protected static User user;
    protected static String enteredPassword;

    protected App(String startFxmlFilename, String title, int... borders) {
        super(startFxmlFilename, title, borders);
    }

    public static User getUser() {
        return user;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        App.logger = logger;
    }

    public HashMap<String, String> getLangData() {
        return user.getLanguageData();
    }

    public static String getEnteredPassword() {
        return enteredPassword;
    }

    public static void setEnteredPassword(String text) {
        enteredPassword = text;
    }

    public static void changeLoggerLevel(boolean isDebug) {
        if (isDebug) {
            logger.setLevel(Level.ALL);
            logger.fine("Logger change self level to \"ALL\"");
        } else {
            logger.setLevel(Level.CONFIG);
        }
    }

    protected void initLogger() throws IOException {
        new File("log").mkdir();

        logger = Logger.getLogger(this.getClass().getSimpleName());
        logger.setUseParentHandlers(true);
        logger.setLevel(Level.CONFIG);
        logger.addHandler(
                new FileHandler(Path.of("log", logger.getName() + ".log").toString(), true)
        );
    }
}
