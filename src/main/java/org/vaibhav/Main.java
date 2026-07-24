package org.vaibhav;

import org.vaibhav.menu.Menu;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        while(true){
            Menu.display();
            Integer input = sc.nextInt();
            switch (input) {
                case 1:
                    // Create Account
                    break;

                case 2:
                    // Deposit Money
                    break;

                case 3:
                    // Withdraw Money
                    break;

                case 4:
                    // Transfer Money
                    break;

                case 5:
                    // Check Balance
                    break;

                case 6:
                    // View Transaction History
                    break;

                case 7:
                    // List All Accounts
                    break;

                case 8:
                    // Delete Account
                    break;

                case 0:
                    // Exit
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}