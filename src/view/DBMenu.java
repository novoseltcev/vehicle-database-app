package view;

import model.vehicle.Vehicle;

import java.util.List;

public class DBMenu extends BaseMenu{
    public void show() {
//        clearStack();
        String[] msgList =new String[] {
                langData.getProperty("BACK_CMD"),
                langData.getProperty("SHOW_DB_CMD"),
                langData.getProperty("ADD_DB_CMD"),
                langData.getProperty("EDIT_DB_CMD"),
                langData.getProperty("REMOVE_DB_CMD"),
                langData.getProperty("SAVE_DB_CMD"),
        };
        display_ln("\n---------------------------");
        display_ln("\t\t" + title);
        display_ln("---------------------------");
        for (int i = 0; i <msgList.length; ++i)
            display_ln(String.format(msgList[i], i));

        display(langData.getProperty("ENTER_CMD"));
    }

    public void showVehicles(List<Vehicle> vehicles) {
        display_ln(langData.getProperty("VEHICLES_TITLE"));
        if (vehicles.size() < 1) {
            display_ln(langData.getProperty("NO_DATA"));
        } else {
            for (Vehicle vehicle: vehicles)
                display_ln(vehicle.toString());
        }
    }

    public void save() {
        display_ln(langData.getProperty("SUCCESSFUL_SAVE"));
    }

    public void load() {
        display_ln(langData.getProperty("SUCCESSFUL_LOAD"));
    }
}
