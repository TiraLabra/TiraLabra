/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import javax.swing.JLabel;

/**
 *
 * @author skaipio
 */
public class SelectedLettersLabel extends JLabel{
    public SelectedLettersLabel(){
        super();
        this.setFont(GUISettings.getMediumFont());
        this.setText("");
    }
    
    @Override
    public void setText(String text){
        super.setText(text.toUpperCase());
    }
}
