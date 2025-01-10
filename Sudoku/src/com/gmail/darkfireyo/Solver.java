package com.gmail.darkfireyo;

public class Solver {
    private Grid grid;
    
    public Solver(Grid grid) {
        this.grid = grid;
    }

    public void useDeductionRule(){
        int length = grid.getWidth();
        for (int x = 0; x < length; x++) {
            int valTest = grid.getElement(x, 0);
            for (int y = 1; y < length; y++) {
                if (valTest == grid.getElement(x, y)) {
                    grid.setElement(x, y, valTest + 1);
                    x = 0;
                }
            }
        }
    }
}
