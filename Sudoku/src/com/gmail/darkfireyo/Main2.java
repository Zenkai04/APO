package com.gmail.darkfireyo;

import java.util.*;

public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sélectionnez le type de Multidoku:\n1. Multidoku de Couleur\n2. Multidoku de Blocs\n3. Multidoku Classique");
        int type = getIntInput(scanner);

        if (type == 1) {
            // Multidoku de Couleur
            Map<Integer, String> colorMapping = new HashMap<>();
            colorMapping.put(1, "rouge");
            colorMapping.put(2, "bleu");
            colorMapping.put(3, "vert");
            colorMapping.put(4, "jaune");
            colorMapping.put(5, "orange");
            colorMapping.put(6, "violet");
            colorMapping.put(7, "rose");
            colorMapping.put(8, "gris");
            colorMapping.put(9, "noir");

            ColorMultidoku colorMultidoku = new ColorMultidoku(9, 9, 3, 3, colorMapping);
            colorMultidoku.fillColorBlocks();

            // Afficher la grille sans les coordonnées
            Grid grid = colorMultidoku.getGrid();
            System.out.println("Solution complète de la Grille de Couleur générée:");
            for (int y = 0; y < grid.getHeight(); y++) {
                for (int x = 0; x < grid.getWidth(); x++) {
                    int value = grid.getElement(x, y);
                    System.out.print(value + " ");
                }
                System.out.println();
            }
            // Afficher les chiffres individuels avec leurs couleurs et positions
            printNumbersWithColors(grid, colorMultidoku);

        } else if (type == 2) {
            // Multidoku de Blocs
            Set<ShapeBlock> shapeBlocks = generateRandomBlocks(9, 9, 3, 3);

            ShapeMultidoku shapeMultidoku = new ShapeMultidoku(9, 9, shapeBlocks);

            // Remplir les blocs avec des chiffres distincts
            if (fillBlocks(shapeMultidoku, shapeBlocks)) {
                System.out.println("Solution complète de la Grille de Blocs générée:");
                Grid grid = shapeMultidoku.getGrid();
                for (int y = 0; y < grid.getHeight(); y++) {
                    for (int x = 0; x < grid.getWidth(); x++) {
                        int value = grid.getElement(x, y);
                        System.out.print(value + " ");
                    }
                    System.out.println();
                }

                // Afficher les coordonnées des chiffres pour chaque bloc
                printBlockCoordinates(grid, shapeBlocks);
            } else {
                System.out.println("Échec de la génération de la solution.");
            }

        } else if (type == 3) {
            // Multidoku Classique
            System.out.println("Sélectionnez le type de Multidoku Classique:\n1. Multidoku en X\n2. Multidoku en +");
            int classicType = getIntInput(scanner);

            ClassicMultidoku classicMultidoku;
            if (classicType == 1) {
                classicMultidoku = new ClassicMultidoku(5, 9, 9, 3, 3, "X");
                classicMultidoku.generateClassicMultidokuX();
            } else if (classicType == 2) {
                classicMultidoku = new ClassicMultidoku(5, 9, 9, 3, 3, "+");
                classicMultidoku.generateClassicMultidokuPlus();
            } else {
                System.out.println("Option invalide.");
                scanner.close();
                return;
            }

            // Générer et afficher chaque grille sans les coordonnées
            for (int i = 0; i < 5; i++) {  // Boucler sur les 5 grilles
                Grid grille = classicMultidoku.getGrid(i);
                Generator generator = new Generator(grille);
                generator.generateNumber();
                // Ne pas supprimer de nombres pour obtenir une solution complète

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
        } else {
            System.out.println("Option invalide.");
        }

        scanner.close();
    }

    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static boolean fillBlocks(ShapeMultidoku shapeMultidoku, Set<ShapeBlock> shapeBlocks) {
        for (ShapeBlock block : shapeBlocks) {
            List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            if (!fillBlock(shapeMultidoku, block, numbers, 0)) {
                return false; // Échec de la validation
            }
        }
        return true; // Succès de la génération
    }

    private static boolean fillBlock(ShapeMultidoku shapeMultidoku, ShapeBlock block, List<Integer> numbers, int index) {
        if (index >= numbers.size()) {
            return true; // Tous les chiffres ont été placés avec succès
        }

        Collections.shuffle(numbers);
        for (ShapeBlock.Coordinate coord : block.getCoordinates()) {
            int x = coord.x;
            int y = coord.y;
            if (shapeMultidoku.getElement(x, y) == 0) {
                int value = numbers.get(index);
                if (shapeMultidoku.isValid(x, y, value)) {
                    shapeMultidoku.setElement(x, y, value);
                    if (fillBlock(shapeMultidoku, block, numbers, index + 1)) {
                        return true;
                    }
                    shapeMultidoku.setElement(x, y, 0);
                }
            }
        }
        return false; // Échec de la validation
    }

    private static void printNumbersWithColors(Grid grid, ColorMultidoku colorMultidoku) {
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                int value = grid.getElement(x, y);
                if (value != 0) {
                    String color = colorMultidoku.getColorAt(x, y);
                    System.out.println("Chiffre " + value + " (" + color + ") à la position (" + x + "," + y + ")");
                }
            }
        }
    }

    private static void printBlockCoordinates(Grid grid, Set<ShapeBlock> shapeBlocks) {
        for (ShapeBlock block : shapeBlocks) {
            System.out.println("Bloc:");
            for (ShapeBlock.Coordinate coord : block.getCoordinates()) {
                int value = grid.getElement(coord.x, coord.y);
                System.out.println("Chiffre " + value + " à la position (" + coord.x + "," + coord.y + ")");
            }
        }
    }

    private static Set<ShapeBlock> generateRandomBlocks(int width, int height, int blockWidth, int blockHeight) {
        Set<ShapeBlock> blocks = new HashSet<>();
        Random rand = new Random();
        boolean[][] usedCells = new boolean[width][height];

        for (int i = 0; i < (width * height) / (blockWidth * blockHeight); i++) {
            ShapeBlock block = new ShapeBlock();
            while (block.getCells().size() < blockWidth * blockHeight) {
                int x = rand.nextInt(width);
                int y = rand.nextInt(height);
                if (!usedCells[x][y]) {
                    block.addCell(x, y);
                    usedCells[x][y] = true;
                }
            }
            blocks.add(block);
        }

        return blocks;
    }
}