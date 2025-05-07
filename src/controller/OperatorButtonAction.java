package controller;

import model.CalculatorModel;
import view.CalculatorView;

// Using buttonAction to handle operator
public class OperatorButtonAction implements ButtonAction {
    private final String operator;

    public OperatorButtonAction(String operator) {
        this.operator = operator;
    }

    @Override
    public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
        model.insertAtPosition(operator, cursorPosition);
        view.setDisplayText(model.getExpression(), cursorPosition + operator.length());
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Button " + operator + " clicked");
    }
}