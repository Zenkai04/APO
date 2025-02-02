package com.gmail.darkfireyo.ui;

import com.gmail.darkfireyo.Generator;
import com.gmail.darkfireyo.Grid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe contrôleur qui gère les interactions entre l'interface utilisateur et la logique du Sudoku.
 */
public class SudokuController {
    private SudokuFrame frame;
    private Grid grid;
    private Generator generator;

    /**
     * Constructeur de la classe SudokuController.
     * Initialise le contrôleur et configure les écouteurs d'événements.
     * 
     * @param frame la fenêtre de l'application Sudoku
     */
    public SudokuController(SudokuFrame frame) {
        this.frame = frame;
        initializeGame();
    }

    /**
     * Initialise le jeu en ajoutant des écouteurs aux boutons de l'interface.
     */
    private void initializeGame() {
        frame.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int width = frame.getGridWidth();
                int height = frame.getGridHeight();
                int blockWidth = frame.getBlockWidth();
                int blockHeight = frame.getBlockHeight();
                int difficulty = frame.getDifficulty();
                difficulty = difficulty * (width * height) / 6;
                
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
                generator.deleteNumbers(difficulty);
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
