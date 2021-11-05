package repository;

import model.vehicle.Vehicle;
import service.VehicleService;

import java.util.List;
import java.util.Optional;

public class VehicleRepository implements IRepository<Vehicle> {
    private final List<Vehicle> container;

    public VehicleRepository(String path) {
        container = VehicleService.load(path);
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
    public Optional<Vehicle> read(int id) {
        if (container.size() <= id) {
            return Optional.empty();
        }
        return Optional.of(container.get(id));
    }

    @Override
    public boolean delete(int id) {
        if (container.size() <= id) {
            return false;
        }
        container.remove(id);
        return true;
    }

    @Override
    public boolean update(int id, Vehicle vehicle) {
        if (container.size() <= id) {
            return false;
        }
        container.set(id, vehicle);
        return true;
    }



    public int size() {
        return container.size();
    }
}
