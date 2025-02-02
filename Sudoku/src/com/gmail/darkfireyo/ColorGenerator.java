package com.gmail.darkfireyo;

import java.util.Random;

public class ColorGenerator extends Generator {

    private ColorGrid colorGrid;
    private static final int SIZE = 9;

    public ColorGenerator(ColorGrid grid) {
        super(grid);
        this.colorGrid = grid;
    }

    @Override
    public boolean generateNumber() {
        return fillGrid(0, 0);
    }

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