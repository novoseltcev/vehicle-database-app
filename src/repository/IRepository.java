package repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    void create(T object);
    List<T> readAll();
    Optional<T> read(int id);
    boolean delete(int id);
    boolean update(int id, T object);
}
