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
                frame.updateMultidokuPanel(classicMultidoku.getGrid(0));
            }
        });

        frame.getSecondShape().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClassicMultidoku classicMultidoku = new ClassicMultidoku(5, 9, 9, 3, 3, "+");
                classicMultidoku.generateClassicMultidokuPlus();
                frame.updateMultidokuPanel(classicMultidoku.getGrid(0));
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

            generateur.deleteNumbers(2 *(9 * 9)/ 6);

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
                Set<ShapeBlock> shapeBlocks = generateRandomBlocks(9, 9, 3, 3);
                ShapeMultidoku shapeMultidoku = new ShapeMultidoku(9, 9, shapeBlocks);
                fillBlocks(shapeMultidoku, shapeBlocks);
                frame.updateMultidokuPanel(shapeMultidoku.getGrid());
            }
        });
    }

    /**
     * Génère des blocs de forme aléatoire pour le Multidoku.
     * @param width La largeur de la grille.
     * @param height La hauteur de la grille.
     * @param blockWidth La largeur de chaque bloc.
     * @param blockHeight La hauteur de chaque bloc.
     * @return Un ensemble de blocs de forme aléatoire.
     */
    private Set<ShapeBlock> generateRandomBlocks(int width, int height, int blockWidth, int blockHeight) {
        Set<ShapeBlock> blocks = new HashSet<>();
        Random rand = new Random();
        boolean[][] usedCells = new boolean[width][height];

        for (int i = 0; i < (width * height) / (blockWidth * blockHeight); i++) {
            ShapeBlock block = new ShapeBlock();
            while (block.getCells().size() < blockWidth * blockHeight) {
                int x = rand.nextInt(width);
                int y = rand.nextInt(height);
                if (!usedCells[x][y]) {
                    block.addCell(x, y);
                    usedCells[x][y] = true;
                }
            }
            blocks.add(block);
        }

        return blocks;
    }

    /**
     * Remplit les blocs de forme avec des chiffres aléatoires.
     * @param shapeMultidoku Le Multidoku de forme.
     * @param shapeBlocks Les blocs de forme.
     * @return True si les blocs ont été remplis avec succès, false sinon.
     */
    private boolean fillBlocks(ShapeMultidoku shapeMultidoku, Set<ShapeBlock> shapeBlocks) {
        for (ShapeBlock block : shapeBlocks) {
            List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            if (!fillBlock(shapeMultidoku, block, numbers, 0)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Remplit un bloc de forme avec des chiffres aléatoires.
     * @param shapeMultidoku Le Multidoku de forme.
     * @param block Le bloc de forme.
     * @param numbers La liste des chiffres à utiliser.
     * @param index L'index actuel dans la liste des chiffres.
     * @return True si le bloc a été rempli avec succès, false sinon.
     */
    private boolean fillBlock(ShapeMultidoku shapeMultidoku, ShapeBlock block, List<Integer> numbers, int index) {
        if (index >= numbers.size()) {
            return true;
        }

        Collections.shuffle(numbers);
        for (ShapeBlock.Coordinate coord : block.getCoordinates()) {
            int x = coord.x;
            int y = coord.y;
            if (shapeMultidoku.getElement(x, y) == 0) {
                int value = numbers.get(index);
                if (shapeMultidoku.isValid(x, y, value)) {
                    shapeMultidoku.setElement(x, y, value);
                    if (fillBlock(shapeMultidoku, block, numbers, index + 1)) {
                        return true;
                    }
                    shapeMultidoku.setElement(x, y, 0);
                }
            }
        }
        return false;
    }
}