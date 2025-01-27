package com.gmail.darkfireyo;

public class Block {
    private Cell[] cells;
    private int height;
    private int width;

    public Block(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.cells[i + j * width] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
        return cells[x + y * width];
    }

    public void setCell(int x, int y, int value) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
        cells[x + y * width].setVal(value);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getSize(){
        return width * height;
    }


}