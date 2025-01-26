
package com.gmail.darkfireyo;

public class Game {
    private Solver solver;
    private Generator generator;
    private Grid currentGrid;

    public Game(Solver solver, Generator generator) {
        this.solver = solver;
        this.generator = generator;
        this.currentGrid = new Grid(9);
    }

    public void newGame() {
        this.currentGrid = generator.generateCompleteGrid();
    }

    public void solveCurrentGrid() {
        solver.solve(currentGrid);
    }

    public void generateNewPuzzle() {
        this.currentGrid = generator.generateCompleteGrid();
        generator.removeNumbers(currentGrid);
    }

    public Grid getCurrentGrid() {
        return currentGrid;
    }
}