package task5;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

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
    String envPath = System.getenv("PATH");
    if (envPath != null && !envPath.isEmpty())
      throw new IllegalStateException("PATH is set");

    String envJavaHome = System.getenv("JAVA_HOME");
    if (envJavaHome != null && !envJavaHome.isEmpty())
      throw new IllegalStateException("JAVA_HOME is set");

    String apprenticePath = System.getenv("APPRENTICE_PATH");
    String[] locations = apprenticePath.split("[;:]");

    Path java = findJava(locations);

    System.out.println("using " + java);
    List<String> command = Arrays.asList(java.toString(), "-version");
    new ProcessBuilder(command).inheritIO().start().waitFor();
  }

  private static Path findJava(String[] locations) {
    for (String location : locations) {
      Path java = Paths.get(location).resolve(isWindows() ? "java.exe" : "java");
      if (Files.isRegularFile(java))
        return java;
    }
    throw new IllegalStateException("java not found");
  }

  private static boolean isWindows() {
    // rough heuristic; could also check System.getProperty("os.name")
    return File.pathSeparatorChar == ';';
  }
}
