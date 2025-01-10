package com.gmail.darkfireyo;

public class Block {
    private Cell[][] cells;
    
    public Block(Cell cells[][]) {
        this.cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.cells[i][j] = new Cell();
            }
        }
    }
}
