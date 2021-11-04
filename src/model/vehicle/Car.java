package model.vehicle;

import model.vehicle.exception.*;


public class Car extends Vehicle {
    public Car(String brand, String model, int cargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions {
        this.name = Name.CAR;
        thresholdSpeed = 110;

        thresholdCargoWeight = 1000;
        thresholdMinNumPassengers = 1;
        thresholdMaxNumPassengers = 8;

        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;
        this.numPassengers = numPassengers;

        check();
    }
}
