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
    public User user;
    public Logger logger;
    public HashMap<String, String> langData;
    public ObservableList<Vehicle> vehicleData;
    public String enteredPassword;

    public Stage stage;

    public EntryPoint() throws Exception {
        this.setLogger();

        logger.config("Loading user config...");
        this.user = new User(Path.of("data", "users.ini"));
        logger.config("Successful loaded user config");
        logger.finest("Successful loaded user config: " + user.toString());

        changeLoggerLevel(user.isDebug());

        logger.info("Loading language locale...");
        this.langData = user.getLanguageData();
        logger.fine("Loaded language locale");
        logger.finest("Loaded language locale: " + langData);

        logger.info("Starting Application...");
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.changeScene(Path.of("login-view.fxml").normalize());
        this.stage.setTitle("Login");
        this.setBoundary(320, 320, 140, 140);
    }

    public void changeScene(Path scenePath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenePath.toString()));
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

    public User getUser() {
        return user;
    }

    private void setLogger() throws IOException {
        logger = Logger.getLogger(this.getClass().getSimpleName());
        logger.setUseParentHandlers(true);
        logger.setLevel(Level.CONFIG);
        new File("log").mkdir();
        Handler handler = new FileHandler(Path.of("log", logger.getName() + ".log").toString(), true);
        logger.addHandler(handler);
    }

    public Logger getLogger() {
        return logger;
    }

    public void changeLoggerLevel(boolean isDebug) {
        if (isDebug) {
            logger.setLevel(Level.ALL);
            logger.fine("Logger change self level to \"ALL\"");
        } else {
            logger.setLevel(Level.CONFIG);
        }
    }


    public static void main(String[] args) throws IOException {
//        Repository<Vehicle> repository = new Repository<>();
//        repository.add(new Motorcycle("dd", "dfd", 12, 1));
//        File file = new File("ass.db");
//        file.createNewFile();
//        repository.saveTo(file);
        // TODO
        try {
            launch(EntryPoint.class);
//        logger.info("Application has been stopped");
        } catch (Exception e) { //// TODO
            System.out.println(Arrays.toString(e.getStackTrace()));
//            launch(EntryPoint.class);
//            logger.severe("Application has been interrupted");

//            throw e;
//            new EntryPoint("crasher-view.fxml", "Crasher", new int[] {320, 320, 240, 240});
        }
    }
}