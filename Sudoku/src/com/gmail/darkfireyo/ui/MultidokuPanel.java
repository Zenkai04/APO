package com.gmail.darkfireyo.ui;

import javax.swing.*;
import java.awt.*;
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
     * Met à jour la grille affichée avec les valeurs de la grille spécifiée.
     * @param grid La grille à afficher.
     */
    public void updateGrid(Grid grid) {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                String element = (grid.getElement(x, y) != 0) ? Integer.toString(grid.getElement(x, y), 36) : "";
                cells[x][y].setText(element);
            }
        }
    }
}