package model;

// IMPORTATNT! DO NOT CHANGE ANYTHING.
// handles the precedence logic. Uses stack to perform postfix algo
import java.util.Stack;

public class ExpressionParser {
    private int getPrecedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1; // Lowest precedence
            case '*':
            case '/':
                return 2; // Higher precedence
            case '^':
            case 'n': // 'n' represents nth root ( nrt for nth root operation)
                return 3; // Highest precedence for exponentiation and nth root
            default:
                return 0; // Default for non-operators
        }
    }

    //identify operators during infix-to-postfix conversion
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == 'n';
    }

    public String toPostfix(String infix) {
        Stack<Character> operators = new Stack<>(); // Stack to hold operators during conversion
        Stack<String> functions = new Stack<>(); // Stack to hold functions
        StringBuilder postfix = new StringBuilder(); // Builds the postfix expression
        String number = ""; // Temporarily stores digits to form numbers
        boolean afterFunction = false; // Flag to track if immediately after a function

        // Loop through each character in the infix expression
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            // If the character is a digit or decimal point, build the number
            if (Character.isDigit(c) || c == '.') {
                number += c;
            }
            // If the character is an opening parenthesis
            else if (c == '(') {
                // If we have a number, append it to postfix with a space
                if (!number.isEmpty()) {
                    postfix.append(number).append(" ");
                    number = "";
                }
                operators.push(c);
                afterFunction = false;
            }

            // If the character is a closing parenthesis
            else if (c == ')') {
                if (!number.isEmpty()) {
                    postfix.append(number).append(" ");
                    number = "";
                }
                // Pop operators until we find the matching opening parenthesis
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop()).append(" ");
                }
                // Remove the opening parenthesis from the stack
                if (!operators.isEmpty()) {
                    operators.pop();
                }
                // If there's a function, append it to postfix
                if (!functions.isEmpty()) {
                    postfix.append(functions.pop()).append(" ");
                }
            }
            // If the character is an operator (+, -, *, /, ^, n)
            else if (isOperator(c)) {
                // Append any pending number to postfix
                if (!number.isEmpty()) {
                    postfix.append(number).append(" ");
                    number = "";
                }
                // Pop operators with higher or equal precedence
                while (!operators.isEmpty() && operators.peek() != '(' && getPrecedence(operators.peek()) >= getPrecedence(c)) {
                    postfix.append(operators.pop()).append(" ");
                }
                operators.push(c == 'n' ? 'n' : c);
            }
            // If the character is a letter (indicating a function like sin, cos)
            else if (Character.isLetter(c)) {
                StringBuilder func = new StringBuilder();
                // Collect all letters to form the function name (e.g., "sin")
                while (i < infix.length() && Character.isLetter(infix.charAt(i))) {
                    func.append(infix.charAt(i));
                    i++;
                }
                i--;
                functions.push(func.toString()); // Push the function to the functions stack
                afterFunction = true; // indicate if its a function
            }
            // For any other character (e.g., spaces)
            else {
                if (!number.isEmpty()) {
                    postfix.append(number).append(" ");
                    number = "";
                }
            }
        }

        // Append any remaining number to postfix
        if (!number.isEmpty()) {
            postfix.append(number).append(" ");
        }

        // Pop all remaining operators and append them to postfix
        while (!operators.isEmpty()) {
            postfix.append(operators.pop()).append(" ");
        }

        // Return final postfix expression trimmed of extra spaces
        return postfix.toString().trim();
    }

    public double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>(); // Stack to hold operands during evaluation
        String[] tokens = postfix.split("\\s+"); // Split the postfix expression into tokens

        // Loop through each token in the postfix expression
        for (String token : tokens) {
            // If the token is a number (including negative numbers)
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            }
            // If the token is a function (e.g., sin, cos, tan)
            else if (token.equals("sin") || token.equals("cos") || token.equals("tan") ||
                    token.equals("log") || token.equals("ln") || token.equals("sqrt") ||
                    token.equals("csc") || token.equals("sec") || token.equals("cot")) {
                double a = stack.pop();
                switch (token) {
                    case "sin":
                        stack.push(Math.sin(Math.toRadians(a)));
                        break;
                    case "cos":
                        stack.push(Math.cos(Math.toRadians(a)));
                        break;
                    case "tan":
                        stack.push(Math.tan(Math.toRadians(a)));
                        break;
                    case "log":
                        stack.push(Math.log10(a));
                        break;
                    case "ln":
                        stack.push(Math.log(a));
                        break;
                    case "sqrt":
                        stack.push(Math.sqrt(a));
                        break;
                    case "csc":
                        double sinVal = Math.sin(Math.toRadians(a));
                        if (sinVal == 0) throw new ArithmeticException("Division by zero");
                        stack.push(1.0 / sinVal); // Compute cosecant (1/sin)
                        break;
                    case "sec":
                        double cosVal = Math.cos(Math.toRadians(a));
                        if (cosVal == 0) throw new ArithmeticException("Division by zero");
                        stack.push(1.0 / cosVal); // Compute secant (1/cos)
                        break;
                    case "cot":
                        double tanVal = Math.tan(Math.toRadians(a));
                        if (tanVal == 0) throw new ArithmeticException("Division by zero");
                        stack.push(1.0 / tanVal); // Compute cotangent (1/tan)
                        break;
                }
            }

            // If the token is the nth root operator (nrt)
            else if (token.equals("n")) {
                double y = stack.pop();
                double x = stack.pop();
                stack.push(Math.pow(y, 1.0 / x)); // Compute xth root of y (y^(1/x))
            }
            // If the token is a binary operator (+, -, *, /, ^)
            else {
                double b = stack.pop(); // Pop the second operand
                double a = stack.isEmpty() ? 0 : stack.pop(); // Pop the first operand (default to 0 if stack is empty)
                switch (token.charAt(0)) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        if (b == 0) throw new ArithmeticException("Division by zero");
                        stack.push(a / b);
                        break;
                    case '^':
                        stack.push(Math.pow(a, b));
                        break;
                }
            }
        }

        // Return the final result from the stack, or 0 if the stack is empty
        return stack.isEmpty() ? 0 : stack.pop();
    }
}