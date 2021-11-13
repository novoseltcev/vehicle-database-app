package app;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;

import java.nio.file.Path;
import java.util.Arrays;

public class MainApp extends App {
    public MainApp() throws Exception {
        this.initLogger();

        logger.config("Loading user config...");
        this.user = new User(Path.of("data", "users.ini"));
        logger.config("Successful loaded user config");
        logger.finest("Successful loaded user config: " + user.toString());

        changeLoggerLevel(user.isDebug());

        logger.info("Loading language locale...");
        logger.fine("Loaded language locale");
        logger.finest("Loaded language locale: " + getLangData());

        logger.info("Starting Application...");
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        this.logger.info("Loading scene...");
        this.changeScene(Path.of("login-view.fxml").normalize(), "Login");
        this.setBoundary(320, 320, 140, 140);
        this.setPositionToCentral();

        this.stage.show();
    }

    public static void main(String[] args) throws Exception {
        try {
            launch( (MainApp.class));
//        logger.info("Application has been stopped");
        } catch (Exception e) { //// TODO
            System.out.println(Arrays.toString(e.getStackTrace()));
            new NotifyApp(e, Alert.AlertType.ERROR);
//            launch(EntryPoint.class);
//            logger.severe("Application has been interrupted");
        }
    }
}