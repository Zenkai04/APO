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
}
