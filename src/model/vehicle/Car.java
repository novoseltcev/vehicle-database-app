package model.vehicle;

import model.vehicle.exception.*;

/**
 * Represents a car class extended from {@link Vehicle}
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public class Car extends Vehicle {

    /**
     * @param brand model.vehicle brand, as example "VAZ"
     * @param model model.vehicle brand, as example "3110 Volga"
     * @param maxCargoWeight weight of the transported cargo
     * @param numPassengers number of passengers
     * @throws InvalidBrandExceptions passing an invalid brand to the constructor
     * @throws InvalidModelExceptions passing an invalid model to the constructor
     * @throws InvalidMaxCargoWeightExceptions passing an invalid maxCargoWeight to the constructor
     * @throws InvalidNumPassengerExceptions passing an invalid numPassengers to the constructor
     */
    public Car(String brand, String model, int maxCargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidMaxCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions {
        setBrand(brand);
        setModel(model);
        setMaxCargoWeight(maxCargoWeight, 0, 1000);
        setNumPassengers(numPassengers, 1, 8);
        this.thresholdSpeed = 110;
        name = Name.CAR.name();
    }
}
