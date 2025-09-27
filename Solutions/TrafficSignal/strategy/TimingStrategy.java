package strategy;

import state.State;

public interface TimingStrategy {
    int getCurrentStateDuration(State state); // in seconds
}
