package view.vehicle;

import view.BaseMenu;

public class VehicleMenu extends BaseMenu {

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
}
