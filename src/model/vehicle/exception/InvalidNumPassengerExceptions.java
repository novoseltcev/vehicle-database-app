package model.vehicle.exception;

public class InvalidNumPassengerExceptions extends Exception{
    protected int downBorder;
    protected int upBorder;
    public InvalidNumPassengerExceptions(int downBorder, int upBorder) {
        super("should be in the range from " +  downBorder + "to " + upBorder);
    }
    public InvalidNumPassengerExceptions(int downBorder) {
        super("should be not less than" +  downBorder);
    }
}
