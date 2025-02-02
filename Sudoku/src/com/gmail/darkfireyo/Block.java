package com.gmail.darkfireyo;

import java.util.HashSet;
import java.util.Set;

/**
 * Représente un bloc composé de cellules disposées en grille.
 */
public class Block {
    private Cell[] cells;
    private int height;
    private int width;
    private Set<Cell> cellSet;

    /**
     * Constructeur de la classe Block.
     *
     * @param width  Largeur du bloc (nombre de colonnes).
     * @param height Hauteur du bloc (nombre de lignes).
     */
    public Block(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width * height];
        this.cellSet = new HashSet<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = new Cell(i, j);
                this.cells[i + j * width] = cell;
                this.cellSet.add(cell);
            }
        }
    }

    /**
     * Ajoute une cellule au bloc.
     *
     * @param x Coordonnée en X de la cellule.
     * @param y Coordonnée en Y de la cellule.
     */
    public void addCell(int x, int y) {
        Cell cell = new Cell(x, y);
        cellSet.add(cell);
    }

    /**
     * Vérifie si une cellule existe dans le bloc.
     *
     * @param x Coordonnée en X de la cellule.
     * @param y Coordonnée en Y de la cellule.
     * @return true si la cellule existe, false sinon.
     */
    public boolean containsCell(int x, int y) {
        return cellSet.contains(new Cell(x, y));
    }

    /**
     * Récupère une cellule du bloc.
     *
     * @param x Coordonnée en X de la cellule.
     * @param y Coordonnée en Y de la cellule.
     * @return La cellule demandée.
     * @throws IllegalArgumentException si les coordonnées sont en dehors des limites du bloc.
     */
    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Coordonnées de cellule invalides");
        }
        return cells[x + y * width];
    }

    /**
     * Modifie la valeur d'une cellule spécifique du bloc.
     *
     * @param x     Coordonnée en X de la cellule.
     * @param y     Coordonnée en Y de la cellule.
     * @param value Nouvelle valeur à attribuer à la cellule.
     * @throws IllegalArgumentException si les coordonnées sont en dehors des limites du bloc.
     */
    public void setCell(int x, int y, int value) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Coordonnées de cellule invalides");
        }
        cells[x + y * width].setVal(value);
    }

    /**
     * Retourne la largeur du bloc.
     *
     * @return Largeur du bloc.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retourne la hauteur du bloc.
     *
     * @return Hauteur du bloc.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Retourne la taille totale du bloc (nombre total de cellules).
     *
     * @return Taille du bloc (width * height).
     */
    public int getSize() {
        return width * height;
    }

    /**
     * Retourne l'ensemble des cellules du bloc.
     *
     * @return Un ensemble contenant toutes les cellules du bloc.
     */
    public Set<Cell> getCells() {
        return cellSet;
    }
}
