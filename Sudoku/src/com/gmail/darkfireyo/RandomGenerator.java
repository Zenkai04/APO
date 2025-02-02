package com.gmail.darkfireyo;

import java.util.Random;

/**
 * Classe générant des nombres aléatoires pour remplir une grille, héritée de la classe Generator.
 * La grille est remplie avec des nombres de manière à respecter les règles définies par la méthode isValid.
 */
public class RandomGenerator extends Generator {

    /**
     * Constructeur de la classe RandomGenerator.
     * 
     * @param grid La grille à remplir avec des nombres aléatoires.
     */
    public RandomGenerator(Grid grid) {
        super(grid);
    }

    /**
     * Méthode principale pour générer un nombre dans la grille.
     * Elle initialise un objet Random et appelle la méthode fillGrid pour remplir la grille.
     * 
     * @return true si la grille a été remplie correctement, false sinon.
     */
    @Override
    public boolean generateNumber() {
        Random rand = new Random(); // Création d'un générateur de nombres aléatoires
        return fillGrid(0, 0, rand); // Appel à la méthode pour remplir la grille en partant de la position (0, 0)
    }

    /**
     * Remplir la grille de manière récursive avec des nombres aléatoires.
     * Cette méthode parcours chaque élément de la grille et essaie de placer un nombre valide à chaque position.
     * 
     * @param x La coordonnée x de la grille où commencer à remplir.
     * @param y La coordonnée y de la grille où commencer à remplir.
     * @param rand L'objet Random pour générer des choix aléatoires.
     * @return true si la grille a été remplie correctement, false sinon.
     */
    @Override
    protected boolean fillGrid(int x, int y, Random rand) {
        // Si x atteint la largeur de la grille, on passe à la ligne suivante
        if (x == currGrid.getWidth()) {
            x = 0; // On revient à la première colonne
            y++; // On passe à la ligne suivante
            if (y == currGrid.getHeight()) { // Si on a parcouru toutes les lignes
                return true; // La grille est complètement remplie
            }
        }

        // Si l'élément à la position (x, y) est déjà rempli, on passe à la case suivante
        if (currGrid.getElement(x, y) != 0) {
            return fillGrid(x + 1, y, rand); // On passe à la colonne suivante
        }

        // Tableau des nombres possibles à insérer (de 1 à 9)
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Mélange aléatoire du tableau des nombres
        for (int i = 0; i < numbers.length; i++) {
            int j = rand.nextInt(numbers.length); // Choix d'un index aléatoire
            int temp = numbers[i]; // Échange de la valeur courante avec celle à l'index aléatoire
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        // Essai de placer un nombre dans la position (x, y)
        for (int num : numbers) {
            // Si le nombre est valide à cette position
            if (currGrid.isValid(x, y, num)) {
                currGrid.setElement(x, y, num); // On place le nombre dans la grille

                // On essaie de remplir le reste de la grille récursivement
                if (fillGrid(x + 1, y, rand)) {
                    return true; // Si la grille est remplie correctement, on retourne true
                }

                // Si cela échoue, on annule le placement du nombre et on essaie le suivant
                currGrid.setElement(x, y, 0);
            }
        }

        return false; // Si aucun nombre valide n'a pu être placé, on retourne false
    }
}
