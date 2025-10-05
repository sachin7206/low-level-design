package state;

import controller.CoffeeVendingMachineController;
import entities.Coffee;

public interface VendingMachineState {
    void selectCoffee(Coffee coffee, CoffeeVendingMachineController machine);
    void insertMoney(int amount, CoffeeVendingMachineController machine);
    void dispenseCoffee(CoffeeVendingMachineController machine);
    void cancel(CoffeeVendingMachineController machine);
}
