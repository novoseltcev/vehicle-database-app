package model.vehicle;

import model.vehicle.exception.NonSelfWalkableVehicleException;


public class Trailer extends Vehicle {
    public Trailer(String brand, String model, Integer cargoWeight) {
        super(brand, model, cargoWeight, 0);
        type = Name.TRAILER;
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
