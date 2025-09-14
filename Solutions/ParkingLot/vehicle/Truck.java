package vehicle;

import enums.SpotType;

public class Truck implements Vehicle {
    static SpotType type = SpotType.TRUCK;
    private String number;
    public Truck(String number) {
        this.number = number;
    }
    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public SpotType getSpotType() {
        return type;
    }
}
