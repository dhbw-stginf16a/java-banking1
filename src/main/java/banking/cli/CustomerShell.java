package banking.cli;

import banking.backend.Bank;
import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.persons.Customer;
import com.budhash.cliche.Command;
import com.budhash.cliche.Shell;
import com.budhash.cliche.ShellDependent;

import java.util.List;

public class CustomerShell implements ShellDependent {
    Shell shell;
    private Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Command
    public String showAccounts() {
        List<Account> accounts = Bank.getInstance().getCustomerAccounts(customer);
        TextTable table = new TextTable(5);
        table.setHeader("Id", "Balance", "Holder");
        for (Account account : accounts) {
            table.addRow(account.getAccountId(), account.getBalance(), account.getHolder());
        }
        return table.toString();
    }

    @Command
    public String withdraw(String accountId, String amount) {
        Account account = Bank.getInstance().getAccount(new AccountId(accountId));
        if (account != null) {
            customer.withdraw(account, new Money(amount));
        } else {
            // TODO: account doesnt exist
        }
        return "";
    }

    @Command
    public String showBalance(String accountId) {
        // TODO: check if account is owned by customer
        Account account = Bank.getInstance().getAccount(new AccountId(accountId));
        return String.format("%s has a balance of %s", accountId, account.getBalance());
    }

    @Command
    public String invoice(String fromId, String toId, String amount) {
        Bank bank = Bank.getInstance();
        customer.invoice(bank.getAccount(new AccountId(fromId)), new AccountId(toId), new Money(amount));
        // TODO: check if account exists, invoice fails
        return String.format("Invoice from %s to %s with a value of %s", fromId, toId, amount);
    }

    @Override
    public void cliSetShell(Shell theShell) {
        shell = theShell;
    }
}
