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

public class Control {

    private static final Scanner scan = new Scanner(System.in);
    private static final Object lock = new Object(); // A lock object for synchronization

    // Method to start the main control loop
    public void start() {
        boolean running = true;

        while (running) {
            // Display the main menu
            System.out.println("\nWelcome to PC Q&A");
            System.out.println("-------------");
            System.out.println("Please Choose what you want to know?:");
            System.out.println("1. CPU");
            System.out.println("2. Motherboard");
            System.out.println("3. GPU");
            System.out.println("4. Memory");
            System.out.println("5. Storage");
            System.out.println("6. Power Supply");
            System.out.println("7. Cooling");
            System.out.println("8. Admin");
            System.out.println("9. Quit");

            try {
                int choice = Integer.parseInt(scan.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.println("CPU");
                        synchronized (lock) {
                            Cpu cpu = new Cpu(); // make cpu menu and start it
                            cpu.start();
                            while (Cpu.isCpuMenuActive()) {
                                lock.wait(); // Wait until CPUMenu is done and signals it's going back
                            }
                        }
                        break;
                    case 2:
                        System.out.println("Motherboard");
                        synchronized (lock) {
                            MotherBoard motherboard = new MotherBoard();
                            motherboard.start();
                            while (MotherBoard.isMotherboardMenuActive()) {
                                lock.wait(); // Wait until Motherboard is done and signals it's going back
                            }
                        }
                        break;
                    case 3:
                        System.out.println("GPU");
                        synchronized (lock) {
                            Gpu gpu = new Gpu();
                            gpu.start();
                            while (Gpu.isGpuMenuActive()) {
                                lock.wait(); // Wait until GPU is done and signals it's going back
                            }
                        }
                        break;
                    case 4:
                        System.out.println("Memory");
                        synchronized (lock) {
                            Memory memory = new Memory();
                            memory.start();
                            while (Memory.isMemoryMenuActive()) {
                                lock.wait(); // Wait until Memory is done and signals it's going back
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Storage");
                        synchronized (lock) {
                            Storage storage = new Storage();
                            storage.start();
                            while (Storage.isStorageMenuActive()) {
                                lock.wait(); // Wait until Storage is done and signals it's going back
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Power Supply");
                        synchronized (lock) {
                            PowerSupply powerSupply = new PowerSupply();
                            powerSupply.start();
                            while (PowerSupply.isPowerSupplyMenuActive()) {
                                lock.wait(); // Wait until Power Supply is done and signals it's going back
                            }
                        }
                        break;
                    case 7:
                        System.out.println("Cooling");
                        synchronized (lock) {
                            Cooling cooling = new Cooling();
                            cooling.start();
                            while (Cooling.isCoolingMenuActive()) {
                                lock.wait(); // Wait until Cooling is done and signals it's going back
                            }
                        }
                        break;
                    case 8:
                        System.out.println("Admin");
                        synchronized (lock) {
                            Admin admin = new Admin();
                            admin.adminMenu();
                            while (admin.isAdminMenuActive()) {
                                lock.wait(); // Wait until Admin is done and signals it's going back
                            }
                        }
                        break;
                    case 9:
                        System.out.println("Quitting...");
                        running = false;
                        break;
                    default:
                        System.err.println("400, Invalid input, please try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid input, please enter a valid option.");
            } catch (InterruptedException e) {
                System.err.println("Interrupted while waiting: " + e.getMessage());
            }
        }
        scan.close(); // Close the scanner when done
    }
}
