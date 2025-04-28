package calculator;
// using buttonAction, takes a number and insert it at the current position of the cursor and und update the display panel

public class NumberButtonAction implements ButtonAction {
    private final String number;

    public NumberButtonAction(String number) {
        this.number = number;
    }

    @Override
    public void execute(CalculatorModel model, CalculatorView view, int cursorPosition) {
        model.insertAtPosition(number, cursorPosition);
        view.setDisplayText(model.getExpression(), cursorPosition + number.length());
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println("Button " + number + " clicked");
    }
}