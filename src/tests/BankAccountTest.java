package tests;

import atmbank.BankAccount;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {

    @Test
    void shouldCreateAccount() {
        BankAccount account = new BankAccount("101", "Edivaldo");
        assertEquals("101", account.getAccountNumber());
        assertEquals("Edivaldo", account.getAccountHolder());
        assertEquals(0, new BigDecimal("0").compareTo(account.getBalance()));
        
    }

    @Test
    void shouldDepositMoneySuccessfully() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.deposit(new BigDecimal(100));
        assertEquals(0, new BigDecimal("100").compareTo(account.getBalance()));

    }

    @Test
    void shouldWithdrawMoneySuccessfully() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.deposit(new BigDecimal(150));
        account.withdraw(new BigDecimal(100));
        assertEquals(0, new BigDecimal("50").compareTo(account.getBalance()));
    }

    @Test
    void shouldConsultAmount() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.deposit(new BigDecimal(100));
        assertEquals("Edivaldo",  account.getAccountHolder());
        assertEquals("101", account.getAccountNumber());
        assertEquals(0, new BigDecimal("100").compareTo(account.getBalance()));
    }

    @Test
    void shouldTransferMoneySuccessfully() {
        BankAccount account1 = new BankAccount("101", "Edivaldo");
        BankAccount account2 = new BankAccount("102", "Joao");

        account1.deposit(new BigDecimal(2000));
        account2.deposit(new BigDecimal(9999));

        account2.transfer(account1, new BigDecimal(999));

        assertEquals(0, new BigDecimal("2999").compareTo(account1.getBalance()));
        assertEquals(0, new BigDecimal("9000").compareTo(account2.getBalance()));
    }
}

