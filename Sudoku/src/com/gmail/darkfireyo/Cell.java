package com.gmail.darkfireyo;

import java.util.Objects;

/**
 * Représente une cellule dans une grille.
 */
public class Cell {
    private int val;
    private int row;
    private int col;

    /**
     * Constructeur par défaut de la classe Cell.
     * Initialise la valeur de la cellule à 0.
     */
    public Cell() {
        this.val = 0;
    }

    /**
     * Constructeur de la classe Cell avec des coordonnées.
     *
     * @param row Coordonnée de la ligne.
     * @param col Coordonnée de la colonne.
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.val = 0;
    }

    /**
     * Retourne la valeur de la cellule.
     *
     * @return Valeur de la cellule.
     */
    public int getVal() {
        return val;
    }

    /**
     * Définit une nouvelle valeur pour la cellule.
     *
     * @param val Nouvelle valeur à attribuer.
     */
    public void setVal(int val) {
        this.val = val;
    }

    /**
     * Retourne la ligne de la cellule.
     *
     * @return Numéro de la ligne.
     */
    public int getRow() {
        return row;
    }

    /**
     * Retourne la colonne de la cellule.
     *
     * @return Numéro de la colonne.
     */
    public int getCol() {
        return col;
    }

    /**
     * Vérifie si deux cellules sont égales en comparant leurs coordonnées.
     *
     * @param o Objet à comparer.
     * @return true si les cellules ont les mêmes coordonnées, sinon false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col;
    }

    /**
     * Génère un code de hachage basé sur les coordonnées de la cellule.
     *
     * @return Code de hachage de la cellule.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
