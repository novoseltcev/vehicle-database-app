package ctr.vehicle;

import utils.Command;
import view.vehicle.RemoveMenu;

public class RemoveCtrl extends VehicleCtrl {
    public RemoveCtrl(RemoveMenu menu) {
        super(menu);
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        int index = super.chooseVehicle(command);
        ((RemoveMenu)menu).showVehicle(index, vehicles.get(index - 1));
        vehicles.remove(index - 1);
    }
}
