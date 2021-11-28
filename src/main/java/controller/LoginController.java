package controller;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;


public class LoginController extends Controller {
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
                    user.getLanguageData().get(item.getId())
            );
        }
    }

    @FXML
    void buttonClickHandler() throws Exception {
        App.setEnteredPassword(passwordInput.getText());
        if (user.checkPassword(App.getEnteredPassword())) {
            app.nextScene("main-view.fxml");
            app.getStage().setTitle("Main");
            app.setBoundary(900, 450, 1920, 1080);
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