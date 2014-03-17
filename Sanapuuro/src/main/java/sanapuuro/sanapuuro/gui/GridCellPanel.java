/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 * 
 * @author skaipio
 */
public class GridCellPanel extends JLabel {

    private final int cellSize;
    public final int x, y;
    private boolean isSelected = false;
    private boolean showAsSelectable = false;
    private boolean cursorEnabled = false;

    public GridCellPanel(int x, int y, int cellSize) {
        this.cellSize = cellSize;
        this.x = x;
        this.y = y;
        this.setOpaque(true);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.setBackground(GUISettings.getColorGridCell());
        this.setPreferredSize(new Dimension(cellSize, cellSize));
        this.setFont(GUISettings.getMediumFont());
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public boolean isSelectable() {
        return this.showAsSelectable;
    }

    public void setLetter(String letter) {
        this.setText(letter);
    }

    public void removeLetter() {
        this.setText("");
    }

    public void hoverOn() {
        this.setBackground(GUISettings.getGridButtonHighlight());
    }

    public void hoverOff() {
        if (cursorEnabled) {
            this.setBackground(GUISettings.getColorCursorEnabled());
        } else if (this.showAsSelectable) {
            this.setBackground(GUISettings.getColorSelectableCell());
        } else if (isSelected) {
            this.setBackground(GUISettings.getColorSelectedCell());
        } else {
            this.setBackground(GUISettings.getColorGridCell());
        }
    }

    public void enableCursor(boolean enabled) {
        this.cursorEnabled = enabled;
        if (enabled) {
            this.setBackground(GUISettings.getColorCursorEnabled());
        } else {
            if (this.isSelected) {
                this.setBackground(GUISettings.getColorSelectedCell());
            } else {
                this.setBackground(GUISettings.getColorGridCell());
            }
        }
    }

    public void select() {
        this.isSelected = true;
        if (this.cursorEnabled) {
            this.setBackground(GUISettings.getColorCursorEnabled());
        } else {
            this.setBackground(GUISettings.getColorSelectedCell());
        }
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.repaint();
    }

    public void deselect() {
        this.isSelected = false;
        if (this.cursorEnabled) {
            this.setBackground(GUISettings.getColorCursorEnabled());
        } else {
            this.setBackground(GUISettings.getColorGridCell());
        }
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        this.repaint();
    }

    public void showAsSelectable(boolean show) {
        this.showAsSelectable = show;
        if (show) {
            this.setBackground(GUISettings.getColorSelectableCell());
        } else if (this.cursorEnabled) {
            this.setBackground(GUISettings.getColorCursorEnabled());
        } else if (this.isSelected) {
            this.setBackground(GUISettings.getColorSelectedCell());
        } else {
            this.setBackground(GUISettings.getColorGridCell());
        }
        this.repaint();
    }
}
