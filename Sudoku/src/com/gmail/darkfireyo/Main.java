package com.gmail.darkfireyo;

import com.gmail.darkfireyo.ui.SelectionFrame;
import com.gmail.darkfireyo.ui.SudokuFrame;
import com.gmail.darkfireyo.ui.SudokuController;
import com.gmail.darkfireyo.ui.MultidokuController;
import com.gmail.darkfireyo.ui.MultidokuFrame;

public class Main {
    public static void main(String[] args) {
        SelectionFrame selectionFrame = new SelectionFrame();
        selectionFrame.setVisible(true);

        selectionFrame.getSudokuButton().addActionListener(e -> { // Définit l'action effectuer quand le bouton est appuyé
            selectionFrame.setVisible(false);
            SudokuFrame sudokuFrame = new SudokuFrame();
            SudokuController controller = new SudokuController(sudokuFrame);
            sudokuFrame.setVisible(true);
        });

        selectionFrame.getMultidokuButton().addActionListener(e -> {
        	 selectionFrame.setVisible(false);
         MultidokuFrame multidokuFrame = new MultidokuFrame();
         MultidokuController controller = new MultidokuController(multidokuFrame);
             multidokuFrame.setVisible(true);
        });
    }
}