/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PcQnA;

import java.io.*;
import java.util.*;

/**
 *
 * @author kq635
 */
public class FileUtility {

    // Read a section of a file based on a section header
    public static String readSectionFromFile(String filePath, String sectionHeader) {
        StringBuilder sectionContent = new StringBuilder();

        try {
            // Open the file for reading
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            // Read the file line by line
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean printingSection = false;

                while ((line = reader.readLine()) != null) {
                    // Check if the line starts with the specified section header
                    String trimmedLine = line.trim();

                    if (trimmedLine.startsWith(sectionHeader.trim())) {
                        printingSection = true;
                        continue; // Skip the section header
                    } else if (trimmedLine.matches("\\d+\\..*")) { // gpt
                        printingSection = false; // Skip other sections
                    }

                    // If we're in the desired section, add the line to the section content
                    if (printingSection && !trimmedLine.isEmpty()) {
                        sectionContent.append(trimmedLine).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return sectionContent.toString();
    }

    // Append content to the end of a file (admin)
    public static void appendToFile(String filePath, String content) {
        try {
            // Open the file for writing (append mode)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.newLine();
                writer.write(content);
                System.out.println("Content printed to file: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error printing to file: " + e.getMessage());
        }
    }

    // Read a password from a file (admin)
    public static String readPasswordFromFile(String filePath) {
        StringBuilder password = new StringBuilder();

        try {
            // Open the file for reading
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            // Read the first line as the password
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                if ((line = reader.readLine()) != null) {
                    password.append(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading password from file: " + e.getMessage());
        }

        return password.toString();
    }

    // Print the content of a file with line numbers
    public static void printFileContentWithLineNumbers(String filePath) {
        try {
            // Open the file for reading
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            // Read the file line by line and print each line with a line number
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNumber = 1;

                while ((line = reader.readLine()) != null) {
                    System.out.println("line " + lineNumber + ": " + line);
                    lineNumber++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Edit a specific line in a file
    public static boolean editFileLine(String filePath, int lineNumber, String newContent) {
        try {
            // Open the file for reading
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            // Read all lines from the file
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            // Check if the requested line number is valid
            if (lineNumber >= 1 && lineNumber <= lines.size()) {
                // Replace the specified line with the new content
                lines.set(lineNumber - 1, newContent);

                // Write the updated lines back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                }
                return true;
            } else {
                System.err.println("Invalid line number: " + lineNumber);
                return false;
            }
        } catch (IOException e) {
            System.err.println("Error when editing file: " + e.getMessage());
            return false;
        }
    }

    // Delete a specific line from a file
    public static boolean deleteFileLine(String filePath, int lineNumber) {
        try {
            // Open the file for reading
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            // Read all lines from the file
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            // Check if the requested line number is valid
            if (lineNumber >= 1 && lineNumber <= lines.size()) {
                // Remove the specified line
                lines.remove(lineNumber - 1);

                // Write the remaining lines back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    for (String remainingLine : lines) {
                        writer.write(remainingLine);
                        writer.newLine();
                    }
                }
                return true;
            } else {
                System.err.println("Invalid line number: " + lineNumber);
                return false;
            }
        } catch (IOException e) {
            System.err.println("Error deleting line from file: " + e.getMessage());
            return false;
        }
    }
}
