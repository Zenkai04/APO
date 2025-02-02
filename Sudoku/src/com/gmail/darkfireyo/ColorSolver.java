package com.gmail.darkfireyo;

/**
 * ColorSolver est une classe qui étend Solver et fournit des méthodes pour résoudre des grilles de Sudoku colorées.
 */
public class ColorSolver extends Solver {

    /**
     * Vérifie si la grille donnée est solvable.
     *
     * @param grid la grille à vérifier
     * @return true si la grille est solvable, false sinon
     * @throws IllegalArgumentException si la grille n'est pas une instance de ColorGrid
     */
    @Override
    public boolean isSolvable(Grid grid) {
        if (!(grid instanceof ColorGrid)) {
            throw new IllegalArgumentException("Grid must be an instance of ColorGrid");
        }
        return solve((ColorGrid) grid.clone(), 0, 0);
    }

    /**
     * Résout la grille de Sudoku donnée.
     *
     * @param grid la grille à résoudre
     * @return la grille résolue si elle est solvable, null sinon
     * @throws IllegalArgumentException si la grille n'est pas une instance de ColorGrid
     */
    @Override
    public Grid solveSudoku(Grid grid) {
        if (!(grid instanceof ColorGrid)) {
            throw new IllegalArgumentException("Grid must be an instance of ColorGrid");
        }
        ColorGrid colorGrid = (ColorGrid) grid;
        if (solve(colorGrid, 0, 0)) {
            return colorGrid;
        } else {
            return null;
        }
    }

    /**
     * Méthode récursive pour résoudre la grille de Sudoku.
     *
     * @param grid la grille à résoudre
     * @param x la position x actuelle dans la grille
     * @param y la position y actuelle dans la grille
     * @return true si la grille est résolue, false sinon
     */
    private boolean solve(ColorGrid grid, int x, int y) {
        if (x == grid.getWidth()) {
            x = 0;
            y++;
            if (y == grid.getHeight()) {
                return true;
            }
        }

        if (grid.getElement(x, y) != 0) {
            return solve(grid, x + 1, y);
        }

        int size = grid.getWidth();
        for (int num = 1; num <= size; num++) {
            if (grid.isValid(x, y, num)) {
                grid.setElement(x, y, num);

                if (solve(grid, x + 1, y)) {
                    return true;
                }

                grid.setElement(x, y, 0);
            }
        }

        return false;
    }
}