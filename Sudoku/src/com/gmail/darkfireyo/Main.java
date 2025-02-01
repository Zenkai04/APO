package com.gmail.darkfireyo;

import com.gmail.darkfireyo.ui.SelectionFrame;
import com.gmail.darkfireyo.ui.SudokuFrame;
import com.gmail.darkfireyo.ui.GameController;

public class Main {
    public static void main(String[] args) {
        SelectionFrame selectionFrame = new SelectionFrame();
        selectionFrame.setVisible(true);

        selectionFrame.getSudokuButton().addActionListener(e -> {
            selectionFrame.setVisible(false);
            SudokuFrame sudokuFrame = new SudokuFrame();
            GameController controller = new GameController(sudokuFrame);
            sudokuFrame.setVisible(true);
        });

        selectionFrame.getMultidokuButton().addActionListener(e -> {
            // Future implementation for Multidoku
            System.out.println("Multidoku selected. Feature not yet implemented.");
        });
    }
}