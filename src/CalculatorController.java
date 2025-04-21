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
            JButton btnHistory, JButton btnSqrt, JButton btnNthRoot, JButton btnCsc, JButton btnSec, JButton btnCot
    ) {
        // Number buttons
        btnOne.addActionListener(e -> {
            System.out.println("Button 1 clicked");
            model.appendToExpression("1");
            view.setDisplayText(model.getExpression());
        });
        btnTwo.addActionListener(e -> {
            System.out.println("Button 2 clicked");
            model.appendToExpression("2");
            view.setDisplayText(model.getExpression());
        });
        btnThree.addActionListener(e -> {
            System.out.println("Button 3 clicked");
            model.appendToExpression("3");
            view.setDisplayText(model.getExpression());
        });
        btnFour.addActionListener(e -> {
            System.out.println("Button 4 clicked");
            model.appendToExpression("4");
            view.setDisplayText(model.getExpression());
        });
        btnFive.addActionListener(e -> {
            System.out.println("Button 5 clicked");
            model.appendToExpression("5");
            view.setDisplayText(model.getExpression());
        });
        btnSix.addActionListener(e -> {
            System.out.println("Button 6 clicked");
            model.appendToExpression("6");
            view.setDisplayText(model.getExpression());
        });
        btnSeven.addActionListener(e -> {
            System.out.println("Button 7 clicked");
            model.appendToExpression("7");
            view.setDisplayText(model.getExpression());
        });
        btnEight.addActionListener(e -> {
            System.out.println("Button 8 clicked");
            model.appendToExpression("8");
            view.setDisplayText(model.getExpression());
        });
        btnNine.addActionListener(e -> {
            System.out.println("Button 9 clicked");
            model.appendToExpression("9");
            view.setDisplayText(model.getExpression());
        });
        btnZero.addActionListener(e -> {
            System.out.println("Button 0 clicked");
            model.appendToExpression("0");
            view.setDisplayText(model.getExpression());
        });

        // Operation buttons
        btnPlus.addActionListener(e -> {
            System.out.println("Button + clicked");
            model.appendToExpression("+");
            view.setDisplayText(model.getExpression());
        });
        btnMinus.addActionListener(e -> {
            System.out.println("Button - clicked");
            model.appendToExpression("-");
            view.setDisplayText(model.getExpression());
        });
        btnMultiply.addActionListener(e -> {
            System.out.println("Button * clicked");
            model.appendToExpression("*");
            view.setDisplayText(model.getExpression());
        });
        btnDivide.addActionListener(e -> {
            System.out.println("Button / clicked");
            model.appendToExpression("/");
            view.setDisplayText(model.getExpression());
        });
        btnExponent.addActionListener(e -> {
            System.out.println("Button ^ clicked");
            model.appendToExpression("^");
            view.setDisplayText(model.getExpression());
        });
        btnLeftParen.addActionListener(e -> {
            System.out.println("Button ( clicked");
            model.appendToExpression("(");
            view.setDisplayText(model.getExpression());
        });
        btnRightParen.addActionListener(e -> {
            System.out.println("Button ) clicked");
            model.appendToExpression(")");
            view.setDisplayText(model.getExpression());
        });

        // Function buttons
        btnSin.addActionListener(e -> {
            System.out.println("Button sin clicked");
            model.appendToExpression("sin(");
            view.setDisplayText(model.getExpression());
        });
        btnCos.addActionListener(e -> {
            System.out.println("Button cos clicked");
            model.appendToExpression("cos(");
            view.setDisplayText(model.getExpression());
        });
        btnTan.addActionListener(e -> {
            System.out.println("Button tan clicked");
            model.appendToExpression("tan(");
            view.setDisplayText(model.getExpression());
        });
        btnLog.addActionListener(e -> {
            System.out.println("Button log clicked");
            model.appendToExpression("log(");
            view.setDisplayText(model.getExpression());
        });
        btnLn.addActionListener(e -> {
            System.out.println("Button ln clicked");
            model.appendToExpression("ln(");
            view.setDisplayText(model.getExpression());
        });
        btnSqrt.addActionListener(e -> {
            System.out.println("Button sqrt clicked");
            model.appendToExpression("sqrt(");
            view.setDisplayText(model.getExpression());
        });
        btnNthRoot.addActionListener(e -> {
            System.out.println("Button nrt clicked");
            model.appendToExpression("nrt");
            view.setDisplayText(model.getExpression());
        });
        btnCsc.addActionListener(e -> {
            System.out.println("Button csc clicked");
            model.appendToExpression("csc(");
            view.setDisplayText(model.getExpression());
        });
        btnSec.addActionListener(e -> {
            System.out.println("Button sec clicked");
            model.appendToExpression("sec(");
            view.setDisplayText(model.getExpression());
        });
        btnCot.addActionListener(e -> {
            System.out.println("Button cot clicked");
            model.appendToExpression("cot(");
            view.setDisplayText(model.getExpression());
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
                view.setDisplayText(model.getExpression());
            } catch (Exception ex) {
                view.setDisplayText("Error");
                model.setExpression("");
            }
        });

        // Clear button
        btnClear.addActionListener(e -> {
            System.out.println("Button Clear clicked");
            model.clearExpression();
            view.setDisplayText("");
        });

        // Decimal point
        btnPoint.addActionListener(e -> {
            System.out.println("Button . clicked");
            model.appendToExpression(".");
            view.setDisplayText(model.getExpression());
        });

        // Memory buttons
        btnMemoryStore.addActionListener(e -> {
            System.out.println("Button MS clicked");
            try {
                double value = Double.parseDouble(model.getExpression());
                model.storeMemory(value);
            } catch (NumberFormatException ex) {
                view.setDisplayText("Error");
            }
        });
        btnMemoryRecall.addActionListener(e -> {
            System.out.println("Button MR clicked");
            model.appendToExpression(model.recallMemory());
            view.setDisplayText(model.getExpression());
        });
        btnMemoryClear.addActionListener(e -> {
            System.out.println("Button MC clicked");
            model.clearMemory();
            view.setDisplayText("Memory Cleared");
        });
        btnMemoryAdd.addActionListener(e -> {
            System.out.println("Button M+ clicked");
            try {
                double value = Double.parseDouble(model.getExpression());
                model.addToMemory(value);
            } catch (NumberFormatException ex) {
                view.setDisplayText("Error");
            }
        });

        // History button
        btnHistory.addActionListener(e -> {
            System.out.println("Button Hist clicked");
            view.showHistoryDialog(model.getHistory());
        });
    }
}