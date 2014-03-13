/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author skaipio
 */
public class LetterGridPanel extends JPanel {

    private final int rows, columns;
    private final int cellSize = 40;
    private final GridCellPanel[][] cells;

    public LetterGridPanel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = new GridCellPanel[rows][columns];
        this.setLayout(new GridBagLayout());
        this.setBackground(GUISettings.getColorBackground());
        this.initCells();
        this.setPreferredSize(new Dimension(columns * cellSize, rows * cellSize));
        System.out.println(this.getSize());

    }

    private void initCells() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1d;
        constraints.weighty = 1d;
        constraints.fill = GridBagConstraints.BOTH;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < rows; x++) {
                GridCellPanel cell = new GridCellPanel(x, y, this.cellSize);
                this.cells[y][x] = cell;
                constraints.gridx = x;
                constraints.gridy = y;

                this.add(cell, constraints);
            }
        }
    }
    
    public GridCellPanel getCellAt(int x, int y){
        return this.cells[y][x];
    }

    public void setAllUnfocusable() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < rows; x++) {
                this.cells[y][x].setFocusable(false);
            }
        }
    }

    public void addListenerToCells(MouseListener inputHandler) {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < rows; x++) {
                this.cells[y][x].addMouseListener(inputHandler);
            }
        }
    }

    public void setCellSelectionAt(boolean selected, int x, int y) {
        if (selected) {
            this.cells[y][x].select();
        } else {
            this.cells[y][x].deselect();
        }

    }

    public void setLetterToCell(String letter, int x, int y) {
        this.cells[y][x].setLetter(letter);
    }

    public void removeLetterFromCell(int x, int y) {
        this.cells[y][x].removeLetter();
    }
}
