package model.vehicle;

import model.vehicle.exception.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a Vehicle abstract class
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public abstract class Vehicle implements Serializable {
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

    public void check(String brand, String model, int maxCargoWeight, int numPassengers) throws InvalidBrandExceptions, InvalidModelExceptions, InvalidMaxCargoWeightExceptions, InvalidNumPassengerExceptions {
        checkBrand(brand);
        checkModel(model);
        checkMaxCargoWeight(maxCargoWeight);
        checkNumPassengers(numPassengers);
    }

    public void check(String brand, String model, int maxCargoWeight) throws InvalidBrandExceptions, InvalidModelExceptions, InvalidMaxCargoWeightExceptions {
        checkBrand(brand);
        checkModel(model);
        checkMaxCargoWeight(maxCargoWeight);
    }

    public abstract void checkMaxCargoWeight(int maxCargoWeight) throws InvalidMaxCargoWeightExceptions;
    public abstract void checkNumPassengers(int numPassengers) throws InvalidNumPassengerExceptions;

    public static void checkBrand(String brand) throws InvalidBrandExceptions {
        if (brand.isEmpty()) {
            throw new InvalidBrandExceptions();
        }
    }

    public static void checkModel(String model) throws InvalidModelExceptions {
        if (model.isEmpty()) {
            throw new InvalidModelExceptions();
        }
    }

    public static  void checkMaxCargoWeight(Integer maxCargoWeight, Integer minValue, Integer maxValue) throws InvalidMaxCargoWeightExceptions {
        if (maxCargoWeight < minValue || maxCargoWeight > maxValue) {
            throw new InvalidMaxCargoWeightExceptions(minValue, maxValue);
        }
    }

    public static void checkNumPassengers(Integer numPassengers, Integer minValue) throws InvalidNumPassengerExceptions {
        if (numPassengers < minValue) {
            throw new InvalidNumPassengerExceptions(minValue);
        }
    }

    public static void checkNumPassengers(Integer numPassengers, Integer minValue, Integer maxValue) throws InvalidNumPassengerExceptions {
        if (numPassengers < minValue || numPassengers > maxValue) {
            throw new InvalidNumPassengerExceptions(minValue, maxValue);
        }
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
