package task3;

import java.util.Scanner;

public class Calculator {

  public static void main(String[] args) throws Exception {
    // TODO: implement
    // (loop) read lines from the standard input.
    // for each line:
    //  find two numbers and an operator
    //  write a line containing the result to the standard output
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
      double a = sc.nextDouble();
      String op = sc.next();
      double b = sc.nextDouble();
      System.out.println(calculate(op, a, b));
    }
  }

  private static double calculate(String arg, double a, double b) {
    switch (arg) {
      case "+": return a + b;
      case "-": return a - b;
      case "*": return a * b;
      case "/": return a / b;
      default: throw new IllegalArgumentException(arg);
    }
  }
}
