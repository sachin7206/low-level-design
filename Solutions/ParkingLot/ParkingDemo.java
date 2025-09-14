import entities.ParkingFloor;
import entities.ParkingSpot;
import enums.SpotType;
import vehicle.Bike;
import vehicle.Car;
import vehicle.Truck;
import vehicle.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParkingDemo {
    public static void main(String[] args) {
        ParkingLotSystem parkingLotSystem = ParkingLotSystem.getInstance();

        // 1. Initialize the parking lot with floors and spots
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("f1-b1", SpotType.BIKE));
        floor1.addSpot(new ParkingSpot("f1-c1", SpotType.CAR));
        floor1.addSpot(new ParkingSpot("f1-t1", SpotType.TRUCK));
        parkingLotSystem.addFloor(floor1);

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("f2-b1", SpotType.BIKE));
        floor2.addSpot(new ParkingSpot("f2-c1", SpotType.CAR));
        floor2.addSpot(new ParkingSpot("f2-t1", SpotType.TRUCK));
        parkingLotSystem.addFloor(floor2);

        // 2. Simulate vehicle entries
        System.out.println("\n--- Vehicle Queue ---");

        Vehicle bike1 = new Bike("KA-01-HH-BIKE1-1234");
        Vehicle car1 = new Car("KA-01-HH-Car1-1234");
        Vehicle truck1 = new Truck("KA-01-HH-Truck1-1234");

        Vehicle bike2 = new Bike("KA-01-HH-BIKE2-1234-2");
        Vehicle car2 = new Car("KA-01-HH-Car2-1234");
        Vehicle truck2 = new Truck("KA-01-HH-Truck2-1234");

        Vehicle bike3 = new Bike("KA-01-HH-BIKE2-1234-2");

        System.out.println("\n--- check availability ---");
        Map<SpotType, List<ParkingSpot>> availabilityFloor1 = floor1.checkAvailability();
        Map<SpotType, List<ParkingSpot>> availabilityFloor2 = floor2.checkAvailability();
        Map<SpotType, List<ParkingSpot>> totalAvailability = parkingLotSystem.combineAvailability(List.of(availabilityFloor1, availabilityFloor2));
        for(Map.Entry<SpotType, List<ParkingSpot>> entry : totalAvailability.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().size() + " spots available");
        }

        System.out.println("\n--- Parking Vehicles ---");

        if(!totalAvailability.get(SpotType.BIKE).isEmpty()) {
            parkingLotSystem.parkVehicle(bike1);
            parkingLotSystem.parkVehicle(bike2);
            parkingLotSystem.parkVehicle(bike3);
        } else {
            System.out.println("No bike spot available");
        }

        if(!totalAvailability.get(SpotType.CAR).isEmpty()) {
            parkingLotSystem.parkVehicle(car1);
            parkingLotSystem.parkVehicle(car2);
        } else {
            System.out.println("No Car spot available");
        }

        if(!totalAvailability.get(SpotType.TRUCK).isEmpty()) {
            parkingLotSystem.parkVehicle(truck1);
            parkingLotSystem.parkVehicle(truck2);
        } else {
            System.out.println("No Truck spot available");
        }


        System.out.println("\n--- removing vehicle ---");

        parkingLotSystem.unParkVehicle(bike1);
        parkingLotSystem.unParkVehicle(car1);



    }
}
