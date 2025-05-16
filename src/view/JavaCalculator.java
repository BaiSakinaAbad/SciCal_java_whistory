package view;

import controller.CalculatorController;
import model.CalculatorModel;
import javax.swing.*;
import java.awt.*;
// setup main ui using swing
public class JavaCalculator {
    public JPanel JavaCalculator;
    private JTextField displayPanel;
    private JButton btnClear, btnFour, btnOne, btnNine, btnMultiply, btnSeven, btnZero, btnEight, btnPoint,
            btnFive, btnTwo, btnThree, btnSix, btnPlus, btnMinus, btnDivide, btnEqual, btnSin, btnCos,
            btnTan, btnLog, btnLn, btnExponent, btnLeftParen, btnRightParen, btnMemoryStore, btnMemoryRecall,
            btnMemoryClear, btnMemoryAdd, btnHistory, btnSqrt, btnNthRoot, btnCsc, btnSec, btnCot,
            btnLeftArrow, btnRightArrow, btnDelete;
    private JLabel gifLabel;
    private JPanel buttonPanel;
    private JPanel gifAndButtons;

    private CalculatorModel model;
    private CalculatorView view;
    private CalculatorController controller;

    public JavaCalculator() {
        model = new CalculatorModel();
        view = new CalculatorView(displayPanel);
        controller = new CalculatorController(model, view);

        controller.setupButtonListeners(
                btnClear, btnFour, btnOne, btnNine, btnMultiply, btnSeven, btnZero,
                btnEight, btnPoint, btnFive, btnTwo, btnThree, btnSix, btnPlus,
                btnMinus, btnDivide, btnEqual, btnSin, btnCos, btnTan, btnLog,
                btnLn, btnExponent, btnLeftParen, btnRightParen, btnMemoryStore,
                btnMemoryRecall, btnMemoryClear, btnMemoryAdd, btnHistory, btnSqrt,
                btnNthRoot, btnCsc, btnSec, btnCot, btnLeftArrow, btnRightArrow, btnDelete
        );

        if (displayPanel != null) {
            displayPanel.setEditable(true);
            displayPanel.setHorizontalAlignment(JTextField.RIGHT);
            displayPanel.setFont(new Font("Arial", Font.PLAIN, 20));
            displayPanel.setText("(˶˃ ᵕ ˂˶)");
            displayPanel.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {// to sync manual text with Calculator model

                @Override
                public void insertUpdate(javax.swing.event.DocumentEvent e) {
                    model.setExpression(displayPanel.getText());
                }

                @Override
                public void removeUpdate(javax.swing.event.DocumentEvent e) {
                    model.setExpression(displayPanel.getText());
                }

                @Override
                public void changedUpdate(javax.swing.event.DocumentEvent e) {
                    model.setExpression(displayPanel.getText());
                }
            });
        } else {
            System.err.println("displayPanel is null - check form binding");
        }
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Scientific Calculator");
//        frame.setContentPane(new JavaCalculator().JavaCalculator);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

    private void createUIComponents() {
        JavaCalculator = new JPanel();
        JavaCalculator.setLayout(new BorderLayout());
        JavaCalculator.add(displayPanel, BorderLayout.NORTH);
    }
}