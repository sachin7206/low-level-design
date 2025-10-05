package state;

import controller.CoffeeVendingMachineController;
import controller.Inventory;
import entities.Coffee;
import enums.IngredientEnum;

public class PaidState implements VendingMachineState {
    @Override
    public void selectCoffee(Coffee coffee, CoffeeVendingMachineController machine) {
        System.out.println("Coffee is already selected.");
    }

    @Override
    public void insertMoney(int amount, CoffeeVendingMachineController machine) {
        System.out.println("Payment already made. Dispensing coffee...");
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachineController machine) {
        Inventory inventory = Inventory.getInstance();
        if (!inventory.checkStockAvailable(machine.getSelectedCoffee().getRecipe())) {
            // stock not available, refund money and reset state
            machine.setState(new OutOfIngredientState());
            machine.getState().cancel(machine);
            return;
        }

        Coffee selectedCoffee = machine.getSelectedCoffee();
        selectedCoffee.prepare();
        inventory.deductIngredients(selectedCoffee.getRecipe());
        int change = machine.getMoneyInserted() - selectedCoffee.getPrice();
        if(change > 0) {
            System.out.println("Please collect your change: $" + change);
        }

        machine.reset();
        machine.setState(new ReadyState());
    }

    @Override
    public void cancel(CoffeeVendingMachineController machine) {
        System.out.println("Transaction cancelled. Refunding " + machine.getMoneyInserted());
        machine.reset();
        machine.setState(new ReadyState());
    }
}
