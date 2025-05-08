package controller;

import model.CalculatorModel;
import view.CalculatorView;

public class CalculationAndResetActions {
    public static class ClearButtonAction implements ButtonAction {
        @Override
        public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
            model.clearExpression();
            view.setDisplayText("", 0);
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Button Clear clicked");
        }
    }

    public static class EqualButtonAction implements ButtonAction {
        @Override
        public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
            try {
                String postfix = model.toPostfix(model.getExpression());
                double result = model.evaluatePostfix(postfix);
                String operation = model.getExpression() + " = " + result;
                model.addToHistory(operation);
                model.setExpression(String.valueOf(result));
                view.setDisplayText(model.getExpression(), model.getExpression().length());
            } catch (Exception ex) {
                view.setDisplayText("Error", 5);
                model.setExpression("");
            }
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Button = clicked");
        }
    }

    public static class DeleteButtonAction implements ButtonAction {
        @Override
        public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
            CalculatorView.deleteCharBeforeCursor();
            model.setExpression(CalculatorView.getDisplayText());
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Button Delete clicked");
        }
    }
}