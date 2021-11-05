package model.vehicle;

import model.vehicle.exception.InvalidBrandExceptions;
import model.vehicle.exception.InvalidCargoWeightExceptions;
import model.vehicle.exception.InvalidModelExceptions;
import model.vehicle.exception.InvalidNumPassengerExceptions;

import java.util.Optional;


public class VehicleFactory {
    public static Optional<Vehicle> create(Name vehicleClass, String brand, String model, int maxCargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidBrandExceptions, InvalidCargoWeightExceptions, InvalidModelExceptions {
        Vehicle vehicle = null;
        switch (vehicleClass) {
            case MOTORCYCLE -> vehicle = new Motorcycle(brand, model, maxCargoWeight, numPassengers);
            case CAR        -> vehicle = new Car(brand, model, maxCargoWeight, numPassengers);
            case TRUCK      -> vehicle = new Truck(brand, model, maxCargoWeight);
            case BUS        -> vehicle = new Bus(brand, model, maxCargoWeight, numPassengers);
            case TRAILER    -> vehicle = new Trailer(brand, model, maxCargoWeight);
        }
        check(vehicle);
        return Optional.ofNullable(vehicle);
    }

    public static Optional<Vehicle> random() {
        Name value = Name.values()[(int) (Math.random() * 4)];
        try {
            return create(value, String.valueOf(Math.random() * 1000000), String.valueOf(Math.random() * 10000), (int) (Math.random() * 100), (int) (Math.random() * 2));
        } catch (InvalidNumPassengerExceptions |InvalidBrandExceptions | InvalidCargoWeightExceptions | InvalidModelExceptions ignored) {}
        return Optional.empty();
    }


    public static void check(Vehicle vehicle) throws InvalidBrandExceptions, InvalidModelExceptions, InvalidCargoWeightExceptions, InvalidNumPassengerExceptions {
        checkBrand(vehicle, vehicle.getBrand());
        checkModel(vehicle, vehicle.getModel());
        checkCargoWeight(vehicle, vehicle.getCargoWeight());
        checkNumPassengers(vehicle, vehicle.getNumPassengers());
    }

    public static void checkBrand(Vehicle vehicle, String value) throws InvalidBrandExceptions {
        if (value.isEmpty()) {
            throw new InvalidBrandExceptions(value);
        }
    }

    public static void checkModel(Vehicle vehicle, String value) throws InvalidModelExceptions {
        if (value.isEmpty()) {
            throw new InvalidModelExceptions(value);
        }
    }

    public static void checkCargoWeight(Vehicle vehicle, int value) throws InvalidCargoWeightExceptions {
        int thresholdCargoWeight = vehicle.getThresholdCargoWeight();
        if (value < 0 || thresholdCargoWeight < value) {
            throw new InvalidCargoWeightExceptions(thresholdCargoWeight);
        }
    }

    public static void checkNumPassengers(Vehicle vehicle, int value) throws InvalidNumPassengerExceptions {
        int[] thresholdsNumPassengers = vehicle.getThresholdsNumPassengers();
        if (value < thresholdsNumPassengers[0] || thresholdsNumPassengers[1] < value) {
            throw new InvalidNumPassengerExceptions(thresholdsNumPassengers[0], thresholdsNumPassengers[1]);
        }
    }
}