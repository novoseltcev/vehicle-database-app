package model.vehicle;


import javafx.beans.property.SimpleObjectProperty;

public class Motorcycle extends Vehicle{
    public Motorcycle(String brand, String model, int cargoWeight, int numPassengers) {
        super(brand, model, cargoWeight, numPassengers);
        type = new SimpleObjectProperty<>(Name.MOTORCYCLE);
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
