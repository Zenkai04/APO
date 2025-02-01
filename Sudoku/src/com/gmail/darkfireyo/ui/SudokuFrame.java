package com.gmail.darkfireyo.ui;

import javax.swing.*;
import javax.swing.plaf.SliderUI;

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
    private JSlider difficultySlider;

    public SudokuFrame() {
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1280, 720);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Largeur de la grille:"));
        widthField = new JTextField(3);
        widthField.setPreferredSize(new Dimension(50, 20));
        widthField.setFont(new Font("Arial", Font.PLAIN, 18)); 
        inputPanel.add(widthField);

        inputPanel.add(new JLabel("Hauteur de la grille:"));
        heightField = new JTextField(3);
        heightField.setPreferredSize(new Dimension(50, 20));
        heightField.setFont(new Font("Arial", Font.PLAIN, 18)); 
        inputPanel.add(heightField);

        inputPanel.add(new JLabel("Largeur des blocs:"));
        blockWidthField = new JTextField(3);
        blockWidthField.setPreferredSize(new Dimension(50, 20));
        blockWidthField.setFont(new Font("Arial", Font.PLAIN, 18)); 
        inputPanel.add(blockWidthField);

        inputPanel.add(new JLabel("Hauteur des blocs:"));
        blockHeightField = new JTextField(3);
        blockHeightField.setPreferredSize(new Dimension(50, 20));
        blockHeightField.setFont(new Font("Arial", Font.PLAIN, 18)); 
        inputPanel.add(blockHeightField);

        difficultySlider = new JSlider(JSlider.HORIZONTAL, 1, 3, 1);
        inputPanel.add(new JLabel("Difficulté:"));
        difficultySlider.setPaintTrack(true);
        difficultySlider.setPaintTicks(true);
        difficultySlider.setPaintLabels(true);
        difficultySlider.setMajorTickSpacing(1);
        difficultySlider.setMinorTickSpacing(1); 
        inputPanel.add(difficultySlider);


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

    public int getDifficulty(){
        return difficultySlider.getValue();
    }

    public void updateSudokuPanel(Grid grid) {
        if (sudokuPanel != null) {
            remove(sudokuPanel);
        }
        sudokuPanel = new SudokuPanel(grid.getWidth(), grid.getHeight());
        sudokuPanel.setPreferredSize(new Dimension(400, 400)); 
        sudokuPanel.updateGrid(grid);
        add(sudokuPanel, BorderLayout.CENTER);
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