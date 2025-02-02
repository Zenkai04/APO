package com.gmail.darkfireyo.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import com.gmail.darkfireyo.ColorGrid;
import com.gmail.darkfireyo.Grid;

/**
 * La classe MultidokuPanel représente le panneau qui affiche la grille de Multidoku.
 */
public class MultidokuPanel extends JPanel {
    private JTextField[][] cells;

    /**
     * Construit un nouveau panneau Multidoku avec les dimensions spécifiées.
     * @param width La largeur de la grille.
     * @param height La hauteur de la grille.
     */
    public MultidokuPanel(int width, int height) {
        setLayout(new GridLayout(height, width));
        cells = new JTextField[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[x][y] = new JTextField();
                cells[x][y].setEditable(false);
                cells[x][y].setHorizontalAlignment(JTextField.CENTER);
                cells[x][y].setFont(new Font("Arial", Font.PLAIN, 18));
                add(cells[x][y]);
            }
        }
    }

    public void updateGrid(Grid grid) {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                String element = (grid.getElement(x, y) != 0) ? Integer.toString(grid.getElement(x, y), 36) : "";
                cells[x][y].setText(element);

                if (grid instanceof ColorGrid) {
                    ColorGrid colorGrid = (ColorGrid) grid;
                    String colorName = colorGrid.getColor(x, y);
                    Color color = getColorFromName(colorName);
                    cells[x][y].setForeground(color);
                } else {
                    cells[x][y].setForeground(Color.BLACK);
                }
            }
        }
    }

    public void updateMultidokuPanel(List<Grid> grids, String shape) {
        removeAll();
        setLayout(new GridLayout(27, 27)); // 27x27 grid for the entire panel
    
        cells = new JTextField[27][27]; // 27x27 cells for the entire panel
    
        // Initialize all cells
        for (int y = 0; y < 27; y++) {
            for (int x = 0; x < 27; x++) {
                cells[x][y] = new JTextField();
                cells[x][y].setEditable(false);
                cells[x][y].setHorizontalAlignment(JTextField.CENTER);
                cells[x][y].setFont(new Font("Arial", Font.PLAIN, 18));
                add(cells[x][y]);
            }
        }
    
        if (shape.equals("X")) {
            // Center grid (grid 1)
            fillGrid(cells, grids.get(0), 9, 9);
    

            fillGrid(cells, grids.get(1), 3, 3);
    

            fillGrid(cells, grids.get(2), 15, 3);
    

            fillGrid(cells, grids.get(3), 3, 15);
    
            // Bottom-right grid (grid 5)
            fillGrid(cells, grids.get(4), 15, 15);
        }else if (shape.equals("+")) {
            // Top-left grid (grid 1)
            fillGrid(cells, grids.get(0), 9, 9);
    

            fillGrid(cells, grids.get(1), 3, 9);
    
            fillGrid(cells, grids.get(2), 15, 9);
    

              fillGrid(cells, grids.get(3), 9, 3);
    
            
            fillGrid(cells, grids.get(4), 9, 15);
        } 
    
        revalidate();
        repaint();
    }
    
    private void fillGrid(JTextField[][] cells, Grid grid, int offsetX, int offsetY) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                String element = (grid.getElement(x, y) != 0) ? Integer.toString(grid.getElement(x, y), 36) : "";
                cells[offsetX + x][offsetY + y].setText(element);
    
                if (grid instanceof ColorGrid) {
                    ColorGrid colorGrid = (ColorGrid) grid;
                    String colorName = colorGrid.getColor(x, y);
                    Color color = getColorFromName(colorName);
                    cells[offsetX + x][offsetY + y].setForeground(color);
                } else {
                    cells[offsetX + x][offsetY + y].setForeground(Color.BLACK);
                }
            }
        }
    }
    
    private Color getColorFromName(String colorName) {
        switch (colorName) {
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "yellow":
                return Color.YELLOW;
            case "orange":
                return Color.ORANGE;
            case "purple":
                return Color.MAGENTA;
            case "cyan":
                return Color.CYAN;
            case "magenta":
                return Color.PINK;
            case "black":
                return Color.BLACK;
            default:
                return Color.WHITE;
        }
    }
}