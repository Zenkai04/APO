package com.gmail.darkfireyo;

import java.util.*;

/**
 * La classe ColorMultidoku représente un puzzle de Sudoku coloré.
 */
public class ColorMultidoku {
    private Grid grid;
    private Map<Integer, String> colorMapping;
    private Map<String, Set<Coordinate>> colorBlocks;

    /**
     * Construit un nouveau ColorMultidoku avec les dimensions spécifiées et la correspondance des couleurs.
     * @param width La largeur de la grille.
     * @param height La hauteur de la grille.
     * @param blockWidth La largeur de chaque bloc.
     * @param blockHeight La hauteur de chaque bloc.
     * @param colorMapping La correspondance des couleurs.
     */
    public ColorMultidoku(int width, int height, int blockWidth, int blockHeight, Map<Integer, String> colorMapping) {
        this.grid = new Grid(width, height, blockWidth, blockHeight);
        this.colorMapping = colorMapping;
        this.colorBlocks = new HashMap<>();

        for (String color : colorMapping.values()) {
            colorBlocks.put(color, new HashSet<>());
        }

        assignColorsToBlocks();
    }

    /**
     * Assigne des couleurs aux blocs de la grille.
     */
    private void assignColorsToBlocks() {
        Random rand = new Random();
        List<Coordinate> allCoordinates = new ArrayList<>();

        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                allCoordinates.add(new Coordinate(x, y));
            }
        }

        Collections.shuffle(allCoordinates, rand);

        int colorIndex = 1;
        for (Coordinate coord : allCoordinates) {
            String color = colorMapping.get(colorIndex);
            colorBlocks.get(color).add(coord);
            colorIndex = (colorIndex % colorMapping.size()) + 1;
        }
    }

    public void setElement(int x, int y, int value) {
        grid.setElement(x, y, value);
    }

    public int getElement(int x, int y) {
        return grid.getElement(x, y);
    }

    /**
     * Vérifie si la valeur spécifiée est valide aux coordonnées données.
     * @param x La coordonnée x.
     * @param y La coordonnée y.
     * @param value La valeur à vérifier.
     * @return True si la valeur est valide, false sinon.
     */
    public boolean isValid(int x, int y, int value) {
        String color = getColorAt(x, y);

        for (int i = 0; i < grid.getWidth(); i++) {
            if (grid.getElement(i, y) == value || grid.getElement(x, i) == value) {
                return false;
            }
        }

        for (Coordinate coord : colorBlocks.get(color)) {
            if (grid.getElement(coord.x, coord.y) == value) {
                return false;
            }
        }

        return true;
    }

    /**
     * Obtient la couleur à la position spécifiée.
     * @param x La coordonnée x.
     * @param y La coordonnée y.
     * @return La couleur à la position spécifiée.
     */
    public String getColorAt(int x, int y) {
        for (Map.Entry<String, Set<Coordinate>> entry : colorBlocks.entrySet()) {
            if (entry.getValue().contains(new Coordinate(x, y))) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Grid getGrid() {
        return grid;
    }

    /**
     * Remplit les blocs de couleur avec des chiffres aléatoires.
     */
    public void fillColorBlocks() {
        for (String color : colorBlocks.keySet()) {
            List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            Collections.shuffle(numbers);
            int index = 0;
            for (Coordinate coord : colorBlocks.get(color)) {
                setElement(coord.x, coord.y, numbers.get(index++));
            }
        }
    }

    /**
     * La classe Coordinate représente une coordonnée dans la grille.
     */
    public static class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}