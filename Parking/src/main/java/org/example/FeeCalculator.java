package org.example;

interface FeeCalculator {
    double calculateFee(int hours);
}
class CarFeeCalculator implements FeeCalculator{
    @Override
    public double calculateFee(int hours) {
        return 50*hours;
    }
}
class BikeFeeCalculator implements FeeCalculator{
    @Override
    public double calculateFee(int hours) {
        return 20*hours;
    }
}
