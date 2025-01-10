package com.gmail.darkfireyo;

public class Main {
    public static void main(String[] args) {
        Solver solver = new Solver();
        Generator generator = new Generator();
        Game game = new Game(solver, generator);
        game.newGame();
        game.solveCurrentGrid();
        game.generateNewPuzzle();
        game.solveCurrentGrid();
    }
}
