package ctr;

import ctr.vehicle.*;
import model.vehicle.Vehicle;
import utils.Command;
import view.BaseMenu;
import view.CrashNotifier;
import view.DBMenu;
import view.vehicle.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DBCtrl extends VehicleCtrl{
    private static final String SAVE_PATH = "vehicles.db";
    DBCtrl(VehicleMenu menu) {
        super(menu);
        load();
    }

    @Override
    public void run() throws InterruptedException {
        ((DBMenu)menu).show();
        super.run();
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        new BaseCtrl(menu).chooseCMD(command);
        int cmd = command.getValue();

        switch (cmd) {
            case (1):
                save();
                break;

            case (2):
                show();
                break;

            case (3):
                add();
                break;

            case (4):
                show();
                edit();
                break;

            case (5):
                show();
                remove();
                break;

            default:
                menu.errorCommand(String.valueOf(cmd));
        }
    }


    private void load() {
        try {
            loadVehicle(SAVE_PATH);
            ((DBMenu) menu).load();
        } catch (Exception e) {
            vehicles = new ArrayList<>();
            CrashNotifier crasher = new CrashNotifier();
            crasher.handler(e);
        }
    }

    private void loadVehicle(String path) throws IOException {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            vehicles = (List<Vehicle>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new IOException(path);
        } catch (ClassNotFoundException e) {
            throw new IOException("invalid data in " + path);
        }
    }

    private void show() {
        ((DBMenu)menu).showVehicles(vehicles);
    }

    private void add() {
        AddMenu vehicleMenu = new AddMenu();
        AddCtrl vehicleCtrl = new AddCtrl(vehicleMenu);
        try {
            while (true) {
                vehicleCtrl.run();
            }
        } catch (InterruptedException ignored) {}
    }

    private void edit() {
        EditMenu editVehicleMenu = new EditMenu();
        EditCtrl editVehicleCtrl = new EditCtrl(editVehicleMenu);
        try {
            while (true) {
                editVehicleCtrl.run();
            }
        } catch (InterruptedException ignored) {}
    }

    private void remove() {
        RemoveMenu removeVehicleMenu = new RemoveMenu();
        RemoveCtrl removeVehicleCtrl = new RemoveCtrl(removeVehicleMenu);
        try {
            while (true) {
                removeVehicleCtrl.run();
            }
        } catch (InterruptedException ignored) {}
    }
    private void save() {
        try {
            saveVehicle(SAVE_PATH);
            ((DBMenu) menu).save();
        } catch (IOException e) {
            CrashNotifier crashNotifier = new CrashNotifier();
            crashNotifier.handler(e);
        }
    }

    private void saveVehicle(String path) throws IOException {
        File file = new File(path);
        try {
            //noinspection ResultOfMethodCallIgnored
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(vehicles);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new IOException("Failed saving file: " + path);
        }
    }


}
