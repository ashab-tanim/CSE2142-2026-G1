/*
  Holds all application-wide constant values including file paths,
  delimiters, command line flags, and display messages.
 */
public class Constants {
    // File Paths
    public static final String FILE_NAME = "employees.txt";

    // Delimiters
    public static final String COMMA_DELIMITER = ",";
    public static final String COMMA_SPACE = ", ";

    // Command Flags
    public static final String FLAG_LIST = "l";
    public static final String FLAG_RANDOM = "s";
    public static final String FLAG_ADD = "+";
    public static final String FLAG_SEARCH = "?";
    public static final String FLAG_COUNT = "c";
    public static final String FLAG_UPDATE = "u";
    public static final String FLAG_DELETE = "d";

    // Console Output Messages
    public static final String MSG_LOADING = "Loading data ...";
    public static final String MSG_LOADED = "Data Loaded.";
    public static final String MSG_UPDATED = "Data Updated.";
    public static final String MSG_DELETED = "Data Deleted.";
    public static final String MSG_FOUND = "Employee found!";
    public static final String MSG_NOT_FOUND = "Employee not found.";
    public static final String MSG_UPDATED_TEXT = "Updated";
    public static final String MSG_INVALID_ARGS = "Error: Invalid argument provided.";
    public static final String MSG_WRONG_ARG_COUNT = "Error: Please provide exactly one argument.";
}