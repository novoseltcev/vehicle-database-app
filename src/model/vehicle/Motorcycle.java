package model.vehicle;

import model.vehicle.exception.*;

/**
 * Represents a motorcycle extended from {@link Vehicle}
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public class Motorcycle extends Vehicle{

    /**
     * @param brand motorcycle brand, as example "Honda"
     * @param model motorcycle brand, as example "VRF800FI"
     * @param maxCargoWeight weight of the transported cargo
     * @param numPassengers number of passengers
     * @throws InvalidBrandExceptions passing an invalid brand to the constructor
     * @throws InvalidModelExceptions passing an invalid model to the constructor
     * @throws InvalidMaxCargoWeightExceptions passing an invalid maxCargoWeight to the constructor
     * @throws InvalidNumPassengerExceptions passing an invalid numPassengers to the constructor
     */
    public Motorcycle(String brand, String model, int maxCargoWeight, int numPassengers) throws InvalidNumPassengerExceptions, InvalidMaxCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions {
        check(brand, model, maxCargoWeight, numPassengers);
        this.brand = brand;
        this.model = model;
        this.maxCargoWeight = maxCargoWeight;
        this.numPassengers = numPassengers;
        this.thresholdSpeed = 110;
        this.name = Name.MOTORCYCLE.name();
    }


    @Override
    public void checkMaxCargoWeight(int maxCargoWeight) throws InvalidMaxCargoWeightExceptions {
        checkMaxCargoWeight(maxCargoWeight, 0, 100);
    }

    @Override
    public void checkNumPassengers(int numPassengers) throws InvalidNumPassengerExceptions {
        checkNumPassengers(numPassengers, 0, 2);
    }
}
