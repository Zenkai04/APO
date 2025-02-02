package com.gmail.darkfireyo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ColorGrid extends Grid {
    private Map<String, String> ansiColorMap; // Map to store ANSI codes for colors
    private Map<Integer, Set<String>> colorAssignments; // Map to track assigned colors for each number

    public ColorGrid(int width, int height, int blockWidth, int blockHeight) {
        super(width, height, blockWidth, blockHeight);
        this.ansiColorMap = new HashMap<>();
        this.colorAssignments = new HashMap<>();
        initializeAnsiColors();
    }

    private void initializeAnsiColors() {
        ansiColorMap.put("red","\u001B[31m");
        ansiColorMap.put("blue", "\u001B[34m");
        ansiColorMap.put("green", "\u001B[32m");
        ansiColorMap.put("yellow", "\u001B[33m");
        ansiColorMap.put("orange", "\u001B[38;5;214m"); // ANSI code for orange
        ansiColorMap.put("purple", "\u001B[35m");
        ansiColorMap.put("cyan", "\u001B[36m");
        ansiColorMap.put("magenta", "\u001B[35m");
        ansiColorMap.put("black", "\u001B[30m");
        ansiColorMap.put("reset", "\u001B[0m");
    }

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

    private void setColor(int x, int y, String color) {
        ansiColorMap.put(getPositionKey(x, y), color);
    }

    private String getPositionKey(int x, int y) {
        return x + "," + y;
    }

    public String getColor(int x, int y) {
        return ansiColorMap.getOrDefault(getPositionKey(x, y), ansiColorMap.get("reset"));
    }

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
            result.append(ligne.toString());
            result.append(System.lineSeparator());
        }
        return result.toString();
    }
}