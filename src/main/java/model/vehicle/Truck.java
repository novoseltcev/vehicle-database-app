package model.vehicle;


public class Truck extends Vehicle {
    public Truck(String brand, String model, int cargoWeight) {
        name = Name.TRUCK;
        numPassengers = 2;

        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;
    }

    @Override
    int getThresholdSpeed() {
        return 110;
    }

    @Override
    int getThresholdCargoWeight() {
        return 100000;
    }

    @Override
    int[] getThresholdsNumPassengers() {
        return new int[] {2, 3};
    }
}
