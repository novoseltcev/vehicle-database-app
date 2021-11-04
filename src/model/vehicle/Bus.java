package model.vehicle;

import model.vehicle.exception.*;


public class Bus extends Vehicle{
    public Bus(String brand, String model, int cargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions {
        this.name = Name.BUS;
        this.thresholdSpeed = 90;

        this.thresholdCargoWeight = 10000;
        this.thresholdMinNumPassengers = 8;
        this.thresholdMaxNumPassengers = 100;

        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;
        this.numPassengers = numPassengers;

        check();
    }
}
