package view.vehicle;

import model.vehicle.Name;

import java.util.ArrayList;
import java.util.List;

public class AddMenu extends VehicleMenu {

    public void show() {
        showTitle();
        List<String> msgList =new ArrayList<>();
        msgList.add(langData.getProperty("BACK_CMD"));
        for (Name val: Name.values()){
            if (!val.name().equals("VEHICLE"))
                msgList.add("\t%d\t-\t" + val.name());
        }
        for (int i = 0; i <msgList.size(); ++i) {
            display_ln(String.format(msgList.get(i), i));
        } display(langData.getProperty("ENTER_CMD"));
    }
}
