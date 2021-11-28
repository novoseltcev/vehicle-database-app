package app;

import javafx.scene.control.Alert;
import model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
public class MainApp extends App {

    public MainApp() throws IOException {
        super("login-view.fxml", "Login", 320, 140, 320, 140);

        this.initLogger();
        logger.config("Loading user config...");
        MainApp.user = new User(Path.of("data", "users.ini"));
        logger.config("Successful loaded user config");
        logger.finest("Successful loaded user config: " + user.toString());

        changeLoggerLevel(user.isDebug());

        logger.info("Loading language locale...");
        logger.fine("Loaded language locale");
        logger.finest("Loaded language locale: " + getLangData());

        logger.info("Starting Application...");
    }

    @Override
    protected void show() {
        this.setPositionToCentral();
        this.stage.show();
    }

    public static void main(String[] args) throws Exception {
        try {
            launch();
        } catch (Exception e) { //// TODO
            System.out.println(Arrays.toString(e.getStackTrace()));
//            new NotifyApp(e, Alert.AlertType.ERROR);
        }
    }
}