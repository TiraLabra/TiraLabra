/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import labyrintti.logiikka.LyhinReitti;
import labyrintti.logiikka.Maapala;
import labyrintti.logiikka.Maapalarekisteri;

/**
 * Labyrintin graafinen käyttöliittymä
 * @author Mikael Parvamo
 */
public class LabyrinthGUI {

    private int koko;
    private JFrame frame;
    private Maapalarekisteri maapalarekisteri;
    private LyhinReitti lyhinReitti;
    private Nappula[][] nappulat;

    public LabyrinthGUI(int koko) {
        this.koko = koko;
        this.nappulat = new Nappula[koko][koko];
        this.maapalarekisteri = new Maapalarekisteri(koko,0,0,0,0);
    }
    
    /**
     * Metodi luo ikkunan, jonka sisältämät komponentit luodaan
     * kutsumalla luoKomponentit- metodia
     */

    public void run() {
        frame = new JFrame("Labyrinth");

        int leveys = 1000;
        int korkeus = 1000;

        frame.setPreferredSize(new Dimension(leveys, korkeus));

        luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);

    }
    /**
     * Metodi asettaa ikkunalle ja sen elementeille tietyt layoutit, jotka määräävät,
     * miten komponentit ikkunalle asettuvat. Samalla luodaan pelin "nappulat", ja
     * kiinnitetään niille hiirenKuuntelija(MouseListener).
     * 
     * @param container 
     */

    public void luoKomponentit(Container container) {
        BorderLayout layout = new BorderLayout();
        container.setLayout(layout);

        JLabel ohjeet = new JLabel("Aseta labyrintin seinät hiiren vasemmalla, sekä aloitus- ja päätepiste hiiren oikealla.");
        JButton kaynnistys = new JButton("Käynnistä");

        JPanel instructions = new JPanel(new GridLayout(1, 2));
        instructions.add(ohjeet);
        instructions.add(kaynnistys);

        for (int i = 0; i < this.koko; i++) {
            for (int j = 0; j < this.koko; j++) {
                Nappula nappula = new Nappula(j, i);
                nappulat[j][i] = nappula;
            }
        }
        JPanel nappuloita = new JPanel(new GridLayout(koko, koko));

        HiirenKuuntelija kuuntelija = new HiirenKuuntelija(koko, nappulat, kaynnistys, maapalarekisteri, lyhinReitti);
        kaynnistys.addMouseListener(kuuntelija);

        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                nappuloita.add(nappulat[j][i]);
                nappulat[j][i].addMouseListener(kuuntelija);
            }
        }
        container.add(instructions, BorderLayout.NORTH);
        container.add(nappuloita, BorderLayout.CENTER);
    }
}
