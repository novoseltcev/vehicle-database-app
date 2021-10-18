package ctr.vehicle;

import ctr.BaseCtrl;
import model.vehicle.Vehicle;
import view.BaseMenu;
import view.vehicle.VehicleMenu;

import java.util.List;

public class VehicleCtrl extends BaseCtrl {
    protected static List<Vehicle> vehicles;
    protected String currentVehicleName;
    public VehicleCtrl(BaseMenu menu) {
        super(menu);
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
