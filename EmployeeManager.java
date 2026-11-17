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
                for (String employeeName : employeeLine.split(Constants.COMMA_DELIMITER)) {
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
                System.out.println(employeeNames[new Random().nextInt(employeeNames.length)]);
            }
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.FLAG_ADD)) {
            System.out.println(Constants.MSG_LOADING);
            appendToFile(Constants.COMMA_SPACE + args[0].substring(1));
            System.out.println(Constants.MSG_LOADED);
        } else if (args[0].contains(Constants.FLAG_SEARCH)) {
            System.out.println(Constants.MSG_LOADING);
            String employeeLine = readFromFile();
            if (employeeLine != null) {
                String targetEmployee = args[0].substring(1).trim();
                boolean employeeFound = false;

                // Task #7: Straightforward search logic with explicit found/not-found response
                for (String employeeName : employeeLine.split(Constants.COMMA_DELIMITER)) {
                    if (employeeName.trim().equalsIgnoreCase(targetEmployee)) {
                        employeeFound = true;
                        break;
                    }
                }

                if (employeeFound) {
                    System.out.println(Constants.MSG_FOUND);
                } else {
                    System.out.println(Constants.MSG_NOT_FOUND);
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
                List<String> employeeList = new ArrayList<>(Arrays.asList(employeeLine.split(Constants.COMMA_DELIMITER)));
                employeeList.remove(args[0].substring(1));
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