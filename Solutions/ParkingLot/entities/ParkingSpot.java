package entities;

import enums.SpotType;
import vehicle.Vehicle;

public class ParkingSpot {
    private String spotId;
    private SpotType spotType;
    private boolean isFree = true;
    private Vehicle vehicle;

    public ParkingSpot(String spotId, SpotType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.vehicle = null;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public Boolean isFree() {
        return isFree;
    }

    public String getSpotId() {
        return spotId;
    }

    public void setFree(boolean free) {
        this.isFree = free;
    }

    public synchronized void assignVehicle(Vehicle vehicle) {
        setFree(false);
        this.vehicle = vehicle;
    }

    public synchronized void reassignVehicle() {
        setFree(true);
        this.vehicle = null;
    }
}
