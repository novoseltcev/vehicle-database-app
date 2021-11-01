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

public class DBCtrl extends VehicleCtrl {
    private static final String SAVE_PATH = "vehicles.db";
    DBCtrl(VehicleMenu menu) {
        super(menu);
        load();
    }

    @Override
    protected void chooseCMD(Command command) throws InterruptedException {
        int cmd = super.chooseCMD(command, 5);
        switch (cmd) {
            case (1) -> save();
            case (2) -> show();
            case (3) -> add();
            case (4) -> {
                show();
                edit();
            }
            case (5) -> {
                show();
                remove();
            }
            default -> {
                logger.log(Level.WARNING, "Invalid command");
                menu.errorCommand(String.valueOf(cmd));
            }
        }
    }


    private void load() {
        try {
        	logger.log(Level.INFO, "Reading DataBase from: " + SAVE_PATH );
            loadVehicle(SAVE_PATH);
            ((DBMenu) menu).load();
            logger.log(Level.INFO, "Successful read DataBase from: " + SAVE_PATH );
        } catch (IOException e) {
        	logger.log(Level.SEVERE, "Error reading DataBase from: " + SAVE_PATH );
            vehicles = new ArrayList<>();
            new CrashNotifier(e);
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
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }

    private void show() {
        menu.showVehicles(vehicles);
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
            logger.log(Level.INFO, "Successful wrote data to DataBase: " + SAVE_PATH );
        } catch (IOException e) {
        	logger.log(Level.SEVERE, "Error writing data to DataBase: " + SAVE_PATH );
            new CrashNotifier(e);
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
