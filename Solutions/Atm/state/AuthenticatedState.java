package state;

import Controller.AtmMachineController;
import enums.OperationType;

public class AuthenticatedState implements AtmState {
    private AtmMachineController atmMachine;
    public AuthenticatedState(AtmMachineController atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void insertCard(String cardNumber) {
        System.out.println("A card is already authenticated. Please complete your transactions or eject the card.");
    }

    @Override
    public void enterPin(String pin) {
        System.out.println("Card is already authenticated. You can proceed with your transactions.");
    }

    @Override
    public void ejectCard() {
        System.out.println("Card has been ejected. Thank you for using our ATM.");
        atmMachine.setCurrentCard(null);
        atmMachine.changeState(new IdleState(atmMachine));
    }

    @Override
    public void selectOperation(OperationType operationType, int... args) {
        switch(operationType) {
            case WITHDRAW:
                if(args.length == 0 || args[0] <= 0) {
                    System.out.println("Invalid Withdraw amount.");
                    return;
                }
                atmMachine.withdraw(args[0]);
                break;
            case DEPOSIT:
                if(args.length == 0 || args[0] <= 0) {
                    System.out.println("Invalid deposit amount.");
                    return;
                }
                atmMachine.deposit(args[0]);
                break;
            case CHECK_BALANCE:
                atmMachine.checkBalance();
                break;
            default:
                System.out.println("Invalid operation selected.");
        }
        System.out.println("Transaction complete.");
        ejectCard();

    }
}
