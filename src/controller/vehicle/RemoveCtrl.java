package controller.vehicle;

import model.vehicle.Vehicle;
import utils.Command;
import view.BaseMenu;


public class RemoveCtrl extends VehicleCtrl {
    public RemoveCtrl(BaseMenu menu) throws Exception {
        super(menu);
    }

    @Override
    protected void call(Command command) throws InterruptedException, IndexOutOfBoundsException {
        super.call(command, repository.size());
        int index = command.getValue() - 1;
        Vehicle vehicle = repository.read(index);
        menu.showVehicle(index, vehicle);
        repository.delete(index);
    }
}
