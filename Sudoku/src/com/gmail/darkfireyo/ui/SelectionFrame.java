package com.gmail.darkfireyo.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectionFrame extends JFrame {
    private JButton sudokuButton;
    private JButton multidokuButton;

    public SelectionFrame() {
        setTitle("Choisissez le type de Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(300, 100);

        sudokuButton = new JButton("Sudoku Classique");
        multidokuButton = new JButton("Multidoku / Sudoku Spéciaux");

        add(sudokuButton);
        add(multidokuButton);
    }

    public JButton getSudokuButton() {
        return sudokuButton;
    }

    public JButton getMultidokuButton() {
        return multidokuButton;
    }
}