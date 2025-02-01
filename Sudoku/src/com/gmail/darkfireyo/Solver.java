package com.gmail.darkfireyo;

/**
 * La classe Solver fournit des méthodes pour résoudre les puzzles de Sudoku.
 */
public class Solver {

    public Solver() {
    }

    /**
     * Applique une règle de déduction à la grille.
     * @param grid La grille à laquelle appliquer la règle.
     */
    public void useDeductionRule(Grid grid) {
        int length = grid.getWidth();
        for (int x = 0; x < length; x++) {
            int valTest = grid.getElement(x, 0);
            for (int y = 1; y < length; y++) {
                if (valTest == grid.getElement(x, y)) {
                    grid.setElement(x, y, valTest + 1);
                    x = 0;
                }
            }
        }
    }

    /**
     * Vérifie si la grille est solvable.
     * @param grid La grille à vérifier.
     * @return True si la grille est solvable, false sinon.
     */
    public boolean isSolvable(Grid grid) {
        return solve(grid.clone(), 0, 0);
    }

    /**
     * Résout le puzzle de Sudoku.
     * @param grid La grille à résoudre.
     * @return La grille résolue, ou null si le puzzle n'est pas solvable.
     */
    public Grid solveSudoku(Grid grid) {
        if (solve(grid, 0, 0)) {
            return grid;
        } else {
            return null;
        }
    }

    private boolean solve(Grid grid, int x, int y) {
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