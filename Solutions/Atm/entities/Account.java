package entities;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private final String accountNumber;
    private int balance;
    private Map<String, Card> accountCardMap;

    public Account(String accountNumber, int amount) {
        this.accountNumber = accountNumber;
        this.balance = amount;
        accountCardMap = new HashMap<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public boolean withdraw(int amount) {
        this.balance -= amount;
        return true;
    }

    public Map<String, Card> getCards() {
        return accountCardMap;
    }
}
