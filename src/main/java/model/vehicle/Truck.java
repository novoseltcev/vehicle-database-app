package model.vehicle;


public class Truck extends Vehicle {
    public Truck(String brand, String model, int cargoWeight) {
        super(brand, model, cargoWeight, 2);
        type = Name.TRUCK;
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
