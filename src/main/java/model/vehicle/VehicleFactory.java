package model.vehicle;

import model.vehicle.exception.*;

import java.util.InputMismatchException;


public class VehicleFactory {
    public static Vehicle create(Name name, String brand, String model, int maxCargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidBrandExceptions, InvalidCargoWeightExceptions, InvalidModelExceptions {
        Vehicle vehicle;
        switch (name) {
            case MOTORCYCLE -> vehicle = new Motorcycle(brand, model, maxCargoWeight, numPassengers);
            case CAR        -> vehicle = new Car(brand, model, maxCargoWeight, numPassengers);
            case TRUCK      -> vehicle = new Truck(brand, model, maxCargoWeight);
            case BUS        -> vehicle = new Bus(brand, model, maxCargoWeight, numPassengers);
            case TRAILER    -> vehicle = new Trailer(brand, model, maxCargoWeight);
            default -> throw new InputMismatchException(name.name());
        }
//        check(vehicle);
        return vehicle;
    }

    private static Vehicle getByName(Name name) {
        try {
            return create(
                    name,
                    String.valueOf(Math.random() * 1000000),
                    String.valueOf(Math.random() * 10000),
                    (int) (Math.random() * 100),
                    (int) ( (name.equals(Name.BUS)) ? ((Math.random() + 8) * 10) : (Math.random() + 1) )
            );
        } catch (InvalidNumPassengerExceptions |InvalidBrandExceptions | InvalidCargoWeightExceptions | InvalidModelExceptions ignored) {
            throw new AssertionError();
        }
    }

    public static Vehicle random() {
        Name name = Name.values()[(int) (Math.random() * 4)];
        return getByName(name);
    }


    public static void check(Vehicle vehicle) throws InvalidBrandExceptions, InvalidModelExceptions, InvalidCargoWeightExceptions, InvalidNumPassengerExceptions {
        checkBrand(vehicle.getType(), vehicle.getBrand());
        checkModel(vehicle.getType(), vehicle.getModel());
        checkCargoWeight(vehicle.getType(), vehicle.getCargoWeight());
        checkNumPassengers(vehicle.getType(), vehicle.getNumPassengers());
    }

    public static void checkBrand(Name name, String value) throws InvalidBrandExceptions {
        Vehicle vehicle = getByName(name);
        if (value.isEmpty()) {
            throw new InvalidBrandExceptions(value);
        }
    }

    public static void checkModel(Name name, String value) throws InvalidModelExceptions {
        Vehicle vehicle = getByName(name);
        if (value.isEmpty()) {
            throw new InvalidModelExceptions(value);
        }
    }

    public static void checkCargoWeight(Name name, int value) throws InvalidCargoWeightExceptions {
        Vehicle vehicle = getByName(name);
        int thresholdCargoWeight = vehicle.getThresholdCargoWeight();
        if (value <= 0 || thresholdCargoWeight < value) {
            throw new InvalidCargoWeightExceptions(thresholdCargoWeight);
        }
    }

    public static void checkNumPassengers(Name name, int value) throws InvalidNumPassengerExceptions {
        Vehicle vehicle = getByName(name);
        int[] thresholdsNumPassengers = vehicle.getThresholdsNumPassengers();
        if (value < thresholdsNumPassengers[0] || thresholdsNumPassengers[1] < value) {
            throw new InvalidNumPassengerExceptions(thresholdsNumPassengers[0], thresholdsNumPassengers[1]);
        }
    }
}