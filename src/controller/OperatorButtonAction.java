package controller;

import model.CalculatorModel;
import view.CalculatorView;

// Using buttonAction to handle operator
public class OperatorButtonAction implements ButtonAction {
    private final String operator;


    // operator button action
    public OperatorButtonAction(String operator) {
        this.operator = operator;
    }

    @Override
    public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
        model.insertAtPosition(operator, cursorPosition); // passing it to expression in model
        view.setDisplayText(model.getExpression(), cursorPosition + operator.length());// to update cursor position
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Button " + operator + " clicked");
    }

}