package Controller;

import chainOfResponsibilities.DispenseChain;
import chainOfResponsibilities.NoteDispenser100;
import chainOfResponsibilities.NoteDispenser20;
import chainOfResponsibilities.NoteDispenser50;
import entities.BankService;
import entities.Card;
import entities.CashDispenser;
import enums.OperationType;
import state.AtmState;
import state.IdleState;

public class AtmMachineController {
    private static AtmMachineController instance = null;
    AtmState atmCurrentState;
    private BankService bankService;
    private Card currentCard;
    CashDispenser cashDispenser;

    private AtmMachineController() {
        atmCurrentState = new IdleState(this);
        bankService = new BankService();
        // Setup the dispenser chain
        DispenseChain c1 = new NoteDispenser100(10); // 10 x $100 notes
        DispenseChain c2 = new NoteDispenser50(20); // 20 x $50 notes
        DispenseChain c3 = new NoteDispenser20(30); // 30 x $20 notes
        cashDispenser = new CashDispenser(c1);
        // private constructor to prevent instantiation
    }

    public static AtmMachineController getInstance() {
        if(instance == null) {
            instance = new AtmMachineController();
        }
        return instance;
    
    }

    public void insertCard(String cardNumber) {
        atmCurrentState.insertCard(cardNumber);
    }

    public void enterPin(String pin) {
        atmCurrentState.enterPin(pin);
    }

    public void selectOperation(OperationType operationType, int... args) {
        atmCurrentState.selectOperation(operationType, args);
    }

    public void deposit(int amount) {
        bankService.deposit(currentCard, amount);
    }

    public void withdraw(int amount) {
        if(cashDispenser.isSufficientCashAvailable(amount)) {
            boolean success = bankService.withdraw(currentCard, amount);
            if(success) {
                try {
                    cashDispenser.dispenseCash(amount);
                } catch (Exception e) {
                    System.out.println("Error dispensing cash: " + e.getMessage());
                    // Rollback the transaction
                    bankService.deposit(currentCard, amount);
                }
            } else {
                System.out.println("Transaction failed due to insufficient funds in your account.");
            }
        } else {
            System.out.println("insufficient cash in ATM Or Please enter the amount multiple of 10.");
        }



    }

    public void checkBalance() {
        int balance = bankService.getBalance(currentCard);
        System.out.println("Your current balance is: $" + balance);
    }

    public void changeState(AtmState newState) {
        atmCurrentState = newState;
    }

    public boolean AuthenticateCard(String pin) {
        return bankService.AuthenticateCard(currentCard, pin);
    }

    public Card getCard(String cardNumber) {
       return bankService.getCard(cardNumber);
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }


}
