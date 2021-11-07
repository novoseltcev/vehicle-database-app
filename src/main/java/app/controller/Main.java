package app.controller;

import app.EntryPoint;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import model.User;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Main {
    public RadioMenuItem english;
    public RadioMenuItem russian;
    @FXML
    private MenuItem newFileMenu;

    @FXML
    private MenuItem openFileMenu;

    @FXML
    private MenuItem saveFileMenu;

    @FXML
    private MenuItem saveAsFileMenu;

    @FXML
    private MenuItem nameSettingsMenu;

    @FXML
    private MenuItem passwordSettingsMenu;

    @FXML
    private Menu languageSettingsMenu;

    @FXML
    private MenuItem debugSettingsMenu;

    @FXML
    private MenuItem autotestSettingsMenu;

    @FXML
    private Menu autotestRunMenu;

    @FXML
    private MenuItem aboutHelpMenu;

    private User user = EntryPoint.user;
    private final List<RadioMenuItem> languages = new LinkedList<>();

    @FXML
    private void initialize() {
        if (user.isSudoMode()) {
            debugSettingsMenu.setVisible(true);
            autotestSettingsMenu.setVisible(true);
            autotestRunMenu.setVisible(true);
        }

        for (MenuItem item: languageSettingsMenu.getItems()) {
            if (item.isMnemonicParsing()) {
                RadioMenuItem lang = (RadioMenuItem) item;
                lang.setSelected(false);
                if (Objects.equals(lang.getId(), user.getLanguage())) {
                    lang.setSelected(true);
                } languages.add(lang);
            }
        }
    }

    public void chooseLanguage(ActionEvent event) throws IOException {
        EventTarget target = event.getTarget();
        String targetId = ((RadioMenuItem) target).getId();
        for (RadioMenuItem lang: languages) {
            lang.setSelected(false);
            if (Objects.equals(lang.getId(), targetId)) {
                lang.setSelected(true);
                if (!user.setLanguage(targetId)) {
                    throw new AssertionError();
                }
                System.out.println(user.getLanguage());
            }
        }
        System.out.println();
    }
}
