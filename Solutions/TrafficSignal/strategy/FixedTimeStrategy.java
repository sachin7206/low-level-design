package strategy;

import state.State;

import java.util.Map;

public class FixedTimeStrategy implements TimingStrategy {
    private final Map<String, Integer> stateDurations = Map.of(
            "green", 10, // 15 seconds green
            "yellow", 5, // 5 seconds yellow
            "red",  15// 65 seconds red (to cover green + yellow of the other signal)
    );
    public FixedTimeStrategy() {

    }
    @Override
    public int getCurrentStateDuration(State state) {
        return stateDurations.get(state.getStateName()); // return in seconds
    }
}
