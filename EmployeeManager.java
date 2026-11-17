//File Name EmployeeManager.java
import java.io.*;
import java.util.*;

public class EmployeeManager {
    public static void main(String[] args) {
        // Check arguments count (Task #2)
        if (args == null || args.length != 1) {
            System.out.println(Constants.MSG_WRONG_ARG_COUNT);
            return;
        }

        if (args[0].equals(Constants.FLAG_LIST)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(Constants.COMMA_DELIMITER);
                for (String employeeName : employeeNames) {
                    System.out.println(employeeName);
                }
            }
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].equals(Constants.FLAG_RANDOM)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                System.out.println(employeeLine);
                String[] employeeNames = employeeLine.split(Constants.COMMA_DELIMITER);
                Random randomNumberGenerator = new Random();
                int randomIndex = randomNumberGenerator.nextInt(employeeNames.length);
                System.out.println(employeeNames[randomIndex]);
            }
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.FLAG_ADD)) {
            System.out.println(Constants.MSG_LOADING);
            String nameToAdd = args[0].substring(1);
            appendToFile(Constants.COMMA_SPACE + nameToAdd);
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.FLAG_SEARCH)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(Constants.COMMA_DELIMITER);
                boolean isFound = false;
                String targetEmployee = args[0].substring(1);
                for (int index = 0; index < employeeNames.length && !isFound; index++) {
                    if (employeeNames[index].equals(targetEmployee)) {
                        System.out.println(Constants.MSG_FOUND);
                        isFound = true;
                    }
                }
            }
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.FLAG_COUNT)) {
            System.out.println(Constants.MSG_LOADING);
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
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.FLAG_UPDATE)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(Constants.COMMA_DELIMITER);
                String targetEmployee = args[0].substring(1);
                for (int index = 0; index < employeeNames.length; index++) {
                    if (employeeNames[index].equals(targetEmployee)) {
                        employeeNames[index] = "Updated";
                    }
                }
                writeToFile(String.join(Constants.COMMA_DELIMITER, employeeNames));
            }
            System.out.println(Constants.MSG_UPDATED);
        } else if (args[0].contains(Constants.FLAG_DELETE)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String[] employeeNames = employeeLine.split(Constants.COMMA_DELIMITER);
                String targetEmployee = args[0].substring(1);
                List<String> employeeList = new ArrayList<>(Arrays.asList(employeeNames));
                employeeList.remove(targetEmployee);
                writeToFile(String.join(Constants.COMMA_DELIMITER, employeeList));
            }
            System.out.println(Constants.MSG_DELETED);
        }
    }

    private static String readFromFile() {
        try {
            BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(Constants.FILE_NAME)
                )
            );
            String line = fileReader.readLine();
            fileReader.close();
            return line;
        } catch (Exception exception) {
            return null;
        }
    }

    private static void writeToFile(String data) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME)
            );
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception exception) {}
    }

    private static void appendToFile(String data) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, true)
            );
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception exception) {}
    }
}