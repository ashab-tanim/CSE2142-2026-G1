// File Name: EmployeeManager.java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Manages employee data operations including listing, selecting randomly,
 * adding, searching, counting words, updating, and deleting employee records.
 */
public class EmployeeManager {

    public static void main(String[] args) {
        // Validate command line arguments count
        if (args == null || args.length != 1) {
            System.out.println(Constants.MSG_WRONG_ARG_COUNT);
            return;
        }

        String commandFlag = args[0];

        // Operation: List all employees
        if (commandFlag.equals(Constants.FLAG_LIST)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(Constants.COMMA_DELIMITER);
                for (String employeeName : employeeNames) {
                    System.out.println(employeeName.trim());
                }
            }
            System.out.println(Constants.MSG_LOADED);

        // Operation: Print a random employee
        } else if (commandFlag.equals(Constants.FLAG_RANDOM)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                System.out.println(employeeLine);
                String[] employeeNames = employeeLine.split(Constants.COMMA_DELIMITER);
                System.out.println(employeeNames[new Random().nextInt(employeeNames.length)].trim());
            }
            System.out.println(Constants.MSG_LOADED);

        // Operation: Add a new employee
        } else if (commandFlag.startsWith(Constants.FLAG_ADD)) {
            System.out.println(Constants.MSG_LOADING);
            String nameToAdd = commandFlag.substring(1).trim();
            appendToFile(Constants.COMMA_SPACE + nameToAdd);
            System.out.println(Constants.MSG_LOADED);

        // Operation: Search for an employee by name
        } else if (commandFlag.startsWith(Constants.FLAG_SEARCH)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String targetEmployee = commandFlag.substring(1).trim();
                boolean isFound = false;

                for (String employeeName : employeeLine.split(Constants.COMMA_DELIMITER)) {
                    if (employeeName.trim().equalsIgnoreCase(targetEmployee)) {
                        isFound = true;
                        break;
                    }
                }

                if (isFound) {
                    System.out.println(Constants.MSG_FOUND);
                } else {
                    System.out.println(Constants.MSG_NOT_FOUND);
                }
            }
            System.out.println(Constants.MSG_LOADED);

        // Operation: Count words and total character count
        } else if (commandFlag.equals(Constants.FLAG_COUNT)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] words = employeeLine.trim().split("\\s+");
                int wordCount = employeeLine.trim().isEmpty() ? 0 : words.length;
                System.out.println(wordCount + " word(s) found " + employeeLine.length());
            }
            System.out.println(Constants.MSG_LOADED);

        // Operation: Update an existing employee name
        } else if (commandFlag.startsWith(Constants.FLAG_UPDATE)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(Constants.COMMA_DELIMITER);
                String targetEmployee = commandFlag.substring(1).trim();

                for (int index = 0; index < employeeNames.length; index++) {
                    if (employeeNames[index].trim().equalsIgnoreCase(targetEmployee)) {
                        employeeNames[index] = Constants.MSG_UPDATED_TEXT;
                    }
                }
                writeToFile(String.join(Constants.COMMA_DELIMITER, employeeNames));
            }
            System.out.println(Constants.MSG_UPDATED);

        // Operation: Delete an employee from records
        } else if (commandFlag.startsWith(Constants.FLAG_DELETE)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(Constants.COMMA_DELIMITER);
                String targetEmployee = commandFlag.substring(1).trim();

                List<String> employeeList = new ArrayList<>(Arrays.asList(employeeNames));
                employeeList.removeIf(employee -> employee.trim().equalsIgnoreCase(targetEmployee));
                writeToFile(String.join(Constants.COMMA_DELIMITER, employeeList));
            }
            System.out.println(Constants.MSG_DELETED);

        // Default: Invalid argument passed
        } else {
            System.out.println(Constants.MSG_INVALID_ARGS);
        }
    }

    /**
     * Reads a single line of data from the employee data file.
     * @return The first line read from file, or null if an error occurs.
     */
    private static String readFromFile() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(Constants.FILE_NAME)))) {
            return reader.readLine();
        } catch (IOException ioException) {
            System.out.println("Error reading file: " + ioException.getMessage());
            return null;
        }
    }

    /**
     * Overwrites the employee data file with new content.
     * @param data The formatted string content to write into the file.
     */
    private static void writeToFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME))) {
            writer.write(data);
        } catch (IOException ioException) {
            System.out.println("Error writing to file: " + ioException.getMessage());
        }
    }

    /**
     * Appends content to the existing employee data file.
     * @param data The string content to append at the end of the file.
     */
    private static void appendToFile(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Constants.FILE_NAME, true))) {
            writer.write(data);
        } catch (IOException ioException) {
            System.out.println("Error appending to file: " + ioException.getMessage());
        }
    }
}