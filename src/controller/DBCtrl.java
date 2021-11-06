package controller;

import controller.vehicle.AddCtrl;
import controller.vehicle.EditCtrl;
import controller.vehicle.RemoveCtrl;
import controller.vehicle.VehicleCtrl;
import repository.VehicleRepository;
import utils.Command;
import view.BaseMenu;
import view.DBMenu;
import view.vehicle.AddMenu;
import view.vehicle.EditMenu;
import view.vehicle.RemoveMenu;

import java.io.IOException;

public class DBCtrl extends VehicleCtrl {
    public DBCtrl(BaseMenu menu) throws Exception {
        super(menu);
        logger.finer("Create new VehicleRepository instance from \"vehicles.db\"");
        repository = new VehicleRepository("vehicles.db");
        try {
            logger.info("Loading data...");
            repository.load();
            logger.fine("Successfully loaded data");
            ((DBMenu)menu).load();
        } catch (IOException | ClassNotFoundException e) {
            logger.warning("Load corrupt. Creating new data.");
            repository.recreate();
            logger.info("Successfully create new data");
            ((DBMenu)menu).create();
        }
    }

    @Override
    protected void call(Command command) throws Exception {
        super.call(command, 5);
        int cmd = command.getValue();
        switch (cmd) {
            case (2), (4), (5) -> menu.showVehicles(repository.readAll());
        }
        switch (cmd) {
            case (1) -> {
                repository.save();
                ((DBMenu)menu).save();
            }
            case (3) -> runSubController(AddCtrl.class, AddMenu.class);
            case (4) -> runSubController(EditCtrl.class, EditMenu.class);
            case (5) -> runSubController(RemoveCtrl.class, RemoveMenu.class);
//            default -> {
//                logger.log(Level.WARNING, "Invalid command");
//                menu.errorCommand(String.valueOf(cmd));
//            }
        }
    }
}
