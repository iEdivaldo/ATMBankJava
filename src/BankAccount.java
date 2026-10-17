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
}
