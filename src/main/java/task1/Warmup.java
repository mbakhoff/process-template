package task1;

import java.util.Arrays;
import java.util.List;

public class Warmup {

  public static void main(String[] args) throws Exception {
    // TODO: implement
    // 1) run "java -version" using ProcessBuilder
    //   don't forget to pass the command as a list: Arrays.asList("java", "-version")
    // 2) inherit input/output
    // 3) wait for the process to finish
    // 4) make sure the java version is visible

    // make sure to inherit input/output using inheritIO()
    // don't use Process#getInputStream in this task

    List<String> command = Arrays.asList("java", "-version");
    new ProcessBuilder(command)
        .inheritIO()
        .start()
        .waitFor();
  }
}
