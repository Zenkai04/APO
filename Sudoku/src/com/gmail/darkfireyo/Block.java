package com.gmail.darkfireyo;

import java.util.HashSet;
import java.util.Set;

public class Block {
    private Cell[] cells;
    private int height;
    private int width;
    private Set<Cell> cellSet;

    public Block(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width * height];
        this.cellSet = new HashSet<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                this.cells[i + j * width] = cell;
                this.cellSet.add(cell);
            }
        }
    }

    public void addCell(int x, int y) {
        Cell cell = new Cell(x, y);
        cellSet.add(cell);
    }

    public boolean containsCell(int x, int y) {
        return cellSet.contains(new Cell(x, y));
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return width * height;
    }

    public Set<Cell> getCells() {
        return cellSet;
    }
}