package controller;

import model.CalculatorModel;
import view.CalculatorView;

import java.awt.event.ActionListener;
// java interface, parang abstract ang behavior, to perform action when button is clicked.
//

public interface ButtonAction extends ActionListener {
    void execute(CalculatorModel model, CalculatorView view, int cursorPosition);
}