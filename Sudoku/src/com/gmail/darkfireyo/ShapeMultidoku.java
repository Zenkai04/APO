package com.gmail.darkfireyo;

import java.util.Set;

public class ShapeMultidoku {
    private Grid grid;
    private Set<ShapeBlock> shapeBlocks;

    public ShapeMultidoku(int width, int height, Set<ShapeBlock> shapeBlocks) {
        this.grid = new Grid(width, height, 1, 1); // Blocks will be managed by ShapeBlocks
        this.shapeBlocks = shapeBlocks;
    }

    public void setElement(int x, int y, int value) {
        grid.setElement(x, y, value);
    }

    public int getElement(int x, int y) {
        return grid.getElement(x, y);
    }

    public boolean isValid(int x, int y, int value) {
        for (ShapeBlock block : shapeBlocks) {
            if (block.contains(x, y)) {
                for (ShapeBlock.Coordinate coord : block.getCoordinates()) {
                    if (grid.getElement(coord.x, coord.y) == value) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Grid getGrid() {
        return grid;
    }
}