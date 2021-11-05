package controller;

import controller.vehicle.AddCtrl;
import controller.vehicle.EditCtrl;
import controller.vehicle.RemoveCtrl;
import controller.vehicle.VehicleCtrl;
import repository.VehicleRepository;
import service.VehicleService;
import utils.Command;
import view.BaseMenu;
import view.vehicle.AddMenu;
import view.vehicle.EditMenu;
import view.vehicle.RemoveMenu;
import view.vehicle.VehicleMenu;

public class DBCtrl extends VehicleCtrl {
    private static final String SAVE_PATH = "vehicles.db";

    public DBCtrl(BaseMenu menu) {
        super((VehicleMenu) menu);
        repository = new VehicleRepository(SAVE_PATH);
    }

    @Override
    protected void call(Command command) throws Exception {
        super.call(command, 5);
        int cmd = command.getValue();
        switch (cmd) {
            case (2), (4), (5) -> show();
        }
        switch (cmd) {
            case (1) -> VehicleService.save(SAVE_PATH, repository.readAll());
            case (3) -> runSubController(AddCtrl.class, AddMenu.class);
            case (4) -> runSubController(EditCtrl.class, EditMenu.class);
            case (5) -> runSubController(RemoveCtrl.class, RemoveMenu.class);
//            default -> {
//                logger.log(Level.WARNING, "Invalid command");
//                menu.errorCommand(String.valueOf(cmd));
//            }
        }
    }

    private void show() {
        menu.showVehicles(repository.readAll());
    }
}
