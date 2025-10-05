package state;

import controller.CoffeeVendingMachineController;
import entities.Coffee;

public class OutOfIngredientState implements VendingMachineState {
    @Override
    public void selectCoffee(Coffee c, CoffeeVendingMachineController m) {
        System.out.println("Sorry, the machine is out of ingredients.");
    }

    @Override
    public void insertMoney(int a, CoffeeVendingMachineController m) {
        System.out.println("Sorry, the machine is out of ingredients. Money refunded.");
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachineController m) {
        System.out.println("Sorry, the machine is out of ingredients.");
    }

    @Override
    public void cancel(CoffeeVendingMachineController machine) {
        System.out.println("Refunding " + machine.getMoneyInserted());
        machine.reset();
        machine.setState(new ReadyState());
    }
}
