package model.vehicle;


public class CarWithTrailer extends Car implements IWithTrailer{
    protected Trailer trailer;

    public CarWithTrailer(Car car, Trailer trailer) {
        super(car.brand, car.model, car.cargoWeight + trailer.cargoWeight, car.numPassengers);
        this.trailer = trailer;
    }

    @Override
    int getThresholdSpeed() {
        return super.getThresholdSpeed() - 20;
    }

    @Override
    public Integer getCargoWeight() {
        return super.getCargoWeight() + trailer.getCargoWeight();
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
