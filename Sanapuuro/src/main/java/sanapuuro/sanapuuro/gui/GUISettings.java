/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.sanapuuro.gui;

import java.awt.Color;
import java.awt.Font;

/**
 * A class meant to be static. Holds GUI appearance settings.
 * @author skaipio
 */
public class GUISettings {

    private static final int hex = 16;
    private static final String LogicalFont = "SansSerif";
    private static Font mediumFont;
    private static Font largeFont;
    private static Color background;
    private static Color colorSelectedCell;
    private static Color colorBorder;
    private static Color gridCell;
    private static Color letterPoolCell;
    private static Color cursorEnabled;
    private static Color colorButtonHighlight2;
    private static Color colorLetterPoolButtonUsed;
    private static Color gridCellHover;
    private static Color colorSelectableCell;
    private static Color colorDefaultFont;
    
    private GUISettings(){}

    public static void initGUISettings() {
        mediumFont = new Font(LogicalFont, Font.PLAIN, 20);
        largeFont = new Font(LogicalFont, Font.PLAIN, 26);
        background = new Color(Integer.parseInt("b2d0f0", hex));
        colorSelectedCell = new Color(Integer.parseInt("d0e0f0", hex));
        colorBorder = new Color(Integer.parseInt("8f753f", hex));
        gridCell = new Color(Integer.parseInt("7097bf", hex));
        letterPoolCell = new Color(Integer.parseInt("d0e0f0", hex));      
        cursorEnabled = new Color(Integer.parseInt("fff1db", hex));      
        colorButtonHighlight2 = new Color(Integer.parseInt("b2d0f0", hex));
        colorLetterPoolButtonUsed = new Color(Integer.parseInt("303e60", hex));
        gridCellHover = new Color(Integer.parseInt("d0e0f0", hex));
        colorSelectableCell = new Color(Integer.parseInt("b2d0f0", hex));
        colorDefaultFont = new Color(Integer.parseInt("000000", hex));
    }

    public static Font getMediumFont() {
        return mediumFont;
    }
    
    public static Font getLargeFont() {
        return largeFont;
    }

    public static Color getColorBackground() {
        return background;
    }

    public static Color getColorSelectedCell() {
        return colorSelectedCell;
    }

    public static Color getColorBorder() {
        return colorBorder;
    }

    public static Color getColorGridCell() {
        return gridCell;
    }
    
    public static Color getColorLetterPoolCell() {
        return letterPoolCell;
    }

    public static Color getColorCursorEnabled() {
        return cursorEnabled;
    }

    public static Color getColorLetterPoolButtonUsed() {
        return colorLetterPoolButtonUsed;
    }
    
    public static Color getGridButtonHighlight() {
        return gridCellHover;
    }

    public static Color getColorButtonHighlight2() {
        return colorButtonHighlight2;
    }

    public static Color getColorSelectableCell() {
        return colorSelectableCell;
    }

    public static Color getColorDefaultFont() {
        return colorDefaultFont;
    }
}
