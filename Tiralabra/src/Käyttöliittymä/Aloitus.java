/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Käyttöliittymä;

import Algoritmi.Reitinhaku;
import Algoritmi.Verkko;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Jaakko
 * 
 * Aloitus on pääluokka joka luo ikkunan jossa olevaan kuvaan voi piirtää.
 */
public class Aloitus {

    static private JFrame käyttis;
    

    public static void main(String[] args) throws IOException, URISyntaxException {
        
        käyttis=new JFrame();

        
        käyttis.setVisible(true);

  
 
        luoKomponentit();
           
        käyttis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        käyttis.setVisible(true);
        käyttis.pack();
        käyttis.setTitle("Title");


    }

    /**
    *
    * Luo ikkunan ja siinä olevan napin jota painamalla voi aloittaa reitinhaun.
    * 
    * 
    * 
    */  
    
    public static void luoKomponentit() throws IOException, URISyntaxException {
 
        

        final Kuva ikkuna = new Kuva();

        

        JButton AloitaNappi = new JButton("PIIRRETÄÄN!!!");
        AloitaNappi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    Verkko verkko = new Verkko(ikkuna.kuva, ikkuna.haeMaali());
                
                    Reitinhaku reitti = new Reitinhaku(verkko, ikkuna.haeLahto());
                    reitti.Haku();
                    
                    
                }
            });

        
        käyttis.add(ikkuna);
        käyttis.add(AloitaNappi, BorderLayout.PAGE_END);
        käyttis.setSize(new Dimension(900, 900));
    }
    
    

    
    
}
