package ru.job4j.architecture.solid.srp.parking;

public class ParkingLot implements Parking {
    private Vehicle[] cars;
    private Vehicle[] trucks;
    private final int sizeCarLot = 1;
    private final int sizeTruckLot = 1;

    public ParkingLot(int carNumber, int truckNumber) {
        cars = new Vehicle[carNumber];
        trucks = new Vehicle[truckNumber];
    }

    @Override
    public int getEmptyLotsForTrucks() {
        int countTruck = 0;
        for (int i = 0; i < trucks.length; i++) {
            if (trucks[i] == null) {
                countTruck++;
            }
        }
        return countTruck;
    }

    @Override
    public int getEmptyLotsForCars() {
        int countCar = 0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                countCar++;
            }
        }
        return countCar;
    }

    private boolean searchEmptyLots(Vehicle vehicle, Vehicle[] lots) {
        int targetLot = vehicle.getSizeOfLot();
        int count = 0;
        for (int i = 0; i < lots.length; i++) {
            if (lots[i] == null) {
                count++;
                if (count == targetLot) {
                    for (int j = 0; j < count;) {
                        lots[i--] = vehicle;
                        j++;
                    }
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        boolean rsl = false;
        if (vehicle.getSizeOfLot() == 1) {
            rsl = searchEmptyLots(vehicle, cars);
        } else {
            rsl = searchEmptyLots(vehicle, trucks);
            if (!rsl) {
                rsl = searchEmptyLots(vehicle, cars);
            }
        }
        return rsl;
    }

    private boolean searchForDelete(Vehicle vehicle, Vehicle[] lots) {
        int targetLot = vehicle.getSizeOfLot();
        int count = 0;
        for (int i = 0; i < lots.length; i++) {
            if (lots[i] == vehicle) {
                lots[i] = null;
                count++;
            }
            if (count == targetLot) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteVehicle(Vehicle vehicle) {
        boolean rsl = false;
        if (vehicle.getSizeOfLot() == 1) {
            rsl = searchForDelete(vehicle, cars);
        } else {
            rsl = searchForDelete(vehicle, trucks);
            if (!rsl) {
                rsl = searchForDelete(vehicle, cars);
            }
        }
        return rsl;
    }

}
