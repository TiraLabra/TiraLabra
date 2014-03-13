/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.sanapuuro.gui;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * @author skaipio
 */
public class TimeLabel extends JLabel implements ActionListener{
    private String prefix = "TIME LEFT ";
    
    public TimeLabel(){
        super();
        this.setFont(GUISettings.getMediumFont());
        this.setForeground(GUISettings.getColorDefaultFont());
        this.setText("");
    }
    
    @Override
    public void setText(String text){
        if (text.isEmpty()){
            super.setText(prefix);
        }else{
            super.setText(prefix + text);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TimerWrapper timer = (TimerWrapper) e.getSource();
        String minutes = timer.getMinutes() + "";
        String seconds = timer.getSeconds() + "";
        if (timer.getMinutes() < 10){
            minutes = "0" + minutes;
        }
        if (timer.getSeconds() < 10){
            seconds = "0" + seconds;
        }
        this.setText(minutes + ":" + seconds);
    }
}
