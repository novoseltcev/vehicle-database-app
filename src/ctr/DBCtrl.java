package ctr;

import model.vehicle.Bus;
import model.vehicle.Vehicle;
import view.BaseMenu;
import view.CrashNotifier;
import view.DBMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DBCtrl extends BaseCtrl{
    private static final String SAVE_PATH = "vehicles.db";
    protected List<Vehicle> vehicles;
    DBCtrl(BaseMenu menu) {
        super(menu);
        try {
            load(SAVE_PATH);
            ((DBMenu) menu).load();
        } catch (Exception e) {
            vehicles = new ArrayList<>();
            CrashNotifier crasher = new CrashNotifier();
            crasher.handler(e);
        }
    }

    public void run() throws InterruptedException {
        ((DBMenu)menu).show();
        int cmd = enterInt();
        chooseCMD(cmd);
    }

    private void chooseCMD(int command) throws InterruptedException {
        switch (command) {
            case (0):
                throw new InterruptedException("0");

            case (1):
                ((DBMenu)menu).showVehicles(vehicles);
                break;

            case (2):
                //addVehicle();  // TODO
                try {
                    Bus car = new Bus("Mersedes", "S", 800, 8);
                    vehicles.add(car);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case (3):
                ((DBMenu)menu).showVehicles(vehicles);
                //editVehicle();  // TODO
                break;

            case (4):
                ((DBMenu)menu).showVehicles(vehicles);
                //removeVehicle();  // TODO
                break;

            case (5):
                try {
                    save(SAVE_PATH);
                    ((DBMenu) menu).save();
                } catch (IOException e) {
                    CrashNotifier crashNotifier = new CrashNotifier();
                    crashNotifier.handler(e);
                } break;

            default:
                menu.errorCommand(String.valueOf(command));
        }
    }

    private void save(String path) throws IOException {
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

    // TODO - deserialize List
    private void load(String path) throws IOException {
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
}
