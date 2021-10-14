package view;

import model.vehicle.Vehicle;

import java.util.List;

public class DBMenu extends BaseMenu{
    public void show() {
//        clearStack();
        String[] msgList =new String[] {
                langData.getProperty("BACK_CMD"),
                langData.getProperty("SHOW_CMD"),
                langData.getProperty("ADD_CMD"),
                langData.getProperty("EDIT_CMD"),
                langData.getProperty("REMOVE_CMD"),
                langData.getProperty("SAVE_CMD"),
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
            return;
        }
        for (Vehicle vehicle: vehicles) {
            display_ln(vehicle.toString());
        }
    }

}
