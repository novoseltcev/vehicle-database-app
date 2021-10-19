package ctr.vehicle;

import ctr.BaseCtrl;
import model.vehicle.Vehicle;
import utils.Command;
import view.vehicle.VehicleMenu;

import java.util.List;

public abstract class VehicleCtrl extends BaseCtrl {
    protected static List<Vehicle> vehicles;
    protected String currentVehicleName;

    public VehicleCtrl(VehicleMenu menu) {
        super(menu);
    }

    @Override
    protected void chooseCMD(Command cmd) throws InterruptedException {
        super.chooseCMD(cmd);
        int command = cmd.getValue();
        if (command < 1 || vehicles.size() < command) {
            ((VehicleMenu)menu).invalidInt(String.valueOf(command), 1, vehicles.size());
        }
    }

    public void setCurrentVehicleName(String name) {
        currentVehicleName = name;
    }

    private String getString() {
        while (true) {
            String buffer = scanner.nextLine().toLowerCase();
            if (!buffer.isEmpty()) {
                return buffer;
            } ((VehicleMenu)menu).invalidName(buffer);
        }
    }

    private int getInt(int downBoundary, int upBoundary) {
        while (true) {
            String buffer = scanner.nextLine().toLowerCase();
            try {
                int result = Integer.parseInt(buffer);
                if (downBoundary <= result && result <= upBoundary) {
                    return result;
                } ((VehicleMenu)menu).invalidInt(buffer, downBoundary, upBoundary);
            } catch (NumberFormatException e) {
                ((VehicleMenu)menu).invalidInt(buffer, downBoundary, upBoundary);
            }
        }
    }

    protected String getBrand() {
        ((VehicleMenu)menu).enterBrand(currentVehicleName);
        return getString();
    }

    protected String getModel() {
        ((VehicleMenu)menu).enterModel(currentVehicleName);
        return getString();
    }

    protected int getMaxCargoWeight(int downBoundary, int upBoundary) {
        ((VehicleMenu)menu).enterMaxCargoWeight(currentVehicleName);
        return getInt(downBoundary, upBoundary);
    }

    protected int getNumPassengers(int downBoundary, int upBoundary) {
        ((VehicleMenu)menu).enterNumPassengers(currentVehicleName);
        return getInt(downBoundary, upBoundary);
    }


}
