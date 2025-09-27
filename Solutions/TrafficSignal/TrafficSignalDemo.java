import enums.TrafficMovingDirection;

import java.time.LocalTime;

public class TrafficSignalDemo {
    public static void main(String[] args) {
        TrafficSignalController controllerInstance = TrafficSignalController.getInstance();
        LocalTime.now();
        controllerInstance.startCycle();

        try {
            Thread.sleep(60 * 1000L);// Run for 60 seconds for demo
            controllerInstance.EmergencyOverRiding(TrafficMovingDirection.NORTH_SOUTH);
            Thread.sleep(60 * 1000L);// Run for 60 seconds for demo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controllerInstance.stopCycle();
    }
}
