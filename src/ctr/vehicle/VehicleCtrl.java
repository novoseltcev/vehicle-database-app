package ctr.vehicle;

import ctr.BaseCtrl;
import model.vehicle.Name;
import model.vehicle.Vehicle;
import utils.Command;
import view.vehicle.VehicleMenu;

import java.util.InputMismatchException;
import java.util.List;

public abstract class VehicleCtrl extends BaseCtrl {
    protected static List<Vehicle> vehicles;
    protected Name currentVehicle;
    protected VehicleMenu menu;

    public VehicleCtrl(VehicleMenu menu) {
        super(menu);
        this.menu = menu;
    }

    public void setCurrentVehicleName(int number) {
        switch (number) {
            case 1 -> currentVehicle = Name.MOTORCYCLE;
            case 2 -> currentVehicle = Name.CAR;
            case 3 -> currentVehicle = Name.TRUCK;
            case 4 -> currentVehicle = Name.BUS;
            case 5 -> currentVehicle = Name.TRAILER;
        }
    }

    private String getString() {  // TODO
        while (true) {
            String buffer = scanner.nextLine().toLowerCase();
            if (!buffer.isEmpty()) {
                return buffer;
            }
//            throw new InputMismatchException();
        }
    }

    private int getInt(int downBoundary, int upBoundary) {  // TODO
        while (true) {
            String buffer = scanner.nextLine().toLowerCase();
            try {
                int result = Integer.parseInt(buffer);
                if (downBoundary <= result && result <= upBoundary) {
                    return result;
                }
//                menu.invalidInt(buffer, downBoundary, upBoundary);
            } catch (NumberFormatException e) {
//                menu.invalidInt(buffer, downBoundary, upBoundary);
            }
        }
    }

    protected String getBrand() {  // TODO
        menu.enterBrand(currentVehicle.name());
        return getString();
    }

    protected String getModel() {  // TODO
        menu.enterModel(currentVehicle.name());
        return getString();
    }

    protected int getMaxCargoWeight(int upBoundary) {  // TODO
        menu.enterMaxCargoWeight(currentVehicle.name());
        return getInt(0, upBoundary);
    }

    protected int getNumPassengers(int downBoundary, int upBoundary) {
        menu.enterNumPassengers(currentVehicle.name());
        return getInt(downBoundary, upBoundary);
    }


}
