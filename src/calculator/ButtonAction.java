package calculator;

import java.awt.event.ActionListener;
// java interface, parang poly ang behavoir, to perform action when button is clicked.

public interface ButtonAction extends ActionListener {
    void execute(CalculatorModel model, CalculatorView view, int cursorPosition);
}