package model.vehicle;

import model.vehicle.exception.*;


public class TruckWithTrailer extends Truck implements IWithTrailer {
    protected Trailer trailer;

    public TruckWithTrailer(Truck truck, Trailer trailer) throws InvalidCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions, InvalidNumPassengerExceptions {
        super(truck.brand, truck.model, truck.cargoWeight + trailer.cargoWeight);
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
    public String toString() {
        return super.toString() + " with " + trailer;
    }
}
