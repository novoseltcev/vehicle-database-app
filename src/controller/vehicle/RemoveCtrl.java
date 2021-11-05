package controller.vehicle;

import model.vehicle.Vehicle;
import utils.Command;
import view.BaseMenu;
import view.vehicle.RemoveMenu;

import java.util.InputMismatchException;
import java.util.Optional;


public class RemoveCtrl extends VehicleCtrl {
    public RemoveCtrl(BaseMenu menu) {
        super((RemoveMenu)menu);
    }

    @Override
    protected void call(Command command) throws InterruptedException, InputMismatchException {
        super.call(command, repository.size());
        int index = command.getValue() - 1;
        Optional<Vehicle> optionalVehicle = repository.read(index);
        if (optionalVehicle.isEmpty()) {
            throw new InputMismatchException();
        }
        menu.showVehicle(index, optionalVehicle.get());
        if (!repository.delete(index)) {
            throw new InterruptedException();
        }
    }
}
