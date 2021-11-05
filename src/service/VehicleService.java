package service;

import model.vehicle.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class VehicleService {
    public static List<Vehicle> load(String path) {  // TODO
        try {
//            logger.log(Level.INFO, "Reading DataBase from: " + SAVE_PATH );
            return loadVehicle(path);
//            ((DBMenu) menu).load();
//            logger.log(Level.INFO, "Successful read DataBase from: " + SAVE_PATH );
        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Error reading DataBase from: " + SAVE_PATH );
            return new ArrayList<>();
//            new CrashNotifier(e);
        }
    }

    public static boolean save(String path, List<Vehicle> vehicles) {  // TODO
        try {
//            logger.log(Level.INFO, "Writing data to DataBase: " + SAVE_PATH );
            saveVehicle(path, vehicles);
            return true;
//            ((DBMenu) menu).save();
//            logger.log(Level.INFO, "Successful wrote data to DataBase: " + SAVE_PATH );
        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Error writing data to DataBase: " + SAVE_PATH );
//            new CrashNotifier(e);
            return false;
        }
    }

    @Deprecated
    private static List<Vehicle> loadVehicle(String path) throws IOException {
        ObjectInputStream objectInputStream = null;
        try {
            FileInputStream inputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(inputStream);
            return (List<Vehicle>) objectInputStream.readObject();
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

    @Deprecated
    private static void saveVehicle(String path, List<Vehicle> vehicles) throws IOException {
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
