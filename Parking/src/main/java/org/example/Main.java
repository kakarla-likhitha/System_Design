package org.example;

public class Main {
    public static void main(String[] args){
        ParkingLot pk= new ParkingLot();
        pk.addSpot(new ParkingSpot(1,VehicleType.BIKE));
        Vehicle v=new Bike("123");
        System.out.println(pk.parkVehicle(v));
        System.out.println(pk.calculateParkingFee(v.getNumber(),3));
        pk.addSpot(new ParkingSpot(2,VehicleType.CAR));
        Vehicle v1=new Car("1234");
        System.out.println(pk.parkVehicle(v1));
        System.out.println(pk.calculateParkingFee(v1.getNumber(),5));
        Vehicle v2=new Car("1234");
        System.out.println(pk.parkVehicle(v2));
        System.out.println(pk.calculateParkingFee(v2.getNumber(),5));



    }
}
