package app.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.vehicle.Motorcycle;
import model.vehicle.Name;
import model.vehicle.Vehicle;
import repository.Repository;

import java.io.File;
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
    private Repository<Vehicle> repository = new Repository<>();

    @Override
    protected void initialize() {
        initMenu();
        initTable();
        logger.info("Application has been started");
    }

    private void initTable() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        brandColumn.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        modelColumn.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        cargoColumn.setCellValueFactory(cellData -> cellData.getValue().cargoWeightProperty().asObject());
        passengersColumn.setCellValueFactory(cellData -> cellData.getValue().numPassengersProperty().asObject());
        setTableData();
    }

    private void setTableData() {
        vehiclesTable.setItems(repository.readAll());
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
        List<? extends TableColumn<Vehicle, ?>> columns = vehiclesTable.getColumns().stream().map(item -> (TableColumn<Vehicle, ?>) item).toList();
        for (TableColumn<Vehicle, ?> column : columns) {
            column.setText(
                    app.langData.get(column.getId())
            );
        }

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
                    app.langData.get(menuItem.getId())
            );
        }
    }

    @FXML
    protected void chooseLanguage(ActionEvent event) throws IOException {
        EventTarget target = event.getTarget();
        String languageName = ((RadioMenuItem) target).getId();
        for (RadioMenuItem lang: languages) {
            lang.setSelected(false);
        }

        RadioMenuItem currentLanguage = languages.stream().filter(item -> item.getId().equals(languageName)).toList().get(0);
        currentLanguage.setSelected(true);
        if (!user.setLanguage(languageName)) {
            throw new AssertionError();
        }
        setLang();
    }

    @FXML
    protected void switchDebug(ActionEvent event) throws IOException {
        RadioMenuItem target = (RadioMenuItem) event.getTarget();
        if (!user.setDebug(app.enteredPassword, target.isSelected())) {
            throw new AssertionError();
        } app.changeLoggerLevel(user.isDebug());
    }

    @FXML
    protected void switchAutotests(ActionEvent event) throws IOException {
        RadioMenuItem target = (RadioMenuItem) event.getTarget();
        if (!user.setTests(app.enteredPassword, target.isSelected())) {
            throw new AssertionError();
        }
    }

    @FXML
    protected void startAutotests() {
        // TODO
    }

    @FXML
    protected void newFile() {
        repository = new Repository<>();
        repository.add(new Motorcycle("dd", "dfd", 12, 1));

        vehiclesTable.setVisible(true);
    }

    @FXML
    protected void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Database");

        ObservableList<ExtensionFilter> filters = fileChooser.getExtensionFilters();

        ExtensionFilter databaseFilter = new ExtensionFilter("Database files (*.db)", "*.db");
        filters.add(databaseFilter);

        ExtensionFilter otherFilter = new ExtensionFilter("Other", "*");
        filters.add(otherFilter);

        fileChooser.setInitialDirectory(new File("data"));

        File selectedFile = fileChooser.showOpenDialog(app.stage);
        if (selectedFile != null) {
            System.out.println(selectedFile);
            try {
                repository.load(selectedFile);
                vehiclesTable.setVisible(true);
                setTableData();
            } catch (IOException | ClassNotFoundException exception) {
                logger.warning(Arrays.toString(exception.getStackTrace()));
                Crasher crasher = new Crasher();  // TODO
                crasher.handle(exception);
                crasher.showAndWait();
            }
        }
    }

    @FXML
    protected void saveFile() {
        if (repository.getFile() == null) {
            saveFileAs();
        } else {
            try {
                repository.save();
            } catch (IOException exception) {
                logger.warning(exception.getMessage());
                Crasher crasher = new Crasher();  // TODO
                crasher.handle(exception);
                crasher.showAndWait();
            }
        }
    }

    @FXML
    protected void saveFileAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Database");

        ObservableList<ExtensionFilter> filters = fileChooser.getExtensionFilters();

        ExtensionFilter databaseFilter = new ExtensionFilter("Database files (*.db)", "*.db");
        filters.add(databaseFilter);

        ExtensionFilter otherFilter = new ExtensionFilter("Other", "*");
        filters.add(otherFilter);

        fileChooser.setInitialDirectory(new File("data"));

        File savingFile = fileChooser.showSaveDialog(app.stage);
        if (savingFile != null) {
            System.out.println(savingFile);
            try {
                savingFile.createNewFile();
                repository.saveTo(savingFile);
            } catch (IOException exception) {
                logger.warning(exception.getMessage());
                Crasher crasher = new Crasher();  // TODO
                crasher.handle(exception);
                crasher.showAndWait();
            }
        }
    }

    public void runName() {
        DialogPane dialog = new DialogPane();
    }

    public void runPassword() {
        DialogPane dialog = new DialogPane();
    }
}
