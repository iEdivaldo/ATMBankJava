import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static long nextAccountNumber = 1000001;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        BankAccount account = null;

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
                        if (account == null) {
                            account = createBankAccount(scanner);
                        } else {
                            System.out.println("Account already exists");
                        }
                        break;
                    case 2:
                        if (account != null) {
                            deposit(scanner, account);
                        } else {
                            System.out.println("Please create a bank account first");
                        }
                        break;
                    case 3:
                        if (account != null) {
                            withdraw(scanner, account);
                        }  else {
                            System.out.println("Please create a bank account first");
                        }
                        break;
                    case 4:
                        System.out.println("Transfer Money");
                        break;
                    case 5:
                        if (account != null) {
                            detailsAmount(account);
                        } else {
                            System.out.println("Please create a bank account first");
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

    public static void detailsAmount(BankAccount account) {
        account.consultAmount();
    }
}