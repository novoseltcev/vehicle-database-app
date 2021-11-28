package model.vehicle;


public class Bus extends Vehicle{
    public Bus(String brand, String model, int cargoWeight, int numPassengers) {
        super(brand, model, cargoWeight, numPassengers);
        type = Name.BUS;
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
