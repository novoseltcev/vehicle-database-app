package view.vehicle;

import view.BaseMenu;

//  TODO  - edit vehicle menu
public class EditMenu extends VehicleMenu {

    public void show() {
        showTitle();
        String[] msgList =new String[] {
                langData.getProperty("BACK_CMD"),
        };

        for (int i = 0; i <msgList.length; ++i) {
            display_ln(String.format(msgList[i], i));
        } display(langData.getProperty("ENTER_CMD"));
    }
}
