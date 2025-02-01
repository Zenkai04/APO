package com.gmail.darkfireyo;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

public class ClassicMultidoku {
    private List<Grid> grids;
    private String type; // Ajout de l'attribut type

    public ClassicMultidoku(int numberOfGrids, int width, int height, int blockWidth, int blockHeight, String type) {
        this.grids = new ArrayList<>();
        for (int i = 0; i < numberOfGrids; i++) {
            this.grids.add(new Grid(width, height, blockWidth, blockHeight));
        }
        this.type = type;
    }

    public Grid getGrid(int index) {
        if (index < 0 || index >= grids.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return grids.get(index);
    }

    public void generateClassicMultidokuX() {
        for (Grid grid : grids) {
            generateSudoku(grid);
        }
        copyAngularBlocks();
    }

    public void generateClassicMultidokuPlus() {
        for (Grid grid : grids) {
            generateSudoku(grid);
        }
        copyFaceBlocks();
    }

    private void generateSudoku(Grid sudoku) {
        fillGrid(sudoku, 0, 0);
    }

    private boolean fillGrid(Grid sudoku, int x, int y) {
        if (sudoku == null) {
            throw new IllegalArgumentException("sudoku is null");
        }

        if (x == sudoku.getWidth()) {
            x = 0;
            y++;
            if (y == sudoku.getHeight()) {
                return true;
            }
        }

        if (sudoku.getElement(x, y) != 0) {
            return fillGrid(sudoku, x + 1, y);
        }

        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (isValid(sudoku, x, y, num)) {
                sudoku.setElement(x, y, num);
                if (fillGrid(sudoku, x + 1, y)) {
                    return true;
                }
                sudoku.setElement(x, y, 0);
            }
        }

        return false;
    }

    private boolean isValid(Grid sudoku, int x, int y, int value) {
        // Vérifier la ligne
        for (int i = 0; i < sudoku.getWidth(); i++) {
            if (sudoku.getElement(i, y) == value) {
                return false;
            }
        }

        // Vérifier la colonne
        for (int i = 0; i < sudoku.getHeight(); i++) {
            if (sudoku.getElement(x, i) == value) {
                return false;
            }
        }

        // Vérifier le bloc
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
        for (int i = startX; i < startX + 3; i++) {
            for (int j = startY; j < startY + 3; j++) {
                if (sudoku.getElement(i, j) == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public void printSharedBlocks() {
        // Imprimer les blocs partagés entre les Sudokus
        System.out.println("Blocs partagés entre les Sudokus :");
        if ("X".equals(type)) {
            // Multidoku en "X"
            printBlock(0, 0, 6, 6); // Bloc supérieur gauche du central et bloc inférieur droit de l'externe en haut à gauche
            printBlock(0, 6, 6, 0); // Bloc supérieur droit du central et bloc inférieur gauche de l'externe en haut à droite
            printBlock(6, 0, 0, 6); // Bloc inférieur gauche du central et bloc supérieur droit de l'externe en bas à gauche
            printBlock(6, 6, 0, 0); // Bloc inférieur droit du central et bloc supérieur gauche de l'externe en bas à droite
        } else if ("+".equals(type)) {
            // Multidoku en "+"
            for (int i = 0; i < 3; i++) {
                printBlock(0, i * 3, 6, i * 3); // Blocs supérieurs du central et blocs inférieurs de l'externe en haut
                printBlock(6, i * 3, 0, i * 3); // Blocs inférieurs du central et blocs supérieurs de l'externe en bas
                printBlock(i * 3, 0, i * 3, 6); // Blocs à gauche du central et blocs à droite de l'externe à gauche
                printBlock(i * 3, 6, i * 3, 0); // Blocs à droite du central et blocs à gauche de l'externe à droite
            }
        }
    }

    private void printBlock(int startX, int startY, int extStartX, int extStartY) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                System.out.print(grids.get(0).getElement(startX + x, startY + y) + " ");
            }
            System.out.println();
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                System.out.print(grids.get(0).getElement(extStartX + x, extStartY + y) + " ");
            }
            System.out.println();
        }
    }

    private void copyAngularBlocks() {
        // Copier les blocs angulaires du Sudoku central vers les Sudokus externes
        copyBlock(0, 0, grids.get(1), 6, 6); // Bloc supérieur gauche du central vers le bloc inférieur droit de l'externe en haut à gauche
        copyBlock(0, 6, grids.get(2), 6, 0); // Bloc supérieur droit du central vers le bloc inférieur gauche de l'externe en haut à droite
        copyBlock(6, 0, grids.get(3), 0, 6); // Bloc inférieur gauche du central vers le bloc supérieur droit de l'externe en bas à gauche
        copyBlock(6, 6, grids.get(4), 0, 0); // Bloc inférieur droit du central vers le bloc supérieur gauche de l'externe en bas à droite
    }

    private void copyFaceBlocks() {
        // Copier les blocs des faces du Sudoku central vers les Sudokus externes
        for (int i = 0; i < 3; i++) {
            copyBlock(0, i * 3, grids.get(1), 6, i * 3); // Blocs supérieurs du central vers blocs inférieurs de l'externe en haut
            copyBlock(6, i * 3, grids.get(2), 0, i * 3); // Blocs inférieurs du central vers blocs supérieurs de l'externe en bas
            copyBlock(i * 3, 0, grids.get(3), i * 3, 6); // Blocs à gauche du central vers blocs à droite de l'externe à gauche
            copyBlock(i * 3, 6, grids.get(4), i * 3, 0); // Blocs à droite du central vers blocs à gauche de l'externe à droite
        }
    }

    private void copyBlock(int startX, int startY, Grid externalGrid, int extStartX, int extStartY) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                externalGrid.setElement(extStartX + x, extStartY + y, grids.get(0).getElement(startX + x, startY + y));
            }
        }
    }
}