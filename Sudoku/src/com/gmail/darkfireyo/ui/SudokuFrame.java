package com.gmail.darkfireyo.ui;

import javax.swing.*;
import java.awt.*;
import com.gmail.darkfireyo.Grid;

public class SudokuFrame extends JFrame {
    private JTextField widthField;
    private JTextField heightField;
    private JTextField blockWidthField;
    private JTextField blockHeightField;
    private JButton startButton;
    private JButton solveButton;
    private SudokuPanel sudokuPanel;
    private JLabel errorLabel;

    public SudokuFrame() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1280, 720);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Largeur de la grille:"));
        widthField = new JTextField(3);
        widthField.setPreferredSize(new Dimension(50, 20));
        inputPanel.add(widthField);

        inputPanel.add(new JLabel("Hauteur de la grille:"));
        heightField = new JTextField(3);
        heightField.setPreferredSize(new Dimension(50, 20));
        inputPanel.add(heightField);

        inputPanel.add(new JLabel("Largeur des blocs:"));
        blockWidthField = new JTextField(3);
        blockWidthField.setPreferredSize(new Dimension(50, 20));
        inputPanel.add(blockWidthField);

        inputPanel.add(new JLabel("Hauteur des blocs:"));
        blockHeightField = new JTextField(3);
        blockHeightField.setPreferredSize(new Dimension(50, 20));
        inputPanel.add(blockHeightField);

        startButton = new JButton("Génerer un Sudoku !");
        inputPanel.add(startButton);

        errorLabel = new JLabel("Valeur pour générer un Sudoku invalide !");
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        inputPanel.add(errorLabel);

        add(inputPanel, BorderLayout.NORTH);

        solveButton = new JButton("Résoudre le Sudoku");
        solveButton.setVisible(false);
        add(solveButton, BorderLayout.SOUTH);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getSolveButton() {
        return solveButton;
    }

    public int getGridWidth() {
        return Integer.parseInt(widthField.getText());
    }

    public int getGridHeight() {
        return Integer.parseInt(heightField.getText());
    }

    public int getBlockWidth() {
        return Integer.parseInt(blockWidthField.getText());
    }

    public int getBlockHeight() {
        return Integer.parseInt(blockHeightField.getText());
    }

    public void updateSudokuPanel(Grid grid) {
        if (sudokuPanel != null) {
            remove(sudokuPanel);
        }
        sudokuPanel = new SudokuPanel(grid.getWidth(), grid.getHeight());
        sudokuPanel.setPreferredSize(new Dimension(400, 400)); // Set preferred size
        sudokuPanel.updateGrid(grid);
        add(sudokuPanel);
        solveButton.setVisible(true);
        revalidate();
        repaint();
    }

    public void showErrorLabel(boolean show) {
        errorLabel.setVisible(show);
        revalidate();
        repaint();
    }
}