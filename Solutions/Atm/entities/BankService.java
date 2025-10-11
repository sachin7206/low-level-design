package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final Map<String, Card> cards = new ConcurrentHashMap<>();
    private final Map<Card, Account> cardAccountMap = new ConcurrentHashMap<>();
    public BankService() {
        Card card = createCard("1234-5678-9101-2344", "1234");
        Account account = createAccount("3623782187382", 1000);
        linkCardToAccount(card, account);
    }

    public Card createCard(String cardNumber, String pin) {
        Card card = new Card(cardNumber, pin);
        cards.put(cardNumber, card);
        return card;
    }

    public Account createAccount(String accountNumber, int amount) {
        return new Account(accountNumber, amount);
    }

    private void linkCardToAccount(Card card, Account account) {
        account.getCards().put(card.getCardNumber(), card);
        cardAccountMap.put(card, account);
    }

    public boolean AuthenticateCard(Card card, String pin) {
        if (card.getPin().equalsIgnoreCase(pin)) {
           return true;
        }
        return false;
    }

    public Card getCard(String cardNumber) {
        return cards.getOrDefault(cardNumber, null);
    }

    public synchronized int getBalance(Card card) {
        Account account = cardAccountMap.get(card);
        if(account == null) {
            System.out.println("No account linked to this card");
            return 0;
        }
        return account.getBalance();
    }

    public synchronized void deposit(Card card, int amount) {
        Account account = cardAccountMap.get(card);
        if(account == null) {
            System.out.println("No account linked to this card");
            return;
        }
        account.deposit(amount);
        System.out.println("Deposited $" + amount + " successfully.");
    }

    public synchronized boolean withdraw(Card card, int amount) {
        Account account = cardAccountMap.get(card);
        if(account == null) {
            System.out.println("No account linked to this card");
            return false;
        }
        if(account.getBalance() < amount) {
            System.out.println("Insufficient balance");
            return false;
        }
        account.withdraw(amount);
        return true;
    }


}
