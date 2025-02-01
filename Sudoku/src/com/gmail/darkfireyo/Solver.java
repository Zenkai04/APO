package com.gmail.darkfireyo;

public class Solver {

    public Solver() {
    }

    public void useDeductionRule(Grid grid){
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

    public boolean isSolvable(Grid grid) {
        return solve(grid.clone(), 0, 0);
    }

    public Grid solveSudoku(Grid grid) {
        if (solve(grid, 0, 0)) {
            return grid;
        } else {
            return null; // Return null if the Sudoku is not solvable
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