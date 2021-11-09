package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
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


    protected void initialize() {
        System.out.println("initialize");
    }

    @Override
    protected void setLang() {
        usernameText.setText(user.getName());
        List<Labeled> items = new ArrayList<>() {{
            add(errorText);
            add(enterButton);
        }};

        for (Labeled item : items) {
            item.setText(
                    app.langData.get(item.getId())
            );
        }
    }

    @FXML
    void buttonClickHandler() throws IOException {
        app.enteredPassword = passwordInput.getText();
        if (user.checkPassword(app.enteredPassword)) {
            app.changeScene(Path.of("main-view.fxml"));
            app.stage.setMinWidth(900);
            app.stage.setMinHeight(400);
            app.stage.setMaxWidth(1920);
            app.stage.setMaxHeight(1080);
            app.stage.setX((1920 - 900) >> 1);
            app.stage.setY((1080 - 400) >> 1);
            app.stage.setTitle("Main");
            logger.fine(String.format("User %s successfully authorized", user.getName()));
        } else {
            passwordInput.clear();
            errorText.setVisible(true);
        }
    }

    @FXML
    void passwordEnterKeyHandler() throws IOException {
        buttonClickHandler();
    }


}