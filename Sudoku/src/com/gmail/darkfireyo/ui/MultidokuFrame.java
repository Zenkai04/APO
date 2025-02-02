package com.gmail.darkfireyo.ui;

import javax.swing.*;
import java.awt.*;
import com.gmail.darkfireyo.Grid;

/**
 * Fenêtre principale de l'application Multidoku.
 * Cette classe définit une interface utilisateur avec des boutons
 * pour choisir différentes variantes du jeu Multidoku.
 */
public class MultidokuFrame extends JFrame {
    private JButton firstShape;
    private JButton secondShape;
    private JButton colored;
    private JButton block;
    private MultidokuPanel multidokuPanel;

    /**
     * Constructeur de la fenêtre Multidoku.
     * Initialise les composants graphiques et leur disposition.
     */
    public MultidokuFrame() {
        setTitle("Multidoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1280, 720);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Choisissez la forme du Multidoku / Sudoku Spécial");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        firstShape = new JButton("Forme en X");
        buttonPanel.add(firstShape);

        secondShape = new JButton("Forme en +");
        buttonPanel.add(secondShape);

        colored = new JButton("Multidoku coloré");
        buttonPanel.add(colored);

        block = new JButton("Multidoku");
        buttonPanel.add(block);

        inputPanel.add(buttonPanel);
        add(inputPanel, BorderLayout.NORTH);
    }

    /**
     * Retourne le bouton correspondant à la forme en X.
     * @return JButton pour la forme en X
     */
    public JButton getFirstShape() {
        return firstShape;
    }

    /**
     * Retourne le bouton correspondant à la forme en +.
     * @return JButton pour la forme en +
     */
    public JButton getSecondShape() {
        return secondShape;
    }

    /**
     * Retourne le bouton pour le Multidoku classique.
     * @return JButton pour le Multidoku classique
     */
    public JButton getBlock() {
        return block;
    }

    /**
     * Retourne le bouton pour le Multidoku coloré.
     * @return JButton pour le Multidoku coloré
     */
    public JButton getColored() {
        return colored;
    }

    /**
     * Met à jour le panneau Multidoku avec une nouvelle grille.
     * @param grid Objet Grid représentant la grille à afficher.
     */
    public void updateMultidokuPanel(Grid grid) {
        if (multidokuPanel != null) {
            remove(multidokuPanel);
        }
        multidokuPanel = new MultidokuPanel(grid.getWidth(), grid.getHeight());
        multidokuPanel.setPreferredSize(new Dimension(400, 400));
        multidokuPanel.updateGrid(grid);
        add(multidokuPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
