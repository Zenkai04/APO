package com.gmail.darkfireyo;

import java.util.Random;

public class Generator {
    private Grid currGrid;

    public Generator(Grid grid){
        this.currGrid = grid;
    }

    public boolean generateNumber(){
        Random rand = new Random();

        return fillGrid(0,0,rand);
    }

    private boolean fillGrid(int x, int y,Random rand){

        if (x == currGrid.getWidth()) {
            x = 0;
            y++;
            if (y == currGrid.getHeight()) {
                return true;
            }
        }
        if (currGrid.getElement(x, y) != 0) {
            return fillGrid(x + 1, y, rand);
        }

        int[] numbers = new int[9];
        for (int i = 0; i < 9; i++) {
            numbers[i] = i + 1;
        }


        for (int i = 0; i < 9; i++) {
            int j = rand.nextInt(9);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        for (int num : numbers) {
            if (currGrid.isValid(x, y, num)) {
                currGrid.setElement(x, y, num);


                if (fillGrid(x + 1, y, rand)) {
                    return true;
                }

                currGrid.setElement(x, y, 0);
            }
        }

        return false;
    }

    public void deleteNumbers(int numberToDelete) {
        Random rand = new Random();

        Grid tempGrid = new Grid(currGrid.getWidth(), currGrid.getHeight());

        for (int i = 0; i < numberToDelete; i++) {
            int x = rand.nextInt(currGrid.getWidth());
            int y = rand.nextInt(currGrid.getHeight());


            if (tempGrid.getElement(x, y) == 0) {
                i--; // On réessaie
                continue;
            }

            tempGrid.setElement(x, y, 0);

            if (!isSolvable(tempGrid)) {

                tempGrid.setElement(x, y, currGrid.getElement(x, y));
                i--;
            }
        }


        currGrid = tempGrid;
    }
    private boolean isSolvable(Grid grid) {
        return solve(grid, 0, 0);
    }

    private boolean solve(Grid grid, int x, int y) {
        if (x == grid.getWidth()) {
            x = 0;
            y++;
            if (y == grid.getHeight()) {
                return true;
            }
        }

        if (grid.getElement(x, y) != 0) {
            return solve(grid, x + 1, y);
        }

        for (int num = 1; num <= 9; num++) {
            if (grid.isValid(x, y, num)) {
                grid.setElement(x, y, num);

                if (solve(grid, x + 1, y)) {
                    return true;
                }

                grid.setElement(x, y, 0);
            }
        }

        return false;
    }

    }
