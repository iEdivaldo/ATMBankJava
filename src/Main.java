import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static long nextAccountNumber = 1000001;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        List<BankAccount> bankAccounts = new ArrayList<>();

        do {
            System.out.println("\n==== ATM Bank =======");
            System.out.println("1. Create Bank Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Consult the amount");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        BankAccount newAccount = createBankAccount(scanner);
                        if (newAccount != null) {
                            bankAccounts.add(newAccount);
                        }
                        break;
                    case 2:
                        BankAccount depositAccount = findAccount(scanner, bankAccounts);
                        if (depositAccount != null) {
                            deposit(scanner, depositAccount);
                        }
                        break;
                    case 3:
                        BankAccount withdrawAccount = findAccount(scanner, bankAccounts);
                        if (withdrawAccount != null) {
                            withdraw(scanner, withdrawAccount);
                        }
                        break;
                    case 4:
                        transfer(scanner, bankAccounts);
                        break;
                    case 5:
                        BankAccount consultAccount = findAccount(scanner, bankAccounts);
                        if (consultAccount != null) {
                            consultAccount.consultAmount();
                        }
                        break;
                    case 6:
                        System.out.println("Exit");
                        break;
                    default:
                        System.out.println("Invalid option");
                        System.out.println("Try again!");
                }
            } else {
                System.out.println("Invalid option");
                scanner.next();
            }

        } while (option != 6);
        scanner.close();
    }

    // Methods
    public static BankAccount createBankAccount(Scanner scanner) {
        System.out.println("Enter account name: ");
        scanner.nextLine();

        String accountHolder = scanner.nextLine();

        if (accountHolder.isBlank()) {
            System.out.println("Account holder cannot be empty!.");
            return null;
        }

        String accountNumber = String.valueOf(nextAccountNumber++);

        BankAccount account = new BankAccount(
                accountNumber, accountHolder
        );

        System.out.println("Account created successfully!");
        System.out.println("Account number: " + account.getAccountNumber());
        System.out.println("Account holder: " + account.getAccountHolder());

        return account;
    }

    public static void deposit(Scanner scanner, BankAccount account) {
        System.out.println("Enter amount to deposit: ");

        if (!scanner.hasNextBigDecimal()) {
            System.out.println("Amount cannot be negative!");
            scanner.next();
            return;
        }

        BigDecimal amount = scanner.nextBigDecimal();
        account.deposit(amount);
    }

    public static void withdraw(Scanner scanner, BankAccount account) {
        System.out.println("Enter amount to withdraw: ");

        if (!scanner.hasNextBigDecimal()) {
            System.out.println("Amount cannot be negative!");
            scanner.next();
            return;
        }

        BigDecimal amount = scanner.nextBigDecimal();
        account.withdraw(amount);
    }

    public static void transfer(Scanner scanner, List<BankAccount> accounts) {
        if (accounts.size() < 2) {
            System.out.println("Please enter the amount you would like to transfer!");
            return;
        }

        System.out.println("Enter source account number: ");
        String sourceAccountNumber = scanner.next();

        System.out.println("Enter destination account number: ");
        String destinationAccountNumber = scanner.next();

        BankAccount source = null;
        BankAccount destination = null;

        for  (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(sourceAccountNumber)) {
                source = account;
            }

            if (account.getAccountNumber().equals(destinationAccountNumber)) {
                destination = account;
            }
        }

        if (source == null) {
            System.out.println("Source account does not exist!");
            return;
        }

        if (destination == null) {
            System.out.println("Destination account does not exist!");
            return;
        }

        System.out.println("Enter transfer amount: ");
        if (!scanner.hasNextBigDecimal()) {
            System.out.println("Invalid amount!.");
            scanner.next();
            return;
        }

        BigDecimal transferAmount = scanner.nextBigDecimal();

        source.transfer(destination, transferAmount);

    }

    public static BankAccount findAccount(Scanner scanner, List<BankAccount> accounts) {
        System.out.println("Enter account number: ");
        String accountNumber = scanner.next();

        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        System.out.println("Account number " + accountNumber + " does not exist!");
        return null;
    }
}