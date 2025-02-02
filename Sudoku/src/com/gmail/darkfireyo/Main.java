package com.gmail.darkfireyo;

import com.gmail.darkfireyo.ui.SelectionFrame;
import com.gmail.darkfireyo.ui.SudokuFrame;
import com.gmail.darkfireyo.ui.SudokuController;
import com.gmail.darkfireyo.ui.MultidokuController;
import com.gmail.darkfireyo.ui.MultidokuFrame;

import java.util.Scanner;

/**
 * La classe Main est le point d'entrée de l'application Sudoku.
 * Elle permet de choisir entre un mode d'affichage graphique ou textuel,
 * et de lancer les différentes fonctionnalités du jeu en fonction du choix de l'utilisateur.
 */
public class Main {

    private static int gridLength;
    private static int gridWidth;
    private static int blockLength;
    private static int blockWidth;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Méthode principale qui démarre l'application.
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        while (true) {
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

    /**
     * Lance le mode d'affichage graphique.
     */
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

    /**
     * Lance le mode d'affichage textuel.
     * @param scanner Scanner pour lire les entrées de l'utilisateur.
     */
    private static void launchTextualMode(Scanner scanner) {
        print("Choisissez le type de jeu:");
        print("1. Sudoku");
        print("2. Multidoku / Sudoku Spéciaux");

        int gameChoice = scanner.nextInt();

        if (gameChoice == 1) {
            setValues();

            print("Entrez une difficulté entre 1 et 3:");
            int difficulty = scanner.nextInt();

            if (difficulty < 1) {
                difficulty = 1;
            } else if (difficulty > 3) {
                difficulty = 3;
            }

            Grid grille = new Grid(gridLength, gridWidth, blockLength, blockWidth);
            Generator generateur = new Generator(grille);

            print("Voulez vous entrer une grille ou laisser le programme la créer ?");
            print("1. Rentrer la grille");
            print("2. Laisser le programme génerer");
            int gridChoice = scanner.nextInt();
            if (gridChoice == 1) {
                for (int x = 0; x < grille.getWidth(); x++) {
                    for (int y = 0; y < grille.getHeight(); y++) {
                        Main.print("x : " + x + "y : " + y);
                        grille.setElement(x, y, scanner.nextInt());
                    }
                }
            } else {
                boolean test = generateur.generateNumber();
                generateur.deleteNumbers(difficulty * (gridLength * gridWidth) / 6);
            }

            print(grille);

            print("Voulez-vous résoudre la grille ? (O/N)");

            String gridsolve = scanner.next();
            if (gridsolve.equals("O") || gridsolve.equals("o")) {
                Grid newGrid = generateur.getSolver().solveSudoku(grille);
                if (newGrid != null) {
                    print("Grille résolu : ");
                    print(grille);
                } else {
                    print("Impossible de résoudre la grille");
                }
            }

        } else if (gameChoice == 2) {
            print("Choisissez la forme du Multidoku / Sudoku Speciaux :");
            print("1. Multidoku en forme de x ou +");
            print("2. Sudoku coloré");
            print("3. Sudoku formé avec des blocs irréguliers");

            int multidokuChoice = scanner.nextInt();

            switch (multidokuChoice) {
                case 1:
                    System.out.println("Sélectionnez le type de Multidoku Classique:\n1. Multidoku en X\n2. Multidoku en +");
                    int classicType = scanner.nextInt();

                    if (classicType == 1) {
                        print("Lancement du Multidoku en forme de x en mode textuel...");
                        MultiDoku multiDoku = new MultiDoku("croix");
                        multiDoku.generateGrids();
                        multiDoku.printGrids();
                    } else if (classicType == 2) {
                        print("Lancement du Multidoku en forme de + mode textuel...");
                        MultiDoku multiDoku = new MultiDoku("plus");
                        multiDoku.generateGrids();
                        multiDoku.printGrids();
                    } else {
                        System.out.println("Option invalide.");
                        scanner.close();
                        return;
                    }
                    break;
                case 2:
                    print("Lancement du Multidoku coloré en mode textuel...");
                    setValues();
                    ColorGrid grille = new ColorGrid(gridLength, gridWidth, blockLength, blockWidth);

                    ColorGenerator generateur = new ColorGenerator(grille);
                    boolean test = generateur.generateNumber();

                    if (test) {
                        grille.assignColors();
                        generateur.deleteNumbers(2 * (gridLength * gridWidth) / 6);

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
                    RandomGrid grid = new RandomGrid();

                    print("Blocks Générés :");
                    grid.printBlocks();

                    RandomGenerator generator = new RandomGenerator(grid);

                    if (generator.generateNumber()) {
                        print("Generation du Sudoku de block:");
                        print(grid);

                        RandomSolver solver = new RandomSolver();

                        if (solver.isSolvable(grid)) {
                            print("Est résolvable !");
                            Grid solvedGrid = solver.solveSudoku(grid);
                            print("Grille résolu :");
                            print(solvedGrid);
                        } else {
                            print("Le sudoku n'est pas solvable.");
                        }
                    } else {
                        System.out.println("Impossible de générer le sudoku.");
                    }
                    
                    break;
                default:
                    print("Choix invalide.");
                    break;
            }
        } else {
            print("Choix invalide.");
        }
    }

    /**
     * Définit les valeurs de la grille et des blocs en demandant à l'utilisateur de les entrer.
     */
    public static void setValues() {
        print("Entrez la longueur de la grille:");
        gridLength = scanner.nextInt();

        print("Entrez la largeur de la grille:");
        gridWidth = scanner.nextInt();

        print("Entrez la longueur des blocs:");
        blockLength = scanner.nextInt();

        print("Entrez la largeur des blocs:");
        blockWidth = scanner.nextInt();
    }

    /**
     * Affiche un objet dans la console.
     * @param o L'objet à afficher.
     */
    public static void print(Object o) {
        System.out.println(o);
    }
}