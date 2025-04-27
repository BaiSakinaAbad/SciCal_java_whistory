import javax.swing.*;
import java.awt.*;

public class JavaCalculator {
    private JPanel JavaCalculator;
    private JTextField displayPanel;
    private JButton btnClear;
    private JButton btnFour;
    private JButton btnOne;
    private JButton btnNine;
    private JButton btnMultiply;
    private JButton btnSeven;
    private JButton btnZero;
    private JButton btnEight;
    private JButton btnPoint;
    private JButton btnFive;
    private JButton btnTwo;
    private JButton btnThree;
    private JButton btnSix;
    private JButton btnPlus;
    private JButton btnMinus;
    private JButton btnDivide;
    private JButton btnEqual;
    private JButton btnSin;
    private JButton btnCos;
    private JButton btnTan;
    private JButton btnLog;
    private JButton btnLn;
    private JButton btnExponent;
    private JButton btnLeftParen;
    private JButton btnRightParen;
    private JButton btnMemoryStore;
    private JButton btnMemoryRecall;
    private JButton btnMemoryClear;
    private JButton btnMemoryAdd;
    private JButton btnHistory;
    private JButton btnSqrt;
    private JButton btnNthRoot;
    private JButton btnCsc;
    private JButton btnSec;
    private JButton btnCot;
    private JButton btnLeftArrow;
    private JButton btnRightArrow;
    private JButton btnDelete;

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

        // Configure the displayPanel (JTextField) after the form initializes it
        if (displayPanel != null) {
            displayPanel.setEditable(true);
            displayPanel.setHorizontalAlignment(JTextField.RIGHT);
            displayPanel.setFont(new Font("Arial", Font.PLAIN, 20));
            displayPanel.setText("0");
            // Add a DocumentListener to sync the model with the displayPanel text
            displayPanel.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaCalculator");
        frame.setContentPane(new JavaCalculator().JavaCalculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        JavaCalculator = new JPanel();
        JavaCalculator.setLayout(new BorderLayout());
        JavaCalculator.add(displayPanel, BorderLayout.NORTH);
    }
}