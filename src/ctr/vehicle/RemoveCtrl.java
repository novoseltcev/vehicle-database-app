package ctr.vehicle;

import utils.Command;
import view.vehicle.RemoveMenu;

import java.util.InputMismatchException;


public class RemoveCtrl extends VehicleCtrl {
    public RemoveCtrl(RemoveMenu menu) {
        super(menu);
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException, InputMismatchException {
        super.chooseCMD(command, vehicles.size());
        int index = command.getValue();
        menu.showVehicle(index, vehicles.get(index - 1));
        vehicles.remove(index - 1);
    }
}
