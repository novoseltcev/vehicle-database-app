package ctr.vehicle;

import model.vehicle.*;
import model.vehicle.exception.*;
import utils.Command;
import view.CrashNotifier;
import view.vehicle.AddMenu;

public class AddCtrl extends VehicleCtrl {
    public AddCtrl(AddMenu menu) {
        super(menu);
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        int cmd = super.chooseCMD(command, 5);
        Vehicle vehicle = getVehicle(cmd);
        if (vehicle != null) {
            vehicles.add(vehicle);
        }
    }

    private Vehicle getVehicle(int number) {
        String brand;
        String model;
        int maxCargoWeight;
        int numPassengers;setCurrentVehicleName(number);
        brand = getBrand();
        model = getModel();
        try {
            switch (currentVehicle) {
                case MOTORCYCLE -> {
                    maxCargoWeight = getMaxCargoWeight(100);
                    numPassengers = getNumPassengers(0, 2);
                    return new Motorcycle(brand, model, maxCargoWeight, numPassengers);
                }
                case CAR -> {
                    maxCargoWeight = getMaxCargoWeight(1000);
                    numPassengers = getNumPassengers(1, 8);
                    return new Car(brand, model, maxCargoWeight, numPassengers);
                }
                case TRUCK -> {
                    maxCargoWeight = getMaxCargoWeight(10000);
                    return new Truck(brand, model, maxCargoWeight);
                }
                case BUS -> {
                    maxCargoWeight = getMaxCargoWeight(10000);
                    numPassengers = getNumPassengers(8, 150);
                    return new Bus(brand, model, maxCargoWeight, numPassengers);
                }
                case TRAILER -> {
                    maxCargoWeight = getMaxCargoWeight(100000);
                    return new Trailer(brand, model, maxCargoWeight);
                }
            }
        } catch (InvalidNumPassengerExceptions | InvalidMaxCargoWeightExceptions | InvalidModelExceptions | InvalidBrandExceptions e) {
            new CrashNotifier(e);
        }
        return null;
    }
}