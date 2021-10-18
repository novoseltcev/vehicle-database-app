package ctr.vehicle;

import ctr.BaseCtrl;
import utils.Command;
import view.BaseMenu;
import view.vehicle.EditMenu;

//  TODO  - edit vehicle controller
public class EditCtrl extends BaseCtrl {
    public EditCtrl(BaseMenu menu) {
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
        int cmd = command.getValue();

//        switch (cmd) {
//
//            default:
//                menu.errorCommand(String.valueOf(cmd));
//        }
    }
}
