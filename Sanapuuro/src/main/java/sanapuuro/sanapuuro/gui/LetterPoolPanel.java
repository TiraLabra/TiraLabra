/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author skaipio
 */
public class LetterPoolPanel extends JPanel {
    private final LetterPoolCellButton[] letterCells;
    private LetterPoolCellButton currentHoverOn;
    
    public LetterPoolPanel(){
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 0,0));
        this.setBackground(GUISettings.getColorBackground());
        this.letterCells = new LetterPoolCellButton[8];
        for(int i = 0; i < letterCells.length; i++){
            LetterPoolCellButton cell = new LetterPoolCellButton(i);
            this.letterCells[i] = cell;
            this.add(cell);           
        }
    }
    
    public void init(int size){
        this.currentHoverOn = this.letterCells[0];
        this.setHoverTo(0);
        this.repaint();
    }
    
    public void addListenerToCells(ActionListener listener){
        for (LetterPoolCellButton letterCell : letterCells) {
            letterCell.addActionListener(listener);
        }
    }   
    
    public void setLetterToCell(String letter, int i){
        this.letterCells[i].setLetter(letter);
        this.letterCells[i].setInUse(false);
    }
    
    public void setContainerAsUsed(int i, boolean enabled){
        this.letterCells[i].setInUse(enabled);
    }
    
    public void setHoverTo(int i){
        this.currentHoverOn.hover(false);
        this.currentHoverOn = this.letterCells[i];
        this.currentHoverOn.hover(true);
    }
}
