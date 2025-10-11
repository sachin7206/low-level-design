package state;
import Controller.AtmMachineController;
import enums.OperationType;

public interface AtmState {
    void insertCard(String cardNumber);
    void enterPin(String pin);
    void ejectCard();
    void selectOperation(OperationType operationType, int... args);
}
