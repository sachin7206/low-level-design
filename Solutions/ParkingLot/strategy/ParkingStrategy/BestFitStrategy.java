package strategy.ParkingStrategy;

import entities.ParkingFloor;
import entities.ParkingSpot;
import vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class BestFitStrategy implements strategy.ParkingStrategy.ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Vehicle vehicle) {
        for(ParkingFloor floor : floors) {
            List<ParkingSpot> spots = floor.checkAvailability().get(vehicle.getSpotType());
            if(spots != null && !spots.isEmpty()) {
                // Assuming spots are sorted by size, return the first available spot
                return Optional.of(spots.get(0));
            }
        }
        return Optional.empty();
    }
}
