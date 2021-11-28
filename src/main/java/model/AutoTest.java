package model;

import model.vehicle.Vehicle;
import model.vehicle.VehicleFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class AutoTest {
    private final String listClass;
    private final int size;

    private List<Vehicle> container;
    private Logger logger;
    private long addTotalNanos;
    private long rmTotalNanos;

    private double addMeanNanos;
    private double rmMeanNanos;

    public long getAddTotalNanos() { return addTotalNanos; }

    public long getRmTotalNanos() { return rmTotalNanos; }

    public double getAddMeanNanos() { return addMeanNanos; }

    public double getRmMeanNanos() { return rmMeanNanos; }

    public String getListClass() { return listClass; }

    public int getSize() {
        return size;
    }

    public AutoTest(Class<?> listClass, int size) {
        this.listClass = listClass.getSimpleName().split(Pattern.quote("[]"))[0];
        this.size = size;
        setLogger();

        if (this.listClass.equals(ArrayList.class.getSimpleName())) {
            System.out.println("ArrayList");
            container = new ArrayList<>();
        } else if (this.listClass.equals(LinkedList.class.getSimpleName())) {
            System.out.println("LinkedList");
            container = new LinkedList<>();
        }

        generateList();
        removeTest();

        logger.log(Level.ALL, String.format("AddTotalTime:\t%d", addTotalNanos));
        logger.log(Level.ALL, String.format("AddMeanTime:\t%f", addMeanNanos));
        logger.log(Level.ALL, String.format("RemoveTotalTime:\t%d", rmTotalNanos));
        logger.log(Level.ALL, String.format("RemoveMeanTime:\t%f", rmMeanNanos));
    }

    private void setLogger() {
        String loggerName = "test" + size + listClass + ".log";
        logger = Logger.getLogger(loggerName);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        try {
            Path handlerPath = Path.of("log", "tests", loggerName).normalize();
            handlerPath.getParent().toFile().mkdir();
            Handler handler = new FileHandler(handlerPath.toString());
            logger.addHandler(handler);
        } catch (IOException ignored) {}
    }

    private void generateList() {
        logger.info("Add: Size - " + size);
        addTotalNanos = 0;
        for (int i = 0; i < size; i++) {
            Vehicle vehicle = VehicleFactory.random();
            long startTime = System.nanoTime();
            container.add(vehicle);
            long elapsedNanos = System.nanoTime() - startTime;
            logger.info(String.format("ID=%d, nanos=%d", i + 1, elapsedNanos));
            addTotalNanos += elapsedNanos;
        }
        addMeanNanos = (double) addTotalNanos / size;
    }

    private void removeTest() {
        logger.info("Remove: Size - " + size);
        rmTotalNanos = 0;
        int new_size = size / 10;
        for (int i = 0; i < new_size; i++) {
//            int j = generateIndex(size - i);
            int j = (size - i) / 2;
            long startTime = System.nanoTime();
            container.remove(j);
            long elapsedNanos = System.nanoTime() - startTime;
            logger.info(String.format("ID=%d, nanos=%d", i + 1, elapsedNanos));
            rmTotalNanos += elapsedNanos;
        }
        rmMeanNanos = (double) rmTotalNanos / new_size;
    }

    private int generateIndex(int i) {
        return (int) Math.round(Math.random() * (i - 1) );
    }
}
