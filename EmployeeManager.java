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
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String employeeLine = fileReader.readLine();
                String[] employeeNames = employeeLine.split(",");
                for (String employeeName : employeeNames) {
                    System.out.println(employeeName);
                }
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String employeeLine = fileReader.readLine();
                System.out.println(employeeLine);
                String[] employeeNames = employeeLine.split(",");
                Random randomNumberGenerator = new Random();
                int randomIndex = randomNumberGenerator.nextInt(employeeNames.length);
                System.out.println(employeeNames[randomIndex]);
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            try {
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("employees.txt", true)
                );
                String nameToAdd = args[0].substring(1);
                fileWriter.write(", " + nameToAdd);
                fileWriter.close();
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String employeeLine = fileReader.readLine();
                String[] employeeNames = employeeLine.split(",");
                boolean isFound = false;
                String targetEmployee = args[0].substring(1);
                for (int index = 0; index < employeeNames.length && !isFound; index++) {
                    if (employeeNames[index].equals(targetEmployee)) {
                        System.out.println("Employee found!");
                        isFound = true;
                    }
                }
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String employeeLine = fileReader.readLine();
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
            } catch (Exception exception) {}
            System.out.println("Data Loaded.");
        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String employeeLine = fileReader.readLine();
                String[] employeeNames = employeeLine.split(",");
                String targetEmployee = args[0].substring(1);
                for (int index = 0; index < employeeNames.length; index++) {
                    if (employeeNames[index].equals(targetEmployee)) {
                        employeeNames[index] = "Updated";
                    }
                }
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                fileWriter.write(String.join(",", employeeNames));
                fileWriter.close();
            } catch (Exception exception) {}
            System.out.println("Data Updated.");
        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");
            try {
                BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(
                        new FileInputStream("employees.txt")
                    )
                );
                String employeeLine = fileReader.readLine();
                String[] employeeNames = employeeLine.split(",");
                String targetEmployee = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employeeNames));
                employeeList.remove(targetEmployee);
                BufferedWriter fileWriter = new BufferedWriter(
                    new FileWriter("employees.txt")
                );
                fileWriter.write(String.join(",", employeeList));
                fileWriter.close();
            } catch (Exception exception) {}
            System.out.println("Data Deleted.");
        }
    }
}