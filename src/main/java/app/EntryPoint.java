package app;

import app.controller.Base;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import model.vehicle.Vehicle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntryPoint extends Application {
    public static User user;
    public static Logger logger;
    public static HashMap<String, String> langData;
    public ObservableList<Vehicle> vehicleData;
    public String enteredPassword;

    public Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.changeScene(Path.of("login-view.fxml").normalize());
        this.stage.setTitle("Login");
        this.setBoundary(320, 320, 140, 140);
    }

    public void changeScene(Path scenePath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EntryPoint.class.getResource(scenePath.toString()));
        Scene scene = new Scene(fxmlLoader.load());
        Base controller = fxmlLoader.getController();
        System.out.println(controller);
        controller.setApp(this);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private void setBoundary(int minWidth, int maxWidth, int minHeight, int maxHeight) {
        this.stage.setMinWidth(minWidth);
        this.stage.setMaxWidth(maxWidth);
        this.stage.setMinHeight(minHeight);
        this.stage.setMaxHeight(maxHeight);
    }


    public static void main(String[] args) throws IOException {
        logger = Logger.getLogger(EntryPoint.class.getCanonicalName());
        setLogger();
        logger.config("Loading user config...");
        try{
            Path userPath = Path.of("data", "users.ini");
            user = new User(userPath);
            langData = user.getLanguageData();
            logger.config("Successful loaded user config");
            changeLoggerLevel(user.isDebug());

            logger.finest(user.toString());
            logger.finest("LangData:\t" + langData.toString());
            logger.info("Starting Application...");
            launch(args);
            logger.info("Application has been stopped");
        } catch (Exception e) { // TODO
            logger.severe("Application has been interrupted");
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw e;
//            new EntryPoint("crasher-view.fxml", "Crasher", new int[] {320, 320, 240, 240});
        }
    }

    private static void setLogger() throws IOException {
        logger.setUseParentHandlers(true);
        logger.setLevel(Level.CONFIG);
        new File("log").mkdir();
        Handler handler = new FileHandler(Path.of("log", "app.log").toString(), true);
        logger.addHandler(handler);
    }

    public static void changeLoggerLevel(boolean isDebug) {
        if (user.isDebug()) {
            logger.setLevel(Level.ALL);
            logger.fine("Logger change self level to \"ALL\"");
        } else {
            logger.setLevel(Level.CONFIG);
        }
    }
}