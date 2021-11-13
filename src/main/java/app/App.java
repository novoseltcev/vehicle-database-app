package app;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class App extends Application {
    protected Stage stage;
    
    protected User user;
    protected Logger logger;
    protected String enteredPassword;


    public Stage getStage() {
        return stage;
    }

    public User getUser() {
        return user;
    }

    public Logger getLogger() {
        return logger;
    }

    public HashMap<String, String> getLangData() {
        return user.getLanguageData();
    }

    public String getEnteredPassword() {
        return enteredPassword;
    }

    public void setEnteredPassword(String text) {
        enteredPassword = text;
    }


    public void changeScene(Path scenePath, String title) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(scenePath.toString()));
        Scene scene = new Scene(fxmlLoader.load());
        Controller controller = fxmlLoader.getController();
        System.out.println(controller);
        controller.setApp(this);
        this.stage.setScene(scene);
        this.stage.setTitle(title);
    }

    public void setBoundary(int minWidth, int maxWidth, int minHeight, int maxHeight) {
        this.stage.setMinWidth(minWidth);
        this.stage.setMaxWidth(maxWidth);
        this.stage.setMinHeight(minHeight);
        this.stage.setMaxHeight(maxHeight);
    }

    public void setPosition(int X, int Y) {
        this.stage.setX(X);
        this.stage.setY(Y);
    }

    public void setPositionToCentral() {
        setPosition(
                (int) (1920 - this.stage.getMinWidth()) >> 1,
                (int) (1080 - this.stage.getMinHeight()) >> 1
        );
    }

    public void changeLoggerLevel(boolean isDebug) {
        if (isDebug) {
            logger.setLevel(Level.ALL);
            logger.fine("Logger change self level to \"ALL\"");
        } else {
            logger.setLevel(Level.CONFIG);
        }
    }

    protected void setLogger() throws IOException {
        new File("log").mkdir();

        logger = Logger.getLogger(this.getClass().getSimpleName());
        logger.setUseParentHandlers(true);
        logger.setLevel(Level.CONFIG);
        logger.addHandler(
                new FileHandler(Path.of("log", logger.getName() + ".log").toString(), true)
        );
    }
}
