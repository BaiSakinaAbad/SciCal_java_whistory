package controller;

import model.CalculatorModel;
import view.CalculatorView;

public class FunctionButtonAction implements ButtonAction {
    private final String function;
    private final int cursorOffset;

    public FunctionButtonAction(String function, int cursorOffset) {
        this.function = function;
        this.cursorOffset = cursorOffset;
    }

    @Override
    public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
        model.insertAtPosition(function, cursorPosition);
        view.setDisplayText(model.getExpression(), cursorPosition + cursorOffset);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Button " + function + " clicked");
    }
}