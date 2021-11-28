package model.vehicle;


public class TruckWithTrailer extends Truck implements IWithTrailer {
    protected Trailer trailer;

    public TruckWithTrailer(Truck truck, Trailer trailer) {
        super(truck.getBrand(), truck.getModel(), truck.getCargoWeight() + trailer.getCargoWeight());
//        thresholdSpeed -= 20;
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
