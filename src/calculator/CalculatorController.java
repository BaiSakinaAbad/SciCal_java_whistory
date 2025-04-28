package calculator;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
// Sets up listeners using a map of ButtonAction objects,
// manages actions for number, operator, function, and special buttons
// and updates the model and view. note para hindi ako malito

public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView view;
    private final Map<JButton, ButtonAction> buttonActions;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        this.buttonActions = new HashMap<>();
    }

    public void setupButtonListeners(
            JButton btnClear, JButton btnFour, JButton btnOne, JButton btnNine, JButton btnMultiply,
            JButton btnSeven, JButton btnZero, JButton btnEight, JButton btnPoint, JButton btnFive,
            JButton btnTwo, JButton btnThree, JButton btnSix, JButton btnPlus, JButton btnMinus,
            JButton btnDivide, JButton btnEqual, JButton btnSin, JButton btnCos, JButton btnTan,
            JButton btnLog, JButton btnLn, JButton btnExponent, JButton btnLeftParen, JButton btnRightParen,
            JButton btnMemoryStore, JButton btnMemoryRecall, JButton btnMemoryClear, JButton btnMemoryAdd,
            JButton btnHistory, JButton btnSqrt, JButton btnNthRoot, JButton btnCsc, JButton btnSec, JButton btnCot,
            JButton btnLeftArrow, JButton btnRightArrow, JButton btnDelete
    ) {
        // Number buttons
        buttonActions.put(btnZero, new NumberButtonAction("0"));
        buttonActions.put(btnOne, new NumberButtonAction("1"));
        buttonActions.put(btnTwo, new NumberButtonAction("2"));
        buttonActions.put(btnThree, new NumberButtonAction("3"));
        buttonActions.put(btnFour, new NumberButtonAction("4"));
        buttonActions.put(btnFive, new NumberButtonAction("5"));
        buttonActions.put(btnSix, new NumberButtonAction("6"));
        buttonActions.put(btnSeven, new NumberButtonAction("7"));
        buttonActions.put(btnEight, new NumberButtonAction("8"));
        buttonActions.put(btnNine, new NumberButtonAction("9"));
        buttonActions.put(btnPoint, new NumberButtonAction("."));

        // Operator buttons
        buttonActions.put(btnPlus, new OperatorButtonAction("+"));
        buttonActions.put(btnMinus, new OperatorButtonAction("-"));
        buttonActions.put(btnMultiply, new OperatorButtonAction("*"));
        buttonActions.put(btnDivide, new OperatorButtonAction("/"));
        buttonActions.put(btnExponent, new OperatorButtonAction("^"));
        buttonActions.put(btnLeftParen, new OperatorButtonAction("("));
        buttonActions.put(btnRightParen, new OperatorButtonAction(")"));

        // Function buttons
        buttonActions.put(btnSin, new FunctionButtonAction("sin(", 4));
        buttonActions.put(btnCos, new FunctionButtonAction("cos(", 4));
        buttonActions.put(btnTan, new FunctionButtonAction("tan(", 4));
        buttonActions.put(btnLog, new FunctionButtonAction("log(", 4));
        buttonActions.put(btnLn, new FunctionButtonAction("ln(", 3));
        buttonActions.put(btnSqrt, new FunctionButtonAction("sqrt(", 5));
        buttonActions.put(btnNthRoot, new FunctionButtonAction("nrt", 3));
        buttonActions.put(btnCsc, new FunctionButtonAction("csc(", 4));
        buttonActions.put(btnSec, new FunctionButtonAction("sec(", 4));
        buttonActions.put(btnCot, new FunctionButtonAction("cot(", 4));

        // Special actions
        buttonActions.put(btnClear, new ButtonAction() {
            @Override
            public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
                model.clearExpression();
                view.setDisplayText("", 0);
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Button Clear clicked");
            }
        });

        buttonActions.put(btnEqual, new ButtonAction() {
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
        });

        buttonActions.put(btnMemoryStore, new ButtonAction() {
            @Override
            public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
                try {
                    double value = Double.parseDouble(model.getExpression());
                    model.storeMemory(value);
                } catch (NumberFormatException ex) {
                    view.setDisplayText("Error", 5);
                }
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Button MS clicked");
            }
        });

        buttonActions.put(btnMemoryRecall, new ButtonAction() {
            @Override
            public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
                String recalledValue = model.recallMemory();
                model.insertAtPosition(recalledValue, cursorPosition);
                view.setDisplayText(model.getExpression(), cursorPosition + recalledValue.length());
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Button MR clicked");
            }
        });

        buttonActions.put(btnMemoryClear, new ButtonAction() {
            @Override
            public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
                model.clearMemory();
                view.setDisplayText("Memory Cleared", 14);
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Button MC clicked");
            }
        });

        buttonActions.put(btnMemoryAdd, new ButtonAction() {
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
        });

        buttonActions.put(btnHistory, new ButtonAction() {
            @Override
            public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
                view.showHistoryDialog(model.getHistory());
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Button Hist clicked");
            }
        });

        buttonActions.put(btnLeftArrow, new ButtonAction() {
            @Override
            public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
                view.moveCursorLeft();
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Button Left Arrow clicked");
            }
        });

        buttonActions.put(btnRightArrow, new ButtonAction() {
            @Override
            public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
                view.moveCursorRight();
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Button Right Arrow clicked");
            }
        });

        buttonActions.put(btnDelete, new ButtonAction() {
            @Override
            public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
                view.deleteCharBeforeCursor();
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println("Button Delete clicked");
            }
        });


        for (Map.Entry<JButton, ButtonAction> entry : buttonActions.entrySet()) {
            entry.getKey().addActionListener(e -> {
                entry.getValue().actionPerformed(e);
                entry.getValue().execute(model, view, view.getCursorPosition());
            });
        }
    }
}