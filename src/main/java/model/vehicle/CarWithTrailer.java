package model.vehicle;


public class CarWithTrailer extends Car implements IWithTrailer{
    protected Trailer trailer;

    public CarWithTrailer(Car car, Trailer trailer) {
        super(car.getBrand(), car.getModel(), car.getCargoWeight() + trailer.getCargoWeight(), car.getNumPassengers());
        this.trailer = trailer;
    }

    @Override
    int getThresholdSpeed() {
        return super.getThresholdSpeed() - 20;
    }

    @Override
    public int getCargoWeight() {
        return super.getCargoWeight() + trailer.getCargoWeight();
    }

    @Override
    public String getTrailerBrand() {
        return trailer.getBrand();
    }

    @Override
    public String getTrailerModel() {
        return trailer.getModel();
    }

    @Override
    public String toString() {
        return super.toString() + " with " + trailer;
    }
}
