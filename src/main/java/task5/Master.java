package task5;

import java.util.Arrays;
import java.util.List;

public class Master {

  public static void main(String[] args) throws Exception {
    // TODO: implement
    // 1) start the Apprentice using ProcessBuilder
    // 1.1) find the value of the PATH environment variable from System.getenv
    // 1.2) remove the PATH variable from the apprentice's environment
    // 1.3) remove the JAVA_HOME variable from the apprentice's environment
    // 1.4) add the value of PATH to the apprentice's environment as APPRENTICE_PATH
    // 1.5) inherit input/output
    // 2) wait for the apprentice to finish
    List<String> command = Arrays.asList(
        "java",
        "-cp", "target/classes",
        "task5.Apprentice"
    );
    ProcessBuilder builder = new ProcessBuilder(command).inheritIO();
    builder.environment().remove("PATH");
    builder.environment().remove("JAVA_HOME");
    builder.environment().put("APPRENTICE_PATH", System.getenv("PATH"));
    builder.start().waitFor();
  }
}
