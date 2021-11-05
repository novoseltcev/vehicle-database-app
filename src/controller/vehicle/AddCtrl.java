package controller.vehicle;

import model.vehicle.*;
import model.vehicle.exception.*;
import utils.Command;
import view.BaseMenu;
import view.vehicle.AddMenu;

import java.util.InputMismatchException;

public class AddCtrl extends VehicleCtrl {
    public AddCtrl(BaseMenu menu) {
        super((AddMenu)menu);
    }

    @Override
    protected void call(Command command) throws InputMismatchException, InterruptedException {
        super.call(command, 5);
        Vehicle vehicle = getVehicle(command.getValue());
        repository.create(vehicle);
    }

    @Deprecated
    private Vehicle getVehicle(int number) throws InputMismatchException {
        setCurrentVehicleName(number);
        String brand = getBrand();
        String model = getModel();
        int maxCargoWeight = getMaxCargoWeight();
        int numPassengers = -1;
        if (
                name.equals(Name.MOTORCYCLE)
             || name.equals(Name.CAR)
             || name.equals(Name.BUS)
        ) {
            numPassengers = getNumPassengers();
        }

        try {
            return VehicleFactory.create(name, brand, model, maxCargoWeight, numPassengers);
        }
        catch (InvalidNumPassengerExceptions | InvalidCargoWeightExceptions | InvalidModelExceptions | InvalidBrandExceptions e) {
            throw new InputMismatchException(e.getClass() + e.getMessage());
        }
    }

    @Deprecated
    private int getMaxCargoWeight() {
        int result = -1;
        switch (name) {
            case MOTORCYCLE -> result = getMaxCargoWeight(100);
            case CAR        -> result = getMaxCargoWeight(1000);
            case TRUCK, BUS -> result = getMaxCargoWeight(10000);
            case TRAILER    -> result = getMaxCargoWeight(100000);
        }
        return result;
    }

    @Deprecated
    private int getNumPassengers() {
        int result = -1;
        switch (name) {
            case MOTORCYCLE -> result = getNumPassengers(0, 2);
            case CAR        -> result = getNumPassengers(1, 8);
            case BUS        -> result = getNumPassengers(8, 150);
        }
        return result;
    }
}