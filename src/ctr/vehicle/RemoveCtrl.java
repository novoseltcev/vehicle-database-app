package ctr.vehicle;

import utils.Command;
import view.vehicle.RemoveMenu;
import view.vehicle.VehicleMenu;

//  TODO  - remove vehicle controller
public class RemoveCtrl extends VehicleCtrl {
    public RemoveCtrl(VehicleMenu menu) {
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
        int index = command.getValue();
        ((RemoveMenu)menu).showVehicle(index, vehicles.get(index));
        vehicles.remove(index - 1);
    }
}
