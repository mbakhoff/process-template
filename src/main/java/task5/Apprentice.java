package task5;

public class Apprentice {

  public static void main(String[] args) throws Exception {
    // TODO: implement
    // 1) make sure that environment variable PATH is missing/empty
    // 2) make sure that environment variable JAVA_HOME is missing/empty
    // 3) find the value of APPRENTICE_PATH environment variable
    // 4) find the path of the java binary:
    //  (this is what the ProcessBuilder would do with the PATH variable if it would exist)
    // 4.1) APPRENTICE_PATH is a list of location that contain binaries
    // 4.2) try each location and check if the java binary exists
    //  search for LOCATION/java on linux/macos
    //  search for LOCATION/java.exe on windows
    // 5) print the path of the java binary
    // 6) start java with the "-version" argument, inherit input/output
  }
}
