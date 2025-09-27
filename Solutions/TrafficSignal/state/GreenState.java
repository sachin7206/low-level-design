package state;

import entities.TrafficSignal;
import enums.SignalDirection;

public class GreenState implements State {
    private static final String stateName = "green";
    @Override
    public void enterState(TrafficSignal signal) {
        System.out.println("The traffic light is GREEN. You can go.");
    }

    @Override
    public void nextState(TrafficSignal signal) {
        if(signal.getSignalDirection() == SignalDirection.FORWARD) {
            signal.setState(new YellowState());
        } else {
            signal.setSignalDirection(SignalDirection.BACKWARD);
        }

    }

    @Override
    public String getStateName() {
        return stateName;
    }
}
