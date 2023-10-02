/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PcQnA;

/**
 * modifed the read and print code bc orig code the brief and full gets mixed up
 * this is due to my inconsistency naming on the txt file. i added continue to
 * skip the title, which is more explicite compared to the old one. why it works
 * im not really sure but at least it works.
 *
 * update: actually i think it might be my spacing and stuff.... cause my
 * powersupply i just kinda play around and got it working with orignial code.
 *
 * update: major change for i/o made another class to meet the SRP requirement
 *
 *
 * @author kq635
 */
import java.util.*;
import java.io.*;

public class Memory extends Pc {

    private static final Scanner scan = new Scanner(System.in);
    private static boolean memoryMenuStatus = false;

    @Override
    protected void printBriefInfo() {
        // Read and print a brief memory info section from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 1.  memory brief info");
        System.out.println(content);
    }

    @Override
    protected void printFullInfo() {
        // Read and print the full memory info section from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 2. memory full info");
        System.out.println(content);
    }

    @Override
    protected void printTypes() {
        // Read and print memory types from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "3. memory types");
        System.out.println(content);
    }

    @Override
    protected void printRecommendation() {
        // Read and print memory recommendations from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "4. what we recommend memory");
        System.out.println(content);
    }

    @Override
    protected void start() {
        synchronized (this) {
            boolean memoryMenuStatus = true; // Set memory menu status to active

            while (memoryMenuStatus) {
                System.out.println("\nMemory");
                System.out.println("------------");
                System.out.println("Choose an option:");
                System.out.println("1. Brief Info");
                System.out.println("2. Full Info");
                System.out.println("3. Memory Types");
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
                            System.out.println("Memory types:");
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
                            memoryMenuStatus = false; // Exit Memory submenu & go back to main menu + update status
                            break;
                        default:
                            System.err.println("400, Invalid input, please try again");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("400, Please enter a valid option.");
                }
            }
            notify(); // Notify the waiting Control thread when the Memory is done
        }
    }

    public static boolean isMemoryMenuActive() {
        return memoryMenuStatus;
    }
}
