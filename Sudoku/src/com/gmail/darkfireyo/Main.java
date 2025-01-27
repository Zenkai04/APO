package com.gmail.darkfireyo;

public class Main {

    public static void main(String[] args) {
        int taillex = 16;
        int tailley = 16;
        int testbordelique = (int) Math.sqrt(taillex);
        Grid grille = new Grid(taillex, tailley,testbordelique,testbordelique);

        Generator generateur = new Generator(grille);

        boolean test = generateur.generateNumber();
        generateur.deleteNumbers(5);

        System.out.println(grille);


    }

}