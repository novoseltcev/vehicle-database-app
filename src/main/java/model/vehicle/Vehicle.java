package model.vehicle;

import model.vehicle.exception.*;

import java.io.Serializable;
import java.util.Objects;


public abstract class Vehicle implements Serializable {
    protected String brand;
    protected String model;
    protected int cargoWeight;
    protected int numPassengers;

    protected Name name = Name.VEHICLE;

    public String  getBrand() { return brand; }
    public String  getModel() { return model; }
    public Integer getCargoWeight() { return cargoWeight; }
    public Integer getNumPassengers() { return numPassengers; }

    abstract int getThresholdSpeed() throws NonSelfWalkableVehicleException;
    abstract int getThresholdCargoWeight();
    abstract int[] getThresholdsNumPassengers();

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
        String result =  name.name() + "{" +
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
