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
            }
        });
    }
}