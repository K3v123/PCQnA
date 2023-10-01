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

public class Admin {

    private static final int MaxAttempts = 3;
    private static Scanner scan;

    // Variable to keep track of whether the admin is logged in
    private static boolean isAdminLoggedIn = false;
    private static boolean AdminMenuStatus = false;

    // Method for handling the admin menu
    public static void adminMenu() {
        scan = new Scanner(System.in);
        int numOfAttempts = 3, attempts = 0;
        boolean running = true; // Initialize the running flag

        // Login loop
        while (!isAdminLoggedIn && attempts < MaxAttempts) {
            System.out.print("Please enter your password: ");
            String inputPassword = scan.nextLine();

            if (authenticateAdmin(inputPassword)) {
                System.out.println("Admin privileges granted.");
                isAdminLoggedIn = true;
            } else {
                attempts++;
                numOfAttempts--;
                System.err.println("400, Access denied. " + numOfAttempts + " left");
            }
        }

        // If admin is logged in, display options
        if (isAdminLoggedIn) {
            while (running) { // Use the running flag to control the loop
                running = displayOptions(); // Update the running flag based on user choice
            }
        }
    }

    // Authenticate admin based on the entered password
    private static boolean authenticateAdmin(String inputPassword) {
        FileUtility fileUtility = new FileUtility();
        String storedPassword = fileUtility.readPasswordFromFile("data/password.txt");

        return inputPassword.equals(storedPassword.trim());
    }

    // Display admin options and handle user input
    private static boolean displayOptions() {
        synchronized (Admin.class) { // Use the class as the synchronization object
            System.out.println("Admin Options:");
            System.out.println("1. Add");
            System.out.println("2. Edit");
            System.out.println("3. Delete");
            System.out.println("4. Quit");
            System.out.println("5. Go Back");

            try {
                System.out.print("Please choose one of the following options: ");
                String choiceInput = scan.nextLine().trim();

                if (choiceInput.isEmpty()) {
                    System.err.println("400, Invalid input. Please enter a valid option.");
                    return true; // Continue running the Admin menu
                }

                int choice = Integer.parseInt(choiceInput);

                switch (choice) {
                    case 1:
                        addInfo();
                        break;
                    case 2:
                        editInfo();
                        break;
                    case 3:
                        deleteInfo();
                        break;
                    case 4:
                        System.out.println("Terminating program....");
                        System.out.println("Program Terminated");
                        System.exit(0);
                        break;
                    case 5:
                        System.out.println("Going back...");
                        return false; // Set running to false to exit the Admin menu
                    default:
                        System.err.println("400, option doesn't exist");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input. Please enter a valid option.");
            }
            return true; // Continue running the Admin menu
        }
    }

    // Method for adding information
    private static void addInfo() {
        System.out.print("Please enter what you want to add: ");
        String info = scan.nextLine();

        FileUtility fileUtility = new FileUtility();
        fileUtility.appendToFile("data/pcInfo.txt", info);
        System.out.println("Information added!");
    }

    // Method for editing information
    private static void editInfo() {
        FileUtility fileUtility = new FileUtility();
        fileUtility.printFileContentWithLineNumbers("data/pcInfo.txt");

        try {
            System.out.print("Please enter the line number you want to edit: ");
            String lineNumberInput = scan.nextLine().trim();

            if (lineNumberInput.isEmpty()) {
                System.err.println("Invalid input. Please enter a valid line number.");
                return;
            }

            int lineNumber = Integer.parseInt(lineNumberInput);

            System.out.print("Please enter the new information: ");
            String newInfo = scan.nextLine();

            boolean success = fileUtility.editFileLine("data/pcInfo.txt", lineNumber, newInfo);
            if (success) {
                System.out.println("Information edited successfully.");
            } else {
                System.err.println("400, Failed to edit information. Please check the line number.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid line number.");
        }
    }

    // Method for deleting information
    private static void deleteInfo() {
        FileUtility fileUtility = new FileUtility();
        fileUtility.printFileContentWithLineNumbers("data/pcInfo.txt");

        try {
            System.out.print("Please enter the line number to delete: ");
            String lineNumberInput = scan.nextLine().trim();

            if (lineNumberInput.isEmpty()) {
                System.err.println("Invalid input. Please enter a valid line number.");
                return;
            }

            int lineNumber = Integer.parseInt(lineNumberInput);

            boolean success = fileUtility.deleteFileLine("data/pcInfo.txt", lineNumber);
            if (success) {
                System.out.println("Information deleted successfully.");
            } else {
                System.err.println("400, Failed to delete information. Please check the line number.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter a valid line number.");
        }
    }

    // Check if the admin menu is active
    public static boolean isAdminMenuActive() {
        return AdminMenuStatus;
    }
}
