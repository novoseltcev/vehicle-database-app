package model;

import model.vehicle.Vehicle;
import model.vehicle.VehicleFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
    private long addTotalNanos = 0;
    private long rmTotalNanos  = 0;

    private double addMeanNanos = 0;
    private double rmMeanNanos  = 0;

    public AutoTest(Class<?> listClass, int size) {
        this.listClass = listClass.getSimpleName().split(Pattern.quote("[]"))[0];
        this.size = size;
        setLogger();

        if (this.listClass.equals(ArrayList.class.getSimpleName())) {
            container = new ArrayList<>();
        } else if (this.listClass.equals(LinkedList.class.getSimpleName())) {
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
            Handler handler = new FileHandler(loggerName);
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
            int j = generateIndex(size - i);
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
