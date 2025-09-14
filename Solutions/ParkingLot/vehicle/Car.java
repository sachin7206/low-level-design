package vehicle;

import enums.SpotType;

public class Car implements Vehicle {
    static SpotType type = SpotType.CAR;
    private String number;
    public Car(String number) {
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
