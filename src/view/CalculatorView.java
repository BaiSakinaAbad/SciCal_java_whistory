package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CalculatorView {
    private static JTextField displayPanel;

    public CalculatorView(JTextField displayPanel) {
        this.displayPanel = displayPanel;
    }

    public void setDisplayText(String text, int cursorPosition) {
        if (displayPanel != null) {
            displayPanel.setText(text);
            int newPosition = Math.min(cursorPosition, text.length());
            displayPanel.setCaretPosition(newPosition);
            displayPanel.requestFocusInWindow();
            displayPanel.getCaret().setVisible(true);
        }
    }

    public int getCursorPosition() {
        return displayPanel != null ? displayPanel.getCaretPosition() : 0;
    }

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

    public static void deleteCharBeforeCursor() {
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

    // Added method to get the current display text
    public static String getDisplayText() {
        return displayPanel != null ? displayPanel.getText() : "";
    }
}