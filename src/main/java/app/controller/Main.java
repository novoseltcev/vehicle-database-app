package app.controller;

import app.EntryPoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.vehicle.Motorcycle;
import model.vehicle.Name;
import model.vehicle.Vehicle;

import java.io.IOException;
import java.util.*;

public class Main extends Base {

    public Menu fileMenu;

    @FXML
    private MenuItem newFileMenu;

    @FXML
    private MenuItem openFileMenu;

    @FXML
    private MenuItem saveFileMenu;

    @FXML
    private MenuItem saveAsFileMenu;


    public Menu settingsMenu;

    @FXML
    private MenuItem nameSettingsMenu;

    @FXML
    private MenuItem passwordSettingsMenu;

    @FXML
    private Menu languageSettingsMenu;

    @FXML
    private SeparatorMenuItem preDebugSeparator;

    @FXML
    private RadioMenuItem debugSettingsMenu;

    @FXML
    private SeparatorMenuItem preAutotestsSeparator;

    @FXML
    private RadioMenuItem autotestSettingsMenu;


    public Menu autotestRunMenu;

    public Menu helpMenu;

    @FXML
    private MenuItem aboutHelpMenu;


    @FXML
    private TableView<Vehicle> vehiclesTable;

    @FXML
    private TableColumn<Vehicle, Integer> idColumn;

    @FXML
    private TableColumn<Vehicle, Name> typeColumn;

    @FXML
    private TableColumn<Vehicle, String> brandColumn;

    @FXML
    private TableColumn<Vehicle, String> modelColumn;

    @FXML
    private TableColumn<Vehicle, Integer> cargoColumn;

    @FXML
    private TableColumn<Vehicle, Integer> passengersColumn;


    private List<RadioMenuItem> languages;
    private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        initMenu();
        initTable();
        EntryPoint.logger.info("Application has been started");
    }

    private void initTable() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        cargoColumn.setCellValueFactory(cellData -> cellData.getValue().cargoWeightProperty().asObject());
        passengersColumn.setCellValueFactory(cellData -> cellData.getValue().numPassengersProperty().asObject());

        vehicleData.add(new Motorcycle("ssds", "fdfdfd", 10, 0));
        vehicleData.add(new Motorcycle("ssds", "fdfdfd", 10, 1));
        vehiclesTable.setItems(vehicleData);
    }

    private void initMenu() {
        if (user.isSudoMode()) {
            preDebugSeparator.setVisible(true);
            debugSettingsMenu.setVisible(true);
            debugSettingsMenu.setSelected(user.isDebug());

            preAutotestsSeparator.setVisible(true);
            autotestSettingsMenu.setVisible(true);
            autotestSettingsMenu.setSelected(user.isTests());

            autotestRunMenu.setVisible(true);
        }



        languages = languageSettingsMenu.getItems()
                        .filtered(MenuItem::isMnemonicParsing)
                            .stream().map(item -> (RadioMenuItem) item)
                                .toList();

        for (RadioMenuItem lang: languages) {
            lang.setSelected(false);
            if (Objects.equals(lang.getId(), user.getLanguage())) {
                lang.setSelected(true);
            }
        }
    }

    @Override
    protected void setLang() {
        List<MenuItem> menuItems = new ArrayList<>() {{
            add(fileMenu);
            add(newFileMenu);
            add(openFileMenu);
            add(saveFileMenu);
            add(saveAsFileMenu);

            add(settingsMenu);
            add(nameSettingsMenu);
            add(passwordSettingsMenu);
            add(languageSettingsMenu);
            add(debugSettingsMenu);
            add(autotestSettingsMenu);

            add(autotestRunMenu);

            add(helpMenu);
            add(aboutHelpMenu);
        }};

        for (MenuItem menuItem : menuItems) {
            menuItem.setText(
                    langData.get(menuItem.getId())
            );
        }

        for (Object obj : vehiclesTable.getColumns()) {
            TableColumn tableColumn = (TableColumn) obj;
            tableColumn.setText(
                    langData.get(tableColumn.getId())
            );
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
        setLang();
    }

    public void switchDebug(ActionEvent event) throws IOException {
        RadioMenuItem target = (RadioMenuItem) event.getTarget();
        if (!user.setDebug(app.enteredPassword, target.isSelected())) {
            throw new AssertionError();
        } EntryPoint.changeLoggerLevel(user.isDebug());
    }

    public void switchAutotests(ActionEvent event) throws IOException {
        RadioMenuItem target = (RadioMenuItem) event.getTarget();
        if (!user.setTests(app.enteredPassword, target.isSelected())) {
            throw new AssertionError();
        }
    }

    public void startAutotests(ActionEvent actionEvent) {
        // TODO
    }
}
