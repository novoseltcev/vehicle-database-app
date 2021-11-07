package model.vehicle.exception;

public class InvalidCargoWeightExceptions extends Exception{
    public InvalidCargoWeightExceptions(int border) {
        super("should be in the less than from " + border);
    }
}

