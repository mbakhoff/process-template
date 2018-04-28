package task4;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Apprentice {

  public static void main(String[] args) throws Exception {
    // TODO: implement
    // 1) make sure that environment variable PATH is missing/empty
    //   hint: use System.getenv
    // 2) find the value of JAVA_HOME environment variable
    // 3) find the path of the java binary (it's in JAVA_HOME/bin)
    //  the code should work regardless where Java is installed (as long as JAVA_HOME is correct)
    // 4) print the path of the java binary
    // 5) start java with the "-version" argument, inherit input/output
    String envPath = System.getenv("PATH");
    if (envPath != null && !envPath.isEmpty())
      throw new IllegalStateException("PATH is set");
    String javaHome = System.getenv("JAVA_HOME");
    Path java = Paths.get(javaHome)
        .resolve("bin")
        .resolve(isWindows() ? "java.exe" : "java");

    System.out.println("using " + java);
    List<String> command = Arrays.asList(java.toString(), "-version");
    new ProcessBuilder(command).inheritIO().start().waitFor();
  }

  private static boolean isWindows() {
    // rough heuristic; could also check System.getProperty("os.name")
    return File.pathSeparatorChar == ';';
  }
}
