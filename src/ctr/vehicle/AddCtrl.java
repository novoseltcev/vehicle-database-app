package ctr.vehicle;

import model.vehicle.*;
import model.vehicle.exception.*;
import utils.Command;
import view.CrashNotifier;
import view.vehicle.AddMenu;
import view.vehicle.VehicleMenu;

public class AddCtrl extends VehicleCtrl {
    public AddCtrl(VehicleMenu menu) {
        super(menu);
    }

    @Override
    public void run() throws InterruptedException {
        ((AddMenu) menu).show();
        super.run();
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        if (command.getValue() == 0) {
            throw new InterruptedException("0");
        }
        int cmd = command.getValue();
        if (cmd < 1 || 5 < cmd) {
            menu.errorCommand(String.valueOf(cmd));
            return;
        }
        Vehicle vehicle = getVehicle(cmd);
        if (vehicle != null) {
            vehicles.add(vehicle);
        }
    }

    private Vehicle getVehicle(int number) {
        String brand;
        String model;
        int maxCargoWeight;
        int numPassengers;
        try {

            switch (number) {
                case (1):
                    setCurrentVehicleName(Name.MOTORCYCLE.name());
                    brand = getBrand();
                    model = getModel();
                    maxCargoWeight = getMaxCargoWeight(0, 100);
                    numPassengers = getNumPassengers(0, 2);
                    return new Motorcycle(brand, model, maxCargoWeight, numPassengers);

                case (2):
                    setCurrentVehicleName(Name.CAR.name());
                    brand = getBrand();
                    model = getModel();
                    maxCargoWeight = getMaxCargoWeight(0, 1000);
                    numPassengers = getNumPassengers(1, 8);
                    return new Car(brand, model, maxCargoWeight, numPassengers);

                case (3):
                    setCurrentVehicleName(Name.TRUCK.name());
                    brand = getBrand();
                    model = getModel();
                    maxCargoWeight = getMaxCargoWeight(0, 10000);
                    return new Truck(brand, model, maxCargoWeight);

                case (4):
                    setCurrentVehicleName(Name.BUS.name());
                    brand = getBrand();
                    model = getModel();
                    maxCargoWeight = getMaxCargoWeight(0, 10000);
                    numPassengers = getNumPassengers(8, 150);
                    return new Bus(brand, model, maxCargoWeight, numPassengers);

                case (5):
                    setCurrentVehicleName(Name.TRAILER.name());
                    brand = getBrand();
                    model = getModel();
                    maxCargoWeight = getMaxCargoWeight(0, 100000);
                    return new Trailer(brand, model, maxCargoWeight);
            }
        } catch (InvalidNumPassengerExceptions | InvalidMaxCargoWeightExceptions | InvalidModelExceptions | InvalidBrandExceptions e) {
            CrashNotifier crasher = new CrashNotifier();
            crasher.handler(e);
        }
        return null;
    }
}