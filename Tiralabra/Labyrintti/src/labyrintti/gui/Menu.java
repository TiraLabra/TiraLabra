/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Luokan tarkoituksena on luoda ikkuna (menu), johon käyttäjä syöttää haluamansa
 * labyrintin koon. Koko on rajattu [2-40] alueelle.
 *  
 * @author Mikael Parvamo
 */
public class Menu {

    private Scanner lukija;
    private JFrame frame;

    public Menu() {
        this.lukija = new Scanner(System.in);
    }
    
    /**
     * Metodi luo ikkunan, johon komponentit sijoitetaan.
     */

    public void run() {
        frame = new JFrame("Minesweeper");
        int leveys = 400;
        int korkeus = 150;

        frame.setPreferredSize(new Dimension(leveys, korkeus));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);

    }
    
    /**
     * Metodissa luodaan komponentit, jotka sisältyvät menuun.
     * 
     * @param container 
     */

    private void luoKomponentit(Container container) {
        GridLayout layout = new GridLayout(3, 1, 20, 20);
        container.setLayout(layout);
        
        JButton kaynnista = new JButton("Käynnistä");
        JLabel vastauskentta = new JLabel("Määrittele labyrintin koko [2-40] alla olevaan syötekenttään:");
        JTextField syotekentta = new JTextField("");
        
        MenuKomentojenKuuntelija kuuntelija = new MenuKomentojenKuuntelija(syotekentta, vastauskentta, kaynnista);
        
        kaynnista.addActionListener(kuuntelija);
        
        container.add(vastauskentta);
        container.add(syotekentta);
        container.add(kaynnista);



    }

    public JFrame getFrame() {
        return this.frame;
    }
}
