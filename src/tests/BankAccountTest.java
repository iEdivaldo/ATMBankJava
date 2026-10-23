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
        assertEquals(0, BigDecimal.ZERO.compareTo(account.getBalance()));
    }

    @Test
    void shouldDepositMoneySuccessfully() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.deposit(new BigDecimal(100));

        assertEquals(0, new BigDecimal("100").compareTo(account.getBalance()));
    }

    @Test
    void shouldRejectZeroDeposit() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.deposit(new BigDecimal(0));

        assertEquals(0, BigDecimal.ZERO.compareTo(account.getBalance()));
    }

    @Test
    void shouldRejectNegativeDeposit() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.deposit(new BigDecimal(-100));

        assertEquals(0, BigDecimal.ZERO.compareTo(account.getBalance()));
    }

    @Test
    void shouldWithdrawMoneySuccessfully() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.deposit(new BigDecimal(150));
        account.withdraw(new BigDecimal(100));

        assertEquals(0, new BigDecimal("50").compareTo(account.getBalance()));
    }

    @Test
    void shouldRejectZeroWithdraw() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.withdraw(new BigDecimal(0));

        assertEquals(0, BigDecimal.ZERO.compareTo(account.getBalance()));
    }

    @Test
    void shouldRejectWithdrawalWithInsufficientBalance() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.deposit(new BigDecimal(100));

        account.withdraw(new BigDecimal(500));

        assertEquals(0, new BigDecimal(100).compareTo(account.getBalance()));
    }

    @Test
    void shouldTransferMoneySuccessfully() {
        BankAccount source = new BankAccount("101", "Edivaldo");
        BankAccount destination = new BankAccount("102", "Joao");

        source.deposit(new BigDecimal(2000));
        destination.deposit(new BigDecimal(9999));

        destination.transfer(source, new BigDecimal(999));

        assertEquals(0, new BigDecimal("2999").compareTo(source.getBalance()));
        assertEquals(0, new BigDecimal("9000").compareTo(destination.getBalance()));
    }

    @Test
    void shouldTransferMoneyWithInsufficientBalance() {
        BankAccount source = new BankAccount("101", "Edivaldo");
        BankAccount destination = new BankAccount("102", "Joao");

        source.deposit(new BigDecimal(2000));

        source.transfer(destination, new BigDecimal(9999));

        assertEquals(0, new BigDecimal("2000").compareTo(source.getBalance()));
        assertEquals(0, BigDecimal.ZERO.compareTo(destination.getBalance()));
    }

    @Test
    void shouldRejectTransferToSameAccount() {
        BankAccount source = new BankAccount("101", "Edivaldo");

        source.deposit(new BigDecimal(2000));
        source.transfer(source, new BigDecimal(100));

        assertEquals(0, new BigDecimal("2000").compareTo(source.getBalance()));
    }

    @Test
    void shouldConsultAmount() {
        BankAccount account = new BankAccount("101", "Edivaldo");

        account.deposit(new BigDecimal(100));

        assertEquals("Edivaldo",  account.getAccountHolder());
        assertEquals("101", account.getAccountNumber());
        assertEquals(0, new BigDecimal("100").compareTo(account.getBalance()));
    }
}

