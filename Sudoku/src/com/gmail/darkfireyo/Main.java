package com.gmail.darkfireyo;

import com.gmail.darkfireyo.ui.SelectionFrame;
import com.gmail.darkfireyo.ui.SudokuFrame;
import com.gmail.darkfireyo.ui.SudokuController;
import com.gmail.darkfireyo.ui.MultidokuController;
import com.gmail.darkfireyo.ui.MultidokuFrame;

import java.util.Scanner;

public class Main {

    private static int gridLength;
    private static int gridWidth;
    private static int blockLength;
    private static int blockWidth;
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
    	while(true) {
    		
    		print("Choisissez le mode d'affichage:");
    		print("1. Affichage graphique");
    		print("2. Affichage textuel");

    		int choice = scanner.nextInt();

    		if (choice == 1) {
    			launchGraphicalMode();
    			return;
    		} else if (choice == 2) {
    			launchTextualMode(scanner);
    			return;
    		} else {
    			print("Choix invalide.");
    		}
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
        print("Choisissez le type de jeu:");
        print("1. Sudoku");
        print("2. Multidoku / Sudoku Spéciaux");

        int gameChoice = scanner.nextInt();


        if (gameChoice == 1) {
            
        setValues();

        print("Entrez une difficulté entre 1 et 3:");
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

        print(grille);

        print("Voulez-vous résoudre la grille ? (O/N)");

        String gridsolve = scanner.next();
        if(gridsolve.equals("O") || gridsolve.equals("o")){
            generateur.getSolver().solveSudoku(grille);
            print("Grille résolu : ");
            print(grille);
        }

        } else if (gameChoice == 2) {
            print("Choisissez la forme du Multidoku / Sudoku Speciaux :");
            print("1. Multidoku en forme de x ou +");
            print("3. Sudoku coloré");
            print("4. Sudoku formé avec des blocs irréguliers");

            int multidokuChoice = scanner.nextInt();

            switch (multidokuChoice) {
                case 1:
                    
                    System.out.println("Sélectionnez le type de Multidoku Classique:\n1. Multidoku en X\n2. Multidoku en +");
                    int classicType = scanner.nextInt();
        
                    ClassicMultidoku classicMultidoku;
                    if (classicType == 1) {
                        print("Lancement du Multidoku en forme de x en mode textuel...");
                        classicMultidoku = new ClassicMultidoku(5, 9, 9, 3, 3, "X");
                        classicMultidoku.generateClassicMultidokuX();
                    } else if (classicType == 2) {
                        print("Lancement du Multidoku en forme de + mode textuel...");
                        classicMultidoku = new ClassicMultidoku(5, 9, 9, 3, 3, "+");
                        classicMultidoku.generateClassicMultidokuPlus();
                    } else {
                        System.out.println("Option invalide.");
                        scanner.close();
                        return;
                    }
        
                    for (int i = 0; i < 5; i++) { 
                        Grid grille = classicMultidoku.getGrid(i);
                        Generator generator = new Generator(grille);
                        generator.generateNumber();
                        
        
                        System.out.println("Solution complète de la Grille " + (i + 1) + " générée:");
                        for (int y = 0; y < grille.getHeight(); y++) {
                            for (int x = 0; x < grille.getWidth(); x++) {
                                int value = grille.getElement(x, y);
                                System.out.print(value + " ");
                            }
                            System.out.println();
                        }
                    }
                    classicMultidoku.printSharedBlocks();
                    break;
                case 2:
                    print("Lancement du Multidoku coloré en mode textuel...");
                     setValues();
                     ColorGrid grille = new ColorGrid(gridLength, gridWidth, blockLength, blockWidth);

        ColorGenerator generateur = new ColorGenerator(grille);
        boolean test = generateur.generateNumber();

        if (test) {
            grille.assignColors();

            generateur.deleteNumbers(2 *(gridLength * gridWidth) / 6);

            print("Grille générée:");
            print(grille);

            ColorSolver solver = new ColorSolver();
            Grid solvedGrid = solver.solveSudoku(grille);

            if (solvedGrid != null) {
                print("Grille résolue:");
                print(solvedGrid);
            } else {
                print("La grille n'est pas solvable.");
            }
        } else {
            print("Échec de la génération de la grille de Sudoku colorée.");
        }
                    break;
                case 3:
                    print("Lancement du Multidoku formé avec des blocs irréguliers en mode textuel...");
                    // Placeholder for textual Multidoku blocs irréguliers implementation
                    break;
                default:
                    print("Choix invalide.");
                    break;
            }
        } else {
            print("Choix invalide.");
        }
    }

    public static void setValues(){
        print("Entrez la longueur de la grille:");
        gridLength = scanner.nextInt();

        print("Entrez la largeur de la grille:");
        gridWidth = scanner.nextInt();

        print("Entrez la longueur des blocs:");
        blockLength = scanner.nextInt();

        print("Entrez la largeur des blocs:");
        blockWidth = scanner.nextInt();
    }
    
    public static void print(Object o) {
    	System.out.println(o);
    }
}
    