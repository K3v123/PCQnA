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

public class MotherBoard extends Pc {

    private static final Scanner scan = new Scanner(System.in);
    private static boolean motherboardMenuStatus = false;

    @Override
    protected void printBriefInfo() {
        // Read and print a brief motherboard info section from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "1. motherboard brief");
        System.out.println(content);
    }

    @Override
    protected void printFullInfo() {
        // Read and print the full motherboard info section from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "2.motherboard full detail");
        System.out.println(content);
    }

    @Override
    protected void printTypes() {
        // Read and print motherboard types from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 3.  motherboard Type");
        System.out.println(content);
    }

    @Override
    protected void printRecommendation() {
        // Read and print motherboard recommendations from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "4.  (motherboard) what we recommend");
        System.out.println(content);
    }

    @Override
    protected void start() {
        synchronized (this) {
            boolean motherboardMenuStatus = true; // Set motherboard menu status to active

            while (motherboardMenuStatus) {
                System.out.println("\nMotherboard");
                System.out.println("------------");
                System.out.println("Choose an option:");
                System.out.println("1. Brief Info");
                System.out.println("2. Full Info");
                System.out.println("3. Types of Motherboard");
                System.out.println("4. What We Recommend");
                System.out.println("5. Quit");
                System.out.println("6. Go back");

                try {
                    int choice = Integer.parseInt(scan.nextLine().trim());

                    switch (choice) {
                        case 1:
                            System.out.println("Brief info:");
                            printBriefInfo();
                            break;
                        case 2:
                            System.out.println("Full info:");
                            printFullInfo();
                            break;
                        case 3:
                            System.out.println("Types of Motherboard:");
                            printTypes();
                            break;
                        case 4:
                            System.out.println("Recommendation:");
                            printRecommendation();
                            break;
                        case 5:
                            System.out.println("Quitting...");
                            System.exit(0);
                            break;
                        case 6:
                            System.out.println("Going back...");
                            motherboardMenuStatus = false; // Exit Motherboard submenu + go back to main menu, update status
                            break;
                        default:
                            System.err.println("400, Invalid input, please try again");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("400, Please enter a valid option.");
                }
            }
            notify(); // Notify the waiting Control thread when the Motherboard is done
        }
    }

    public static boolean isMotherboardMenuActive() {
        return motherboardMenuStatus;
    }
}
