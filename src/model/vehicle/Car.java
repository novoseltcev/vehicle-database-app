package model.vehicle;


public class Car extends Vehicle {
    protected Name name = Name.CAR;

    public Car(String brand, String model, int cargoWeight, int numPassengers) {
        this.brand = brand;
        this.model = model;
        this.cargoWeight = cargoWeight;
        this.numPassengers = numPassengers;
    }

    @Override
    int getThresholdSpeed() {
        return 110;
    }

    @Override
    int getThresholdCargoWeight() {
        return 1000;
    }

    @Override
    int[] getThresholdsNumPassengers() {
        return new int[] {1, 8};
    }
}
