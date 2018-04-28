package task2;

import java.util.Arrays;
import java.util.List;

public class CalculatorCaller {

  public static void main(String[] args) throws Exception {
    // TODO: implement
    // 1) start the Calculator using ProcessBuilder:
    // 1.1) the command should consist of:
    //  java binary,
    //  classpath options (can be hardcoded to target/classes),
    //  main class name,
    //  main class arguments (numbers and the operator)
    // 1.2) inherit standard output/output
    // 2) wait for the calculator to finish
    //  the result should appear in the standard output
    List<String> command = Arrays.asList(
        "java",
        "-cp", "target/classes",
        "task2.Calculator",
        "1", "+", "2"
    );
    new ProcessBuilder(command).inheritIO().start().waitFor();
  }
}
