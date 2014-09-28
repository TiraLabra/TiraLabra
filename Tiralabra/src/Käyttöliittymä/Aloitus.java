/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Käyttöliittymä;

import Algoritmi.Verkko;
import Algoritmi.Reitinhaku;
import java.awt.BorderLayout;
import java.awt.Container;
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
public class Aloitus extends JFrame {


    public static void main(String[] args) throws IOException, URISyntaxException {
        
        Aloitus käyttis = new Aloitus();
        

 
        luoKomponentit(käyttis);
//        käyttis.setSize(700, 700);        
        käyttis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        käyttis.setVisible(true);
        käyttis.pack();
        käyttis.setTitle("asdasd");


    }

    /**
    *
    * Luo ikkunan ja siinä olevan napin jota painamalla voi aloittaa reitinhaun.
    * 
    * @param grafiikka grafiikka
    * 
    */  
    
    public static void luoKomponentit(Container container) throws IOException, URISyntaxException {
 
        

        final Kuva ikkuna = new Kuva();
        
//        container.setSize(700, 700);

        JButton AloitaNappi = new JButton("PIIRRETÄÄN!!!");
        AloitaNappi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    Verkko verkko = new Verkko(ikkuna.kuva, ikkuna.haeMaali());
                
                    Reitinhaku reitti = new Reitinhaku(verkko, ikkuna.haeLahto());
                    reitti.Haku();
                    
                    
                }
            });

        
        container.add(ikkuna, BorderLayout.CENTER);
        container.add(AloitaNappi, BorderLayout.PAGE_END);
        
    }
    
    

    
    
}
