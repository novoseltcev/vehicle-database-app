package model.vehicle;

import model.vehicle.exception.*;


public class Trailer extends Vehicle {

    public Trailer(String brand, String model, Integer cargoWeight) throws InvalidCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions, InvalidNumPassengerExceptions {
        name = Name.TRAILER;
        thresholdCargoWeight = 100000;
        thresholdMinNumPassengers = thresholdMaxNumPassengers = 0;

        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;

        check();

    }

    @Override
    public Integer getThresholdSpeed() throws NonSelfWalkableVehicleException {
        throw new NonSelfWalkableVehicleException();
    }
}
