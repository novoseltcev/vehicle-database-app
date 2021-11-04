package model.vehicle;

import model.vehicle.exception.*;

import java.io.Serializable;
import java.util.Objects;


public abstract class Vehicle implements Serializable {
    protected Name name = Name.VEHICLE;
    protected String brand;
    protected String model;
    protected int cargoWeight;
    protected int numPassengers;

    protected int thresholdSpeed;
    protected int thresholdCargoWeight;
    protected int thresholdMinNumPassengers;
    protected int thresholdMaxNumPassengers;


    public String  getBrand() { return brand; }
    public String  getModel() { return model; }
    public Integer getCargoWeight() { return cargoWeight; }
    public Integer getNumPassengers() { return numPassengers; }

    public Integer getThresholdSpeed() throws NonSelfWalkableVehicleException { return thresholdSpeed; }
    public Integer getThresholdCargoWeight() { return thresholdCargoWeight; }
    public Integer getThresholdMinNumPassengers() { return thresholdMinNumPassengers; }
    public Integer getThresholdMaxNumPassengers() { return thresholdMaxNumPassengers; }

    public void check() throws InvalidBrandExceptions, InvalidModelExceptions, InvalidCargoWeightExceptions, InvalidNumPassengerExceptions {
        checkBrand();
        checkModel();
        checkCargoWeight();
        checkNumPassengers();
    }

    public void checkCargoWeight() throws InvalidCargoWeightExceptions {
        if (cargoWeight < 0 || cargoWeight > thresholdCargoWeight) {
            throw new InvalidCargoWeightExceptions(thresholdCargoWeight);
        }
    }

    public void checkNumPassengers() throws InvalidNumPassengerExceptions {
        if (numPassengers < thresholdMinNumPassengers || numPassengers > thresholdMaxNumPassengers) {
            throw new InvalidNumPassengerExceptions(thresholdMinNumPassengers, thresholdMaxNumPassengers);
        }
    }

    public void checkBrand() throws InvalidBrandExceptions {
        if (brand.isEmpty()) {
            throw new InvalidBrandExceptions();
        }
    }

    public void checkModel() throws InvalidModelExceptions {
        if (model.isEmpty()) {
            throw new InvalidModelExceptions();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return brand.equals(vehicle.brand)
                && model.equals(vehicle.model)
                && Objects.equals(cargoWeight, vehicle.cargoWeight)
                && Objects.equals(numPassengers, vehicle.numPassengers)
                && Objects.equals(thresholdSpeed, vehicle.thresholdSpeed);
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
