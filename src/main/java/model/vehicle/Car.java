package model.vehicle;


import javafx.beans.property.SimpleObjectProperty;

public class Car extends Vehicle {
    public Car(String brand, String model, int cargoWeight, int numPassengers) {
        super(brand, model, cargoWeight, numPassengers);
        type = new SimpleObjectProperty<>(Name.CAR);
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
