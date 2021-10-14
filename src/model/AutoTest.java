package model;

public class AutoTest {
    private final int _size;
    private double _meanTime;
    public AutoTest(int size) {
        _size = size;
        run();
    }

    // TODO - add autotest running
    private void run() {}

    @Override
    public String toString() {
        return "size: " + _size + ", execute mean time: " + _meanTime;
    }
}
