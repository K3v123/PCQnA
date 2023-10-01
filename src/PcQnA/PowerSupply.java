/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PcQnA;

/**
 *
 * @author kq635
 */
import java.util.*;
import java.io.*;

public class PowerSupply extends Pc {

    private static final Scanner scan = new Scanner(System.in);
    private static boolean powerSupplyMenuStatus = false;

    @Override
    protected void printBriefInfo() {
        // Print brief information about Power Supply by reading from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 1. (ps) brief:");
        System.out.println(content);
    }

    @Override
    protected void printFullInfo() {
        // Implement this method as needed for Power Supply class
    }

    @Override
    protected void printTypes() {
        // Print information about Power Supply types by reading from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 2.(ps)  types");
        System.out.println(content);
    }

    @Override
    protected void printRecommendation() {
        // Print recommendations for Power Supply by reading from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 3. (ps) what we recommend:");
        System.out.println(content);
    }

    @Override
    protected void start() {
        synchronized (this) {
            boolean powerSupplyMenuStatus = true; // Set Power Supply menu status to active

            while (powerSupplyMenuStatus) {
                System.out.println("\nPower Supply");
                System.out.println("------------");
                System.out.println("Choose an option:");
                System.out.println("1. Brief Info");
                System.out.println("2. Power Supply Types");
                System.out.println("3. What We Recommend");
                System.out.println("4. Quit");
                System.out.println("5. Go back");

                try {
                    int choice = Integer.parseInt(scan.nextLine().trim());

                    switch (choice) {
                        case 1:
                            System.out.println("Brief info:");
                            printBriefInfo();
                            break;
                        case 2:
                            System.out.println("Power Supply Types:");
                            printTypes();
                            break;
                        case 3:
                            System.out.println("Recommendation:");
                            printRecommendation();
                            break;
                        case 4:
                            System.out.println("Quitting...");
                            System.exit(0); // Terminate the program
                            break;
                        case 5:
                            System.out.println("Going back...");
                            powerSupplyMenuStatus = false; // Exit Power Supply submenu +  go back to main menu + Update the status
                            break;
                        default:
                            System.err.println("400, Invalid input, please try again");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("400, Please enter a valid option.");
                }
            }
            notify(); // Notify the waiting Control thread when the Power Supply is done
        }
    }

    public static boolean isPowerSupplyMenuActive() {
        return powerSupplyMenuStatus;
    }
}
