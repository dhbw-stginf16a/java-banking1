package banking.cli;

import banking.backend.DateTime;
import com.budhash.cliche.Command;
import com.budhash.cliche.Shell;
import com.budhash.cliche.ShellDependent;
import com.budhash.cliche.ShellFactory;

import java.io.IOException;

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
        return "all accounts";
    }

    @Command
    public String showCustomers() {
        return "all customers";
    }

    @Command
    public String registerCustomer(String name, String address, DateTime birthdate,
                                   String telephoneNumber, boolean businessCustomer) {
        return "Customer " + name + " created";
    }

    @Command
    public void login(String customerId) throws IOException {
        if (customerShell == null) {
            customerShell = new CustomerShell();
        }
        ShellFactory.createSubshell("customer", shell, "name#customer", customerShell)
                .commandLoop();
    }

    @Command
    public String deposit(String accountId, String amount) {
        return String.format("%s deposited into %s", amount, accountId);
    }

    @Command
    public String showTransactions() {
        return "All transactions: ...";
    }

    @Command
    public String tick() {
        return "Tick applied...";
    }

    @Override
    public void cliSetShell(Shell theShell) {
        this.shell = theShell;
    }
}
