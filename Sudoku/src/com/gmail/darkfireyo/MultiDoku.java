package com.gmail.darkfireyo;

import java.util.ArrayList;
import java.util.List;

public class MultiDoku extends Grid {
    private Grid topGrid;
    private Grid leftGrid;
    private Grid rightGrid;
    private Grid bottomGrid;
    private Grid topLeftGrid;
    private Grid topRightGrid;
    private Grid bottomLeftGrid;
    private Grid bottomRightGrid;
    private String shape;
    private List<Grid> grids;

    public MultiDoku(String shape) {
        super(9, 9, 3, 3); // Initialize the central grid with 9x9 size and 3x3 blocks
        this.shape = shape;
        grids = new ArrayList<>();
        if (shape.equals("plus")) {
            this.topGrid = new Grid(9, 9, 3, 3);
            this.leftGrid = new Grid(9, 9, 3, 3);
            this.rightGrid = new Grid(9, 9, 3, 3);
            this.bottomGrid = new Grid(9, 9, 3, 3);
            grids.add(this);
            grids.add(leftGrid);
            grids.add(rightGrid);
            grids.add(topGrid);
            grids.add(bottomGrid);
        } else if (shape.equals("croix")) {
            this.topLeftGrid = new Grid(9, 9, 3, 3);
            this.topRightGrid = new Grid(9, 9, 3, 3);
            this.bottomLeftGrid = new Grid(9, 9, 3, 3);
            this.bottomRightGrid = new Grid(9, 9, 3, 3);
            grids.add(this);
            grids.add(topLeftGrid);
            grids.add(topRightGrid);
            grids.add(bottomLeftGrid);
            grids.add(bottomRightGrid);
        }

    }

    public void generateGrids() {
        Generator centralGenerator = new Generator(this);
        centralGenerator.generateNumber();

        if (shape.equals("plus")) {
            generatePlusGrids();
        } else if (shape.equals("croix")) {
            generateCroixGrids();
        }
    }

    private void generatePlusGrids() {
        Solver solver = new Solver();

        copyBlock(topGrid, 7, this, 1);
        copyBlock(topGrid, 8, this, 2);
        copyBlock(topGrid, 9, this, 3);
        solver.solveSudoku(topGrid);

        copyBlock(bottomGrid, 1, this, 7);
        copyBlock(bottomGrid, 2, this, 8);
        copyBlock(bottomGrid, 3, this, 9);
        solver.solveSudoku(bottomGrid);

        copyBlock(leftGrid, 3, this, 1);
        copyBlock(leftGrid,6, this, 4);
        copyBlock(leftGrid, 9, this,7);
        solver.solveSudoku(leftGrid);

        copyBlock(rightGrid, 1, this, 3);
        copyBlock(rightGrid,4, this, 6);
        copyBlock(rightGrid, 7, this,9);
        solver.solveSudoku(rightGrid);
    }

    private void generateCroixGrids() {
        Solver solver = new Solver();

        copyBlock(topLeftGrid, 9, this, 1);
        solver.solveSudoku(topLeftGrid);

        copyBlock(topRightGrid, 7, this,3);
        solver.solveSudoku(topRightGrid);

        copyBlock(bottomRightGrid, 1, this,9);
        solver.solveSudoku(bottomRightGrid);

        copyBlock(bottomLeftGrid,3, this,7);
        solver.solveSudoku(bottomLeftGrid);
    }


    private void copyBlock(Grid targetGrid, int targetBlock, Grid sourceGrid, int sourceBlock) {
        int[][] targetCoords = getBlockCoordinates(targetBlock);
        int[][] sourceCoords = getBlockCoordinates(sourceBlock);

        for (int i = 0; i < 9; i++) {
            int targetX = targetCoords[i][0];
            int targetY = targetCoords[i][1];
            int sourceX = sourceCoords[i][0];
            int sourceY = sourceCoords[i][1];

            targetGrid.setElement(targetX, targetY, sourceGrid.getElement(sourceX, sourceY));
        }
    }

    private int[][] getBlockCoordinates(int blockNumber) {
        int[][] coords = new int[9][2];
        int startX = ((blockNumber - 1) % 3) * 3;
        int startY = ((blockNumber - 1) / 3) * 3;

        int index = 0;
        for (int y = startY; y < startY + 3; y++) {
            for (int x = startX; x < startX + 3; x++) {
                coords[index][0] = x;
                coords[index][1] = y;
                index++;
            }
        }

        return coords;
    }

    public Grid getGrid(int i) {
        return grids.get(i);
    }

    public void printGrids() {
        Main.print("Central Grid:");
        Main.print(this);
        if (shape.equals("plus")) {
            Main.print("Top Grid:");
            Main.print(topGrid);
            Main.print("Left Grid:");
            Main.print(leftGrid);
            Main.print("Right Grid:");
            Main.print(rightGrid);
            Main.print("Bottom Grid:");
            Main.print(bottomGrid);
        } else if (shape.equals("croix")) {
            Main.print("Top Left Grid:");
            Main.print(topLeftGrid);
            Main.print("Top Right Grid:");
            Main.print(topRightGrid);
            Main.print("Bottom Left Grid:");
            Main.print(bottomLeftGrid);
            Main.print("Bottom Right Grid:");
            Main.print(bottomRightGrid);
        }
    }
}