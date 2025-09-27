package state;

import entities.TrafficSignal;
import enums.SignalDirection;

public class YellowState implements State {
    private static final String stateName = "yellow";
    @Override
    public void enterState(TrafficSignal signal) {
        System.out.println("The traffic light is YELLOW. Prepare to stop.");
    }

    @Override
    public void nextState(TrafficSignal signal) {
        if(signal.getSignalDirection() == SignalDirection.FORWARD) {
            signal.setState(new RedState());
            signal.setSignalDirection(SignalDirection.BACKWARD);
        } else {
            signal.setState(new GreenState());
            signal.setSignalDirection(SignalDirection.FORWARD);
        }

    }

    @Override
    public String getStateName() {
        return stateName;
    }
}
