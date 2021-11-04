package model.vehicle;

import model.vehicle.exception.*;


public class CarWithTrailer extends Car implements IWithTrailer{
    protected Trailer trailer;

    public CarWithTrailer(Car car, Trailer trailer) throws InvalidNumPassengerExceptions, InvalidCargoWeightExceptions, InvalidModelExceptions, InvalidBrandExceptions {
        super(car.brand, car.model, car.cargoWeight + trailer.cargoWeight, car.numPassengers);
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
