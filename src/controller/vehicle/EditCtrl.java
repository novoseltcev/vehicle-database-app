package controller.vehicle;

import utils.Command;
import view.vehicle.EditMenu;

import java.util.InputMismatchException;


public class EditCtrl extends VehicleCtrl {
    public EditCtrl(EditMenu menu) {
        super(menu);
    }

    @Override
    protected void call(Command command) throws InterruptedException, InputMismatchException {
        super.call(command, vehicles.size());
        int index = command.getValue();
        // TODO
    }
}
