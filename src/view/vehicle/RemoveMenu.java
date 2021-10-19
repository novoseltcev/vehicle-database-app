package view.vehicle;

//  TODO  - add vehicle menu
public class RemoveMenu extends VehicleMenu {
    public RemoveMenu() {
        title += "\n| ";
        title += langData.getProperty("REMOVE_VEHICLE_CMD");
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
