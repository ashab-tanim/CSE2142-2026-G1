//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        // Check arguments count (Task #2)
        if (args == null || args.length != 1) {
            System.out.println("Error: Please provide exactly one argument.");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(",");
                for (String employeeName : employeeNames) {
                    System.out.println(employeeName);
                }
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                System.out.println(employeeLine);
                String[] employeeNames = employeeLine.split(",");
                Random randomNumberGenerator = new Random();
                int randomIndex = randomNumberGenerator.nextInt(employeeNames.length);
                System.out.println(employeeNames[randomIndex]);
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            String nameToAdd = args[0].substring(1);
            appendToFile(", " + nameToAdd);
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(",");
                boolean isFound = false;
                String targetEmployee = args[0].substring(1);
                for (int index = 0; index < employeeNames.length && !isFound; index++) {
                    if (employeeNames[index].equals(targetEmployee)) {
                        System.out.println("Employee found!");
                        isFound = true;
                    }
                }
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                char[] contentCharacters = employeeLine.toCharArray();
                boolean isInsideWord = false;
                int wordCount = 0;
                for (char character : contentCharacters) {
                    if (character == ' ') {
                        if (!isInsideWord) {
                            wordCount++;
                            isInsideWord = true;
                        } else {
                            isInsideWord = false;
                        }
                    }
                }
                System.out.println(wordCount + " word(s) found " + contentCharacters.length);
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(",");
                String targetEmployee = args[0].substring(1);
                for (int index = 0; index < employeeNames.length; index++) {
                    if (employeeNames[index].equals(targetEmployee)) {
                        employeeNames[index] = "Updated";
                    }
                }
                writeToFile(String.join(",", employeeNames));
            }
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(",");
                String targetEmployee = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employeeNames));
                employeeList.remove(targetEmployee);
                writeToFile(String.join(",", employeeList));
            }
            System.out.println("Data Deleted.");
        }
    }

    // Task #4: Helper method to read data from employees.txt
    private static String readFromFile() {
        try {
            BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream("employees.txt")
                )
            );
            String line = fileReader.readLine();
            fileReader.close();
            return line;
        } catch (Exception exception) {
            return null;
        }
    }

    // Task #4: Helper method to write/overwrite data to employees.txt
    private static void writeToFile(String data) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter("employees.txt")
            );
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception exception) {}
    }

    // Task #4: Helper method to append data to employees.txt
    private static void appendToFile(String data) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter("employees.txt", true)
            );
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception exception) {}
    }
}