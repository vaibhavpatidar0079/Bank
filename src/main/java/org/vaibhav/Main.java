package org.vaibhav;

import org.vaibhav.menu.Menu;
import org.vaibhav.model.Bank;
import org.vaibhav.service.BankService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();
        BankService service = new BankService(bank);

        while (true) {
            int choice = Menu.showMainMenu();

            try {
                switch (choice) {
                    case 1 -> {
                        String name = Menu.accountHolderName();
                        double amount = Menu.initialDeposit();

                        service.createAccount(name, amount);
                    }

                    case 2 -> {
                        int accountNum = Menu.accountNumber();
                        double amount = Menu.amount();

                        service.depositMoney(accountNum, amount);
                    }

                    case 3 -> {
                        int accountNum = Menu.accountNumber();
                        double amount = Menu.amount();

                        service.withdrawMoney(accountNum, amount);
                    }

                    case 4 -> {
                        int from = Menu.accountNumber();
                        int to = Menu.destinationAccountNumber();
                        double amount = Menu.amount();

                        service.transferMoney(from, to, amount);
                    }

                    case 5 -> {
                        int accountNum = Menu.accountNumber();

                        service.viewBalance(accountNum);
                    }

                    case 6 -> {
                        System.out.println("Transaction history is not implemented yet.");
                    }

                    case 7 -> service.listAccounts();

                    case 8 -> {
                        int accountNum = Menu.accountNumber();

                        service.deleteAccount(accountNum);
                    }

                    case 0 -> {
                        System.out.println("Thank you for using our Bank.");
                        return;
                    }

                    default -> System.out.println("Invalid choice.");
                }

            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}