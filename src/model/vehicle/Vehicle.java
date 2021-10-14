package model.vehicle;

import model.vehicle.exception.*;
import java.util.Objects;

/**
 * Represents a Vehicle abstract class
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public abstract class Vehicle {
    protected String name = Name.VEHICLE.name();
    protected String brand;
    protected String model;
    protected Integer maxCargoWeight;
    protected Integer numPassengers;
    protected Integer thresholdSpeed;

    public String  getBrand() { return brand; }
    public String  getModel() { return model; }
    public Integer getMaxCargoWeight() { return maxCargoWeight; }
    public Integer getNumPassengers() { return numPassengers; }
    public Integer getThresholdSpeed() throws NonSelfWalkableVehicleException { return thresholdSpeed; }

    public void setBrand(String brand) throws InvalidBrandExceptions {
        if (brand.isEmpty()) {
            throw new InvalidBrandExceptions();
        } this.brand = brand;
    }

    public void setModel(String model) throws InvalidModelExceptions {
        if (model.isEmpty()) {
            throw new InvalidModelExceptions();
        } this.model = model;
    }

    public void setMaxCargoWeight(Integer maxCargoWeight, Integer minValue, Integer maxValue) throws InvalidMaxCargoWeightExceptions {
        if (maxCargoWeight < minValue || maxCargoWeight > maxValue) {
            throw new InvalidMaxCargoWeightExceptions(minValue, maxValue);
        } this.maxCargoWeight = maxCargoWeight;
    }

    public void setNumPassengers(Integer numPassengers, Integer minValue) throws InvalidNumPassengerExceptions {
        if (numPassengers < minValue) {
            throw new InvalidNumPassengerExceptions(minValue);
        } this.numPassengers = numPassengers;
    }

    public void setNumPassengers(Integer numPassengers, Integer minValue, Integer maxValue) throws InvalidNumPassengerExceptions {
        if (numPassengers < minValue || numPassengers > maxValue) {
            throw new InvalidNumPassengerExceptions(minValue, maxValue);
        } this.numPassengers = numPassengers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return brand.equals(vehicle.brand)
                && model.equals(vehicle.model)
                && Objects.equals(maxCargoWeight, vehicle.maxCargoWeight)
                && Objects.equals(numPassengers, vehicle.numPassengers)
                && Objects.equals(thresholdSpeed, vehicle.thresholdSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model);
    }

    @Override
    public String toString() {
        return  name + "{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", maxCargoWeight=" + maxCargoWeight +
                ", numPassengers=" + numPassengers +
                ", thresholdSpeed=" + thresholdSpeed +
                '}';
    }
}
