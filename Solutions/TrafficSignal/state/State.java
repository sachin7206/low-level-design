package state;

import entities.TrafficSignal;

public interface State {
    void enterState(TrafficSignal signal);
    void nextState(TrafficSignal signal);
    String getStateName();
}
