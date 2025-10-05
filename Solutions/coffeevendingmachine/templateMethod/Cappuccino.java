package templateMethod;

import entities.Coffee;
import entities.Ingredient;
import enums.IngredientEnum;

import java.util.Map;

public class Cappuccino extends Coffee {
    public Cappuccino() {
        this.coffeeType = "Cappuccino";
    }

    @Override
    public void addCondiments() {
        System.out.println("- Adding steamed milk and foam.");
    }

    @Override
    public int getPrice() {
        return 100;
    }

    @Override
    public Map<IngredientEnum, Integer> getRecipe() {
        return Map.of(IngredientEnum.COFFEE_BEANS, 7, IngredientEnum.WATER, 30, IngredientEnum.MILK, 100, IngredientEnum.SUGAR, 5);
    }

}
