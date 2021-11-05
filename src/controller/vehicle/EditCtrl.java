package controller.vehicle;

import model.vehicle.Vehicle;
import utils.Command;
import view.BaseMenu;
import view.vehicle.EditMenu;

import java.util.InputMismatchException;
import java.util.Optional;


public class EditCtrl extends VehicleCtrl {
    public EditCtrl(BaseMenu menu) {
        super((EditMenu)menu);
    }

    @Override
    protected void call(Command command) throws InterruptedException, InputMismatchException {
        super.call(command, repository.size());
        int index = command.getValue() - 1;
        Optional<Vehicle> optionalVehicle = repository.read(index);
        if (optionalVehicle.isEmpty()) {
            throw new InputMismatchException();
        }
        Vehicle vehicle = optionalVehicle.get();
        edit(vehicle);
        menu.showVehicle(index, vehicle);
        if (!repository.update(index, vehicle)) {
            throw new InterruptedException();
        }
    }

    private void edit(Vehicle vehicle) {}  // TODO
}
