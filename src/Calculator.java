import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
  private String regExp1 = "[/\\*]";
  private String regExp2 = "[-\\+]";

  public double calculate(String str) throws Exception {
    Pattern pattern1 = Pattern.compile("\\d+?");
    Matcher matcher1 = pattern1.matcher(str);
    if (!matcher1.find())
      throw new Exception("Выражение введено неверно");
    Pattern patternComma = Pattern.compile("\\d+?,\\d+?");
    Matcher matcherComma = patternComma.matcher(str);
    if (matcherComma.find())
      throw new Exception("Неверный ввод нецелочисленного числа (десятичные числа должны вводиться через \".\")");
    Pattern patternTogether = Pattern.compile("(\\d+?\\+\\d+?)|(\\d+?-\\d+?)|(\\d+?\\*\\d+?)|(\\d+?/\\d+?)");
    Matcher matcherTogether = patternTogether.matcher(str);
    if (matcherTogether.find())
      throw new Exception("Выражение введено неверно (символы операций должны обосабливаться пробелами)");

    Pattern patternOperation = Pattern.compile("(\\s-\\s)|(\\s/\\s)|(\\s\\*\\s)|(\\s\\+\\s)");
    Matcher matcherOperation = patternOperation.matcher(str);
    if (!matcherOperation.find()) {
      double answer = Math.round(Double.parseDouble(str) * 1000000);
      return answer / 1000000;
    }

    String[] strArr = str.split("\\s");
    for (int i = 0; i < strArr.length; i++) {
      if (strArr[i].matches(this.regExp1)) {
        double num1 = Double.parseDouble(strArr[i - 1]);
        double num2 = Double.parseDouble(strArr[i + 1]);
        double result;
        if (strArr[i].matches("\\*"))
          result = num1 * num2;
        else if (num2 == 0)
          throw new Exception("Деление на ноль");
        else
          result = num1 / num2;

        String newStr = "";
        for (int j = 0; j < i - 1; j++)
          newStr += strArr[j] + " ";
        newStr += Double.toString(result) + " ";
        for (int j = i + 2; j < strArr.length; j++)
          newStr += strArr[j] + " ";
        newStr = newStr.substring(0, newStr.length() - 1);
        System.out.println(newStr);
        return this.calculate(newStr);
      }
    }

    for (int i = 0; i < strArr.length; i++) {
      if (strArr[i].matches(this.regExp2)) {
        double num1 = Double.parseDouble(strArr[i - 1]);
        double num2 = Double.parseDouble(strArr[i + 1]);
        double result;
        if (strArr[i].matches("\\+"))
          result = num1 + num2;
        else
          result = num1 - num2;

        String newStr = "";
        for (int j = 0; j < i - 2; j++)
          newStr += strArr[j] + " ";
        newStr += Double.toString(result) + " ";
        for (int j = i + 2; j < strArr.length; j++)
          newStr += strArr[j] + " ";
        newStr = newStr.substring(0, newStr.length() - 1);
        System.out.println(newStr);
        return this.calculate(newStr);
      }
    }
    return -1;
  }
}
