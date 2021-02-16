package ru.job4j.architecture.solid.srp.parking;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class ParkingLotTest {
    @Ignore
    @Test
    public void getEmptyLotsForTrucksAndCars() {
        ParkingLot parkingLot = new ParkingLot(5, 12);
        Assert.assertEquals(parkingLot.getEmptyLotsForCars(), 5);
        Assert.assertEquals(parkingLot.getEmptyLotsForTrucks(), 12);
    }

    @Ignore
    @Test
    public void addVehicleCar() {
        ParkingLot parkingLot = new ParkingLot(4, 4);
        Vehicle vehicle1 = new Car();
        parkingLot.addVehicle(vehicle1);
        Assert.assertEquals(parkingLot.getEmptyLotsForCars(), 3);
    }

    @Ignore
    @Test
    public void addVehicleTruck() {
        ParkingLot parkingLot = new ParkingLot(1, 4);
        Vehicle vehicle1 = new Truck(5);
        parkingLot.addVehicle(vehicle1);
        Assert.assertEquals(parkingLot.getEmptyLotsForCars(), 0);
    }

    @Ignore
    @Test
    public void addVehicleTruckFalse() {
        ParkingLot parkingLot = new ParkingLot(4, 4);
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck(8);
        Assert.assertTrue(parkingLot.addVehicle(vehicle1));
        Assert.assertFalse(parkingLot.addVehicle(vehicle2));
    }

    @Ignore
    @Test
    public void addVehicleTruckAndCar() {
        ParkingLot parkingLot = new ParkingLot(1, 4);
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck(4);
        Assert.assertTrue(parkingLot.addVehicle(vehicle1));
        Assert.assertTrue(parkingLot.addVehicle(vehicle2));
    }

    @Ignore
    @Test
    public void deleteVehicle() {
        ParkingLot parkingLot = new ParkingLot(5, 5);
        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck(5);
        parkingLot.addVehicle(vehicle1);
        parkingLot.addVehicle(vehicle2);
        Assert.assertTrue(parkingLot.deleteVehicle(vehicle1));
        Assert.assertTrue(parkingLot.deleteVehicle(vehicle2));
        Assert.assertEquals(parkingLot.getEmptyLotsForCars(), 5);
        Assert.assertEquals(parkingLot.getEmptyLotsForTrucks(), 5);
    }
}