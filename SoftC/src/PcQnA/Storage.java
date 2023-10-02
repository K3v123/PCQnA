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
public class Storage extends Pc {

    private static final Scanner scan = new Scanner(System.in);
    private static boolean storageMenuStatus = false;

    @Override
    protected void printBriefInfo() {
        // Print brief information about Storage by reading from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "1.storage brief:");
        System.out.println(content);
    }

    @Override
    protected void printFullInfo() {
        // Implement this method as needed for Storage class
    }

    @Override
    protected void printTypes() {
        // Print information about Storage types by reading from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "  2. storage type");
        System.out.println(content);
    }

    @Override
    protected void printRecommendation() {
        // Print recommendations for Storage by reading from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 3. (storage) what we recommend");
        System.out.println(content);
    }

    @Override
    protected void start() {
        synchronized (this) {
            boolean storageMenuStatus = true; // Set Storage menu status to active

            while (storageMenuStatus) {
                System.out.println("\nStorage");
                System.out.println("------------");
                System.out.println("Choose an option:");
                System.out.println("1. Brief Info");
                System.out.println("2. Storage Types");
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
                            System.out.println("Storage Types:");
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
                            storageMenuStatus = false; // Exit Storage submenu, go back to main menu + update status
                            break;
                        default:
                            System.err.println("400, Invalid input, please try again");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("400, Please enter a valid option.");
                }
            }
            notify(); // Notify the waiting Control thread when the Storage is done
        }
    }

    public static boolean isStorageMenuActive() {
        return storageMenuStatus;
    }
}
