/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PcQnA;

/**
 * v means down arrow
 *
 * @author kq635
 */
import java.util.*;
import java.io.*;

public class Gpu extends Pc {

    private static boolean gpuMenuStatus = false;

    System.out.println("Hello");
    @Override
    protected void printBriefInfo() {
        // Read and print a brief GPU detail section from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "1.gpu brief detail");
        System.out.println(content);
    }

    @Override
    protected void printFullInfo() {
        // Read and print the full GPU detail section from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "2.gpu full detail");
        System.out.println(content);
    }

    @Override
    protected void printTypes() {
        // Read and print GPU types from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "3. kinds of gpu?");
        System.out.println(content);
    }

    @Override
    protected void printRecommendation() {
        // Read and print GPU recommendations from a file
        String content = FileUtility.readSectionFromFile("data/pcInfo.txt", "4.gpu what we recommend");
        System.out.println(content);
    }

    @Override
    protected void start() {
        synchronized (this) {
             gpuMenuStatus = true;
            Scanner scan = new Scanner(System.in);

            while (gpuMenuStatus) {
                System.out.println("\nGPU");
                System.out.println("------------");
                System.out.println("Choose an option:");
                System.out.println("1. Brief Info");
                System.out.println("2. Full Info");
                System.out.println("3. Kinds of GPU");
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
                            System.out.println("GPU types:");
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
                            gpuMenuStatus = false; // Exit GPU submenu and go back to the main menu
                            break;
                        default:
                            System.err.println("Invalid input, please try again");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Please enter a valid option.");
                }
            }
            notify(); // Notify the waiting Control thread when the GPU is done
        }
    }
    // to let other classes / user know gpu = done
    public static boolean isGpuMenuActive() {
        return gpuMenuStatus;
    }
}
