package com.gmail.darkfireyo;

public class Cell {
    private int val;

    public int getVal() {
        return val;
    }

    public Cell(int val) {
        this.val = val;
    }

    void setVal(int val) {
        this.val = val;
    }

    void resetVal() {
        this.val = 0;
    }
}