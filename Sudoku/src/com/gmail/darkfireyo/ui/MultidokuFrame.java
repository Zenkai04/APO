package com.gmail.darkfireyo.ui;

import javax.swing.*;
import java.awt.*;
import com.gmail.darkfireyo.Grid;

public class MultidokuFrame extends JFrame {
    private JButton firstShape;
    private JButton secondShape;
    private JButton colored;
    private JButton block;
    private MultidokuPanel multidokuPanel;

    public MultidokuFrame() {
        setTitle("Multidoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1280, 720);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Choisissez la forme du Multidoku");
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

    public JButton getFirstShape() {
        return firstShape;
    }

    public JButton getSecondShape() {
        return secondShape;
    }

    public JButton getBlock() {
        return block;
    }

    public JButton getColored() {
        return colored;
    }

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