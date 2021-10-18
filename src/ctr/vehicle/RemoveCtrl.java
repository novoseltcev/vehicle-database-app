package ctr.vehicle;

import utils.Command;
import view.BaseMenu;
import view.vehicle.RemoveMenu;

//  TODO  - remove vehicle controller
public class RemoveCtrl extends VehicleCtrl {
    public RemoveCtrl(BaseMenu menu) {
        super(menu);
    }

    @Override
    public void run() throws InterruptedException {
        ((RemoveMenu)menu).show();
        super.run();
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        super.chooseCMD(command);
        int cmd = command.getValue();

//        switch (cmd) {
//
//            default:
//                menu.errorCommand(String.valueOf(cmd));
//        }
    }
}
