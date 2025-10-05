package entities;

import enums.IngredientEnum;

import java.util.UUID;

public class Ingredient {
    private final String id;
    private IngredientEnum name;
    private int quantity;

    public Ingredient(IngredientEnum name, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.quantity = quantity;
    }

    public IngredientEnum getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getId() {
        return id;
    }
}
