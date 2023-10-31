import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void displayMenu() {
        System.out.println("Welcome to ATM")
        System.out.println("Please select any one option from below:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void checkBalance() {
        double balance = account.getBalance();
        System.out.println("Your account balance is: RS." + balance);
    }

    public void deposit(double amount) {
        account.deposit(amount);
        System.out.println("Deposited Rs." + amount + " into your account.");
    }

    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawn Rs." + amount + " from your account.");
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
    }
}

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double initialBalance = 1000.0; // Initial balance, change as needed
        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);

        while (true) {
            atm.displayMenu();
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: Rs.");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter the withdrawal amount: Rs.");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount >= 0) {
                        atm.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid amount. Please enter a positive value.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the ATM. Thank you!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option (1-4).");
            }
        }
    }
}
