package com.gmail.darkfireyo;

public class Block {
    private Cell[][] cells;

    public Block() {
        this.cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.cells[i][j] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
        return cells[x][y];
    }

    public void setCell(int x, int y, int value) {
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
        cells[x][y].setVal(value);
    }
}