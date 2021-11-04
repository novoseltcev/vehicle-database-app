package model.vehicle;

import model.vehicle.exception.*;


public class Truck extends Vehicle {

    public Truck(String brand, String model, int cargoWeight) throws InvalidCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions, InvalidNumPassengerExceptions {
        name = Name.TRUCK;numPassengers = 2;
        thresholdSpeed = 110;
        thresholdCargoWeight = 100000;
        thresholdMaxNumPassengers = 2;
        thresholdMinNumPassengers = 3;

        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;
        check();
    }
}
