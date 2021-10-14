package ctr;

import model.vehicle.Vehicle;
import view.BaseMenu;
import view.DBMenu;

import java.util.List;

public class DBCtrl extends BaseCtrl{
    protected List<Vehicle> vehicles;
    DBCtrl(BaseMenu menu, List<Vehicle> vehicles) {
        super(menu);
        this.vehicles = vehicles;
    }

    public void run() throws InterruptedException {
        ((DBMenu)menu).show();
        int cmd = enterInt();
        chooseCMD(cmd);
    }

    private void chooseCMD(int command) throws InterruptedException{
        switch (command) {
            case (0):
                throw new InterruptedException("0");

            case (1):
                ((DBMenu)menu).showVehicles(vehicles);
                break;

            case (2):
                //addVehicle();  // TODO
                break;

            case (3):
                //editVehicle();  // TODO
                break;

            case (4):
                //removeVehicle();  // TODO
                break;

            case (5):
                //saveVehicles();  // TODO
                break;

            default:
                menu.errorCommand(String.valueOf(command));
        }
    }

}
