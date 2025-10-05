package controller;

import decorator.CaramelSyrupDecorator;
import decorator.ExtraSugarDecorator;
import entities.Coffee;
import enums.CoffeeType;
import enums.ToppingType;
import factory.CoffeeFactory;
import state.ReadyState;
import state.VendingMachineState;

import java.util.List;

public class CoffeeVendingMachineController {
    private static CoffeeVendingMachineController instance;
    private VendingMachineState state;
    private Coffee selectedCoffee;
    private int moneyInserted;
    private CoffeeVendingMachineController() {
        state = new ReadyState();
    }

    public static synchronized CoffeeVendingMachineController getInstance() {
        if (instance == null) {
            instance = new CoffeeVendingMachineController();
        }
        return instance;
    }

    public void selectCoffee(CoffeeType type, List<ToppingType> toppings) {
        Coffee coffee = CoffeeFactory.createCoffee(type);
        for (ToppingType toppingType : toppings) {
            switch (toppingType) {
                case EXTRA_SUGAR:
                    coffee = new ExtraSugarDecorator(coffee);
                    break;
                case CARAMEL_SYRUP:
                    coffee = new CaramelSyrupDecorator(coffee);
                    break;
            }
        }

        // Let the state handle the rest
        state.selectCoffee(coffee, this);
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public VendingMachineState getState() {
        return state;
    }

    public void insertMoney(int amount) {
        state.insertMoney(amount, this);
    }

    public void setSelectedCoffee(Coffee coffee) {
        this.selectedCoffee = coffee;
    }

    public Coffee getSelectedCoffee() {
        return selectedCoffee;
    }

    public void setMoneyInserted(int amount) {
        this.moneyInserted = amount;
    }

    public int getMoneyInserted() {
        return moneyInserted;
    }

    public void reset() {
        this.selectedCoffee = null;
        this.moneyInserted = 0;
    }

    public void dispenseCoffee() {
        state.dispenseCoffee(this);
    }

    public void cancel(){
        state.cancel(this);
    }


}
