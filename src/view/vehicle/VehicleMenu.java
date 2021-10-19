package view.vehicle;

import model.vehicle.Vehicle;
import view.BaseMenu;

import java.util.List;

public abstract class VehicleMenu extends BaseMenu {
    public void invalidInt(String buffer, int downBoundary, int upBoundary) {
        String msg = String.format(langData.getProperty("INVALID_INT"), buffer, downBoundary, upBoundary);
        error(msg);
    }
    public void invalidName(String buffer) {
        String msg = String.format(langData.getProperty("INVALID_NAME"), buffer);
        error(msg);
    }

    public void enterBrand(String name) {
        String msg = String.format(langData.getProperty("ENTER_BRAND"), name);
        display(msg);
    }

    public void enterModel(String name) {
        String msg = String.format(langData.getProperty("ENTER_MODEL"), name);
        display(msg);
    }

    public void enterMaxCargoWeight(String name) {
        String msg = String.format(langData.getProperty("ENTER_WEIGHT"), name);
        display(msg);
    }

    public void enterNumPassengers(String name) {
        String msg = String.format(langData.getProperty("ENTER_PASSENGERS"), name);
        display(msg);
    }

    public void showVehicles(List<Vehicle> vehicles) {
        display_ln("\n" + langData.getProperty("VEHICLES_TITLE"));
        if (vehicles.size() < 1) {
            display_ln(langData.getProperty("NO_DATA"));
        } else {
            display_ln("|-------------------------------------------------------------------------------------------------");
            int counter = 1;
            for (Vehicle vehicle: vehicles) {
                display("|\t") ;
                showVehicle(counter++, vehicle);
                display_ln("|-----------|-------------------------------------------------------------------------------------");
            }
        }
    }

    public void showVehicle(int index, Vehicle vehicle) {
        display_ln(index + "\t|\t" + vehicle.toString());
    }
}
