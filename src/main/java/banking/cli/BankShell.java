package banking.cli;

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
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The command line shell to be used by an authorized bank clerk.
 */
public class BankShell implements ShellDependent {
    private String name;
    private Shell shell;
    private CustomerShell customerShell;

    public BankShell(String name) {
        this.name = name;
    }

    /**
     * Start the shell with commands for the bank clerk.
     *
     * @throws IOException when can't readLine() from input
     */
    public static void start() throws IOException {
        String name = "bank";
        ShellFactory.createConsoleShell(name, name, new BankShell("name#" + name))
                .commandLoop();
    }

    /**
     * Show all accounts of the bank.
     *
     * @return the string to be displayed
     */
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

    /**
     * Show all customers of the bank.
     *
     * @return the string to be displayed
     */
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

    /**
     * Register a new customer with the given parameters.
     *
     * @param name             the customer's name
     * @param address          the customer's address
     * @param birthdate        the customer's birth date
     * @param telephoneNumber  the customer's telephone number
     * @param businessCustomer is the customer a business customer
     * @return the string to be displayed
     */
    @Command
    public String registerCustomer(String name, String address, String birthdate,
                                   String telephoneNumber, boolean businessCustomer) {
        final String FORMAT = "DD.MM.YYYY";
        try {
            DateTime dateTime = new DateTime(birthdate, FORMAT);
            Customer customer = new Customer(name, address, dateTime, telephoneNumber, businessCustomer);
            Bank.getInstance().addCustomer(customer);
            return "Customer " + name + " created";
        } catch (DateTimeParseException e) {
            return String.format("Datetime format is not correct. Correct is: %s", FORMAT);
        }
    }

    /**
     * Login with a customer id and open a new shell with their commands.
     *
     * @param customerId the customer's id
     * @throws IOException when can't readLine() from input
     */
    @Command
    public String login(String customerId) throws IOException {
        if (customerShell == null) {
            customerShell = new CustomerShell();
        }
        Customer customer = Bank.getInstance().getCustomer(new CustomerId(customerId));
        if (customer != null) {
            customerShell.setCustomer(customer);
            ShellFactory.createSubshell("customer", shell, "name#customer", customerShell)
                    .commandLoop();
        } else {
            return "User does not exist.";
        }
        return "Login went through";
    }

    /**
     * Deposit money into an account.
     *
     * @param accountId the account id of the account
     * @param amount the amount to be deposited
     * @return the string to be displayed
     */
    @Command
    public String deposit(String accountId, String amount) {
        try {
            Bank.getInstance().deposit(new AccountId(accountId), new Money(amount));
            return String.format("%s deposited into %s", amount, accountId);
        } catch (TransactionFailedException e) {
            return String.format("Deposit failed: %s", e.getMessage());
        }
    }

    /**
     * Show all transactions of the bank.
     *
     * @return the string to be displayed
     */
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

    /**
     * Perform a 'tick' on every account registered with the bank.
     * The tick is the (yearly) application of interests
     *
     * @return the string to be displayed
     */
    @Command
    public String tick() {
        Bank.getInstance().getAccounts().forEach((Account account) -> {
            try {
                account.applySavingInterest();
                account.applyBorrowingInterest();
            } catch (IllegalStateException ignored) {
            }
        });
        return "All interests have been applied.";
    }

    /**
     * This method informs the object about the Shell operating it.
     * Is called upon object's registration in Shell.
     * @param theShell Shell running the object.
     */
    @Override
    public void cliSetShell(Shell theShell) {
        this.shell = theShell;
    }
}
