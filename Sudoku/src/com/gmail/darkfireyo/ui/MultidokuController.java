package com.gmail.darkfireyo.ui;

import com.gmail.darkfireyo.ClassicMultidoku;
import com.gmail.darkfireyo.ColorGenerator;
import com.gmail.darkfireyo.ColorGrid;
import com.gmail.darkfireyo.ColorMultidoku;
import com.gmail.darkfireyo.ColorSolver;
import com.gmail.darkfireyo.Generator;
import com.gmail.darkfireyo.Grid;
import com.gmail.darkfireyo.ShapeBlock;
import com.gmail.darkfireyo.ShapeMultidoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * La classe MultidokuController gère la logique du jeu Multidoku et interagit avec l'interface utilisateur.
 */
public class MultidokuController {
    private MultidokuFrame frame;
    private Grid grid;
    private Generator generator;

    /**
     * Construit un nouveau contrôleur Multidoku avec la fenêtre spécifiée.
     * @param frame La fenêtre Multidoku.
     */
    public MultidokuController(MultidokuFrame frame) {
        this.frame = frame;
        initializeGame();
    }

    /**
     * Initialise le jeu en configurant les écouteurs d'action pour les boutons de la fenêtre.
     */
    private void initializeGame() {
        frame.getFirstShape().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassicMultidoku classicMultidoku = new ClassicMultidoku(5, 9, 9, 3, 3, "X");
                classicMultidoku.generateClassicMultidokuX();

                List<Grid> grids = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    Grid grille = classicMultidoku.getGrid(i);
                    Generator generator = new Generator(grille);
                    generator.generateNumber();
                    grids.add(grille);
                }

                frame.updateMultidokuPanel(grids,"X");
                System.out.println("Grille générée:");
                System.out.println(grids);
            }
        });

        frame.getSecondShape().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassicMultidoku classicMultidoku = new ClassicMultidoku(5, 9, 9, 3, 3, "+");
                classicMultidoku.generateClassicMultidokuPlus();

                List<Grid> grids = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    Grid grille = classicMultidoku.getGrid(i);
                    Generator generator = new Generator(grille);
                    generator.generateNumber();
                    grids.add(grille);
                }

                frame.updateMultidokuPanel(grids,"+");
                System.out.println("Grille générée:");
                System.out.println(grids);
            }
        });

        frame.getColored().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ColorGrid grille = new ColorGrid(9, 9, 3, 3);

                ColorGenerator generateur = new ColorGenerator(grille);
                boolean test = generateur.generateNumber();

                if (test) {
                    grille.assignColors();
                    generateur.deleteNumbers(2 * (9 * 9) / 6);

                    System.out.println("Grille générée:");
                    System.out.println(grille);

                    ColorSolver solver = new ColorSolver();
                    Grid solvedGrid = solver.solveSudoku(grille);

                    frame.updateMultidokuPanel(solvedGrid);
                }
            }
        });

        frame.getBlock().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
          
        });
    }

}