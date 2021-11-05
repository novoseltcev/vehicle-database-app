package controller.vehicle;

import model.vehicle.Name;
import model.vehicle.Vehicle;
import model.vehicle.VehicleFactory;
import model.vehicle.exception.*;
import utils.Command;
import view.BaseMenu;

import java.util.InputMismatchException;


public class AddCtrl extends VehicleCtrl {
    public AddCtrl(BaseMenu menu) throws Exception {
        super(menu);
    }

    @Override
    protected void call(Command command) throws InputMismatchException, InterruptedException {
        super.call(command, 5);
        Vehicle vehicle = getVehicle(command.getValue());
        System.out.println(vehicle);
        repository.create(vehicle);
    }

    @Deprecated
    private Vehicle getVehicle(int number) throws InputMismatchException {
        Name name = getCurrentVehicleName(number);

        String brand = getBrand(name);
        String model = getModel(name);
        int maxCargoWeight = getMaxCargoWeight(name);
        int numPassengers = -1;
        if (
                name.equals(Name.MOTORCYCLE)
             || name.equals(Name.CAR)
             || name.equals(Name.BUS)
        ) {
            numPassengers = getNumPassengers(name);
        }
        try {
            return VehicleFactory.create(name, brand, model, maxCargoWeight, numPassengers);
        } catch (InvalidNumPassengerExceptions | InvalidCargoWeightExceptions | InvalidModelExceptions | InvalidBrandExceptions e) {
            throw new InputMismatchException(e.getClass() + e.getMessage());
        }
    }
}