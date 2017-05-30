package banking.cli;

import banking.backend.Bank;
import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.persons.Customer;
import banking.backend.transactions.TransactionFailedException;
import com.budhash.cliche.Command;
import com.budhash.cliche.Shell;
import com.budhash.cliche.ShellDependent;

import java.util.List;

/**
 * The command line shell to be used after authorization by a customer.
 */
public class CustomerShell implements ShellDependent {
    Shell shell;
    private Customer customer;

    /**
     * Assign the customer currently logged in.
     *
     * @param customer logged in customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Show all the account's customer
     *
     * @return the string to be displayed
     */
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

    /**
     * Withdraw money from an account.
     *
     * @param accountId
     * @param amount
     * @return the string to be displayed
     */
    @Command
    public String withdraw(String accountId, String amount) {
        Account account = Bank.getInstance().getAccount(new AccountId(accountId));
        if (account != null) {
            try {
                Money money = new Money(amount);
            } catch (NumberFormatException e) {
                return "This is not a valid format. This is: XXX,XXX.XX€";
            }
            customer.withdraw(account, new Money(amount));
            return String.format("Here you have your %s", amount);
        } else {
            return "Account doesn't exist";
        }
    }

    /**
     * Show the balance of an account belonging to this customer.
     *
     * @param accountId the account id
     * @return the string to be displayed
     */
    @Command
    public String showBalance(String accountId) {
        Account account = Bank.getInstance().getAccount(new AccountId(accountId));
        if (account == null) {
            return "This account does not exist.";
        } else if (!account.getHolder().equals(customer)) {
            return "This account does not belong to you.";
        } else {
            return String.format("%s has a balance of %s", accountId, account.getBalance());
        }
    }

    /**
     * Send an invoice from one account of the customer to any other.
     *
     * @param fromId the id of the account from which the money is drawn
     * @param toId   the id of the account to which the money is transferred
     * @param amount the value of the invoice
     * @return the string to be displayed
     */
    @Command
    public String invoice(String fromId, String toId, String amount) {
        Bank bank = Bank.getInstance();
        try {
            customer.invoice(bank.getAccount(new AccountId(fromId)), new AccountId(toId), new Money(amount));
            return String.format("Invoice sent from %s to %s with a value of %s", fromId, toId, amount);
        } catch (TransactionFailedException e) {
            return e.getMessage();
        }
    }

    /**
     * This method informs the object about the Shell operating it.
     * Is called upon object's registration in Shell.
     * @param theShell Shell running the object.
     */
    @Override
    public void cliSetShell(Shell theShell) {
        shell = theShell;
    }
}
