package model.vehicle;

import model.vehicle.exception.*;

/**
 * Represents a car with trailer class extended from {@link Car}
 * @author <a href="https://github.com/st-a-novoseltcev">Novoseltcev Stanislav</a>
 * @version 1.0
 */
public class TruckWithTrailer extends Truck implements IWithTrailer {
    protected Trailer trailer;

    /**
     * @param truck the car to which the trailer is attached
     * @param trailer trailer attached to the car
     * @throws InvalidBrandExceptions passing an invalid brand to the constructor
     * @throws InvalidModelExceptions passing an invalid model to the constructor
     * @throws InvalidMaxCargoWeightExceptions passing an invalid maxCargoWeight to the constructor
     */
    public TruckWithTrailer(Truck truck, Trailer trailer) throws InvalidMaxCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions {
        super(truck.brand, truck.model, truck.maxCargoWeight);
        thresholdSpeed -= 20;
        this.trailer = trailer;
    }

    @Override
    public String getTrailerBrand() {
        return trailer.brand;
    }

    @Override
    public String getTrailerModel() {
        return trailer.model;
    }

    @Override
    public Integer getMaxCargoWeight() {
        return super.getMaxCargoWeight() + trailer.maxCargoWeight;
    }

    @Override
    public String toString() {
        return super.toString() + " with " + trailer;
    }
}
