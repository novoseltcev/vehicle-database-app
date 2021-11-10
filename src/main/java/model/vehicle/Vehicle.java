package model.vehicle;

import javafx.beans.property.*;
import model.vehicle.exception.*;

import java.io.Serializable;
import java.util.Objects;


public abstract class Vehicle implements Serializable {
    protected transient static int counter = 0;
    protected transient IntegerProperty id;
    protected transient StringProperty brand;
    protected transient StringProperty model;
    protected transient IntegerProperty cargoWeight;
    protected transient IntegerProperty numPassengers;

    protected transient ObjectProperty<Name> type = new SimpleObjectProperty<>(Name.VEHICLE);

    public int getId() { return id.get(); }
    public String  getType() { return type.get().name(); }
    public String  getBrand() { return brand.get(); }
    public String  getModel() { return model.get(); }
    public int getCargoWeight() { return cargoWeight.get(); }
    public int getNumPassengers() { return numPassengers.get(); }

    public IntegerProperty idProperty() { return id; }
    public ObjectProperty<Name>  typeProperty() { return type; }
    public StringProperty  brandProperty() { return brand; }
    public StringProperty  modelProperty() { return model; }
    public IntegerProperty cargoWeightProperty() { return cargoWeight; }
    public IntegerProperty numPassengersProperty() { return numPassengers; }

    abstract int getThresholdSpeed() throws NonSelfWalkableVehicleException;
    abstract int getThresholdCargoWeight();
    abstract int[] getThresholdsNumPassengers();

    Vehicle(String brand, String model, int cargoWeight, int numPassengers) {
        this.id = new SimpleIntegerProperty(++counter);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.cargoWeight = new SimpleIntegerProperty(cargoWeight);
        this.numPassengers = new SimpleIntegerProperty(numPassengers);
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
        String result =  type.get().name() + "{" +
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
