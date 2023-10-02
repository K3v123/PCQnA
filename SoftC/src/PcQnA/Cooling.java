/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PcQnA;

import java.util.*;
import java.io.*;

/**
 *
 * @author kq635
 */
public class Cooling extends Pc {

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean coolingMenuStatus = false;

    @Override
    protected void printBriefInfo() {
        // Display a brief overview of cooling information
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "1.  cooling brief detail:");
        System.out.println(content);
    }

    @Override
    protected void printFullInfo() {
        // This method is intentionally left empty as full cooling information is not available
    }

    @Override
    protected void printTypes() {
        // Display information about types of cooling systems
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 2. cooling  type");
        System.out.println(content);
    }

    @Override
    protected void printRecommendation() {
        // Provide recommendations for cooling solutions
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 3. (cooling)  what we recommend");
        System.out.println(content);
    }
    
    // Start the Cooling submenu
    @Override
    protected void start() {
        synchronized (this) {
            boolean coolingMenuStatus = true;// Set Cooling menu status to active

            while (coolingMenuStatus) {
                System.out.println("\nCooling");
                System.out.println("------------");
                System.out.println("Choose an option:");
                System.out.println("1. Brief Info");
                System.out.println("2. Cooling Types");
                System.out.println("3. What We Recommend");
                System.out.println("4. Quit");
                System.out.println("5. Go back");

                try {
                    int choice = Integer.parseInt(scanner.nextLine().trim());

                    switch (choice) {
                        case 1:
                            System.out.println("Brief info:");
                            printBriefInfo();
                            break;
                        case 2:
                            System.out.println("Cooling Types:");
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
                            coolingMenuStatus = false; // Exit Cooling submenu (go back to main menu) + Update status
                            break;
                        default:
                            System.err.println("400, Invalid input, please try again");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("400, Please enter a valid option.");
                }
            }
            notify(); // Notify the waiting Control thread when the Cooling is done
        }
    }

    // Check if the Cooling menu is active
    public static boolean isCoolingMenuActive() {
        return coolingMenuStatus;
    }
}
