package model.vehicle;


public class Motorcycle extends Vehicle{
    protected Name name = Name.MOTORCYCLE;

    public Motorcycle(String brand, String model, int cargoWeight, int numPassengers) {
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
        return 100;
    }

    @Override
    int[] getThresholdsNumPassengers() {
        return new int[] {0, 2};
    }
}
