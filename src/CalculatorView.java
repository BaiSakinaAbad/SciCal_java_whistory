import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CalculatorView {
    private JTextField displayPanel;

    public CalculatorView(JTextField displayPanel) {
        this.displayPanel = displayPanel;
    }

    // Method to update the display while preserving the cursor position
    public void setDisplayText(String text, int cursorPosition) {
        if (displayPanel != null) {
            displayPanel.setText(text);
            // Ensure the cursor position is within bounds
            int newPosition = Math.min(cursorPosition, text.length());
            displayPanel.setCaretPosition(newPosition);
            // Ensure the cursor is visible by requesting focus
            displayPanel.requestFocusInWindow();
            displayPanel.getCaret().setVisible(true);
        }
    }

    // Method to get the current cursor position
    public int getCursorPosition() {
        if (displayPanel != null) {
            return displayPanel.getCaretPosition();
        }
        return 0;
    }

    // Method to show history dialog
    public void showHistoryDialog(List<String> history) {
        JDialog historyDialog = new JDialog();
        historyDialog.setTitle("Calculation History");
        historyDialog.setSize(300, 400);
        historyDialog.setLocationRelativeTo(null);
        historyDialog.setLayout(new BorderLayout());

        JTextArea historyTextArea = new JTextArea();
        historyTextArea.setEditable(false);
        historyTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        for (String operation : history) {
            historyTextArea.append(operation + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(historyTextArea);
        historyDialog.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> historyDialog.dispose());
        historyDialog.add(closeButton, BorderLayout.SOUTH);

        historyDialog.setVisible(true);
    }

    // Method to move the cursor left
    public void moveCursorLeft() {
        if (displayPanel != null) {
            int pos = displayPanel.getCaretPosition();
            if (pos > 0) {
                displayPanel.setCaretPosition(pos - 1);
                displayPanel.requestFocusInWindow();
                displayPanel.getCaret().setVisible(true);
            }
        }
    }

    // Method to move the cursor right
    public void moveCursorRight() {
        if (displayPanel != null) {
            int pos = displayPanel.getCaretPosition();
            String text = displayPanel.getText();
            if (pos < text.length()) {
                displayPanel.setCaretPosition(pos + 1);
                displayPanel.requestFocusInWindow();
                displayPanel.getCaret().setVisible(true);
            }
        }
    }

    // Method to delete the character before the cursor
    public void deleteCharBeforeCursor() {
        if (displayPanel != null) {
            int pos = displayPanel.getCaretPosition();
            String text = displayPanel.getText();
            if (pos > 0) {
                String newText = text.substring(0, pos - 1) + text.substring(pos);
                displayPanel.setText(newText);
                displayPanel.setCaretPosition(pos - 1);
                displayPanel.requestFocusInWindow();
                displayPanel.getCaret().setVisible(true);
            }
        }
    }
}