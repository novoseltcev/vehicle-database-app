package controller;

import app.AddApp;
import app.NotifyApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.vehicle.Name;
import model.vehicle.Vehicle;
import model.vehicle.VehicleFactory;
import model.vehicle.exception.InvalidBrandExceptions;
import model.vehicle.exception.InvalidCargoWeightExceptions;
import model.vehicle.exception.InvalidModelExceptions;
import model.vehicle.exception.InvalidNumPassengerExceptions;

import java.util.HashMap;

public class AddController extends Controller {
    @FXML
    private Label typeLabel;
    @FXML
    private Label brandLabel;
    @FXML
    private Label modelLabel;
    @FXML
    private Label cargoLabel;
    @FXML
    private Label passengersLabel;

    @FXML
    private ChoiceBox<Name> typeChoosier;
    @FXML
    private TextField modelTextField;
    @FXML
    private TextField brandTextField;
    @FXML
    private TextField cargoTextField;
    @FXML
    private TextField passengersTextField;

    @FXML
    private Button createButton;

    @FXML
    private Label typeError;
    @FXML
    private Label brandError;
    @FXML
    private Label modelError;
    @FXML
    private Label cargoError;
    @FXML
    private Label passengersError;

    @Override
    protected void setLang() {
        HashMap<String, String> lang = app.getLangData();

        Label[] labels = {
                typeLabel,
                brandLabel,
                modelLabel,
                cargoLabel,
                passengersLabel,

                typeError,
                brandError,
                modelError,
                cargoError,
                passengersError
        };
        for (Label label: labels) {
            label.setText(
                    lang.get(label.getId())
            );
        }
        createButton.setText(lang.get(createButton.getId()));
    }

    @Override
    protected void initialize() throws Exception {
        logger.info("Initializing " + this.getClass().getSimpleName());

        Name[] names = {
                Name.MOTORCYCLE,
                Name.CAR,
                Name.BUS,
                Name.TRUCK,
                Name.TRAILER
        };
        typeChoosier.getItems().setAll(names);

        initTextListeners();

        brandTextField.setDisable(true);
        modelTextField.setDisable(true);
        cargoTextField.setDisable(true);
        passengersTextField.setDisable(true);
        createButton.setDisable(true);

        brandError.setVisible(false);
        modelError.setVisible(false);
        cargoError.setVisible(false);
        passengersError.setVisible(false);
    }

    private void initTextListeners() {
        cargoTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                cargoTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        passengersTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                passengersTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        typeChoosier.valueProperty().addListener((observable, oldValue, newValue) -> {
            typeError.setVisible(false);
            brandTextField.setDisable(false);
            modelTextField.setDisable(false);
            cargoTextField.setDisable(false);
            passengersTextField.setDisable(false);

            boolean passengersVisible = !(newValue == Name.TRAILER  || newValue == Name.TRUCK);
            passengersTextField.setVisible(passengersVisible);
            if (!passengersVisible) {
                passengersError.setVisible(false);
            }
            passengersLabel.setVisible(passengersVisible);

            checkButton();
        });

        modelTextField.textProperty().addListener(observable -> {
            modelError.setVisible(checkModel());
            checkButton();
        });

        brandTextField.textProperty().addListener(observable -> {
            brandError.setVisible(checkBrand());
            checkButton();
        });

        cargoTextField.textProperty().addListener(observable -> {
            cargoError.setVisible(checkCargo());
            checkButton();
        });

        passengersTextField.textProperty().addListener(observable -> {
            passengersError.setVisible(checkPassengers());
            checkButton();
        });
    }

    private boolean checkModel() {
        Name chosenName = typeChoosier.getValue();
        String value = modelTextField.getText();
        if (chosenName == null || value.isEmpty()) { return true; }

        try {
            VehicleFactory.checkModel(chosenName, value);
            return false;
        } catch (InvalidModelExceptions ignored) {
            return true;
        }
    }

    private boolean checkBrand() {
        Name chosenName = typeChoosier.getValue();
        String value = brandTextField.getText();
        if (chosenName == null || value.isEmpty()) { return true; }

        try {
            VehicleFactory.checkBrand(chosenName, value);
            return false;
        } catch (InvalidBrandExceptions ignored) {
            return true;
        }
    }

    private boolean checkCargo() {
        Name chosenName = typeChoosier.getValue();
        String value = cargoTextField.getText();
        if (chosenName == null || value.isEmpty()) { return true; }

        try {
            VehicleFactory.checkCargoWeight(chosenName, Integer.parseInt(value));
            return false;
        } catch (InvalidCargoWeightExceptions | NumberFormatException ignored) {
            return true;
        }
    }

    private boolean checkPassengers() {
        if (!passengersTextField.isVisible()) { return false; }
        Name chosenName = typeChoosier.getValue();
        String value = passengersTextField.getText();
        if (chosenName == null || value.isEmpty()) { return true; }

        try {
            VehicleFactory.checkNumPassengers(chosenName, Integer.parseInt(value));
            return false;
        } catch (InvalidNumPassengerExceptions | NumberFormatException ignored) {
            return true;
        }
    }

    private void checkButton() {
        createButton.setDisable(
                checkModel()
             || checkBrand()
             || checkCargo()
             || checkPassengers()
        );
    }

    public void createAction() throws Exception {
        try {
            Vehicle vehicle = VehicleFactory.create(
                    typeChoosier.getValue(),
                    brandTextField.getText(),
                    modelTextField.getText(),
                    Integer.parseInt(cargoTextField.getText()),
                    (!passengersTextField.isVisible()) ? 0 : Integer.parseInt(passengersTextField.getText())
                    );
            ((AddApp)app).setObject(vehicle);
            app.getStage().close();
        } catch (InvalidNumPassengerExceptions | InvalidBrandExceptions | InvalidCargoWeightExceptions | InvalidModelExceptions e) {
            new NotifyApp(e, Alert.AlertType.WARNING);
        }

    }
}
