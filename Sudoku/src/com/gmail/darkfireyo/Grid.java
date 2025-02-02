package com.gmail.darkfireyo;

/**
 * La classe Grid représente une grille de Sudoku.
 */
public class Grid {
    private int[][] grid;
    private int width;
    private int height;
    private int blockWidth;
    private int blockHeight;

    /**
     * Construit une nouvelle grille avec les dimensions spécifiées.
     * @param width La largeur de la grille.
     * @param height La hauteur de la grille.
     * @param blockWidth La largeur de chaque bloc.
     * @param blockHeight La hauteur de chaque bloc.
     */
    public Grid(int width, int height, int blockWidth, int blockHeight) {
        this.width = width;
        this.height = height;
        this.blockWidth = blockWidth;
        this.blockHeight = blockHeight;
        this.grid = new int[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public int getElement(int x, int y) {
        return grid[x][y];
    }

    public void setElement(int x, int y, int value) {
        grid[x][y] = value;
    }

    /**
     * Vérifie si la valeur spécifiée est valide aux coordonnées données.
     * @param x La coordonnée x.
     * @param y La coordonnée y.
     * @param value La valeur à vérifier.
     * @return True si la valeur est valide, false sinon.
     */
    public boolean isValid(int x, int y, int value) {
        for (int i = 0; i < width; i++) {
            if (grid[i][y] == value || grid[x][i] == value) {
                return false;
            }
        }

        int startX = (x / blockWidth) * blockWidth;
        int startY = (y / blockHeight) * blockHeight;
        for (int i = 0; i < blockWidth; i++) {
            for (int j = 0; j < blockHeight; j++) {
                if (grid[startX + i][startY + j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Crée un clone de la grille.
     * @return Un nouvel objet Grid qui est un clone de cette grille.
     */
    public Grid clone() {
        Grid clone = new Grid(width, height, blockWidth, blockHeight);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                clone.setElement(x, y, grid[x][y]);
            }
        }
        return clone;
    }

    /**
     * Copie les éléments d'une autre grille dans cette grille.
     * @param other La grille à partir de laquelle copier.
     */
    public void copy(Grid other) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                setElement(x, y, other.getElement(x, y));
            }
        }
    }
    
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < height; y++) {
            StringBuilder ligne = new StringBuilder();
            for (int i = 0; i < width; i++) {
                ligne.append((this.getElement(i, y) != 0) ? Integer.toString(this.getElement(i, y), 36) : " "); // On convertis ici en base 36 pour pouvoir aller jusqu'a la lettre Z

                if (i < width - 1) {
                    ligne.append(" ");
                }
            }

           result.append(ligne.toString());
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}