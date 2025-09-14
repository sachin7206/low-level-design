package entities;

import vehicle.Vehicle;

import java.util.Date;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final long entryTimestamp;
    private long exitimestamp;
    public ParkingTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTimestamp = new Date().getTime();
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setExitTimestamp() {
        this.exitimestamp =new Date().getTime();
    }

    public long getEntryTimestamp() {
        return entryTimestamp;
    }

    public long getExitimestamp() {
        return exitimestamp;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
