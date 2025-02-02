package com.gmail.darkfireyo;

public class ColorSolver extends Solver {

    @Override
    public boolean isSolvable(Grid grid) {
        if (!(grid instanceof ColorGrid)) {
            throw new IllegalArgumentException("Grid must be an instance of ColorGrid");
        }
        return solve((ColorGrid) grid.clone(), 0, 0);
    }

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