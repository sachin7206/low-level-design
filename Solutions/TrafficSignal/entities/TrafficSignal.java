package entities;

import enums.SignalDirection;
import enums.TrafficMovingDirection;
import state.State;
import strategy.TimingStrategy;

public class TrafficSignal {
    private final TrafficMovingDirection trafficMovingDirection;
    private State state;
    private final TimingStrategy timingStrategy;
    private SignalDirection signalDirection;
    public TrafficSignal(TrafficMovingDirection trafficMovingDirection, State state, TimingStrategy timingStrategy, SignalDirection signalDirection) {
        this.trafficMovingDirection = trafficMovingDirection;
        this.state = state;// initial state set to red
        this.timingStrategy = timingStrategy;
        this.signalDirection = signalDirection;
    }

    public TrafficMovingDirection getTraffingMovingDirection() {
        return trafficMovingDirection;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        System.out.println(getTraffingMovingDirection() + " changing signal light " + this.state.getStateName() +  " to " + state.getStateName() + " state.");
        this.state = state;
    }

    public int getCurrentStateDuration() {
        return timingStrategy.getCurrentStateDuration(state);
    }

    public void changeToNextState() {
        state.nextState(this);
    }

    public void setSignalDirection(SignalDirection signalDirection) {
        this.signalDirection = signalDirection;
    }

    public SignalDirection getSignalDirection() {
        return signalDirection;
    }
}
