package model.vehicle;

import javafx.beans.property.*;
import model.vehicle.exception.*;

import java.io.Serializable;
import java.util.Objects;


public abstract class Vehicle implements Serializable {
    protected static int counter = 0;
    protected int     id;
    protected String  brand;
    protected String  model;
    protected int     cargoWeight;
    protected int     numPassengers;

    protected Name type = Name.VEHICLE;

    public int getId() { return id; }
    public String  getType() { return type.name(); }
    public String  getBrand() { return brand; }
    public String  getModel() { return model; }
    public int getCargoWeight() { return cargoWeight; }
    public int getNumPassengers() { return numPassengers; }

    public IntegerProperty idProperty() {
        System.out.println("id=" + id);
        return new SimpleIntegerProperty(id);
    }

    public ObjectProperty<Name> typeProperty() {
        System.out.println("type=" + type);
        return new SimpleObjectProperty<>(type);
    }

    public StringProperty brandProperty() {
        System.out.println("brand=" + brand);
        return new SimpleStringProperty(brand);
    }

    public StringProperty modelProperty() {
        System.out.println("model=" + model);
        return new SimpleStringProperty(model);
    }

    public IntegerProperty cargoWeightProperty() {
        System.out.println("cargoWeight=" + cargoWeight);
        return new SimpleIntegerProperty(cargoWeight);
    }

    public IntegerProperty numPassengersProperty() {
        System.out.println("numPassengers=" + numPassengers);
        return new SimpleIntegerProperty(numPassengers);
    }

    abstract int getThresholdSpeed() throws NonSelfWalkableVehicleException;
    abstract int getThresholdCargoWeight();
    abstract int[] getThresholdsNumPassengers();

    Vehicle(String brand, String model, int cargoWeight, int numPassengers) {
        this.id = ++counter;
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
    public int hashCode() {
        return Objects.hash(brand, model);
    }

    @Override
    public String toString() {
        String result =  "{" + //type.name() +
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
