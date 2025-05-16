package model;

import java.util.ArrayList;
import java.util.List;

/**
 * CalculatorModel handles the current expression,
 * calculation history, and memory. It handles expression parsing and evaluation to
 * ExpressionParser
 */
public class CalculatorModel {
    private String expression; // Current mathematical expression
    private final MemoryStorage memory; // Handles memory operations
    private final List<String> history; // Stores history of operations
    private final ExpressionParser parser; // Converts infix to postfix and evaluates expressions

    public CalculatorModel() {
        this.expression = "";
        this.memory = new MemoryStorage();
        this.history = new ArrayList<>();
        this.parser = new ExpressionParser();
    }

    //gets the current expression.
    public String getExpression() {
        return expression;
    }

   //current expression to a new value
    public void setExpression(String expression) {
        this.expression = expression;
    }

    //Inserts a value into the expression at a specified position
    public void insertAtPosition(String value, int position) {
        if (position < 0 || position > expression.length()) {
            expression += value; // Append if position is out of bounds
        } else {
            // Split expression at position, insert value, and recombine
            expression = expression.substring(0, position) + value + expression.substring(position);
        }
    }

    public void clearExpression() {
        this.expression = "";
    }

    public void addToHistory(String operation) {
        history.add(operation);
    }

    //retrieves a copy of the calculation history
    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    // store value memory
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


     //Converts an infix expression to postfix notation.
    public String toPostfix(String infix) {
        return parser.toPostfix(infix);
    }
     //Evaluates a postfix expression to produce a numerical result.
    public double evaluatePostfix(String postfix) {
        return parser.evaluatePostfix(postfix);
    }
}