package ru.job4j.architecture.solid.srp.parking;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {

    @Test
    public void getEmptyLotsForTrucks() {
        ParkingLot parkingLot = new ParkingLot(12);
        Assert.assertEquals(parkingLot.getEmptyLotsForTrucks(), 3);

    }

    @Test
    public void getEmptyLotsForTrucksIsZero() {
        ParkingLot parkingLot = new ParkingLot(3);
        Assert.assertEquals(parkingLot.getEmptyLotsForTrucks(), 0);

    }

    @Test
    public void getEmptyLotsForCars() {
        ParkingLot parkingLot = new ParkingLot(12);
        Assert.assertEquals(parkingLot.getEmptyLotsForCars(), 12);
    }

    @Test
    public void addVehicleCar() {
        ParkingLot parkingLot = new ParkingLot(4);
        Vehicle vehicle1 = new Car();
        parkingLot.addVehicle(vehicle1);
        Assert.assertEquals(parkingLot.getEmptyLotsForCars(), 3);
    }

    @Test
    public void addVehicleTruck() {
        ParkingLot parkingLot = new ParkingLot(4);
        Vehicle vehicle1 = new Truck();
        parkingLot.addVehicle(vehicle1);
        Assert.assertEquals(parkingLot.getEmptyLotsForCars(), 0);
    }

    @Test
    public void addVehicleTruckFalse() {
        ParkingLot parkingLot = new ParkingLot(4);
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck();
        Assert.assertTrue(parkingLot.addVehicle(vehicle1));
        Assert.assertFalse(parkingLot.addVehicle(vehicle2));
    }

    @Test
    public void addVehicleTruckAndCar() {
        ParkingLot parkingLot = new ParkingLot(5);
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck();
        Assert.assertTrue(parkingLot.addVehicle(vehicle1));
        Assert.assertTrue(parkingLot.addVehicle(vehicle2));
    }

    @Test
    public void deleteVehicle() {
        ParkingLot parkingLot = new ParkingLot(5);
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck();
        parkingLot.addVehicle(vehicle1);
        parkingLot.addVehicle(vehicle2);
        Assert.assertTrue(parkingLot.deleteVehicle(vehicle2));
        Assert.assertEquals(parkingLot.getEmptyLotsForTrucks(), 1);
    }
}