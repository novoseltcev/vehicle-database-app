package controller;

import app.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import model.vehicle.*;
import model.vehicle.exception.*;
import repository.Repository;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainController extends Controller {

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

    @FXML
    private Button addButton;

    private List<RadioMenuItem> languages;
    private Repository<Vehicle> repository;

    @Override
    protected void initialize() {
        initMenu();
        initTable();
        logger.info("Application has been started");
    }

    private void initTable() {
        idColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(1 + vehiclesTable.getItems().indexOf(cellData.getValue())).asObject());

        typeColumn.setCellValueFactory(      cellData -> cellData.getValue().typeProperty());

        brandColumn.setCellValueFactory(     cellData -> cellData.getValue().brandProperty());
        brandColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        modelColumn.setCellValueFactory(     cellData -> cellData.getValue().modelProperty());
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        cargoColumn.setCellValueFactory(     cellData -> cellData.getValue().cargoWeightProperty().asObject());
        cargoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        passengersColumn.setCellValueFactory(cellData -> cellData.getValue().numPassengersProperty().asObject());
        passengersColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    private void initMenu() {
        setRoot();

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

    private void setTableData() {
        vehiclesTable.setItems(repository.readAll());

        vehiclesTable.setVisible(true);
        addButton.setVisible(true);
    }

    private void setRoot() {
        if (user.isSudoMode()) {
            preDebugSeparator.setVisible(true);
            debugSettingsMenu.setVisible(true);
            debugSettingsMenu.setSelected(user.isDebug());

            preAutotestsSeparator.setVisible(true);
            autotestSettingsMenu.setVisible(true);
            autotestSettingsMenu.setSelected(user.isTests());

            autotestRunMenu.setVisible(true);
        }
        vehiclesTable.setVisible(false);
        addButton.setVisible(false);
    }

    @Override
    protected void setLang() {
        List<? extends TableColumn<Vehicle, ?>> columns = vehiclesTable.getColumns().stream().map(item -> (TableColumn<Vehicle, ?>) item).toList();
        for (TableColumn<Vehicle, ?> column : columns) {
            column.setText(
                    app.getLangData().get(column.getId())
            );
        }
        addButton.setText(app.getLangData().get(addButton.getId()));

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
                    app.getLangData().get(menuItem.getId())
            );
        }
    }

    @FXML
    protected void chooseLanguage(ActionEvent event) throws IOException {
        RadioMenuItem langMenuItem = (RadioMenuItem) event.getTarget();
        if (!langMenuItem.isSelected()) {
            langMenuItem.setSelected(true);
            return;
        }

        String languageName = langMenuItem.getId();
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
        if (!user.setDebug(app.getEnteredPassword(), target.isSelected())) {
            throw new AssertionError();
        } app.changeLoggerLevel(user.isDebug());
    }

    @FXML
    protected void switchAutotests(ActionEvent event) throws IOException {
        RadioMenuItem target = (RadioMenuItem) event.getTarget();
        if (!user.setTests(app.getEnteredPassword(), target.isSelected())) {
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
        repository.add(new Car("VAZ", "Volga", 102, 4));
        setTableData();

        vehiclesTable.setVisible(true);
    }

    @FXML
    protected void openFile() throws Exception {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Database");

            ObservableList<ExtensionFilter> filters = fileChooser.getExtensionFilters();

            ExtensionFilter databaseFilter = new ExtensionFilter("Database files (*.db)", "*.db");
            filters.add(databaseFilter);

            ExtensionFilter otherFilter = new ExtensionFilter("Other", "*");
            filters.add(otherFilter);

            fileChooser.setInitialDirectory(new File("data"));

            File selectedFile = fileChooser.showOpenDialog(app.getStage());
            if (selectedFile != null) {
                System.out.println(selectedFile);
                repository = new Repository<>();
                repository.load(selectedFile);
                vehiclesTable.setVisible(true);
                setTableData();
            }
        } catch (IOException | ClassNotFoundException exception) {
            logger.warning(Arrays.toString(exception.getStackTrace()));
            new NotifyApp(exception, Alert.AlertType.WARNING);
        }
    }

    @FXML
    protected void saveFile() throws Exception {
        try {
            if (repository == null) {
                throw new IOException("No database to save");
            }

            if (repository.getFile() == null) {
                saveFileAs();
            } else {
                repository.save();
            }
        } catch (IOException exception) {
            logger.warning(exception.getMessage());
            new NotifyApp(exception, Alert.AlertType.WARNING);
        }
    }

    @FXML
    protected void saveFileAs() throws Exception {
        try {
            if (repository == null) {
                throw new IOException("No database to save");
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Database");

            ObservableList<ExtensionFilter> filters = fileChooser.getExtensionFilters();

            ExtensionFilter databaseFilter = new ExtensionFilter("Database files (*.db)", "*.db");
            filters.add(databaseFilter);

            ExtensionFilter otherFilter = new ExtensionFilter("Other", "*");
            filters.add(otherFilter);

            fileChooser.setInitialDirectory(new File("data"));

            File savingFile = fileChooser.showSaveDialog(app.getStage());
            if (savingFile != null) {
                System.out.println(savingFile);
                savingFile.createNewFile();
                repository.saveTo(savingFile);
            }
        } catch (IOException exception) {
            logger.warning(exception.getMessage());
            new NotifyApp(exception, Alert.AlertType.WARNING);
        }
    }

    public void changeName() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.showAndWait();
    }

    public void changePassword() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.showAndWait();
    }

    @FXML
    protected void showAboutMe() throws Exception {
        App aboutDialog = new AboutApp();
        aboutDialog.start(new Stage());
    }
}
