package com.gmail.darkfireyo;

/**
 * La classe Cell représente une seule cellule dans une grille de Sudoku.
 */
public class Cell {
    private int val;

    public int getVal() {
        return val;
    }

    public Cell() {
        this.val = 0;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getval() {
        return val;
    }

    public void resetVal() {
        this.val = 0;
    }
}