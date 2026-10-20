import java.math.BigDecimal;

public class BankAccount {

    private String accountNumber;
    private String accountHolder;
    private BigDecimal balance;

    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = BigDecimal.ZERO;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid amount");
            return;
        }

        balance = balance.add(amount);
        System.out.println("Deposited " + amount + " to balance with successfully!");
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid amount");
            return;
        }
        if (balance.compareTo(amount) < 0) {
            System.out.println("Insufficient balance");
            return;
        }

        balance = balance.subtract(amount);
        System.out.println("Withdrawn " + amount + " to balance with successfully!");
    }

    public void consultAmount() {
        System.out.println("All informations about the account");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: " + balance);
    }
}
