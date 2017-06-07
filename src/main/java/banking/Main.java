package banking;

import banking.cli.BankShell;

import java.io.IOException;

public class Main {

    /**
     * Entry point for the command line interface.
     *
     * @param args not required
     */
    public static void main(String[] args) {
        try {
            BankShell.start();
        } catch (IOException e) {
            System.out.println("Could not start shell.");
            e.printStackTrace();
        }
    }
}
