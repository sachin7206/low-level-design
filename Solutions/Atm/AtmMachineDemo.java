import Controller.AtmMachineController;
import enums.OperationType;

public class AtmMachineDemo {
    public static void main(String[] args) {
        AtmMachineController atmMachineController = AtmMachineController.getInstance();

        // Test case 1 - Valid card and pin, check balance
        atmMachineController.insertCard("1234-5678-9101-2344");
        atmMachineController.enterPin("1234");
        atmMachineController.selectOperation(OperationType.CHECK_BALANCE); // 1000

        // Test case 2 - Valid card and pin, Deposit money
        atmMachineController.insertCard("1234-5678-9101-2344");
        atmMachineController.enterPin("1234");
        atmMachineController.selectOperation(OperationType.DEPOSIT,  500); // Deposit $500
//
        // Test case 3 - Valid card and pin, check balance again
        atmMachineController.insertCard("1234-5678-9101-2344");
        atmMachineController.enterPin("1234");
        atmMachineController.selectOperation(OperationType.CHECK_BALANCE); // 1500

        // Test case 4 - Valid card and pin, Withdraw money
        atmMachineController.insertCard("1234-5678-9101-2344");
        atmMachineController.enterPin("1234");
        atmMachineController.selectOperation(OperationType.WITHDRAW, 1000); // Withdraw $1000

        // Test case 4 - Valid card and pin, Withdraw money - Insufficient funds in atm
        atmMachineController.insertCard("1234-5678-9101-2344");
        atmMachineController.enterPin("1234");
        atmMachineController.selectOperation(OperationType.WITHDRAW, 1000); // Withdraw $1000 - Insufficient funds in atm

        // Test case 5 - InValid card
        atmMachineController.insertCard("1234-5678-9101-2346");

        // Test case 6 - InValid Pin
        atmMachineController.insertCard("1234-5678-9101-2344");
        atmMachineController.enterPin("1235");


    }
}
