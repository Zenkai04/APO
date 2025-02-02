package com.gmail.darkfireyo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un MultiDoku, une variante du Sudoku avec plusieurs grilles interconnectées.
 */
public class MultiDoku extends Grid {
    private Grid topGrid;
    private Grid leftGrid;
    private Grid rightGrid;
    private Grid bottomGrid;
    private Grid topLeftGrid;
    private Grid topRightGrid;
    private Grid bottomLeftGrid;
    private Grid bottomRightGrid;
    private String shape;
    private List<Grid> grids;

    /**
     * Constructeur de la classe MultiDoku.
     * @param shape La forme du MultiDoku ("plus" ou "croix").
     */
    public MultiDoku(String shape) {
        super(9, 9, 3, 3); // Initialise la grille centrale avec une taille 9x9 et des blocs 3x3
        this.shape = shape;
        grids = new ArrayList<>();
        
        if (shape.equals("plus")) {
            this.topGrid = new Grid(9, 9, 3, 3);
            this.leftGrid = new Grid(9, 9, 3, 3);
            this.rightGrid = new Grid(9, 9, 3, 3);
            this.bottomGrid = new Grid(9, 9, 3, 3);
            grids.add(this);
            grids.add(leftGrid);
            grids.add(rightGrid);
            grids.add(topGrid);
            grids.add(bottomGrid);
        } else if (shape.equals("croix")) {
            this.topLeftGrid = new Grid(9, 9, 3, 3);
            this.topRightGrid = new Grid(9, 9, 3, 3);
            this.bottomLeftGrid = new Grid(9, 9, 3, 3);
            this.bottomRightGrid = new Grid(9, 9, 3, 3);
            grids.add(this);
            grids.add(topLeftGrid);
            grids.add(topRightGrid);
            grids.add(bottomLeftGrid);
            grids.add(bottomRightGrid);
        }
    }

    /**
     * Génère les grilles du MultiDoku en fonction de sa forme.
     */
    public void generateGrids() {
        Generator centralGenerator = new Generator(this);
        centralGenerator.generateNumber();

        if (shape.equals("plus")) {
            generatePlusGrids();
        } else if (shape.equals("croix")) {
            generateCroixGrids();
        }
    }

    /**
     * Génère et remplit les grilles adjacentes pour la forme "plus".
     */
    private void generatePlusGrids() {
        Solver solver = new Solver();

        copyBlock(topGrid, 7, this, 1);
        copyBlock(topGrid, 8, this, 2);
        copyBlock(topGrid, 9, this, 3);
        solver.solveSudoku(topGrid);

        copyBlock(bottomGrid, 1, this, 7);
        copyBlock(bottomGrid, 2, this, 8);
        copyBlock(bottomGrid, 3, this, 9);
        solver.solveSudoku(bottomGrid);

        copyBlock(leftGrid, 3, this, 1);
        copyBlock(leftGrid, 6, this, 4);
        copyBlock(leftGrid, 9, this, 7);
        solver.solveSudoku(leftGrid);

        copyBlock(rightGrid, 1, this, 3);
        copyBlock(rightGrid, 4, this, 6);
        copyBlock(rightGrid, 7, this, 9);
        solver.solveSudoku(rightGrid);
    }

    /**
     * Génère et remplit les grilles adjacentes pour la forme "croix".
     */
    private void generateCroixGrids() {
        Solver solver = new Solver();

        copyBlock(topLeftGrid, 9, this, 1);
        solver.solveSudoku(topLeftGrid);

        copyBlock(topRightGrid, 7, this, 3);
        solver.solveSudoku(topRightGrid);

        copyBlock(bottomRightGrid, 1, this, 9);
        solver.solveSudoku(bottomRightGrid);

        copyBlock(bottomLeftGrid, 3, this, 7);
        solver.solveSudoku(bottomLeftGrid);
    }

    /**
     * Copie un bloc d'une grille source vers une grille cible.
     * @param targetGrid La grille cible.
     * @param targetBlock Le bloc cible dans la grille cible.
     * @param sourceGrid La grille source.
     * @param sourceBlock Le bloc source dans la grille source.
     */
    private void copyBlock(Grid targetGrid, int targetBlock, Grid sourceGrid, int sourceBlock) {
        int[][] targetCoords = getBlockCoordinates(targetBlock);
        int[][] sourceCoords = getBlockCoordinates(sourceBlock);

        for (int i = 0; i < 9; i++) {
            int targetX = targetCoords[i][0];
            int targetY = targetCoords[i][1];
            int sourceX = sourceCoords[i][0];
            int sourceY = sourceCoords[i][1];

            targetGrid.setElement(targetX, targetY, sourceGrid.getElement(sourceX, sourceY));
        }
    }

    /**
     * Obtient les coordonnées des cellules d'un bloc donné.
     * @param blockNumber Le numéro du bloc (1 à 9).
     * @return Un tableau contenant les coordonnées des cellules du bloc.
     */
    private int[][] getBlockCoordinates(int blockNumber) {
        int[][] coords = new int[9][2];
        int startX = ((blockNumber - 1) % 3) * 3;
        int startY = ((blockNumber - 1) / 3) * 3;

        int index = 0;
        for (int y = startY; y < startY + 3; y++) {
            for (int x = startX; x < startX + 3; x++) {
                coords[index][0] = x;
                coords[index][1] = y;
                index++;
            }
        }
        return coords;
    }

    /**
     * Récupère une grille spécifique du MultiDoku.
     * @param i L'index de la grille dans la liste.
     * @return La grille correspondante.
     */
    public Grid getGrid(int i) {
        return grids.get(i);
    }

    /**
     * Affiche toutes les grilles du MultiDoku.
     */
    public void printGrids() {
        Main.print("Grille Centrale:");
        Main.print(this);
        if (shape.equals("plus")) {
            Main.print("Grille du Haut:");
            Main.print(topGrid);
            Main.print("Grille de Gauche:");
            Main.print(leftGrid);
            Main.print("Grille de Droite:");
            Main.print(rightGrid);
            Main.print("Grille du Bas:");
            Main.print(bottomGrid);
        } else if (shape.equals("croix")) {
            Main.print("Grille Haut Gauche:");
            Main.print(topLeftGrid);
            Main.print("Grille Haut Droite:");
            Main.print(topRightGrid);
            Main.print("Grille Bas Gauche:");
            Main.print(bottomLeftGrid);
            Main.print("Grille Bas Droite:");
            Main.print(bottomRightGrid);
        }
    }
}
