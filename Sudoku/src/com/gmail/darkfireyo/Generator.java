package com.gmail.darkfireyo;

import java.util.Random;

public class Generator {
    private Grid currGrid;
    private Solver solver;

    public Generator(Grid grid){
        this.currGrid = grid;
        this.solver = new Solver();
    }

    public boolean generateNumber(){
        Random rand = new Random();
        boolean tryFill = fillGrid(0, 0, rand);
        if(tryFill) {
        	return tryFill;
        }
        return false;
    }

    private boolean fillGrid(int x, int y, Random rand){
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

        int size = currGrid.getWidth();
        int[] numbers = new int[size];
        for (int i = 0; i < size; i++) {
            numbers[i] = i + 1;
        }

        for (int i = 0; i < size; i++) {
            int j = rand.nextInt(size);
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
        Grid tempGrid = currGrid.clone();

        for (int i = 0; i < numberToDelete; i++) {
            int x = rand.nextInt(currGrid.getWidth());
            int y = rand.nextInt(currGrid.getHeight());

            if (tempGrid.getElement(x, y) == 0) {
                i--; // On réessaie
                continue;
            }

            tempGrid.setElement(x, y, 0);

            if (!solver.isSolvable(tempGrid)) {
                tempGrid.setElement(x, y, currGrid.getElement(x, y));
                i--;
            }
        }

        currGrid.copy(tempGrid);
    }
}