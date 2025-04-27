import javax.swing.*;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
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
        btnOne.addActionListener(e -> {
            System.out.println("Button 1 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("1", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnTwo.addActionListener(e -> {
            System.out.println("Button 2 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("2", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnThree.addActionListener(e -> {
            System.out.println("Button 3 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("3", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnFour.addActionListener(e -> {
            System.out.println("Button 4 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("4", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnFive.addActionListener(e -> {
            System.out.println("Button 5 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("5", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnSix.addActionListener(e -> {
            System.out.println("Button 6 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("6", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnSeven.addActionListener(e -> {
            System.out.println("Button 7 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("7", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnEight.addActionListener(e -> {
            System.out.println("Button 8 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("8", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnNine.addActionListener(e -> {
            System.out.println("Button 9 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("9", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnZero.addActionListener(e -> {
            System.out.println("Button 0 clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("0", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });

        // Operation buttons
        btnPlus.addActionListener(e -> {
            System.out.println("Button + clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("+", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnMinus.addActionListener(e -> {
            System.out.println("Button - clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("-", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnMultiply.addActionListener(e -> {
            System.out.println("Button * clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("*", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnDivide.addActionListener(e -> {
            System.out.println("Button / clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("/", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnExponent.addActionListener(e -> {
            System.out.println("Button ^ clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("^", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnLeftParen.addActionListener(e -> {
            System.out.println("Button ( clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });
        btnRightParen.addActionListener(e -> {
            System.out.println("Button ) clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition(")", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });

        // Function buttons
        btnSin.addActionListener(e -> {
            System.out.println("Button sin clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("sin(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 4);
        });
        btnCos.addActionListener(e -> {
            System.out.println("Button cos clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("cos(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 4);
        });
        btnTan.addActionListener(e -> {
            System.out.println("Button tan clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("tan(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 4);
        });
        btnLog.addActionListener(e -> {
            System.out.println("Button log clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("log(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 4);
        });
        btnLn.addActionListener(e -> {
            System.out.println("Button ln clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("ln(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 3);
        });
        btnSqrt.addActionListener(e -> {
            System.out.println("Button sqrt clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("sqrt(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 5);
        });
        btnNthRoot.addActionListener(e -> {
            System.out.println("Button nrt clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("nrt", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 3);
        });
        btnCsc.addActionListener(e -> {
            System.out.println("Button csc clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("csc(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 4);
        });
        btnSec.addActionListener(e -> {
            System.out.println("Button sec clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("sec(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 4);
        });
        btnCot.addActionListener(e -> {
            System.out.println("Button cot clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition("cot(", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 4);
        });

        // Equal button to evaluate the expression and store in history
        btnEqual.addActionListener(e -> {
            System.out.println("Button = clicked");
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
        });

        // Clear button
        btnClear.addActionListener(e -> {
            System.out.println("Button Clear clicked");
            model.clearExpression();
            view.setDisplayText("", 0);
        });

        // Decimal point
        btnPoint.addActionListener(e -> {
            System.out.println("Button . clicked");
            int cursorPos = view.getCursorPosition();
            model.insertAtPosition(".", cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + 1);
        });

        // Memory buttons
        btnMemoryStore.addActionListener(e -> {
            System.out.println("Button MS clicked");
            try {
                double value = Double.parseDouble(model.getExpression());
                model.storeMemory(value);
            } catch (NumberFormatException ex) {
                view.setDisplayText("Error", 5);
            }
        });
        btnMemoryRecall.addActionListener(e -> {
            System.out.println("Button MR clicked");
            int cursorPos = view.getCursorPosition();
            String recalledValue = model.recallMemory();
            model.insertAtPosition(recalledValue, cursorPos);
            view.setDisplayText(model.getExpression(), cursorPos + recalledValue.length());
        });
        btnMemoryClear.addActionListener(e -> {
            System.out.println("Button MC clicked");
            model.clearMemory();
            view.setDisplayText("Memory Cleared", 14);
        });
        btnMemoryAdd.addActionListener(e -> {
            System.out.println("Button M+ clicked");
            try {
                double value = Double.parseDouble(model.getExpression());
                model.addToMemory(value);
            } catch (NumberFormatException ex) {
                view.setDisplayText("Error", 5);
            }
        });

        // History button
        btnHistory.addActionListener(e -> {
            System.out.println("Button Hist clicked");
            view.showHistoryDialog(model.getHistory());
        });

        // Left arrow button
        btnLeftArrow.addActionListener(e -> {
            System.out.println("Button Left Arrow clicked");
            view.moveCursorLeft();
        });

        // Right arrow button
        btnRightArrow.addActionListener(e -> {
            System.out.println("Button Right Arrow clicked");
            view.moveCursorRight();
        });

        // Delete button
        btnDelete.addActionListener(e -> {
            System.out.println("Button Delete clicked");
            view.deleteCharBeforeCursor();
        });
    }
}