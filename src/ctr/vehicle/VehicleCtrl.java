package ctr.vehicle;

import ctr.BaseCtrl;
import model.vehicle.Name;
import model.vehicle.Vehicle;
import utils.Command;
import view.vehicle.VehicleMenu;

import java.util.List;

public abstract class VehicleCtrl extends BaseCtrl {
    protected static List<Vehicle> vehicles;
    protected Name currentVehicle;
    protected VehicleMenu menu;

    public VehicleCtrl(VehicleMenu menu) {
        super(menu);
    }

    protected int chooseVehicle(Command command) throws InterruptedException {
        int cmd = command.getValue();
        if (0 <= cmd && cmd <= vehicles.size()) {
            if (command.getValue() == 0) {
                throw new InterruptedException("0");
            }
            return cmd;
        }
        menu.invalidInt(String.valueOf(command), 1, vehicles.size());
        return -1;
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

    private String getString() {
        while (true) {
            String buffer = scanner.nextLine().toLowerCase();
            if (!buffer.isEmpty()) {
                return buffer;
            } menu.invalidName(buffer);
        }
    }

    private int getInt(int downBoundary, int upBoundary) {
        while (true) {
            String buffer = scanner.nextLine().toLowerCase();
            try {
                int result = Integer.parseInt(buffer);
                if (downBoundary <= result && result <= upBoundary) {
                    return result;
                }
                menu.invalidInt(buffer, downBoundary, upBoundary);
            } catch (NumberFormatException e) {
                menu.invalidInt(buffer, downBoundary, upBoundary);
            }
        }
    }

    protected String getBrand() {
        menu.enterBrand(currentVehicle.name());
        return getString();
    }

    protected String getModel() {
        menu.enterModel(currentVehicle.name());
        return getString();
    }

    protected int getMaxCargoWeight(int upBoundary) {
        menu.enterMaxCargoWeight(currentVehicle.name());
        return getInt(0, upBoundary);
    }

    protected int getNumPassengers(int downBoundary, int upBoundary) {
        menu.enterNumPassengers(currentVehicle.name());
        return getInt(downBoundary, upBoundary);
    }


}
