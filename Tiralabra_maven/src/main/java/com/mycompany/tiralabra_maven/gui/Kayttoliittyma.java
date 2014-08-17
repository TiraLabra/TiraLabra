/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.algoritmi.Simulaatio;

/**
 * Käyttöliittymän ikkunan ja siihen liitettävät komponentit tekevä luokka.
 *
 * @author mikko
 */
public class Kayttoliittyma implements Runnable {

    private NewJFrame frame;
    private Simulaatio simulaatio;
    private int sivunPituus;
    private Piirtoalusta piirtoalusta;

    /**
     * Konstruktorissa annetaan sivun pituus ja simulaatio
     *
     * @param simulaatio
     * @param sivunPituus
     */
    public Kayttoliittyma(Simulaatio simulaatio, int sivunPituus) {
        this.simulaatio = simulaatio;
        this.sivunPituus = sivunPituus;
    }

    /**
     * Palauttaa käytössä olevan piirtoalustan. Jos piirtoalustaa ei ole vielä
     * luotu tai sitä ei muusta syystä ole, palauttaa null.
     *
     * @return piirtoalusta
     */
    public Piirtoalusta getPiirtoalusta() {
        //return this.piirtoalusta;
        if (frame == null) {
            return null;
        }
        return frame.getPiirtoalusta();
    }

    /**
     * Luo käyttöliittymän.
     */
    @Override
    public void run() {
        frame = new NewJFrame(simulaatio, sivunPituus);
        //frame = new JFrame("Reittialgoritmit");
        //luoKomponentit(frame.getContentPane());
        //piirtoalusta.setPreferredSize(new Dimension(640, 480));
        //frame.pack();
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //frame.setResizable(false);
    }

//    private void luoKomponentit(Container contentPane) {
//        //Luodaan ensin piirtoalusta, lisätään se container-olioon
//        //Sitten luodaan näppäimistönkuuntelija ja annetaan se framelle
//        piirtoalusta = new Piirtoalusta(piirtologiikka, sivunPituus);
//        HiirenKuuntelija hiirenkuuntelija = new HiirenKuuntelija(sivunPituus, piirtologiikka);
//        piirtoalusta.addMouseListener(hiirenkuuntelija);
//        piirtoalusta.addMouseMotionListener(hiirenkuuntelija);
//        contentPane.add(piirtoalusta);
//    }
}
