package strategy.feeStrategy;

import entities.ParkingTicket;
import enums.SpotType;

import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy {
    private static final Map<SpotType, Double> HOURLY_RATES = Map.of(
            SpotType.BIKE, 10.0,
            SpotType.CAR, 20.0,
            SpotType.TRUCK, 30.0
    );
    @Override
    public double calculateFee(ParkingTicket ticket) {
        long duration = (ticket.getExitimestamp() - ticket.getEntryTimestamp());
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * HOURLY_RATES.get(ticket.getParkingSpot().getSpotType());
    }
}
