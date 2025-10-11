package state;

import Controller.AtmMachineController;
import entities.Card;
import enums.OperationType;

public class IdleState implements AtmState {
    private AtmMachineController atmMachine;
    public IdleState(AtmMachineController atmMachine) {
       this.atmMachine = atmMachine;
    }
    @Override
    public void insertCard(String cardNumber) {
        Card card = atmMachine.getCard(cardNumber);
        if(card == null) {
            System.out.println("Card is invalid please try again");
            ejectCard();
            return;
        }
        atmMachine.setCurrentCard(card);
        atmMachine.changeState(new HaveCardState(atmMachine));
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Please insert card first");
    }

    @Override
    public void ejectCard() {
        System.out.println("Error: Card not found.");
        atmMachine.setCurrentCard(null);
    }

    @Override
    public void selectOperation(OperationType operationType, int... args) {
        System.out.println("Please insert card first");
    }
}
