package com.gmail.darkfireyo;

public class Main {

    public static void main(String[] args) {
        int taillex = 9;
        int tailley = 9;
        Grid grille = new Grid(taillex, tailley);

        Generator generateur = new Generator(grille);

        boolean test = generateur.generateNumber();
        generateur.deleteNumbers(5);

        System.out.println(grille);


    }

}