import controller.CoffeeVendingMachineController;
import controller.Inventory;
import enums.CoffeeType;
import enums.IngredientEnum;
import enums.ToppingType;

import java.util.List;

public class CoffeeVendingMachineDemo {


    public static void main(String[] args) {
        CoffeeVendingMachineController machine = CoffeeVendingMachineController.getInstance();
        Inventory inventory = Inventory.getInstance();

        // --- Initial setup: Refill inventory ---
        System.out.println("=== Initializing Vending Machine ===");
        inventory.addStock(IngredientEnum.COFFEE_BEANS, 50);
        inventory.addStock(IngredientEnum.WATER, 500);
        inventory.addStock(IngredientEnum.MILK, 200);
        inventory.addStock(IngredientEnum.SUGAR, 100);
        inventory.addStock(IngredientEnum.CARAMEL_SYRUP, 50);
        inventory.printInventory();

        // --- Scenario 1: Successful Purchase of a Latte ---
        System.out.println("\n--- SCENARIO 1: Buy a Latte (Success) ---");
        machine.selectCoffee(CoffeeType.LATTE, List.of());
        machine.insertMoney(40);
        machine.insertMoney(60); // Total 100, price is 90 -> return 10
        machine.dispenseCoffee();
        inventory.printInventory();

        // --- Scenario 2: Purchase with Insufficient Funds & Cancellation ---
        System.out.println("\n--- SCENARIO 2: Buy Espresso (Insufficient Funds & Cancel) ---");
        machine.selectCoffee(CoffeeType.ESPRESSO, List.of());
        machine.insertMoney(70); // Price is 80
        machine.dispenseCoffee(); // Should fail
        machine.cancel(); // Should refund 70
        inventory.printInventory(); // Should be unchanged


        // --- Scenario 3: Attempt to Buy with Insufficient Ingredients ---
        System.out.println("\n--- SCENARIO 3: Buy Cappuccino (Out of Milk) ---");
        inventory.printInventory();
        machine.selectCoffee(CoffeeType.CAPPUCCINO, List.of(ToppingType.CARAMEL_SYRUP, ToppingType.EXTRA_SUGAR));
        machine.insertMoney(300);
        machine.dispenseCoffee(); // Should fail and refund
        inventory.printInventory();

        // --- Refill and final test ---
        System.out.println("\n--- REFILLING AND FINAL TEST ---");
        inventory.addStock(IngredientEnum.MILK, 200);
        inventory.printInventory();
        machine.selectCoffee(CoffeeType.CAPPUCCINO, List.of(ToppingType.CARAMEL_SYRUP, ToppingType.EXTRA_SUGAR));
        machine.insertMoney(300);
        machine.dispenseCoffee(); // Should fail and refund
        inventory.printInventory();
    }
}
