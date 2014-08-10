/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Piirtologiikka;
import com.mycompany.tiralabra_maven.algoritmi.Simulaatio;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymän ikkunan ja siihen liitettävät komponentit tekevä luokka.
 * @author mikko
 */
public class Kayttoliittyma implements Runnable{
    private JFrame frame;
    private Simulaatio simulaatio;
    private Piirtologiikka piirtologiikka;
    private int sivunPituus;
    private Piirtoalusta piirtoalusta;

    public Kayttoliittyma(Simulaatio simulaatio, Piirtologiikka piirtologiikka, int sivunPituus) {
        this.simulaatio = simulaatio;
        this.piirtologiikka = piirtologiikka;
        this.sivunPituus = sivunPituus;
    }

    public Piirtoalusta getPiirtoalusta() {
        return this.piirtoalusta;
    }

    @Override
    public void run() {
        frame = new JFrame("Reittialgoritmit");
        luoKomponentit(frame.getContentPane());
        piirtoalusta.setPreferredSize(new Dimension(640, 480));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void luoKomponentit(Container contentPane) {
        //Luodaan ensin piirtoalusta, lisätään se container-olioon
        //Sitten luodaan näppäimistönkuuntelija ja annetaan se framelle
        piirtoalusta = new Piirtoalusta(piirtologiikka, sivunPituus);
        HiirenKuuntelija hiirenkuuntelija = new HiirenKuuntelija(sivunPituus, piirtologiikka);
        piirtoalusta.addMouseListener(hiirenkuuntelija);
        piirtoalusta.addMouseMotionListener(hiirenkuuntelija);
        contentPane.add(piirtoalusta);
    }
    
}
