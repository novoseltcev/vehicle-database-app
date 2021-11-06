package model.vehicle;


public class Bus extends Vehicle{
    public Bus(String brand, String model, int cargoWeight, int numPassengers) {
        name = Name.BUS;
        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;
        this.numPassengers = numPassengers;
    }

    @Override
    int getThresholdSpeed() {
        return 90;
    }

    @Override
    int getThresholdCargoWeight() {
        return 10000;
    }

    @Override
    int[] getThresholdsNumPassengers() {
        return new int[] {8, 100};
    }
}
