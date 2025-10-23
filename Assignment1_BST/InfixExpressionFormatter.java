import java.util.*;

public class InfixExpressionFormatter {
    private static String formatExpression(String input) {
        Stack<String> operands = new Stack<>();
        Stack<String> operators = new Stack<>();
        String[] tokens = input.split("\\s+");

        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                operators.push(token);
            } else if (token.equals(")")) {
                String operand2 = operands.pop();
                String operand1 = operands.pop();
                String operator = operators.pop();
                String subExpression = "( " + operand1 + " " + operator + " " + operand2 + " )";
                operands.push(subExpression);
            } else {
                operands.push(token);
            }
        }
        return operands.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Expression: ");
        String input = scanner.nextLine();
        System.out.println("\nThe Result is: " + formatExpression(input));
        scanner.close();
    }
}
