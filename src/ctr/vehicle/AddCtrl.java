package ctr.vehicle;

import model.vehicle.*;
import model.vehicle.exception.*;
import utils.Command;
import view.CrashNotifier;
import view.vehicle.AddMenu;

import java.util.InputMismatchException;

public class AddCtrl extends VehicleCtrl {
    public AddCtrl(AddMenu menu) {
        super(menu);
    }

    @Override
    protected void chooseCMD(Command command) throws InputMismatchException, InterruptedException {
        super.chooseCMD(command, 5);
        int cmd = command.getValue();
        Vehicle vehicle = getVehicle(cmd);
        vehicles.add(vehicle);
    }

    private Vehicle getVehicle(int number) throws InputMismatchException {
        setCurrentVehicleName(number);
        try {
            String brand = getBrand();
            String model = getModel();
            int maxCargoWeight = getMaxCargoWeight();
            int numPassengers = -1;
            if (
                currentVehicle.equals(Name.MOTORCYCLE)
                || currentVehicle.equals(Name.CAR)
                || currentVehicle.equals(Name.BUS)
            ) { numPassengers = getNumPassengers(); }
            return VehicleFactory.create(currentVehicle, brand, model, maxCargoWeight, numPassengers);
        }
        catch (InvalidNumPassengerExceptions | InvalidCargoWeightExceptions | InvalidModelExceptions | InvalidBrandExceptions e) {
            throw new InputMismatchException(e.getClass() + e.getMessage());
        }
    }

    private int getMaxCargoWeight() {
        int result = -1;
        switch (currentVehicle) {
            case MOTORCYCLE -> result = getMaxCargoWeight(100);
            case CAR        -> result = getMaxCargoWeight(1000);
            case TRUCK, BUS -> result = getMaxCargoWeight(10000);
            case TRAILER    -> result = getMaxCargoWeight(100000);
        }
        return result;
    }

    private int getNumPassengers() {
        int result = -1;
        switch (currentVehicle) {
            case MOTORCYCLE -> result = getNumPassengers(0, 2);
            case CAR        -> result = getNumPassengers(1, 8);
            case BUS        -> result = getNumPassengers(8, 150);
        }
        return result;
    }
}