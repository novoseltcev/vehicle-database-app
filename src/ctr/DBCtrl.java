package ctr;

import ctr.vehicle.*;
import model.vehicle.Vehicle;
import utils.Command;
import view.CrashNotifier;
import view.DBMenu;
import view.vehicle.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

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
            	logger.log(Level.WARNING, "Invalid command");
                menu.errorCommand(String.valueOf(cmd));
        }
    }


    private void load() {
        try {
        	logger.log(Level.INFO, "Reading DataBase from: " + SAVE_PATH );
            loadVehicle(SAVE_PATH);
            ((DBMenu) menu).load();
            logger.log(Level.INFO, "Succesful readed DataBase from: " + SAVE_PATH );
        } catch (Exception e) {
        	logger.log(Level.SEVERE, "Error reading DataBase from: " + SAVE_PATH );
            vehicles = new ArrayList<>();
            CrashNotifier crasher = new CrashNotifier();
            crasher.handler(e);
        }
    }

    private void loadVehicle(String path) throws IOException {
    	ObjectInputStream objectInputStream = null;
        try {
            FileInputStream inputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(inputStream);
            vehicles = (List<Vehicle>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new IOException(path);
        } catch (ClassNotFoundException e) {
            throw new IOException("invalid data in " + path);
        } finally {
        	objectInputStream.close();
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
        	logger.log(Level.INFO, "Writing data to DataBase: " + SAVE_PATH );
            saveVehicle(SAVE_PATH);
            ((DBMenu) menu).save();
            logger.log(Level.INFO, "Succesful writed data to DataBase: " + SAVE_PATH );
        } catch (IOException e) {
        	logger.log(Level.SEVERE, "Error writing data to DataBase: " + SAVE_PATH );
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
