package org.example;
enum VehicleType {
    CAR,
    BIKE
}
public class Vehicle {
    private String number;
    private VehicleType type;
    public Vehicle(String number,VehicleType type) {
        this.number=number;
        this.type=type;
    }

    public String getNumber() {
        return number;
    }

    public VehicleType getType() {
        return type;
    }
}
class Car extends Vehicle{
    Car(String number){
        super(number,VehicleType.CAR);
    }
}
class Bike extends Vehicle{
    Bike(String number){
        super(number,VehicleType.BIKE);
    }
}
