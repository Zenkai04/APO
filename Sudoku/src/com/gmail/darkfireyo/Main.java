package com.gmail.darkfireyo;

public class Main {

    public static void main(String[] args) {
        int taillex = 9;
        int tailley = 9;
        Grid grille = new Grid(taillex, tailley);

        Generator generateur = new Generator(grille);

        boolean test = generateur.generateNumber();

        for (int y = 0; y < taillex; y++) {
            StringBuilder ligne = new StringBuilder();

            for (int i = 0; i < tailley; i++) {
                ligne.append(grille.getElement(i, y));

                if (i < tailley - 1) {
                    ligne.append(" ");
                }
            }

            System.out.println(ligne.toString());
        }


    }

}
