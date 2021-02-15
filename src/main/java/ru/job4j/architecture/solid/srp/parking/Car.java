package ru.job4j.architecture.solid.srp.parking;

public class Car implements Vehicle {
    @Override
    public int getSizeOfLot() {
        return 1;
    }
}
