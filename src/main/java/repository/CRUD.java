package repository;

import javafx.collections.ObservableList;


public interface CRUD<T> {
    void add(T object);
    ObservableList<T> readAll();
    T read(int id);
    void delete(int id);
    void update(int id, T object);
}
