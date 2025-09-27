package state;

import entities.TrafficSignal;
import enums.SignalDirection;

public class RedState implements State {
    private static final String stateName = "red";

    @Override
    public void enterState(TrafficSignal signal) {
        System.out.println("The traffic light is RED. You must stop.");
    }

    @Override
    public void nextState(TrafficSignal signal) {
        signal.setState(new YellowState());
    }

    @Override
    public String getStateName() {
        return stateName;
    }
}
