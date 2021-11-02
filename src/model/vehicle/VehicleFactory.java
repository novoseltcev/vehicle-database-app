package model.vehicle;

import model.vehicle.exception.InvalidBrandExceptions;
import model.vehicle.exception.InvalidMaxCargoWeightExceptions;
import model.vehicle.exception.InvalidModelExceptions;
import model.vehicle.exception.InvalidNumPassengerExceptions;


public class VehicleFactory {
    public static Vehicle create(Name vehicleClass, String brand, String model, int maxCargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidBrandExceptions, InvalidMaxCargoWeightExceptions, InvalidModelExceptions {
        Vehicle vehicle = null;
        switch (vehicleClass) {
            case MOTORCYCLE -> vehicle = createMotorcycle(brand, model, maxCargoWeight, numPassengers);
            case CAR        -> vehicle = createCar(brand, model, maxCargoWeight, numPassengers);
            case TRUCK      -> vehicle = createTruck(brand, model, maxCargoWeight);
            case BUS        -> vehicle = createBus(brand, model, maxCargoWeight, numPassengers);
            case TRAILER    -> vehicle = createTrailer(brand, model, maxCargoWeight);
        }
        return vehicle;
    }

    static Motorcycle createMotorcycle(String brand, String model, int maxCargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidBrandExceptions, InvalidMaxCargoWeightExceptions, InvalidModelExceptions {
        return new Motorcycle(brand, model, maxCargoWeight, numPassengers);
    }

    static Car createCar(String brand, String model, int maxCargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidBrandExceptions, InvalidMaxCargoWeightExceptions, InvalidModelExceptions {
        return new Car(brand, model, maxCargoWeight, numPassengers);
    }

    static Truck createTruck(String brand, String model, int maxCargoWeight) throws InvalidBrandExceptions, InvalidMaxCargoWeightExceptions, InvalidModelExceptions {
        return new Truck(brand, model, maxCargoWeight);
    }

    static Bus createBus(String brand, String model, int maxCargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidBrandExceptions, InvalidMaxCargoWeightExceptions, InvalidModelExceptions {
        return new Bus(brand, model, maxCargoWeight, numPassengers);
    }

    static Trailer createTrailer(String brand, String model, int maxCargoWeight) throws InvalidBrandExceptions, InvalidMaxCargoWeightExceptions, InvalidModelExceptions {
        return new Trailer(brand, model, maxCargoWeight);
    }

    public static Vehicle random() {
        Name[] values = Name.values();
        Name value = values[(int) (Math.random() * 4)];
        try {
            return create(value, String.valueOf(Math.random() * 1000000), String.valueOf(Math.random() * 10000), (int) (Math.random() * 100), (int) (Math.random() * 2));
        } catch (InvalidNumPassengerExceptions |InvalidBrandExceptions | InvalidMaxCargoWeightExceptions | InvalidModelExceptions ignored) {}
        return null;
    }
}