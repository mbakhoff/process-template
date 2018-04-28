package task2;

public class Calculator {

  public static void main(String[] args) throws Exception {
    // TODO: implement
    // args contains two numbers and an operator
    // calculate the result and write it to standard output
    double a = Double.parseDouble(args[0]);
    double b = Double.parseDouble(args[2]);
    System.out.println(calculate(args[1], a, b));
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
