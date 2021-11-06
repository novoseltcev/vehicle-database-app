package repository;

import java.io.IOException;
import java.util.List;

public interface IRepository<T> {
    void add(T object);
    List<T> readAll() throws IOException;
    T read(int id);
    void delete(int id);
    void update(int id, T object);
}
