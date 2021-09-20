package vehicle;

import vehicle.exception.*;

/**
 * Represents a truck extended from {@link Vehicle}
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public class Truck extends Vehicle {
    /**
     * @param brand truck brand, as example "MAN"
     * @param model truck brand, as example "TGL12.180 BL"
     * @param maxCargoWeight weight of the transported cargo
     * @throws InvalidBrandExceptions passing an invalid brand to the constructor
     * @throws InvalidModelExceptions passing an invalid model to the constructor
     * @throws InvalidMaxCargoWeightExceptions passing an invalid maxCargoWeight to the constructor
     */
    public Truck(String brand, String model, int maxCargoWeight) throws InvalidMaxCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions {
        setBrand(brand);
        setModel(model);
        setMaxCargoWeight(maxCargoWeight, 0, 100000);
        this.numPassengers = 2;
        this.thresholdSpeed = 110;
        name = Name.TRUCK.name();
    }
}
