package app.controller;

import app.EntryPoint;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;


public class Login {

    @FXML
    private Label usernameText;
    @FXML
    public Label errorText;
    @FXML
    private PasswordField passwordInput;

    @FXML
    private void initialize() {
        System.out.println(EntryPoint.user.getName());
        usernameText.setText(EntryPoint.user.getName());
    }

    @FXML
    void buttonClickHandler(ActionEvent event) throws IOException {
        String enteredPassword = passwordInput.getText();
        if (EntryPoint.user.checkPassword(enteredPassword)) {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gotoMainScene(currentStage);
        } else {
            passwordInput.clear();
            errorText.setVisible(true);
        }
    }

    @FXML
    void passwordEnterKeyHandler(ActionEvent event) throws IOException {
        buttonClickHandler(event);
    }

    private void gotoMainScene(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EntryPoint.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setX((1920 - 1280) >> 1);
        stage.setY((1080 - 720) >> 1);
        stage.setTitle("Main");
        stage.show();
    }

}