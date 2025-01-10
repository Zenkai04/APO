package com.gmail.darkfireyo;

public class Cell {
    private int val;

    public int getValeur() {
        return val;
    }

    public Cell(int val) {
        this.val = val;
    }

    void setValeur(int val) {
        this.val = val;
    }

    void resetValeur() {
        this.val = 0;
    }
}