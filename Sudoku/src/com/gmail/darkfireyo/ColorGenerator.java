package com.gmail.darkfireyo;

import java.util.Random;

/**
 * La classe ColorGenerator est responsable de générer une grille de couleurs valide.
 * Elle étend la classe Generator et utilise un algorithme de retour sur trace pour remplir la grille.
 */
public class ColorGenerator extends Generator {

    private ColorGrid colorGrid;
    private static final int SIZE = 9;

    /**
     * Construit un ColorGenerator avec la ColorGrid spécifiée.
     *
     * @param grid la ColorGrid à remplir
     */
    public ColorGenerator(ColorGrid grid) {
        super(grid);
        this.colorGrid = grid;
    }

    /**
     * Génère la grille de couleurs en la remplissant avec des nombres valides.
     *
     * @return true si la grille est remplie avec succès, false sinon
     */
    @Override
    public boolean generateNumber() {
        return fillGrid(0, 0);
    }

    /**
     * Remplit récursivement la grille avec des nombres valides en utilisant le retour sur trace.
     *
     * @param row l'index de la ligne actuelle
     * @param col l'index de la colonne actuelle
     * @return true si la grille est remplie avec succès, false sinon
     */
    private boolean fillGrid(int row, int col) {
        if (row == SIZE) {
            row = 0;
            col++;
            if (col == SIZE) {
                return true;
            }
        }

        if (colorGrid.getElement(row, col) != 0) {
            return fillGrid(row + 1, col);
        }

        Random rand = new Random();
        int[] numbers = rand.ints(1, SIZE + 1).distinct().limit(SIZE).toArray();

        for (int num : numbers) {
            if (isValid(row, col, num)) {
                colorGrid.setElement(row, col, num);
                if (fillGrid(row + 1, col)) {
                    return true;
                }
                colorGrid.setElement(row, col, 0);
            }
        }

        return false;
    }

    /**
     * Vérifie si un nombre peut être placé à la position spécifiée dans la grille.
     *
     * @param row l'index de la ligne
     * @param col l'index de la colonne
     * @param num le nombre à placer
     * @return true si le nombre peut être placé, false sinon
     */
    private boolean isValid(int row, int col, int num) {
        for (int x = 0; x < SIZE; x++) {
            if (colorGrid.getElement(row, x) == num || colorGrid.getElement(x, col) == num) {
                return false;
            }
        }
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int d = startCol; d < startCol + 3; d++) {
                if (colorGrid.getElement(r, d) == num) {
                    return false;
                }
            }
        }
        return true;
    }
}