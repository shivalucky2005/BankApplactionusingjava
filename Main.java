import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

class Bank {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void createAccount(String accountNumber, String holderName, double initialDeposit) {
        BankAccount account = new BankAccount(accountNumber, holderName, initialDeposit);
        accounts.put(accountNumber, account);
        System.out.println("Account created for " + holderName);
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        BankAccount from = accounts.get(fromAccount);
        BankAccount to = accounts.get(toAccount);

        if (from != null && to != null && from.getBalance() >= amount) {
            from.withdraw(amount);
            to.deposit(amount);
            System.out.println("Transferred: $" + amount + " from " + from.getHolderName() + " to " + to.getHolderName());
        } else {
            System.out.println("Transfer failed. Check account numbers or balance.");
        }
    }

    public void showAccountDetails(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Holder: " + account.getHolderName());
            System.out.println("Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("Simple Banking System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Account Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter Account Holder Name: ");
                    String accountHolder = scanner.next();
                    System.out.print("Enter Initial Deposit: ");
                    double initialDeposit = scanner.nextDouble();
                    bank.createAccount(accountNumber, accountHolder, initialDeposit);
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    BankAccount account = bank.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter Deposit Amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    account = bank.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter Withdrawal Amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        account.withdraw(withdrawalAmount);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter From Account: ");
                    String fromAccount = scanner.next();
                    System.out.print("Enter To Account: ");
                    String toAccount = scanner.next();
                    System.out.print("Enter Amount to Transfer: ");
                    double transferAmount = scanner.nextDouble();
                    bank.transfer(fromAccount, toAccount, transferAmount);
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scanner.next();
                    bank.showAccountDetails(accountNumber);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
