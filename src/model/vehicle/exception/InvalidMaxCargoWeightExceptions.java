package model.vehicle.exception;

public class InvalidMaxCargoWeightExceptions extends Exception{
    protected int downBorder;
    protected int upBorder;
    public InvalidMaxCargoWeightExceptions(int downBorder, int upBorder) {
        super("should be in the range from " +  downBorder + " to " + upBorder);
    }
}
