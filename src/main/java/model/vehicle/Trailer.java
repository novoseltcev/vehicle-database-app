package model.vehicle;

import model.vehicle.exception.*;


public class Trailer extends Vehicle {
    public Trailer(String brand, String model, Integer cargoWeight) {
        name = Name.TRAILER;
        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;
    }

    public int getThresholdSpeed() throws NonSelfWalkableVehicleException {
        throw new NonSelfWalkableVehicleException();
    }

    @Override
    int getThresholdCargoWeight() {
        return 100000;
    }

    @Override
    int[] getThresholdsNumPassengers() {
        return new int[] {0, 0};
    }
}
