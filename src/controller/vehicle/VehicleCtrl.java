package controller.vehicle;

import controller.BaseCtrl;
import model.vehicle.Name;
import repository.VehicleRepository;
import view.BaseMenu;
import view.vehicle.VehicleMenu;

public abstract class VehicleCtrl extends BaseCtrl {
    protected static VehicleRepository repository;
    protected VehicleMenu menu;

    public VehicleCtrl(BaseMenu menu) throws Exception {
        super(menu);
        this.menu = (VehicleMenu)menu;
    }

    public Name getCurrentVehicleName(int number) {
        Name name = null;
        switch (number) {
            case 1 -> name = Name.MOTORCYCLE;
            case 2 -> name = Name.CAR;
            case 3 -> name = Name.TRUCK;
            case 4 -> name = Name.BUS;
            case 5 -> name = Name.TRAILER;
        }
        return name;
    }

    protected String getBrand(Name name) {
        menu.enterBrand(name.name());
        return getLowerString();
    }

    protected String getModel(Name name) {
        menu.enterModel(name.name());
        return getLowerString();
    }

    protected int getMaxCargoWeight(Name name) {
        menu.enterMaxCargoWeight(name.name());
        return getInt();
    }

    protected int getNumPassengers(Name name) {
        menu.enterNumPassengers(name.name());
        return getInt();
    }
}
