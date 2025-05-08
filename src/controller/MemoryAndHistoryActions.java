package controller;

import model.CalculatorModel;
import model.MemoryStorage;
import view.CalculatorView;

public class MemoryAndHistoryActions {
    public static class MemoryStoreButtonAction implements ButtonAction {
        @Override
        public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
            try {
                double value = Double.parseDouble(model.getExpression());
                model.storeMemory(value); // storing the value in the model
            } catch (NumberFormatException ex) {
                view.setDisplayText("Error", 5);
            }
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Button MS clicked");
        }
    }

    public static class MemoryRecallButtonAction implements ButtonAction {
        @Override
        public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
            String recalledValue = model.recallMemory(); // recalling the value in the model
            model.insertAtPosition(recalledValue, cursorPosition);
            view.setDisplayText(model.getExpression(), cursorPosition + recalledValue.length());
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Button MR clicked");
        }
    }

    public static class MemoryClearButtonAction implements ButtonAction {
        @Override
        public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
            model.clearMemory();
            view.setDisplayText("Memory Cleared", 14);
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Button MC clicked");
        }
    }

    public static class MemoryAddButtonAction implements ButtonAction {
        @Override
        public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
            try {
                double value = Double.parseDouble(model.getExpression());
                model.addToMemory(value);
            } catch (NumberFormatException ex) {
                view.setDisplayText("Error", 5);
            }
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Button M+ clicked");
        }
    }

    public static class HistoryButtonAction implements ButtonAction {
        @Override
        public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
            view.showHistoryDialog(model.getHistory());
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            System.out.println("Button Hist clicked");
        }
    }
}