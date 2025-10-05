package decorator;

import entities.Coffee;
import enums.IngredientEnum;

import java.util.HashMap;
import java.util.Map;

public class ExtraSugarDecorator extends CoffeeDecorator {
    public ExtraSugarDecorator(Coffee coffee) {
        super(coffee);
        this.coffeeType = coffee.getCoffeeType() + " + Extra Sugar";
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice() + 5; // Adding cost of extra sugar
    }

    @Override
    public Map<IngredientEnum, Integer> getRecipe() {
        Map<IngredientEnum, Integer> baseRecipe = new HashMap<>(decoratedCoffee.getRecipe());
        baseRecipe.put(IngredientEnum.SUGAR, baseRecipe.getOrDefault(IngredientEnum.SUGAR, 0) + 2);
        return baseRecipe;
    }

    @Override
    public void prepare() {
        // First, prepare the underlying coffee (e.g., the Latte with Sugar)
        super.prepare();
        // Then, add the specific step for this decorator
        System.out.println("- Adding extra sugar.");
    }


}
