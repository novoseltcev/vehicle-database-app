package app.controller;

import app.EntryPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Login extends Base {
    @FXML
    private Label usernameText;

    @FXML
    public Label errorText;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Button enterButton;

    @Override
    protected void setLang() {
        usernameText.setText(user.getName());

        System.out.println(EntryPoint.langData);
        System.out.println(langData);
        List<Labeled> items = new ArrayList<>() {{
            add(errorText);
            add(enterButton);
        }};

        for (Labeled item : items) {
            item.setText(
                    langData.get(item.getId())
            );
        }
    }

    @FXML
    void buttonClickHandler(ActionEvent event) throws IOException {
        app.enteredPassword = passwordInput.getText();
        if (EntryPoint.user.checkPassword(app.enteredPassword)) {
            app.changeScene(Path.of("main-view.fxml"));
            app.stage.setMinWidth(900);
            app.stage.setMinHeight(400);
            app.stage.setMaxWidth(1920);
            app.stage.setMaxHeight(1080);
            app.stage.setX((1920 - 900) >> 1);
            app.stage.setY((1080 - 400) >> 1);
            app.stage.setTitle("Main");
            EntryPoint.logger.fine(String.format("User %s successfully authorized", user.getName()));
        } else {
            passwordInput.clear();
            errorText.setVisible(true);
        }
    }

    @FXML
    void passwordEnterKeyHandler(ActionEvent event) throws IOException {
        buttonClickHandler(event);
    }
}