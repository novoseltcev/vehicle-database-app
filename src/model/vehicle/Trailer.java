package model.vehicle;

import model.vehicle.exception.*;

/**
 * Represents a trailer extended from {@link Vehicle}
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public class Trailer extends Vehicle {

    /**
     * @param brand trailer brand, as example "Tonar"
     * @param model trailer brand, as example "8310"
     * @param maxCargoWeight weight of the transported cargo
     * @throws InvalidBrandExceptions passing an invalid brand to the constructor
     * @throws InvalidModelExceptions passing an invalid model to the constructor
     * @throws InvalidMaxCargoWeightExceptions passing an invalid maxCargoWeight to the constructor
     *
     */
    public Trailer(String brand, String model, Integer maxCargoWeight) throws InvalidMaxCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions {
        setBrand(brand);
        setModel(model);
        setMaxCargoWeight(maxCargoWeight, 0, 100000);
        this.numPassengers = 0;
        name = Name.TRAILER.name();
    }

    /**
     * @throws NonSelfWalkableVehicleException calling a method that is not available for the current model.vehicle
     */
    @Override
    public Integer getThresholdSpeed() throws NonSelfWalkableVehicleException {
        throw new NonSelfWalkableVehicleException();
    }

    @Override
    public String toString() {
        return name + "{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", maxCargoWeight=" + maxCargoWeight + "}";
    }
}
