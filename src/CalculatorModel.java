import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculatorModel {
    private String expression = "";
    private MemoryStorage memory;
    private List<String> history;

    public CalculatorModel() {
        memory = new MemoryStorage();
        history = new ArrayList<>();
    }

    // Getters and setters for expression
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    // Append to expression
    public void appendToExpression(String value) {
        this.expression += value;
    }

    // Clear expression
    public void clearExpression() {
        this.expression = "";
    }

    // History management
    public void addToHistory(String operation) {
        history.add(operation);
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    // Memory operations
    public void storeMemory(double value) {
        memory.store(value);
    }

    public String recallMemory() {
        return memory.recall();
    }

    public void clearMemory() {
        memory.clear();
    }

    public void addToMemory(double value) {
        memory.add(value);
    }

    // Helper method to determine operator precedence
    private int getPrecedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
            case 'n': // For nth root (x√y)
                return 3;
            default:
                return 0;
        }
    }

    // Check if a character is an operator or parenthesis
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == 'n';
    }

    // Convert infix expression to postfix (Shunting Yard Algorithm with parentheses and functions)
    public String toPostfix(String infix) {
        Stack<Character> operators = new Stack<>();
        Stack<String> functions = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        String number = "";
        boolean afterFunction = false;

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                number += c;
            } else if (c == '(') {
                if (!number.isEmpty()) {
                    postfix.append(number).append(" ");
                    number = "";
                }
                operators.push(c);
                afterFunction = false;
            } else if (c == ')') {
                if (!number.isEmpty()) {
                    postfix.append(number).append(" ");
                    number = "";
                }
                while (!operators.isEmpty() && operators.peek() != '(') {
                    postfix.append(operators.pop()).append(" ");
                }
                if (!operators.isEmpty()) {
                    operators.pop(); // Remove '('
                }
                if (!functions.isEmpty()) {
                    postfix.append(functions.pop()).append(" ");
                }
            } else if (isOperator(c)) {
                if (!number.isEmpty()) {
                    postfix.append(number).append(" ");
                    number = "";
                }
                while (!operators.isEmpty() && operators.peek() != '(' && getPrecedence(operators.peek()) >= getPrecedence(c)) {
                    postfix.append(operators.pop()).append(" ");
                }
                operators.push(c == 'n' ? 'n' : c); // Handle nth root
            } else if (Character.isLetter(c)) {
                // Handle functions like sin, cos, tan, log, ln, sqrt, csc, sec, cot
                StringBuilder func = new StringBuilder();
                while (i < infix.length() && Character.isLetter(infix.charAt(i))) {
                    func.append(infix.charAt(i));
                    i++;
                }
                i--; // Step back one char
                functions.push(func.toString());
                afterFunction = true;
            } else {
                if (!number.isEmpty()) {
                    postfix.append(number).append(" ");
                    number = "";
                }
            }
        }

        if (!number.isEmpty()) {
            postfix.append(number).append(" ");
        }

        while (!operators.isEmpty()) {
            postfix.append(operators.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    // Evaluate postfix expression
    public double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else if (token.equals("sin") || token.equals("cos") || token.equals("tan") ||
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
                        stack.push(1.0 / sinVal);
                        break;
                    case "sec":
                        double cosVal = Math.cos(Math.toRadians(a));
                        if (cosVal == 0) throw new ArithmeticException("Division by zero");
                        stack.push(1.0 / cosVal);
                        break;
                    case "cot":
                        double tanVal = Math.tan(Math.toRadians(a));
                        if (tanVal == 0) throw new ArithmeticException("Division by zero");
                        stack.push(1.0 / tanVal);
                        break;
                }
            } else if (token.equals("n")) { // Handle nth root (x√y)
                double y = stack.pop();
                double x = stack.pop();
                stack.push(Math.pow(y, 1.0 / x));
            } else {
                double b = stack.pop();
                double a = stack.isEmpty() ? 0 : stack.pop();
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

        return stack.isEmpty() ? 0 : stack.pop();
    }
}