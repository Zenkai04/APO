package com.gmail.darkfireyo;

/**
 * La classe Block représente un bloc dans une grille de Sudoku.
 */
public class Block {
    private Cell[] cells;
    private int height;
    private int width;

    /**
     * Construit un nouveau bloc avec les dimensions spécifiées.
     * @param width La largeur du bloc.
     * @param height La hauteur du bloc.
     */
    public Block(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width * height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.cells[i + j * width] = new Cell();
            }
        }
    }

    /**
     * Obtient la cellule aux coordonnées spécifiées.
     * @param x La coordonnée x.
     * @param y La coordonnée y.
     * @return La cellule aux coordonnées spécifiées.
     */
    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Coordonnées de cellule invalides");
        }
        return cells[x + y * width];
    }

    /**
     * Définit la valeur de la cellule aux coordonnées spécifiées.
     * @param x La coordonnée x.
     * @param y La coordonnée y.
     * @param value La valeur à définir.
     */
    public void setCell(int x, int y, int value) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Coordonnées de cellule invalides");
        }
        cells[x + y * width].setVal(value);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return width * height;
    }
}