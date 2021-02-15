package ru.job4j.architecture.solid.srp.parking;

public class ParkingLot implements Parking {
    private Vehicle[] lots;
    private final int sizeCarLot = 1;
    private final int sizeTruckLot = 4;

    public ParkingLot(int amount) {
        lots = new Vehicle[amount];
    }

    @Override
    public int getEmptyLotsForTrucks() {
        int countTruck = 0;
        int count = 0;
        for (int i = 0; i < lots.length; i++) {
            if (lots[i] == null) {
                count++;
                if (count == 4) {
                    countTruck++;
                    count = 0;
                }
            } else {
                count = 0;
            }

            }
        return countTruck;
    }

    @Override
    public int getEmptyLotsForCars() {
        int countCar = 0;
        for (int i = 0; i < lots.length; i++) {
            if (lots[i] == null) {
                countCar++;
            }

        }
        return countCar;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
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
    public boolean deleteVehicle(Vehicle vehicle) {
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

}
