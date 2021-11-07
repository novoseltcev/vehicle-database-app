package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntryPoint extends Application {
    public static User user;
    public static Logger logger;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Login");
        FXMLLoader fxmlLoader = new FXMLLoader(EntryPoint.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        logger = Logger.getLogger(EntryPoint.class.getCanonicalName());
        setLogger();
        logger.config("Loading user config...");
        try{
            Path userPath = Path.of("data", "users.ini");
            user = new User(userPath);

            logger.config("Successful loaded user config");
            if (user.isDebug()) {
                logger.setLevel(Level.ALL);
                logger.fine("Logger change self level to \"ALL\"");
            }

            logger.info("Starting Application...");
            launch();
            logger.fine(String.format("User %s successfully authorized", user.getName()));
            logger.info("Application has been started");
            // TODO
            logger.info("Application has been stopped");
        } catch (Exception e) { // TODO
            System.out.println(Arrays.toString(e.getStackTrace()));
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(EntryPoint.class.getResource("crasher-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setScene(scene);
            stage.show();
            logger.severe("Application has been interrupted");

        }
    }

    private static void setLogger() throws IOException {
        logger.setUseParentHandlers(true);
        logger.setLevel(Level.CONFIG);
        new File("log").mkdir();
        Handler handler = new FileHandler(Path.of("log", "app.log").toString(), true);
        logger.addHandler(handler);
    }
}