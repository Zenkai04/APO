package com.gmail.darkfireyo.ui;

import javax.swing.*;
import java.awt.*;
import com.gmail.darkfireyo.Grid;

public class SudokuPanel extends JPanel {
    private JTextField[][] cells;

    public SudokuPanel(int width, int height) {
        setLayout(new GridLayout(height, width));
        cells = new JTextField[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = new JTextField();
                cells[x][y].setHorizontalAlignment(JTextField.CENTER);
                cells[x][y].setFont(new Font("Arial", Font.PLAIN, 18));
                add(cells[x][y]);
            }
        }
    }

    public void updateGrid(Grid grid) {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                cells[x][y].setText(Integer.toString(grid.getElement(x, y), 36));
            }
        }
    }
}