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
    private SudokuPanel sudokuPanel;
    private JLabel errorLabel;

    public SudokuFrame() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(800, 600);

        add(new JLabel("Largeur de la grille:"));
        widthField = new JTextField(5);
        add(widthField);

        add(new JLabel("Hauteur de la grille:"));
        heightField = new JTextField(5);
        add(heightField);

        add(new JLabel("Largeur des blocs:"));
        blockWidthField = new JTextField(5);
        add(blockWidthField);

        add(new JLabel("Hauteur des blocs:"));
        blockHeightField = new JTextField(5);
        add(blockHeightField);

        startButton = new JButton("Génerer un Sudoku !");
        add(startButton);

        errorLabel = new JLabel("Valeur pour générer un Sudoku invalide !");
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        add(errorLabel);
    }

    public JButton getStartButton() {
        return startButton;
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
        sudokuPanel.updateGrid(grid);
        add(sudokuPanel);
        revalidate();
        repaint();
    }

    public void showErrorLabel(boolean show) {
        errorLabel.setVisible(show);
        if (sudokuPanel != null) {
            remove(sudokuPanel);
        }
        revalidate();
        repaint();
       
    }
}