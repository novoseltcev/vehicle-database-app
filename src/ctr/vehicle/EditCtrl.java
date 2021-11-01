package ctr.vehicle;

import utils.Command;
import view.vehicle.EditMenu;

//  TODO  - edit vehicle controller
public class EditCtrl extends VehicleCtrl {
    public EditCtrl(EditMenu menu) {
        super(menu);
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        int index = super.chooseVehicle(command);
    }
}
