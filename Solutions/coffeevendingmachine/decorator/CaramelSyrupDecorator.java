package decorator;

import entities.Coffee;
import enums.IngredientEnum;

import java.util.HashMap;
import java.util.Map;

public class CaramelSyrupDecorator extends CoffeeDecorator {
    public CaramelSyrupDecorator(Coffee coffee) {
        super(coffee);
        this.coffeeType = coffee.getCoffeeType() + " + Caramel syrup";
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice() + 20; // Adding cost of caramel syrup
    }

    @Override
    public Map<IngredientEnum, Integer> getRecipe() {
        Map<IngredientEnum, Integer> baseRecipe = new HashMap<>(decoratedCoffee.getRecipe());
        baseRecipe.put(IngredientEnum.CARAMEL_SYRUP, baseRecipe.getOrDefault(IngredientEnum.CARAMEL_SYRUP, 0) + 30);
        return baseRecipe;
    }

    @Override
    public void prepare() {
        // First, prepare the underlying coffee (e.g., the Latte with Sugar)
        super.prepare();
        // Then, add the specific step for this decorator
        System.out.println("- Drizzling Caramel Syrup on top.");
    }

}
