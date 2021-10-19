package view.vehicle;

//  TODO  - edit vehicle menu
public class EditMenu extends VehicleMenu {
    public EditMenu() {
        title += "\n| ";
        title += langData.getProperty("EDIT_VEHICLE_CMD");
    }

    public void show() {
        showTitle();
        String[] msgList =new String[] {
            langData.getProperty("BACK_CMD"),
        };

        for (int i = 0; i <msgList.length; ++i) {
            display_ln(String.format(msgList[i], i));
        }
    }
}
