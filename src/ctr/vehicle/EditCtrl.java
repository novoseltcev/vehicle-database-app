package ctr.vehicle;

import ctr.BaseCtrl;
import utils.Command;
import view.vehicle.EditMenu;
import view.vehicle.VehicleMenu;

//  TODO  - edit vehicle controller
public class EditCtrl extends VehicleCtrl {
    public EditCtrl(VehicleMenu menu) {
        super(menu);
    }

    @Override
    public void run() throws InterruptedException {
        ((EditMenu)menu).show();
        super.run();
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        super.chooseCMD(command);
        int index = command.getValue();



    }
}
