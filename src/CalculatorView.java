import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CalculatorView {
    private JTextField displayPanel;

    public CalculatorView(JTextField displayPanel) {
        this.displayPanel = displayPanel;
    }

    // Method to update the display
    public void setDisplayText(String text) {
        if (displayPanel != null) {
            displayPanel.setText(text);
        }
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
}