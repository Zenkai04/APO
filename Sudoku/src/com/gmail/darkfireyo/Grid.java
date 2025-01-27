package com.gmail.darkfireyo;

public class Grid {
    private Block[] blocks;
    private int width, blockWidth;
    private int height, blockHeight;

    public Grid(int width, int height, int blockWidth, int blockHeight) {
        this.width = width;
        this.height = height;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.blocks = new Block[(width / blockWidth) * (height / blockHeight)];
        for (int i = 0; i < (width / blockWidth) * (height / blockHeight); i++) {
            this.blocks[i] = new Block(blockWidth, blockHeight);
        }
    }

    private int getBlockIndex(int x, int y) {
        int blockX = x / blockWidth;
        int blockY = y / blockHeight;
        return blockX + blockY * (width / blockWidth);
    }

    public int getElement(int x, int y) {
        int blockIndex = getBlockIndex(x, y);
        int cellX = x % blockWidth;
        int cellY = y % blockHeight;
        return blocks[blockIndex].getCell(cellX, cellY).getVal();
    }

    public void setElement(int x, int y, int value) {
        int blockIndex = getBlockIndex(x, y);
        int cellX = x % blockWidth;
        int cellY = y % blockHeight;
        blocks[blockIndex].setCell(cellX, cellY, value);
    }

    public boolean isValid(int x, int y, int value) {
        for (int i = 0; i < width; i++) {
            if (getElement(i, y) == value) {
                return false;
            }
        }

        for (int j = 0; j < height; j++) {
            if (getElement(x, j) == value) {
                return false;
            }
        }

        int startX = (x / blockWidth) * blockWidth;
        int startY = (y / blockHeight) * blockHeight;
        for (int i = startX; i < startX + blockWidth; i++) {
            for (int j = startY; j < startY + blockHeight; j++) {
                if (getElement(i, j) == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Grid clone() {
        Grid grid = new Grid(width, height, blockWidth, blockHeight);
        grid.copy(this);
        return grid;
    }

    public void copy(Grid grid) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                setElement(i, j, grid.getElement(i, j));
            }
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder ligne = new StringBuilder();
            for (int i = 0; i < width; i++) {
                ligne.append(Integer.toString(this.getElement(i, y),36)); // On convertis ici en base 36 pour pouvoir aller jusqu'a la lettre Z

                if (i < width - 1) {
                    ligne.append(" ");
                }
            }

           result.append(ligne.toString());
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}