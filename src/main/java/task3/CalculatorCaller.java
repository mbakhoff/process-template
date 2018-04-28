package task3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class CalculatorCaller {

  public static void main(String[] args) throws Exception {
    // TODO: implement
    // 1) start the Calculator using ProcessBuilder:
    // 1.1) create the command as in task2, but without the main class args
    // 1.2) don't redirect input/output
    // 2) run some calculations:
    // 2.1) write a line containing two numbers and an operand to the calculator's standard input
    //  note: flush the stream after writing each line!
    // 2.2) read a line containing the result from the calculator's standard output
    // 2.3) repeat 2 more times
    // 3) stop the calculator process
    // 4) wait for the calculator to finish
    List<String> command = Arrays.asList(
        "java",
        "-cp", "target/classes",
        "task3.Calculator"
    );
    Process calculator = new ProcessBuilder(command).start();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(calculator.getInputStream()));
         Writer writer = new OutputStreamWriter(calculator.getOutputStream())) {
      writer.write("1 + 2\n");
      writer.flush();
      System.out.println("calc: " + reader.readLine());
      writer.write("3 + 4\n");
      writer.flush();
      System.out.println("calc: " + reader.readLine());
      writer.write("5 + 6\n");
      writer.flush();
      System.out.println("calc: " + reader.readLine());
    }

    calculator.destroy();
    calculator.waitFor();
  }
}
