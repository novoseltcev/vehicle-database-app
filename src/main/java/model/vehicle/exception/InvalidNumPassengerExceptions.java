package model.vehicle.exception;

public class InvalidNumPassengerExceptions extends Exception{
    public InvalidNumPassengerExceptions(int downBorder, int upBorder) {
        super("should be in the range from " +  downBorder + "to " + upBorder);
    }
}
