package model.vehicle;

import model.vehicle.exception.*;


public class Test {
    public static void main(String[] args) {
        try {
            Truck truck = new Truck("Man", "TGL12.180 BL", 5000);
            Car car = new Car("ГАЗ", "3110 Волга", 390, 5);
            Bus bus = new Bus("Икарус", "Икарус-280", 0,180);
            Motorcycle motorcycle = new Motorcycle("Honda", "VRF800FI", 50,1);
            Trailer carTrailer = new Trailer("Тонар", "8310", 1000);
            Trailer truckTrailer = new Trailer("Планета", "852911В", 16000);
            CarWithTrailer carWithTrailer = new CarWithTrailer(car, carTrailer);
            TruckWithTrailer truckWithTrailer = new TruckWithTrailer(truck, truckTrailer);
            
            Vehicle[] vehicles = {motorcycle, car, truck, bus, carWithTrailer, truckWithTrailer};
            for (Vehicle vehicle :
                    vehicles) {
                System.out.println(vehicle);
            }
        } catch (InvalidMaxCargoWeightExceptions | InvalidBrandExceptions | InvalidModelExceptions | InvalidNumPassengerExceptions e) {
            e.printStackTrace();
        }
        
    }
}
