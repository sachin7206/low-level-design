package state;

import Controller.AtmMachineController;
import enums.OperationType;

public class HaveCardState implements AtmState {
    private AtmMachineController atmMachine;
    public HaveCardState(AtmMachineController atmMachine) {
        this.atmMachine = atmMachine;
    }
    @Override
    public void insertCard(String cardNumber) {
        System.out.println("Card is already been inserted please enter pin");
    }

    @Override
    public void enterPin(String pin) {
        boolean checkPin = atmMachine.AuthenticateCard(pin);
        if(checkPin) {
            System.out.println("Pin is correct");
            atmMachine.changeState(new AuthenticatedState(atmMachine));
        } else {
            System.out.println("Pin is incorrect please try again");
            ejectCard();
        }
    }

    @Override
    public void ejectCard() {
        System.out.println("Card has been ejected. Thank you for using our ATM.");
        atmMachine.setCurrentCard(null);
        atmMachine.changeState(new IdleState(atmMachine));
    }

    @Override
    public void selectOperation(OperationType operationType, int... args) {
        System.out.println("Please enter pin first");
    }
}
