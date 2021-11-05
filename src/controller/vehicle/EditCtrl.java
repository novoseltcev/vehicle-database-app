package controller.vehicle;

import model.vehicle.Vehicle;
import utils.Command;
import view.BaseMenu;

import java.util.InputMismatchException;


public class EditCtrl extends VehicleCtrl {
    public EditCtrl(BaseMenu menu) throws Exception {
        super(menu);
    }

    @Override
    protected void call(Command command) throws InterruptedException, IndexOutOfBoundsException, InputMismatchException {
        super.call(command, repository.size());
        int index = command.getValue() - 1;

        Vehicle vehicle = repository.read(index);
        menu.showVehicle(index, vehicle);
        edit(vehicle);
        menu.showVehicle(index, vehicle);
        repository.update(index, vehicle);
    }

    private void edit(Vehicle vehicle) {}  // TODO
}
