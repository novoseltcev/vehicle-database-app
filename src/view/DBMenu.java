package view;

import view.vehicle.VehicleMenu;

public class DBMenu extends VehicleMenu {
    public void show() {
        showTitle();
        String[] msgList =new String[] {
                langData.getProperty("BACK_CMD"),
                langData.getProperty("SAVE_DB_CMD") + "\n",
                langData.getProperty("SHOW_DB_CMD"),
                langData.getProperty("ADD_DB_CMD"),
                langData.getProperty("EDIT_DB_CMD"),
                langData.getProperty("REMOVE_DB_CMD"),
        };

        for (int i = 0; i < msgList.length; ++i)
            display_ln(String.format(msgList[i], i));
        showSeparator();
        row(langData.getProperty("ENTER_CMD"));
    }



    public void save() {
        success(langData.getProperty("SUCCESSFUL_SAVE"));
    }

    public void load() {
        success(langData.getProperty("SUCCESSFUL_LOAD"));
    }

    public void create() {
        error(langData.getProperty("UNSUCCESSFUL_LOAD"));
    }
}
