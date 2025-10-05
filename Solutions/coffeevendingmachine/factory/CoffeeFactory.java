package factory;

import entities.Coffee;
import enums.CoffeeType;
import templateMethod.Cappuccino;
import templateMethod.Espresso;
import templateMethod.Latte;

public class CoffeeFactory {
    public static Coffee createCoffee(CoffeeType type) {
        return switch (type) {
            case CoffeeType.ESPRESSO -> new Espresso();
            case CoffeeType.LATTE -> new Latte();
            case CoffeeType.CAPPUCCINO -> new Cappuccino();
            default -> throw new IllegalArgumentException("Unknown coffee type: " + type);
        };
    }
}
