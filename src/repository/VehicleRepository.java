package repository;

import model.vehicle.Vehicle;

import java.io.*;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class VehicleRepository implements IRepository<Vehicle> {
    private List<Vehicle> container;
    private File file;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public VehicleRepository(String filename) throws IOException {
        setFile(filename);
        load();
    }

    private void load() throws IOException {
        try {
            in = new ObjectInputStream(new FileInputStream(file));
            container = (List<Vehicle>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            in.close();
            container = new LinkedList<>();
            save();
        }
    }

    private void setFile(String filename) throws IOException {
        new File("data").mkdir();
        Path path = Path.of("data", filename).normalize();
        file = path.toFile();
        file.createNewFile();
    }

    @Override
    public void create(Vehicle vehicle) {
        container.add(vehicle);
    }

    @Override
    public List<Vehicle> readAll() {
        return container;
    }

    @Override
    public Vehicle read(int id) throws IndexOutOfBoundsException{
        if (container.size() <= id) {
            throw new IndexOutOfBoundsException();
        }
        return container.get(id);
    }

    @Override
    public void delete(int id) throws IndexOutOfBoundsException {
        if (container.size() <= id) {
            throw new IndexOutOfBoundsException();
        } container.remove(id);
    }

    @Override
    public void update(int id, Vehicle vehicle) throws IndexOutOfBoundsException{
        if (container.size() <= id) {
            throw new IndexOutOfBoundsException();
        } container.set(id, vehicle);
    }

    public void save() throws IOException {
        out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(container);
        out.close();
    }

    public int size() {
        return container.size();
    }
}
