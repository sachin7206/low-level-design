package templateMethod;

import entities.Coffee;
import enums.IngredientEnum;

import java.util.Map;

public class Espresso extends Coffee  {
    public Espresso() {
        this.coffeeType = "Espresso";
    }

    @Override
    public void addCondiments() { /* No extra condiments for espresso */ }

    @Override
    public int getPrice() {
        return 80;
    }

    @Override
    public Map<IngredientEnum, Integer> getRecipe() {
        return Map.of(IngredientEnum.COFFEE_BEANS, 10, IngredientEnum.WATER, 30);
    }
}
