package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.vehicle.Vehicle;
import repository.exception.InvalidIDError;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.List;


public class Repository<T> implements CRUD<T> {
    private ObservableList<T> container = FXCollections.observableArrayList();
    private File file;
    FileInputStream   fin;
    ObjectInputStream in;

    FileOutputStream   fout;
    ObjectOutputStream out;

    private void setFile(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException();
        }

        if (!file.exists()) {
            throw new FileNotFoundException();
        }

        if (!file.isFile()) {
            throw new NoSuchFileException(file.getAbsolutePath());
        }

        this.file = file;
    }

    public void load(File file) throws IOException, ClassNotFoundException {
        setFile(file);
        try {
            fin = new FileInputStream(file);
            in = new ObjectInputStream(fin);
            List<T> list = (List<T>) in.readObject();
            container = FXCollections.observableArrayList(list);
        } finally {
            fin.close();
            if (in != null) {
                in.close();
            }
        }
    }

    public void saveTo(File file) throws IOException {
        setFile(file);
        save();
    }

    public void save() throws IOException {
        try {
            fout = new FileOutputStream(file);
            out = new ObjectOutputStream(fout);
            out.writeObject(container.stream().toList());
        } finally {
            fout.close();
            if (out != null) {
                out.close();
            }
        }
    }

    public File getFile() {
        return file;
    }

    private void checkId(int id) {
        try {
            container.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIDError();
        }
    }

    @Override
    public void add(T object) {
        container.add(object);
    }

    @Override
    public ObservableList<T> readAll() {
        return container;
    }

    @Override
    public T read(int id) {
        checkId(id);
       return container.get(id);
    }

    @Override
    public void delete(int id) {
        checkId(id);
        container.remove(id, id);
    }

    @Override
    public void update(int id, T object) {
        checkId(id);
        container.set(id, object);
    }
}
