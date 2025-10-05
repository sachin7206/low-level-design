package state;

import controller.CoffeeVendingMachineController;
import entities.Coffee;

public class ReadyState implements VendingMachineState {
    @Override
    public void selectCoffee(Coffee coffee, CoffeeVendingMachineController machine) {
        System.out.println("Coffee selected: " + coffee.getCoffeeType() + " | Price: $" + coffee.getPrice());
        machine.setState(new SelectingState());
        machine.setSelectedCoffee(coffee);
    }

    @Override
    public void insertMoney(int amount, CoffeeVendingMachineController machine) {
        System.out.println("Please select a coffee first.");
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachineController controller) {
        System.out.println("No coffee selected to dispense.");
    }

    @Override
    public void cancel(CoffeeVendingMachineController machine) {
        machine.reset();
        machine.setState(new ReadyState());
    }

}
