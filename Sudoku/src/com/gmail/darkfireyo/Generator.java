package com.gmail.darkfireyo;

import java.util.Random;

/**
 * Générateur de grille pour un jeu de type Sudoku.
 */
public class Generator {
    private Grid currGrid;
    private Solver solver;

    /**
     * Constructeur de la classe Generator.
     * @param grid La grille à générer.
     */
    public Generator(Grid grid) {
        this.currGrid = grid;
        this.solver = new Solver();
    }

    /**
     * Génère une grille remplie avec des nombres valides.
     * @return true si la génération est réussie, false sinon.
     */
    public boolean generateNumber() {
        Random rand = new Random();
        return fillGrid(0, 0, rand);
    }

    /**
     * Remplit la grille de manière récursive avec des nombres aléatoires tout en respectant les règles du jeu.
     * @param x Coordonnée x actuelle dans la grille.
     * @param y Coordonnée y actuelle dans la grille.
     * @param rand Générateur de nombres aléatoires.
     * @return true si la grille est remplie avec succès, false sinon.
     */
    protected boolean fillGrid(int x, int y, Random rand) {
        if (x == currGrid.getWidth()) {
            x = 0;
            y++;
            if (y == currGrid.getHeight()) {
                return true;
            }
        }
        if (currGrid.getElement(x, y) != 0) {
            return fillGrid(x + 1, y, rand);
        }

        int size = currGrid.getWidth();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i + 1;
        }

        for (int i = 0; i < size; i++) {
            int j = rand.nextInt(size);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        for (int num : numbers) {
            if (currGrid.isValid(x, y, num)) {
                currGrid.setElement(x, y, num);

                if (fillGrid(x + 1, y, rand)) {
                    return true;
                }

                currGrid.setElement(x, y, 0);
            }
        }

        return false;
    }

    /**
     * Supprime un certain nombre d'éléments de la grille tout en s'assurant qu'elle reste solvable.
     * @param numberToDelete Nombre de cases à supprimer.
     */
    public void deleteNumbers(int numberToDelete) {
        Random rand = new Random();
        Grid tempGrid = currGrid.clone();

        for (int i = 0; i < numberToDelete; i++) {
            int x = rand.nextInt(currGrid.getWidth());
            int y = rand.nextInt(currGrid.getHeight());

            if (tempGrid.getElement(x, y) == 0) {
                i--;
                continue;
            }

            tempGrid.setElement(x, y, 0);

            if (!solver.isSolvable(tempGrid)) {
                tempGrid.setElement(x, y, currGrid.getElement(x, y));
                i--;
            }
        }

        currGrid.copy(tempGrid);
    }

    /**
     * Retourne l'instance du solveur utilisée.
     * @return L'objet Solver associé.
     */
    public Solver getSolver() {
        return solver;
    }
}
