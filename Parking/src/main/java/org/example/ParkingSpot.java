package org.example;

public class ParkingSpot {
    private int id;
    private VehicleType type;
    private boolean occupied;
    private Vehicle vehicle;
    public ParkingSpot(int id,VehicleType type){
        this.id=id;
        this.type=type;
        this.occupied=false;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public boolean canPark(Vehicle vehicle){
        return !occupied && vehicle.getType() ==type;

    }
    public void park(Vehicle vehicle){
        if(canPark(vehicle)){
            this.vehicle=vehicle;
            this.occupied=true;
        }
    }
    public void removeVehicle(){
        this.vehicle=null;
        this.occupied=false;
    }
}
