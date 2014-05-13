
package chess.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 * Tämä luokka kuuntelee move piece nappia.
 */
public class MovesListener implements ActionListener {
    private Gui gui;
    private JTextField startx;
    private JTextField starty;
    private JTextField endx;
    private JTextField endy;
    private int turn;
        
    /**
     * Luokan konstruktori.
     *
     * @param gui luokka, jota kuunellaan.
     * @param startx X - alkukoordinaatin tekstikentän sisältö.
     * @param starty Y - alkukoordinaatin tekstikentän sisältö.
     * @param endx X - loppukoordinaatin tekstikentän sisältö.
     * @param endy Y - loppukoordinaatin tekstikentän sisältö.
     * @param turn vuoro, jotta tiedetään kumman vuoro on kyseessä.
     */     
    public MovesListener(Gui gui, JTextField startx, JTextField endx, int turn) {
        this.gui = gui;
        this.startx = startx;
        this.starty = null;
        this.endx = endx;
        this.endy = null;
        this.turn = turn;
    }

    /**
     * Tämä metodi muuttaa JTextFieldin sisällöt sopivaksi nappulan siirtoa 
     * varten kun move piece nappia painetaan.
     *
     * @param ae Napinpainallus.
     */     
    @Override
    public void actionPerformed(ActionEvent ae) {
        setStartX();
        setEndX();
        
        if (turn % 2 == 0) {
            gui.moveBlack();
        } else {
            gui.moveWhite();       
        }

    }
    
    /**
     * Tämä metodi muuttaa start square teksikentän sisällön x alkukoordinaatiksi.
     */    
    private void setStartX() {
        String text = startx.getText();
        int sx = 99;
        
        if (text.startsWith("a") || text.startsWith("A")) {
            sx = 0;
        }
        
        if (text.startsWith("b") || text.startsWith("B")) {
            sx = 1;
        }   
        
        if (text.startsWith("c") || text.startsWith("C")) {
            sx = 2;
        }  
        
        if (text.startsWith("d") || text.startsWith("D")) {
            sx = 3;
        }  
        
        if (text.startsWith("e") || text.startsWith("E")) {
            sx = 4;
        }
        
        if (text.startsWith("f") || text.startsWith("F")) {
            sx = 5;
        } 
        
        if (text.startsWith("g") || text.startsWith("G")) {
            sx = 6;
        }  
        
        if (text.startsWith("h") || text.startsWith("H")) {
            sx = 7;
        } 
                        
        if (sx < 0 || sx > 7 || text.length() != 2) {
            gui.turns();
        }              
        
        gui.setSX(sx); 
        setStartY();
    }
    
    /**
     * Tämä metodi muuttaa start square teksikentän sisällön y loppukoordinaatiksi.
     */    
    private void setStartY() {
        int sy = 99;
        
        if (isValidNumber(startx.getText())) {
            String text2 = "" + startx.getText().charAt(1);
            sy = Integer.parseInt(text2); 
        }  else {
            gui.turns();
        }
        
        gui.setSY(8 - sy);         
    }
  

    /**
     * Tämä metodi muuttaa end square teksikentän sisällön x loppukoordinaatiksi.
     */     
    private void setEndX() {
        String text = endx.getText();        
        int ex = 99;  
        
        if (text.startsWith("a") || text.startsWith("A")) {
            ex = 0;
        }
        
        if (text.startsWith("b") || text.startsWith("B")) {
            ex = 1;
        }   
        
        if (text.startsWith("c") || text.startsWith("C")) {
            ex = 2;
        }  
        
        if (text.startsWith("d") || text.startsWith("D")) {
            ex = 3;
        }  
        
        if (text.startsWith("e") || text.startsWith("E")) {
            ex = 4;
        }
        
        if (text.startsWith("f") || text.startsWith("F")) {
            ex = 5;
        }  
        
        if (text.startsWith("g") || text.startsWith("G")) {
            ex = 6;
        } 
        
        if (text.startsWith("h") || text.startsWith("H")) {
            ex = 7;
        } 
                      
        if (ex < 0 || ex > 7 ||  text.length() != 2) {
            gui.turns();
        }    
                                        
        gui.setEX(ex);
        setEndY();
    }
    
    /**
     * Tämä metodi muuttaa end square teksikentän sisällön y loppukoordinaatiksi.
     */    
    private void setEndY() {
        int ey = 99;
        
        if (isValidNumber(endx.getText())) {
            String text2 = "" + endx.getText().charAt(1);
            ey = Integer.parseInt(text2); 
        } else {
            gui.turns();
        }  
        
        gui.setEY(8 - ey);         
    }        
    
     /**
     * Tämä metodi tarkistaa onko y - koordinaatti numero.
     * 
     * @param text teksikentän sisältö
     * @return palauttaa true jos koordinaatti on sopiva numero
     */    
    private boolean isValidNumber(String text) {
        if (text.endsWith("1") || text.endsWith("2") || text.endsWith("3") || text.endsWith("4") ||
                text.endsWith("5") || text.endsWith("6") || text.endsWith("7") || text.endsWith("8")) {
            return true;
        } else {
            return false;
        }
    }
}
