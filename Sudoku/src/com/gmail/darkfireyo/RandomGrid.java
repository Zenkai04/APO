package com.gmail.darkfireyo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

/**
 * Classe représentant une grille de Sudoku générée aléatoirement.
 * Elle hérite de la classe Grid et génère une grille de 9x9 avec des blocs de 3x3.
 */
public class RandomGrid extends Grid {
    private List<RandomBlock> blocks;

    /**
     * Constructeur de RandomGrid.
     * Il tente de générer une grille valide en créant des blocs aléatoires et en remplissant la grille.
     */
    public RandomGrid() {
        super(9, 9, 3, 3);
        boolean success = false;
        while (!success) {
            System.out.println("Generation de blocks...");
            this.blocks = generateRandomBlocks();
            if (this.blocks.size() == 9) {
                System.out.println("Génération de la grille...");
                generateGrid();
                success = true;
            } else {
                System.out.println("Impossible de générer les blocks");
            }
        }
    }

    /**
     * Génère des blocs aléatoires pour la grille de Sudoku.
     * @return Une liste de 9 blocs RandomBlock si la génération est réussie, sinon une liste vide.
     */
    public List<RandomBlock> generateRandomBlocks() {
        List<RandomBlock> blocks = new ArrayList<>();
        List<Cell> availableCells = new ArrayList<>();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                availableCells.add(new Cell(i, j));
            }
        }
        Collections.shuffle(availableCells, new Random());

        for (int i = 0; i < 9; i++) {
            if (availableCells.size() < 9) {
                System.out.println("Pas assez de cellules" + (i + 1));
                break;
            }
            RandomBlock block = new RandomBlock(3, 3);
            for (int j = 0; j < 9; j++) {
                Cell cell = availableCells.remove(0);
                block.addCell(cell.getRow(), cell.getCol());
            }
            blocks.add(block);
        }

        return blocks.size() == 9 ? blocks : new ArrayList<>();
    }

    /**
     * Génère la grille de Sudoku en plaçant des valeurs aléatoires valides dans chaque bloc.
     */
    public void generateGrid() {
        Random random = new Random();
        int[][] grid = new int[9][9];

        for (RandomBlock block : blocks) {
            Set<Integer> usedValues = new HashSet<>();
            for (Cell cell : block.getCells()) {
                int value;
                int attempts = 0;
                do {
                    if (attempts > 100) {
                        System.out.println("Trop d'essais pour atteindre cette cellule (" + cell.getRow() + ", " + cell.getCol() + ")");
                        return;
                    }
                    value = random.nextInt(9) + 1;
                    attempts++;
                } while (usedValues.contains(value) || !isValid(grid, cell.getRow(), cell.getCol(), value));
                usedValues.add(value);
                grid[cell.getRow()][cell.getCol()] = value;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                setElement(i, j, grid[i][j]);
            }
        }
    }

    /**
     * Vérifie si une valeur peut être placée dans une cellule sans violer les règles du Sudoku.
     * @param grid La grille de Sudoku en cours de génération.
     * @param row La ligne de la cellule.
     * @param col La colonne de la cellule.
     * @param value La valeur à vérifier.
     * @return true si la valeur peut être placée, sinon false.
     */
    private boolean isValid(int[][] grid, int row, int col, int value) {
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] == value || grid[row][i] == value) {
                return false;
            }
        }
        return true;
    }

    /**
     * Affiche les blocs générés ainsi que leurs cellules correspondantes.
     */
    public void printBlocks() {
        for (int i = 0; i < blocks.size(); i++) {
            RandomBlock block = blocks.get(i);
            System.out.println("Block #" + (i + 1) + ":");
            for (Cell cell : block.getCells()) {
                System.out.println("Cellule : (" + cell.getRow() + ", " + cell.getCol() + ")");
            }
        }
    }
}
