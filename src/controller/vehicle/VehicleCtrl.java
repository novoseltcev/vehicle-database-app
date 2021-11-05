package controller.vehicle;

import controller.BaseCtrl;
import model.vehicle.Name;
import repository.VehicleRepository;
import view.vehicle.VehicleMenu;

public abstract class VehicleCtrl extends BaseCtrl {
    protected static VehicleRepository repository;
    protected Name name;
    protected VehicleMenu menu;

    public VehicleCtrl(VehicleMenu menu) {
        super(menu);
        this.menu = menu;
    }

    public void setCurrentVehicleName(int number) {
        switch (number) {
            case 1 -> name = Name.MOTORCYCLE;
            case 2 -> name = Name.CAR;
            case 3 -> name = Name.TRUCK;
            case 4 -> name = Name.BUS;
            case 5 -> name = Name.TRAILER;
        }
    }

    private String getString() {  // TODO
        while (true) {
            String buffer = scanner.nextLine().toLowerCase();
            if (!buffer.isEmpty()) {
                return buffer;
            }
//            throw new InputMismatchException();
        }
    }

    private int getInt(int downBoundary, int upBoundary) {  // TODO
        while (true) {
            String buffer = scanner.nextLine().toLowerCase();
            try {
                int result = Integer.parseInt(buffer);
                if (downBoundary <= result && result <= upBoundary) {
                    return result;
                }
//                menu.invalidInt(buffer, downBoundary, upBoundary);
            } catch (NumberFormatException e) {
//                menu.invalidInt(buffer, downBoundary, upBoundary);
            }
        }
    }

    protected String getBrand() {  // TODO
        menu.enterBrand(name.name());
        return getString();
    }

    protected String getModel() {  // TODO
        menu.enterModel(name.name());
        return getString();
    }

    protected int getMaxCargoWeight(int upBoundary) {  // TODO
        menu.enterMaxCargoWeight(name.name());
        return getInt(0, upBoundary);
    }

    protected int getNumPassengers(int downBoundary, int upBoundary) {
        menu.enterNumPassengers(name.name());
        return getInt(downBoundary, upBoundary);
    }


}
