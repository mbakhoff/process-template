package task4;

import java.util.Arrays;
import java.util.List;

public class Master {

  public static void main(String[] args) throws Exception {
    // TODO: implement
    // 1) start the Apprentice using ProcessBuilder
    // 1.1) remove the PATH variable from the apprentice's environment
    // 1.2) inherit input/output
    // 2) wait for the apprentice to finish
    List<String> command = Arrays.asList(
        "java",
        "-cp", "target/classes",
        "task4.Apprentice"
    );
    ProcessBuilder builder = new ProcessBuilder(command).inheritIO();
    builder.environment().remove("PATH");
    builder.start().waitFor();
  }
}
