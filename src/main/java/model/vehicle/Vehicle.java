package model.vehicle;

import javafx.beans.property.*;
import model.vehicle.exception.*;

import java.io.Serializable;
import java.util.Objects;


public abstract class Vehicle implements Serializable {
    protected int     id;
    protected String  brand;
    protected String  model;
    protected int     cargoWeight;
    protected int     numPassengers;

    protected Name type = Name.VEHICLE;

    public Name  getType() { return type; }
    public ObjectProperty<Name> typeProperty() { return new SimpleObjectProperty<>(type); }

    public String  getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public StringProperty brandProperty() { return new SimpleStringProperty(brand); }

    public String  getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public StringProperty modelProperty() { return new SimpleStringProperty(model); }

    public int getCargoWeight() { return cargoWeight; }
    public void setCargoWeight(int cargoWeight) { this.cargoWeight = cargoWeight; }
    public IntegerProperty cargoWeightProperty() { return new SimpleIntegerProperty(cargoWeight); }

    public int getNumPassengers() { return numPassengers; }
    public void setNumPassengers(int numPassengers) { this.numPassengers = numPassengers; }
    public IntegerProperty numPassengersProperty() { return new SimpleIntegerProperty(numPassengers); }


    abstract int getThresholdSpeed() throws NonSelfWalkableVehicleException;
    abstract int getThresholdCargoWeight();
    abstract int[] getThresholdsNumPassengers();

    Vehicle(String brand, String model, int cargoWeight, int numPassengers) {
        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;
        this.numPassengers = numPassengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return cargoWeight == vehicle.cargoWeight && numPassengers == vehicle.numPassengers && brand.equals(vehicle.brand) && model.equals(vehicle.model);
    }

    @Override
    public int hashCode() { return Objects.hash(brand, model); }

    @Override
    public String toString() {
        String result = type.name() + "{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", maxCargoWeight=" + getCargoWeight() +
                ", numPassengers=" + getNumPassengers();
        try {
            result += (", thresholdSpeed=" + getThresholdSpeed());
        } catch (NonSelfWalkableVehicleException ignored) {}
        return result + "}";
    }
}
