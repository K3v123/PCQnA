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

public class Cpu extends Pc {

    private static boolean cpuMenuStatus = false;
    private static final Scanner scan = new Scanner(System.in);

    @Override
    protected void printBriefInfo() {
        // Display a brief overview of CPU information
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 1.CPU brief detail");
        System.out.println(content);
    }

    @Override
    protected void printFullInfo() {
        // Display detailed information about the CPU
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", " 2.CPU full detail");
        System.out.println(content);
    }

    @Override
    protected void printTypes() {
        // Display information about different types of CPUs
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "  3. kinds of cpu?");
        System.out.println(content);
    }

    @Override
    protected void printRecommendation() {
        // Provide recommendations for selecting a CPU
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "  4.CPU what we recommend");
        System.out.println(content);
    } 
 
    @Override
    protected void start() {
        synchronized (this) {
            boolean cpuMenuStatus = true;// Set CPU menu status to active

            while (cpuMenuStatus) {
                System.out.println("\nCPU");
                System.out.println("------------");
                System.out.println("Choose an option:");
                System.out.println("1. Brief Info");
                System.out.println("2. Full Info");
                System.out.println("3. Kinds of CPU");
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
                            System.out.println("CPU types:");
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
                            cpuMenuStatus = false; // Exit CPU submenu & go back to main menu + Update the status
                            break;
                        default:
                            System.err.println("Invalid input, please try again");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Please enter a valid option.");
                }
            }
            notify(); // Notify the waiting Control thread when the CPU is done
        }
    }

    public static boolean isCpuMenuActive() {
        return cpuMenuStatus;
    }
}
