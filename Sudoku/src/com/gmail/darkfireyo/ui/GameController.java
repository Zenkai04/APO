package com.gmail.darkfireyo.ui;

import com.gmail.darkfireyo.Generator;
import com.gmail.darkfireyo.Grid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private SudokuFrame frame;
    private Grid grid;
    private Generator generator;

    public GameController(SudokuFrame frame) {
        this.frame = frame;
        initializeGame();
    }

    private void initializeGame() {
        frame.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width = frame.getGridWidth();
                int height = frame.getGridHeight();
                int blockWidth = frame.getBlockWidth();
                int blockHeight = frame.getBlockHeight();

                if (width % blockWidth != 0 || height % blockHeight != 0) {
                    System.out.println("Invalid grid dimensions or block sizes. Please try again.");
                    frame.showErrorLabel(true);
                    return;
                }

                grid = new Grid(width, height, blockWidth, blockHeight);
                generator = new Generator(grid);
                if (!generator.generateNumber()) {
                    System.out.println("Failed to generate Sudoku. Please try again.");
                    frame.showErrorLabel(true);
                    return;
                }
                frame.showErrorLabel(false);
                generator.deleteNumbers(20);
                frame.updateSudokuPanel(grid);
                frame.getSolveButton().setVisible(true);
            }
        });

        frame.getSolveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (generator.getSolver().isSolvable(grid)) {
                    Grid solvedGrid = generator.getSolver().solveSudoku(grid);
                    frame.updateSudokuPanel(solvedGrid);
                } else {
                    System.out.println("Failed to solve Sudoku.");
                }
            }
        });
    }
}