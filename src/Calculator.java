import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
  private String regExp1 = "[/\\*]";
  private String regExp2 = "[-\\+]";

  public int[] getNumbersBounds(String[] strArr, int index) {
    int startNum1 = 0;
    int endNum1 = index - 1;
    for (int j = index - 1; j > 0; j--) {
      if (strArr[j].matches(this.regExp2) || strArr[j].matches(this.regExp1)) {       // remove second check??
        startNum1 = j + 1;
        break;
      }
    }

    int startNum2 = index + 1;
    int endNum2 = strArr.length - 1;
    for (int j = index + 1; j < strArr.length; j++) {
      if (strArr[j].matches(this.regExp2) || strArr[j].matches(this.regExp1)) {       // remove second check??
        endNum2 = j - 1;
        break;
      }
    }

    return new int[]{ startNum1, endNum1, startNum2, endNum2 };
  }

  public double calculate(String str) throws Exception {
    Pattern pattern = Pattern.compile("[-/\\*\\+]");
    Matcher matcher = pattern.matcher(str);
    if (!matcher.find()) {
      System.out.println("answer");
      return Double.parseDouble(str);
    }

    String[] strArr = str.split("");
    for (int i = 0; i < strArr.length; i++) {
      if (strArr[i].matches(this.regExp1)) {
        int[] numbersBounds = this.getNumbersBounds(strArr, i);
        String numStr1 = "";
        for (int j = numbersBounds[0]; j <= numbersBounds[1]; j++)
          numStr1 += strArr[j];
        double num1 = Double.parseDouble(numStr1);
        String numStr2 = "";
        for (int j = numbersBounds[2]; j <= numbersBounds[3]; j++)
          numStr2 += strArr[j];
        double num2 = Double.parseDouble(numStr2);

        double result;
        if (strArr[i].matches("\\*"))
          result = num1 * num2;
        else if (num2 == 0)
          throw new Exception("Деление на ноль");
        else
          result = num1 / num2;

        String newStr = "";
        for (int j = 0; j < numbersBounds[0]; j++)
          newStr += strArr[j];
        newStr += Double.toString(result);
        for (int j = numbersBounds[3] + 1; j < strArr.length; j++)
          newStr += strArr[j];
//        System.out.println("newstr1: " + newStr);
        return this.calculate(newStr);
      } else if (strArr[i].matches(this.regExp2)) {
        int[] numbersBounds = this.getNumbersBounds(strArr, i);
        String numStr1 = "";
        for (int j = numbersBounds[0]; j <= numbersBounds[1]; j++)
          numStr1 += strArr[j];
        double num1 = Double.parseDouble(numStr1);
        String numStr2 = "";
        for (int j = numbersBounds[2]; j <= numbersBounds[3]; j++)
          numStr2 += strArr[j];
        double num2 = Double.parseDouble(numStr2);

        double result;
        if (strArr[i].matches("\\+"))
          result = num1 + num2;
        else
          result = num1 - num2;

        String newStr = "";
        for (int j = 0; j < numbersBounds[0]; j++)
          newStr += strArr[j];
        newStr += Double.toString(result);
        for (int j = numbersBounds[3] + 1; j < strArr.length; j++)
          newStr += strArr[j];
//        System.out.println("newstr2: " + newStr);
        return this.calculate(newStr);
      }
    }

//    System.out.println(str);
//    return Double.parseDouble(str);
    return -1;
  }
}
