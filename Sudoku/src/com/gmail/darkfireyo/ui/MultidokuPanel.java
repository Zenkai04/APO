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

    /**
     * Met à jour la grille affichée avec les valeurs de la grille donnée.
     * @param grid La grille à afficher.
     */
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

    /**
     * Met à jour le panneau Multidoku avec les grilles et la forme spécifiées.
     * @param grids La liste des grilles à afficher.
     * @param shape La forme de disposition des grilles ("X" ou "+").
     */
    public void updateMultidokuPanel(List<Grid> grids, String shape) {
        removeAll();
        setLayout(new GridLayout(27, 27)); // Grille 27x27 pour l'ensemble du panneau
    
        cells = new JTextField[27][27]; // 27x27 cellules pour l'ensemble du panneau
    
        // Initialiser toutes les cellules
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
            // Grille centrale (grille 1)
            fillGrid(cells, grids.get(0), 9, 9);
    
            // Grilles aux coins
            fillGrid(cells, grids.get(1), 3, 3);
            fillGrid(cells, grids.get(2), 15, 3);
            fillGrid(cells, grids.get(3), 3, 15);
            fillGrid(cells, grids.get(4), 15, 15);
        } else if (shape.equals("+")) {
            // Grille centrale (grille 1)
            fillGrid(cells, grids.get(0), 9, 9);
    
            // Grilles en croix
            fillGrid(cells, grids.get(1), 3, 9);
            fillGrid(cells, grids.get(2), 15, 9);
            fillGrid(cells, grids.get(3), 9, 3);
            fillGrid(cells, grids.get(4), 9, 15);
        } 
    
        revalidate();
        repaint();
    }
    
    /**
     * Remplit une section de la grille avec les valeurs de la grille donnée.
     * @param cells Les cellules de la grille.
     * @param grid La grille à afficher.
     * @param offsetX L'offset en X pour placer la grille.
     * @param offsetY L'offset en Y pour placer la grille.
     */
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
    
    /**
     * Retourne la couleur correspondant au nom donné.
     * @param colorName Le nom de la couleur.
     * @return La couleur correspondante.
     */
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