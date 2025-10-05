package controller;

import entities.Ingredient;
import enums.IngredientEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private static Inventory instance;
    private final Map<IngredientEnum, Integer> stock;

    private Inventory() {
        stock = new ConcurrentHashMap<>();
    }

    public static synchronized Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void addStock(IngredientEnum ingredientName, int quantity) {
        Ingredient ingredient = new Ingredient(ingredientName, quantity);
        stock.put(ingredientName, stock.getOrDefault(ingredientName, 0) + quantity);
    }

    public void printInventory() {
        System.out.println("--- Current Inventory ---");
        stock.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("-------------------------");
    }

    public boolean checkStockAvailable(Map<IngredientEnum, Integer> recipe) {
        for (Map.Entry<IngredientEnum, Integer> entry : recipe.entrySet()) {
            IngredientEnum ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            if(stock.containsKey(ingredient)) {
                int currentQuantity = stock.get(ingredient);
                if (currentQuantity < requiredQuantity) {
                    System.out.println("Not enough " + ingredient + " in stock!");
                    return false;
                }
            } else {
                System.out.println(ingredient + " is not available in stock!");
                return false;
            }
        }
        return true;
    }

    public void deductIngredients(Map<IngredientEnum, Integer> recipe) {
        for (Map.Entry<IngredientEnum, Integer> entry : recipe.entrySet()) {
            IngredientEnum ingredient = entry.getKey();
            int requiredQuantity = entry.getValue();
            stock.put(ingredient, stock.get(ingredient) - requiredQuantity);
        }
    }
}
