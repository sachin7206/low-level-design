package state;

import controller.CoffeeVendingMachineController;
import entities.Coffee;

public class SelectingState implements VendingMachineState {
    @Override
    public void selectCoffee(Coffee coffee, CoffeeVendingMachineController machine) {
        System.out.println("Coffee is already selected please pay.");
    }

    @Override
    public void insertMoney(int amount, CoffeeVendingMachineController machine) {
        Coffee selectedCoffee = machine.getSelectedCoffee();
        machine.setMoneyInserted(machine.getMoneyInserted() + amount);
        if(machine.getMoneyInserted() < selectedCoffee.getPrice()) {
            System.out.println("Insufficient funds. Please insert more : $" + (selectedCoffee.getPrice() - amount));
        } else {
            System.out.println("Payment of $" + machine.getMoneyInserted() + " received for " + selectedCoffee.getCoffeeType() + ". Dispensing coffee...");
            machine.setState(new PaidState());
        }
    }

    @Override
    public void dispenseCoffee(CoffeeVendingMachineController controller) {
        System.out.println("Please complete the payment first.");
    }

    @Override
    public void cancel(CoffeeVendingMachineController machine) {
        System.out.println("Transaction cancelled. Refunding " + machine.getMoneyInserted());
        machine.reset();
        machine.setState(new ReadyState());
    }
}
