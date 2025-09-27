package strategy;

import state.State;

import java.time.LocalTime;
import java.util.Map;

public class PeakHourStrategy implements TimingStrategy {
    private final Map<String, Integer> stateDurations = Map.of(
            "green", 20, // 20 seconds green
            "yellow", 5, // 5 seconds yellow
            "red",  25// 25 seconds red (to cover green + yellow of the other signal)
    );
    private final Map<String, Integer> peakTimeDurations = Map.of(
            "green", 30, // 30 seconds green
                    "yellow", 5, // 5 seconds yellow
                    "red",  35// 35 seconds red (to cover green + yellow of the other signal)
    );

    private final Map<String, Integer> nightDurations = Map.of(
            "green", 10, // 10 seconds green
            "yellow", 5, // 5 seconds yellow
            "red",  15// 15 seconds red (to cover green + yellow of the other signal)
    );
    private LocalTime currentTime;

    public PeakHourStrategy() {}
    @Override
    public int getCurrentStateDuration(State state) {
        if(isWithin(LocalTime.of(7, 0), LocalTime.of(9, 0)) || isWithin(LocalTime.of(17, 0), LocalTime.of(19, 0))) {
            return peakTimeDurations.get(state.getStateName());
        } else if(isWithin(LocalTime.of(22, 0), LocalTime.of(5, 0))) {
            return nightDurations.get(state.getStateName());
        } else {
            return stateDurations.get(state.getStateName());
        }
    }

    private boolean isWithin(LocalTime start, LocalTime end) {
        currentTime = LocalTime.now();
        if(start.isBefore(end)) {
            return !currentTime.isBefore(start) && !currentTime.isAfter(end);
        } else { // crosses midnight
            return !currentTime.isBefore(start) || !currentTime.isAfter(end);
        }
    }
}
