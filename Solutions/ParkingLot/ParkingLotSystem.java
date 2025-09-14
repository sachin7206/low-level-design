import entities.ParkingFloor;
import entities.ParkingSpot;
import entities.ParkingTicket;
import enums.SpotType;
import strategy.ParkingStrategy.BestFitStrategy;
import strategy.ParkingStrategy.ParkingStrategy;
import strategy.feeStrategy.FeeStrategy;
import strategy.feeStrategy.VehicleBasedFeeStrategy;
import vehicle.Vehicle;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotSystem {

    private static ParkingLotSystem instance;
    private ParkingFloor parkingFloor;
    private final List<ParkingFloor> floors;
    private final ParkingStrategy parkingStrategy;
    private final Map<String, ParkingTicket> activeTickets;
    private final FeeStrategy feeStrategy;

    private ParkingLotSystem() {
        // private constructor to prevent instantiation
        this.floors = new ArrayList<>();
        this.parkingStrategy = new BestFitStrategy();
        this.feeStrategy = new VehicleBasedFeeStrategy();
        this.activeTickets = new ConcurrentHashMap<>();
    }
    public static synchronized ParkingLotSystem getInstance() {
        if(instance == null) {
            instance = new ParkingLotSystem();
        }
        return instance;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public Map<SpotType, List<ParkingSpot>> combineAvailability(List<Map<SpotType, List<ParkingSpot>>> availabilities) {
        Map<SpotType, List<ParkingSpot>> combinedAvailability = availabilities.get(0);
        for(int i=1; i<availabilities.size(); i++) {
            Map<SpotType, List<ParkingSpot>> availability = availabilities.get(i);
            for(Map.Entry<SpotType, List<ParkingSpot>> entry : availability.entrySet()) {
                SpotType spotType = entry.getKey();
                List<ParkingSpot> spots = entry.getValue();
                combinedAvailability.putIfAbsent(spotType, new ArrayList<>());
                combinedAvailability.get(spotType).addAll(spots);
            }
        }
        return combinedAvailability;
    }

    public void parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> spots = parkingStrategy.findSpot(floors, vehicle);
        if(spots.isPresent()) {
            ParkingSpot spot = spots.get();
            spot.assignVehicle(vehicle);
            ParkingTicket ticket = new ParkingTicket(vehicle, spots.get());
            activeTickets.put(vehicle.getNumber(), ticket);
            System.out.println(vehicle.getNumber() + " parked at spot " + spot.getSpotId());
            return;
        }
        System.out.println("No available spot for vehicle " + vehicle.getNumber());
    }

    public void unParkVehicle(Vehicle vehicle) {
        ParkingTicket parkingTicket = activeTickets.get(vehicle.getNumber());
        if(parkingTicket != null) {
            parkingTicket.setExitTimestamp();
            ParkingSpot spot = parkingTicket.getParkingSpot();
            activeTickets.remove(vehicle.getNumber());
            Double fee = feeStrategy.calculateFee(parkingTicket);
            spot.reassignVehicle();
            System.out.println("Vehicle " + vehicle.getNumber() + " unparked from spot " + spot.getSpotId() + ". Fee: " + fee);
        } else {
            System.out.println("No active ticket found for vehicle " + vehicle.getNumber());
        }
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }
}
