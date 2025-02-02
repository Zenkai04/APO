package com.gmail.darkfireyo;

/**
 * Classe RandomSolver qui hérite de Solver.
 * Cette classe implémente un solveur de Sudoku utilisant une approche aléatoire.
 */
public class RandomSolver extends Solver {

    /**
     * Constructeur par défaut de RandomSolver.
     */
    public RandomSolver() {
        super();
    }

    /**
     * Vérifie si une grille de Sudoku est solvable.
     *
     * @param grid la grille de Sudoku à vérifier
     * @return true si la grille est solvable, false sinon
     */
    @Override
    public boolean isSolvable(Grid grid) {
        return solve(grid.clone(), 0, 0);
    }

    /**
     * Résout une grille de Sudoku.
     *
     * @param grid la grille de Sudoku à résoudre
     * @return la grille résolue si elle est solvable, null sinon
     */
    @Override
    public Grid solveSudoku(Grid grid) {
        if (solve(grid, 0, 0)) {
            return grid;
        } else {
            return null; 
        }
    }

    /**
     * Méthode récursive pour résoudre la grille de Sudoku.
     *
     * @param grid la grille de Sudoku à résoudre
     * @param x la position x actuelle dans la grille
     * @param y la position y actuelle dans la grille
     * @return true si la grille est résolue, false sinon
     */
    protected boolean solve(Grid grid, int x, int y) {
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