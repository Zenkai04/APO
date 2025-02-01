package com.gmail.darkfireyo;

import java.util.HashSet;
import java.util.Set;

public class ShapeBlock {
    private Set<Cell> cells;

    public ShapeBlock() {
        this.cells = new HashSet<>();
    }

    public void addCell(int x, int y) {
        cells.add(new Cell(x, y));
    }

    public Set<Cell> getCells() {
        return cells;
    }

    public boolean contains(int x, int y) {
        return cells.contains(new Cell(x, y));
    }

    public Set<Coordinate> getCoordinates() {
        Set<Coordinate> coordinates = new HashSet<>();
        for (Cell cell : cells) {
            coordinates.add(new Coordinate(cell.x, cell.y));
        }
        return coordinates;
    }

    public static class Cell {
        public final int x, y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }

    public static class Coordinate {
        public final int x, y;

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
            return 31 * x + y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}