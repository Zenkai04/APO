package com.gmail.darkfireyo;

/**
 * La classe Solver fournit des méthodes pour résoudre les puzzles de Sudoku.
 */
public class Solver {

    public Solver() {
    }

 

    public boolean isDeductable(Grid grid) {
    	for(int x = 0; x < grid.getWidth();x++) {
    		for(int y = 0; y < grid.getHeight();y++) {
    			int back = grid.getElement(x, y);
    			grid.setElement(x, y, 0);
    			
    			if(!grid.isValid(x, y, back)) {
    				return false;
    			}
    			grid.setElement(x, y, back);
    		}
    	}
    	return true;
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
            return isDeductable(grid)? grid : null;
        } else {
            return null;
        }
    }

    

    /**
     * Résout le puzzle de Sudoku.
     * @param grid La grille à résoudre.
     * @param x La position x actuelle dans la grille.
     * @param y La position y actuelle dans la grille.
     * @return La grille résolue, ou null si le puzzle n'est pas solvable.
     */
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
            	Main.print("Essai de " + num + " en position (" + x + ", " + y + ")");
                grid.setElement(x, y, num);

                if (solve(grid, x + 1, y)) {
                    return true;
                }
                Main.print("Backtracking : retrait de " + num + " en position (" + x + ", " + y + ")");
                grid.setElement(x, y, 0);
            }
        }

        return false;
    }
}