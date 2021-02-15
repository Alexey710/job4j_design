package ru.job4j.architecture.solid.srp.parking;

interface Parking {

    int getEmptyLotsForTrucks();

    int getEmptyLotsForCars();

    boolean addVehicle(Vehicle vehicle);

    boolean deleteVehicle(Vehicle vehicle);
}
