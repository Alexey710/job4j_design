package ru.job4j.architecture.solid.srp.parking;

public class Truck implements Vehicle {

    private int sizeOfLot;

    public Truck(int sizeOfLot) {
        this.sizeOfLot = sizeOfLot;
    }

    @Override
    public int getSizeOfLot() {
        return sizeOfLot;
    }
}
