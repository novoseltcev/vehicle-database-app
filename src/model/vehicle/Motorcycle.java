package model.vehicle;

import model.vehicle.exception.*;


public class Motorcycle extends Vehicle{
    public Motorcycle(String brand, String model, int cargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions {
        thresholdSpeed = 110;
        name = Name.MOTORCYCLE;

        thresholdCargoWeight = 100;
        thresholdMinNumPassengers = 0;
        thresholdMaxNumPassengers = 2;

        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;
        this.numPassengers = numPassengers;

        check();
    }
}
