package entities;

import enums.SpotType;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingFloor {
    private int floorNumber;
    private List<ParkingSpot> parkingSpots;
    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        parkingSpots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        this.parkingSpots.add(spot);
    }

    public Map<SpotType, List<ParkingSpot>> checkAvailability() {
        Map<SpotType, List<ParkingSpot>> availableSpots = new TreeMap<>();
        List<ParkingSpot> bikeSpots = new ArrayList<>();
        List<ParkingSpot> carSpots = new ArrayList<>();
        List<ParkingSpot> truckSpots = new ArrayList<>();
        for(ParkingSpot parkingSpot : parkingSpots) {
            if(parkingSpot.isFree()) {
                if(parkingSpot.getSpotType().equals(SpotType.BIKE)) {
                    bikeSpots.add(parkingSpot);
                } else if(parkingSpot.getSpotType().equals(SpotType.CAR)) {
                    carSpots.add(parkingSpot);
                } else if(parkingSpot.getSpotType().equals(SpotType.TRUCK)) {
                    truckSpots.add(parkingSpot);
                }
            }
        }
        availableSpots.put(SpotType.BIKE, bikeSpots);
        availableSpots.put(SpotType.CAR, carSpots);
        availableSpots.put(SpotType.TRUCK, truckSpots);
        return availableSpots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}
