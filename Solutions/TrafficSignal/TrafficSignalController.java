import entities.Timer;
import entities.TrafficSignal;
import enums.SignalDirection;
import enums.TrafficMovingDirection;
import state.GreenState;
import state.RedState;
import strategy.FixedTimeStrategy;
import strategy.PeakHourStrategy;
import strategy.TimingStrategy;

import java.util.Map;

public class TrafficSignalController {
    private static TrafficSignalController controllerInstance;
    private final TrafficSignal signalNs;
    private final TrafficSignal signalEW;
    private final Timer timer;

    private TrafficSignalController() {
        // Private constructor to prevent instantiation
        TimingStrategy fixedTimeStrategy = new FixedTimeStrategy();
        TimingStrategy peakHourStrategy = new PeakHourStrategy();
        signalNs = new TrafficSignal(TrafficMovingDirection.NORTH_SOUTH, new RedState(), peakHourStrategy, SignalDirection.FORWARD);
        signalEW = new TrafficSignal(TrafficMovingDirection.EAST_WEST, new RedState(), peakHourStrategy, SignalDirection.BACKWARD);
        timer = new Timer();
    }

    public static TrafficSignalController getInstance() {
        if (controllerInstance == null) {
            controllerInstance = new TrafficSignalController();
        }
        return controllerInstance;
    }

    public void startCycle() {
        System.out.println("Starting traffic signal lights...\n");
        signalNs.setState(new GreenState());
        scheduleNextTransition();
    }

    private void scheduleNextTransition() {
        // Logic to schedule the next state transition based on timing strategy
        int duration = Math.min(signalNs.getCurrentStateDuration(), signalEW.getCurrentStateDuration());
        timer.start(duration, this::transitionStates);
    }

    private void transitionStates() {
        // Logic to transition states
        signalNs.changeToNextState();
        signalEW.changeToNextState();
        scheduleNextTransition();
    }

    public void stopCycle() {
        timer.stop();
        System.out.println("Traffic cycle stopped.");
    }

    public void EmergencyOverRiding(TrafficMovingDirection trafficMovingDirection) {
        if(trafficMovingDirection == TrafficMovingDirection.NORTH_SOUTH) {
            signalNs.setState(new GreenState());
            signalEW.setState(new RedState());
            System.out.println("Manual override: "+ TrafficMovingDirection.NORTH_SOUTH + " to green.");
        } else {
            signalNs.setState(new RedState());
            signalEW.setState(new GreenState());
            System.out.println("Manual override: "+ TrafficMovingDirection.NORTH_SOUTH + " to green.");
        }
    }
}
