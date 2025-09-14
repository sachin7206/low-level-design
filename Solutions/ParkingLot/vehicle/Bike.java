package vehicle;

import enums.SpotType;

public class Bike implements Vehicle {
    static SpotType type = SpotType.BIKE;
    private String number;
    public Bike(String number) {
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
