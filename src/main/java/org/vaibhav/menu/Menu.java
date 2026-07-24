package org.vaibhav.menu;

import java.util.Scanner;

public final class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    private Menu() {
    }

    public static int showMainMenu() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("          BANK MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Check Balance");
        System.out.println("6. View Account Details");
        System.out.println("7. List All Accounts");
        System.out.println("8. Delete Account");
        System.out.println("0. Exit");
        System.out.println("------------------------------------------");
        System.out.print("Enter your choice: ");

        return scanner.nextInt();
    }

    public static int accountNumber() {
        System.out.print("Account Number: ");
        return scanner.nextInt();
    }

    public static int destinationAccountNumber() {
        System.out.print("Destination Account Number: ");
        return scanner.nextInt();
    }

    public static String accountHolderName() {
        scanner.nextLine();
        System.out.print("Account Holder Name: ");
        return scanner.nextLine();
    }

    public static double amount() {
        System.out.print("Amount: ");
        return scanner.nextDouble();
    }

    public static double initialDeposit() {
        System.out.print("Initial Deposit: ");
        return scanner.nextDouble();
    }

    public static boolean confirmDeletion() {
        scanner.nextLine();
        System.out.print("Are you sure? (Y/N): ");
        return scanner.nextLine().equalsIgnoreCase("Y");
    }

    public static void pressEnter() {
        scanner.nextLine();
        System.out.println();
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}