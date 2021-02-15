package ru.job4j.architecture.solid.srp.parking;

public class Truck implements Vehicle {
    @Override
    public int getSizeOfLot() {
        return 4;
    }
}
