package banking.cli;

import com.budhash.cliche.Command;
import com.budhash.cliche.Shell;
import com.budhash.cliche.ShellDependent;

public class CustomerShell implements ShellDependent {
    Shell shell;

    @Command
    public String showAccounts() {
        return "Your accounts: ...";
    }

    @Command
    public String withdraw(String accountId, String amount) {
        return String.format("%s withrdawn from %s", amount, accountId);
    }

    @Command
    public String showBalance(String accountId) {
        return String.format("%s has a balance of 1$", accountId);
    }

    @Command
    public String invoice(String fromId, String toId, String amount) {
        return String.format("Invoice from %s to %s with a value of %s", fromId, toId, amount);
    }

    @Override
    public void cliSetShell(Shell theShell) {
        shell = theShell;
    }
}
