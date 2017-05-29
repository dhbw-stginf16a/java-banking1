package banking;

import banking.cli.BankShell;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            BankShell.start();
        } catch (IOException e) {
            System.out.println("Could not start shell.");
            e.printStackTrace();
        }
    }
}
