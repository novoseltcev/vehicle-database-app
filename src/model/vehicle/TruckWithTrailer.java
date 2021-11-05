package model.vehicle;


public class TruckWithTrailer extends Truck implements IWithTrailer {
    protected Trailer trailer;

    public TruckWithTrailer(Truck truck, Trailer trailer) {
        super(truck.brand, truck.model, truck.cargoWeight + trailer.cargoWeight);
//        thresholdSpeed -= 20;
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
