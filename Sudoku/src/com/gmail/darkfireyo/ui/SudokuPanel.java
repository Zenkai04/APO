package com.gmail.darkfireyo.ui;

import javax.swing.*;
import java.awt.*;
import com.gmail.darkfireyo.Grid;

/**
 * Classe représentant un panneau d'affichage pour un Sudoku.
 * Ce panneau contient une grille de champs de texte non éditables
 * qui sont utilisés pour afficher les valeurs du Sudoku.
 */
public class SudokuPanel extends JPanel {
    private JTextField[][] cells;

    /**
     * Constructeur de la classe SudokuPanel.
     * Initialise une grille de JTextField avec les dimensions spécifiées.
     * 
     * @param width  la largeur de la grille
     * @param height la hauteur de la grille
     */
    public SudokuPanel(int width, int height) {
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
     * Met à jour l'affichage de la grille avec les valeurs de l'objet Grid fourni.
     * 
     * @param grid l'objet Grid contenant les valeurs du Sudoku
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
