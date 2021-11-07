package app.controller;

import app.EntryPoint;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    private TableView vehiclesTable;

    @FXML
    private TableColumn idColumn;

    @FXML
    private TableColumn typeColumn;

    @FXML
    private TableColumn brandColumn;

    @FXML
    private TableColumn speedColumn;

    @FXML
    private TableColumn cargoColumn;

    @FXML
    private TableColumn modelColumn;

    @FXML
    private TableColumn passengersColumn;


    private User user = EntryPoint.user;
    private final List<RadioMenuItem> languages = new LinkedList<>();

    @FXML
    private void initialize() {
        initMenu();
        setLangData();
        initTable();
    }

    private void setLangData() {
        newFileMenu.setText("");
        openFileMenu.setText("");
        saveFileMenu.setText("");
        saveAsFileMenu.setText("");
        nameSettingsMenu.setText("");
        passwordSettingsMenu.setText("");
        languageSettingsMenu.setText("");
        debugSettingsMenu.setText("");
        autotestSettingsMenu.setText("");
        autotestRunMenu.setText("");
        aboutHelpMenu.setText("");
        idColumn.setText("");
        typeColumn.setText("");
        brandColumn.setText("");
        speedColumn.setText("");
        cargoColumn.setText("");
        modelColumn.setText("");
        passengersColumn.setText("");
    }

    private void initTable() {
        
    }

    private void initMenu() {
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
            }
        }
    }
}
