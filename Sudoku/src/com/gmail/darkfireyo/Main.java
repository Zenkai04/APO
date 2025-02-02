package com.gmail.darkfireyo;

import com.gmail.darkfireyo.ui.SelectionFrame;
import com.gmail.darkfireyo.ui.SudokuFrame;
import com.gmail.darkfireyo.ui.SudokuController;
import com.gmail.darkfireyo.ui.MultidokuController;
import com.gmail.darkfireyo.ui.MultidokuFrame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez le mode d'affichage:");
        System.out.println("1. Affichage graphique");
        System.out.println("2. Affichage textuel");

        int choice = scanner.nextInt();

        if (choice == 1) {
            launchGraphicalMode();
        } else if (choice == 2) {
            launchTextualMode(scanner);
        } else {
            System.out.println("Choix invalide. Veuillez relancer l'application.");
        }
    }

    private static void launchGraphicalMode() {
        SelectionFrame selectionFrame = new SelectionFrame();
        selectionFrame.setVisible(true);

        selectionFrame.getSudokuButton().addActionListener(e -> {
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

    private static void launchTextualMode(Scanner scanner) {
        System.out.println("Choisissez le type de jeu:");
        System.out.println("1. Sudoku");
        System.out.println("2. Multidoku");

        int gameChoice = scanner.nextInt();


        if (gameChoice == 1) {
            
        System.out.println("Entrez la longueur de la grille:");
        int gridLength = scanner.nextInt();

        System.out.println("Entrez la largeur de la grille:");
        int gridWidth = scanner.nextInt();

        System.out.println("Entrez la longueur des blocs:");
        int blockLength = scanner.nextInt();

        System.out.println("Entrez la largeur des blocs:");
        int blockWidth = scanner.nextInt();

        System.out.println("Entrez une difficulté entre 1 et 3:");
        int difficulty = scanner.nextInt();

        if(difficulty < 1 ){
            difficulty = 1;
        } else if(difficulty > 3){
            difficulty = 3;
        }

        Grid grille = new Grid(gridLength, gridWidth, blockLength, blockWidth);



        Generator generateur = new Generator(grille);

        boolean test = generateur.generateNumber();
        generateur.deleteNumbers(difficulty * (gridLength * gridWidth) / 6);

        System.out.println(grille);

        System.out.println("Voulez-vous résoudre la grille ? (O/N)");

        String gridsolve = scanner.next();
        if(gridsolve.equals("O") || gridsolve.equals("o")){
            generateur.getSolver().solveSudoku(grille);
            System.out.println("Grille résolu : ");
            System.out.println(grille);
        }

        } else if (gameChoice == 2) {
            System.out.println("Choisissez la forme du Multidoku:");
            System.out.println("1. Multidoku en forme de X");
            System.out.println("2. Multidoku en forme de +");
            System.out.println("3. Multidoku coloré");
            System.out.println("4. Multidoku formé avec des blocs irréguliers");

            int multidokuChoice = scanner.nextInt();

            switch (multidokuChoice) {
                case 1:
                    System.out.println("Lancement du Multidoku en forme de X en mode textuel...");
                    // Placeholder for textual Multidoku X implementation
                    break;
                case 2:
                    System.out.println("Lancement du Multidoku en forme de + en mode textuel...");
                    // Placeholder for textual Multidoku + implementation
                    break;
                case 3:
                    System.out.println("Lancement du Multidoku coloré en mode textuel...");
                    // Placeholder for textual Multidoku coloré implementation
                    break;
                case 4:
                    System.out.println("Lancement du Multidoku formé avec des blocs irréguliers en mode textuel...");
                    // Placeholder for textual Multidoku blocs irréguliers implementation
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez relancer l'application.");
                    break;
            }
        } else {
            System.out.println("Choix invalide. Veuillez relancer l'application.");
        }
    }
}