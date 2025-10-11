package entities;

import chainOfResponsibilities.DispenseChain;

public class CashDispenser {
    DispenseChain chain;
    public CashDispenser(DispenseChain chain) {
        this.chain = chain;
    }
    public boolean isSufficientCashAvailable(int amount) {
        if(amount % 10 != 0) {
            System.out.println("Please enter the amount in multiples of 10.");
            return false;
        }
        return chain.canDispenseCash(amount);
    }

    public void dispenseCash(int amount) {
        chain.dispenseCash(amount);
    }
}
