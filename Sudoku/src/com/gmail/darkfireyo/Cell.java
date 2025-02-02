package com.gmail.darkfireyo;

import java.util.Objects;

public class Cell {
    private int val;
    private int row;
    private int col;

    public Cell() {
        this.val = 0;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.val = 0;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}