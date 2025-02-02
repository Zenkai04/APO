package com.gmail.darkfireyo;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe représentant un bloc de cellules aléatoires, héritée de la classe Block.
 * Un bloc contient un ensemble de cellules et offre des fonctionnalités pour manipuler cet ensemble.
 */
public class RandomBlock extends Block {
    // Ensemble contenant les cellules du bloc
    private Set<Cell> cellSet;

    /**
     * Constructeur de la classe RandomBlock.
     * Initialise un bloc de taille spécifiée et crée un ensemble vide de cellules.
     * 
     * @param width La largeur du bloc.
     * @param height La hauteur du bloc.
     */
    public RandomBlock(int width, int height) {
        super(width, height); // Appel du constructeur parent
        this.cellSet = new HashSet<>(); // Initialisation de l'ensemble de cellules
    }

    /**
     * Ajoute une cellule à l'ensemble de cellules du bloc à la position spécifiée.
     * 
     * @param x La coordonnée x de la cellule à ajouter.
     * @param y La coordonnée y de la cellule à ajouter.
     */
    @Override
    public void addCell(int x, int y) {
        // Création d'une nouvelle cellule et ajout à l'ensemble
        Cell cell = new Cell(x, y);
        cellSet.add(cell);
    }

    /**
     * Récupère l'ensemble des cellules du bloc.
     * 
     * @return Un ensemble de cellules représentant toutes les cellules du bloc.
     */
    @Override
    public Set<Cell> getCells() {
        return cellSet; // Retourne l'ensemble des cellules
    }

    /**
     * Vérifie si le bloc est complet. Un bloc est complet si le nombre de cellules est exactement égal à 9.
     * 
     * @return true si le bloc contient exactement 9 cellules, false sinon.
     */
    public boolean isComplete() {
        return cellSet.size() == 9; // Le bloc est complet s'il contient 9 cellules
    }

    /**
     * Vérifie si une cellule à la position spécifiée existe dans le bloc.
     * 
     * @param x La coordonnée x de la cellule à vérifier.
     * @param y La coordonnée y de la cellule à vérifier.
     * @return true si la cellule est présente dans l'ensemble, false sinon.
     */
    public boolean containsCell(int x, int y) {
        return cellSet.contains(new Cell(x, y)); // Vérifie si la cellule existe dans l'ensemble
    }

    /**
     * Efface toutes les cellules du bloc.
     * Cette méthode vide l'ensemble de cellules.
     */
    public void clear() {
        cellSet.clear(); // Vide l'ensemble de cellules
    }
}
