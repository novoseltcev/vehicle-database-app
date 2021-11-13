package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Login extends Controller {
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
                    app.getLangData().get(item.getId())
            );
        }
    }

    @FXML
    void buttonClickHandler() throws Exception {
        app.setEnteredPassword(passwordInput.getText());
        if (user.checkPassword(app.getEnteredPassword())) {
            app.changeScene(Path.of("main-view.fxml"), "Main");
            app.setBoundary(900, 1920, 400, 1080);
            app.setPositionToCentral();
            logger.fine(String.format("User %s successfully authorized", user.getName()));
        } else {
            passwordInput.clear();
            errorText.setVisible(true);
        }
    }

    @FXML
    void passwordEnterKeyHandler() throws Exception {
        buttonClickHandler();
    }


}