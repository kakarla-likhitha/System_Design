package org.example;

import java.util.ArrayList;
import java.util.List;


public class ParkingLot {
    private List<ParkingSpot> spots;
    private FeeCalculator carCalculator;
    private FeeCalculator bikeCalculator;
    public ParkingLot() {
        spots = new ArrayList<>();
        bikeCalculator = new BikeFeeCalculator();
        carCalculator = new CarFeeCalculator();


    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (findVehicle(vehicle.getNumber()) != null) {
            return false;
        }
        for (ParkingSpot spot : spots) {
            if (spot.canPark(vehicle)) {
                spot.park(vehicle);
                return true;

            }
        }
        return false;
    }

    public boolean removeVehicle(String vehicleNumber) {
        for (ParkingSpot spot : spots) {
            Vehicle veh = spot.getVehicle();
            if (veh != null && veh.getNumber().equals(vehicleNumber)) {
                spot.removeVehicle();
                return true;
            }
        }
        return false;
    }

    public int getAvailableSpots(VehicleType type) {
        int cnt = 0;
        for (ParkingSpot spot : spots) {
            if (!(spot.isOccupied()) && spot.getType().equals(type)) {
                cnt += 1;
            }
        }
        return cnt;
    }

    public boolean isFull() {
        for (ParkingSpot spot : spots) {
            if (!(spot.isOccupied())){
                return false;
            }
        }
        return true;
    }
    private Vehicle findVehicle(String vehicleNumber){
        for(ParkingSpot spot: spots){
            Vehicle veh= spot.getVehicle();
            if(veh!=null && veh.getNumber().equals(vehicleNumber)){
                return veh;
            }
        }
        return null;
    }
    public double calculateParkingFee(String VehicleNumber,int hours){
        Vehicle veh= findVehicle(VehicleNumber);
        if(veh==null){
            return -1;
        }
        if(veh.getType()==VehicleType.BIKE){
            return bikeCalculator.calculateFee(hours);
        }
        else{
            return carCalculator.calculateFee(hours);
        }
    }

}