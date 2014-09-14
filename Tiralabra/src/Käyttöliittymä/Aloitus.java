/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Käyttöliittymä;

import java.io.IOException;
import java.net.URISyntaxException;
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
        
        käyttis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        käyttis.setVisible(true);
        käyttis.pack();
        käyttis.setTitle("asdasd");
        
        Kuva ikkunan = new Kuva();
        
        käyttis.add(ikkunan);
        
        käyttis.setSize(ikkunan.kuva.getHeight()+30, ikkunan.kuva.getWidth()+30);

    }
    
}
