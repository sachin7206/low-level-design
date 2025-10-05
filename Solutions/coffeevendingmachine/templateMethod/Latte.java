package templateMethod;

import entities.Coffee;
import enums.IngredientEnum;

import java.util.Map;

public class Latte extends Coffee {
    public Latte() {
        this.coffeeType = "Latte";
    }

    @Override
    public void addCondiments() {
        System.out.println("- Adding steamed milk.");
    }

    @Override
    public int getPrice() {
        return 90;
    }

    @Override
    public Map<IngredientEnum, Integer> getRecipe() {
        return Map.of(IngredientEnum.COFFEE_BEANS, 7, IngredientEnum.WATER, 30, IngredientEnum.MILK, 150, IngredientEnum.SUGAR, 5);
    }
}
