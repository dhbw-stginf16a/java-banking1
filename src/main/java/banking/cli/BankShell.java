package banking.cli;

import banking.NotYetImplementedException;
import banking.backend.Bank;
import banking.backend.DateTime;
import banking.backend.Money;
import banking.backend.accounts.Account;
import banking.backend.accounts.AccountId;
import banking.backend.persons.Customer;
import banking.backend.persons.CustomerId;
import banking.backend.transactions.Transaction;
import banking.backend.transactions.TransactionFailedException;
import com.budhash.cliche.Command;
import com.budhash.cliche.Shell;
import com.budhash.cliche.ShellDependent;
import com.budhash.cliche.ShellFactory;

import java.io.IOException;
import java.util.List;

public class BankShell implements ShellDependent {
    private String name;
    private Shell shell;
    private CustomerShell customerShell;

    public BankShell(String name) {
        this.name = name;
    }

    public static void start() throws IOException {
        String name = "bank";
        ShellFactory.createConsoleShell(name, name, new BankShell("name#" + name))
                .commandLoop();
    }

    @Command
    public String showAccounts() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("All bank accounts: \n");
        stringBuilder.append("ID         | Balance    | Holder    \n");
        for (Account account : Bank.getInstance().getAccounts()) {
            stringBuilder.append(String.format(
                    "%s | %s | %s\n",
                    account.getAccountId().toString(),
                    account.getBalance().toString(),
                    account.getHolder().getName()));
        }
        return stringBuilder.toString();
    }

    @Command
    public String showCustomers() {
        TextTable table = new TextTable(5);
        table.setHeader("Name", "Address", "Age", "Telephone", "Business");
        List<Customer> customers = Bank.getInstance().getCustomers();
        for (Customer customer : customers) {
            table.addRow(customer.getName(), customer.getAddress(), customer.getAge(),
                    customer.getTelephone(), customer.isBusinessCustomer());
        }
        table.addRow("Name", "address", 5, "0711", false);
        return table.toString();
    }

    @Command
    public String registerCustomer(String name, String address, String birthdate,
                                   String telephoneNumber, boolean businessCustomer) {
        // TODO: check if format is correct
        String format = "DD.MM.YYYY";
        DateTime dateTime = new DateTime(birthdate, format);
        Customer customer = new Customer(name, address, dateTime, telephoneNumber, businessCustomer);
        Bank.getInstance().addCustomer(customer);
        return "Customer " + name + " created";
    }

    @Command
    public void login(String customerId) throws IOException {
        if (customerShell == null) {
            customerShell = new CustomerShell();
        }
        Customer customer = Bank.getInstance().getCustomer(new CustomerId(customerId));
        if (customer != null) {
            customerShell.setCustomer(customer);
            ShellFactory.createSubshell("customer", shell, "name#customer", customerShell)
                    .commandLoop();
        } else {
            // TODO user doesn't exist
        }
    }

    @Command
    public String deposit(String accountId, String amount) {
        try {
            Bank.getInstance().deposit(new AccountId(accountId), new Money(amount));
            return String.format("%s deposited into %s", amount, accountId);
        } catch (TransactionFailedException e) {
            // TODO notify user of failed deposit
            e.printStackTrace();
            return "";
        }
    }

    @Command
    public String showTransactions() {
        TextTable table = new TextTable(5);
        table.setHeader("Type", "Issued", "Amount", "Status", "Source", "Destination");
        List<Transaction> transactions = Bank.getInstance().getTransactions();
        for (Transaction transaction : transactions) {
            table.addRow(transaction.getClass(), transaction.getIssueDate(),
                    transaction.getStatus(), " - ", " - ");
        }
        return table.toString();
    }

    @Command
    public String tick() {
        throw new NotYetImplementedException();
    }

    @Override
    public void cliSetShell(Shell theShell) {
        this.shell = theShell;
    }
}
