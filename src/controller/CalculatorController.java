package controller;

import model.CalculatorModel;
import view.CalculatorView;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

// interaction of view and model, setting the button listeners
public class CalculatorController {
    private final CalculatorModel model;
    private final CalculatorView view;
    private final Map<JButton, ButtonAction> buttonActions = new HashMap<>();

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
        setupButtons(new JButton[]{btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnPoint},
                new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."}, NumberButtonAction::new);

        // Operator buttons
        setupButtons(new JButton[]{btnPlus, btnMinus, btnMultiply, btnDivide, btnExponent, btnLeftParen, btnRightParen},
                new String[]{"+", "-", "*", "/", "^", "(", ")"}, OperatorButtonAction::new);

        // Function buttons with offsets
        setupFunctionButtons(new JButton[]{btnSin, btnCos, btnTan, btnLog, btnLn, btnSqrt, btnNthRoot, btnCsc, btnSec, btnCot},
                new String[]{"sin(", "cos(", "tan(", "log(", "ln(", "sqrt(", "nrt", "csc(", "sec(", "cot("},
                new int[]{4, 4, 4, 4, 3, 5, 3, 4, 4, 4});

        // Special actions
        buttonActions.put(btnClear, new CalculationAndResetActions.ClearButtonAction());
        buttonActions.put(btnEqual, new CalculationAndResetActions.EqualButtonAction());
        buttonActions.put(btnMemoryStore, new MemoryAndHistoryActions.MemoryStoreButtonAction());
        buttonActions.put(btnMemoryRecall, new MemoryAndHistoryActions.MemoryRecallButtonAction());
        buttonActions.put(btnMemoryClear, new MemoryAndHistoryActions.MemoryClearButtonAction());
        buttonActions.put(btnMemoryAdd, new MemoryAndHistoryActions.MemoryAddButtonAction());
        buttonActions.put(btnHistory, new MemoryAndHistoryActions.HistoryButtonAction());

        // Arrow and delete actions using concise actions
        buttonActions.put(btnLeftArrow, createAction(CalculatorView::moveCursorLeft, "Button Left Arrow clicked"));
        buttonActions.put(btnRightArrow, createAction(CalculatorView::moveCursorRight, "Button Right Arrow clicked"));
        buttonActions.put(btnDelete, new CalculationAndResetActions.DeleteButtonAction());

        // Attach listeners
        buttonActions.forEach((btn, action) -> btn.addActionListener(e -> {
            action.actionPerformed(e);
            action.execute(model, view, view.getCursorPosition());
        }));
    }

    // Helper method for number and operator buttons
    private void setupButtons(JButton[] buttons, String[] values, java.util.function.Function<String, ButtonAction> actionCreator) {
        for (int i = 0; i < buttons.length; i++) {
            buttonActions.put(buttons[i], actionCreator.apply(values[i]));
        }
    }

    // Helper method for function buttons with offsets
    private void setupFunctionButtons(JButton[] buttons, String[] functions, int[] offsets) {
        for (int i = 0; i < buttons.length; i++) {
            buttonActions.put(buttons[i], new FunctionButtonAction(functions[i], offsets[i]));
        }
    }

    // Helper method to create simple actions
    private ButtonAction createAction(java.util.function.Consumer<CalculatorView> executeAction, String logMessage) {
        return new ButtonAction() {
            @Override
            public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
                executeAction.accept(view);
            }

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.out.println(logMessage);
            }
        };
    }
}