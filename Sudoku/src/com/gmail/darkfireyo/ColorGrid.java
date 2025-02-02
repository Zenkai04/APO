package com.gmail.darkfireyo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Classe ColorGrid représentant une grille contenant des couleurs ANSI.
 * Cette classe hérite de Grid et permet d'attribuer des couleurs à chaque valeur dans la grille.
 */
public class ColorGrid extends Grid {
    private Map<String, String> ansiColorMap; // Map associant les noms des couleurs à leurs codes ANSI.
    private Map<Integer, Set<String>> colorAssignments; // Map associant chaque valeur de la grille aux couleurs assignées.

    /**
     * Constructeur de la classe ColorGrid.
     * 
     * @param width       Largeur de la grille
     * @param height      Hauteur de la grille
     * @param blockWidth  Largeur d'un bloc
     * @param blockHeight Hauteur d'un bloc
     */
    public ColorGrid(int width, int height, int blockWidth, int blockHeight) {
        super(width, height, blockWidth, blockHeight);
        this.ansiColorMap = new HashMap<>();
        this.colorAssignments = new HashMap<>();
        initializeAnsiColors();
    }

    /**
     * Initialise la map des couleurs ANSI avec leurs codes correspondants.
     */
    private void initializeAnsiColors() {
        ansiColorMap.put("red", "\u001B[31m");
        ansiColorMap.put("blue", "\u001B[34m");
        ansiColorMap.put("green", "\u001B[32m");
        ansiColorMap.put("yellow", "\u001B[33m");
        ansiColorMap.put("orange", "\u001B[38;5;214m"); 
        ansiColorMap.put("purple", "\u001B[35m");
        ansiColorMap.put("cyan", "\u001B[36m");
        ansiColorMap.put("magenta", "\u001B[35m");
        ansiColorMap.put("black", "\u001B[30m");
        ansiColorMap.put("reset", "\u001B[0m");
    }

    /**
     * Attribue des couleurs aux valeurs présentes dans la grille.
     */
    public void assignColors() {
        String[] colors = {"red", "blue", "green", "yellow", "orange", "purple", "cyan", "magenta", "black"};
        for (int num = 1; num <= 9; num++) {
            colorAssignments.put(num, new HashSet<>());
        }

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                int value = getElement(i, j);
                String color;
                do {
                    color = colors[(int) (Math.random() * colors.length)];
                } while (colorAssignments.get(value).contains(color));
                colorAssignments.get(value).add(color);
                setColor(i, j, color);
            }
        }
    }

    /**
     * Définit une couleur pour une position donnée dans la grille.
     *
     * @param x     Coordonnée X dans la grille
     * @param y     Coordonnée Y dans la grille
     * @param color Nom de la couleur à attribuer
     */
    private void setColor(int x, int y, String color) {
        ansiColorMap.put(getPositionKey(x, y), color);
    }

    /**
     * Génère une clé unique pour identifier une position dans la grille.
     *
     * @param x Coordonnée X
     * @param y Coordonnée Y
     * @return Clé sous forme de chaîne de caractères
     */
    private String getPositionKey(int x, int y) {
        return x + "," + y;
    }

    /**
     * Récupère la couleur attribuée à une position donnée.
     *
     * @param x Coordonnée X
     * @param y Coordonnée Y
     * @return Code ANSI de la couleur attribuée, ou reset si non défini
     */
    public String getColor(int x, int y) {
        return ansiColorMap.getOrDefault(getPositionKey(x, y), ansiColorMap.get("reset"));
    }

    /**
     * Retourne une représentation textuelle de la grille avec les couleurs appliquées.
     *
     * @return Représentation en chaîne de la grille colorée
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int y = 0; y < getHeight(); y++) {
            StringBuilder ligne = new StringBuilder();
            for (int i = 0; i < getWidth(); i++) {
                int value = this.getElement(i, y);
                String color = getColor(i, y);
                String ansiColor = ansiColorMap.getOrDefault(color, ansiColorMap.get("reset"));
                ligne.append(ansiColor).append(value).append(ansiColorMap.get("reset"));

                if (i < getWidth() - 1) {
                    ligne.append(" ");
                }
            }
            result.append(ligne.toString()).append(System.lineSeparator());
        }
        return result.toString();
    }
}
