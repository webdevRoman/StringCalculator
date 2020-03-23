import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    Calculator calc = new Calculator();
    Scanner sc = new Scanner(System.in);

    System.out.println("Введите выражение (символы операций должны обосабливаться пробелами):");
    String expression = sc.nextLine();

    try {
      double answer = calc.calculate(expression);
      System.out.println("Ответ: " + answer);
      sc.close();
    } catch (Exception e) {
      System.out.println("Ошибка: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
