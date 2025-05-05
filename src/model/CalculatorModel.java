package model;

import java.util.ArrayList;
import java.util.List;
// Store expression, history, and memory
// and parse expression parsing/evaluation to ExpressionParser. Just a guide

public class CalculatorModel {
    private String expression;
    private final MemoryStorage memory;
    private final List<String> history;
    private final ExpressionParser parser;

    public CalculatorModel() {
        this.expression = "";
        this.memory = new MemoryStorage();
        this.history = new ArrayList<>();
        this.parser = new ExpressionParser();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void insertAtPosition(String value, int position) {
        if (position < 0 || position > expression.length()) {
            expression += value;
        } else {
            expression = expression.substring(0, position) + value + expression.substring(position);
        }
    }

    public void clearExpression() {
        this.expression = "";
    }

    public void addToHistory(String operation) {
        history.add(operation);
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

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

    public String toPostfix(String infix) {
        return parser.toPostfix(infix);
    }

    public double evaluatePostfix(String postfix) {
        return parser.evaluatePostfix(postfix);
    }
}