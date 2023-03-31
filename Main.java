import java.util.Scanner;

public class Main { //the operations don't occur in order
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Welcome to the Recursion Calculator");
      
      System.out.println("Enter an expression: ");
      String expression = sc.nextLine();
        sc.close();
        int result = evaluate(expression);
        System.out.println("Result: " + result);
    }

    public static int evaluate(String expression) {
        if (expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')') {
            return evaluate(expression.substring(1, expression.length() - 1));
        }
        int depth = 0;
        int maxDepth = 0;
        int index = -1;
        char operator = ' ';
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                depth++;
                if (depth > maxDepth) {
                    maxDepth = depth;
                }
            } else if (c == ')') {
                depth--;
            } else if (depth == 0 && (c == '+' || c == '-' || c == '*' || c == '/' || c == '^')) {
                if (maxDepth == 0 || depth < maxDepth) {
                    operator = c;
                    index = i;
                    maxDepth = depth;
                }
            }
        }
        if (operator == ' ') {
            if (expression.charAt(0) == '(' && expression.charAt(expression.length() - 1) == ')') {
                return evaluate(expression.substring(1, expression.length() - 1));
            } else {
                return Integer.parseInt(expression);
            }
        } else {
            String leftExpression = expression.substring(0, index);
            String rightExpression = expression.substring(index + 1);
            int leftValue = evaluate(leftExpression);
            int rightValue = evaluate(rightExpression);

            switch (operator) {
                case '+':
                    return leftValue + rightValue;
                case '-':
                    return leftValue - rightValue;
                case '*':
                    return leftValue * rightValue;
                case '/':
                    return leftValue / rightValue;
                case '^':
                    return (int) Math.pow(leftValue, rightValue);
                default:
                    return 0;
            }
        }
    }
}
