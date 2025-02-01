package com.gmail.darkfireyo;

public class Grid {
    private int[][] grid;
    private int width;
    private int height;
    private int blockWidth;
    private int blockHeight;

    public Grid(int width, int height, int blockWidth, int blockHeight) {
        this.width = width;
        this.height = height;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.grid = new int[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public int getElement(int x, int y) {
        return grid[x][y];
    }

    public void setElement(int x, int y, int value) {
        grid[x][y] = value;
    }

    public boolean isValid(int x, int y, int value) {
        for (int i = 0; i < width; i++) {
            if (grid[i][y] == value || grid[x][i] == value) {
                return false;
            }
        }

        int startX = (x / blockWidth) * blockWidth;
        int startY = (y / blockHeight) * blockHeight;
        for (int i = 0; i < blockWidth; i++) {
            for (int j = 0; j < blockHeight; j++) {
                if (grid[startX + i][startY + j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public Grid clone() {
        Grid clone = new Grid(width, height, blockWidth, blockHeight);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                clone.setElement(x, y, grid[x][y]);
            }
        }
        return clone;
    }

    public void copy(Grid other) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                setElement(x, y, other.getElement(x, y));
            }
        }
    }
}