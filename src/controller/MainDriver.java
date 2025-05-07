package controller;

import view.JavaCalculator;

import javax.swing.*;

public class MainDriver {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Scientific Calculator");
        frame.setContentPane(new JavaCalculator().JavaCalculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
