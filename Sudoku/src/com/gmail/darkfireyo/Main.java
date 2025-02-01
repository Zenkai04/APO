package com.gmail.darkfireyo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez la largeur de la grille de Sudoku:");
        int taillex = scanner.nextInt();

        System.out.println("Entrez la hauteur de la grille de Sudoku:");
        int tailley = scanner.nextInt();

        System.out.println("Entrez la largeur des blocs:");
        int blockWidth = scanner.nextInt();

        System.out.println("Entrez la hauteur des blocs:");
        int blockHeight = scanner.nextInt();

        Grid grille = new Grid(taillex, tailley, blockWidth, blockHeight);

        Generator generateur = new Generator(grille);

        boolean test = generateur.generateNumber();
        generateur.deleteNumbers(20);

        System.out.println("Grille générée:");
        System.out.println(grille);

        Solver solver = new Solver();
        Grid solvedGrid = solver.solveSudoku(grille);

        if (solvedGrid != null) {
            System.out.println("Grille résolue:");
            System.out.println(solvedGrid);
        } else {
            System.out.println("La grille n'est pas solvable.");
        }

        scanner.close();
    }
}