package pacman.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import pacman.peli.Highscore;
import pacman.peli.Pacman;

/**
 * Pacmanin käyttöliittymä
 * 
* @author hhkopper
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Piirtoalusta piirtoalusta;
    private Pacman peli;
    private Nappaimistonkuuntelija nappaimistonkuuntelija;
    private Highscore highscore;
    private boolean ikkuna;

    /**
     * Konstruktorissa annetaan kaikille tarvitteville muuttujille arvot.
     *
     * @param peli Pacman jota käsitellään.
     */
    public Kayttoliittyma(Pacman peli) {
        this.peli = peli;
        this.piirtoalusta = new Piirtoalusta(30, frame, this);
        this.nappaimistonkuuntelija = new Nappaimistonkuuntelija(this);
        this.highscore = new Highscore(new File("ennatykset"));
        this.ikkuna = false;
    }

    @Override
    public void run() {
        frame = new JFrame("Pacman");
        frame.setPreferredSize(new Dimension(722, 660)); // 38 ruudun sivu
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Luodaan komponentit frameen ja lisätään näppäimistönkuuntelija.
     *     
     * @param container komponentit lisätään.
     */
    private void luoKomponentit(Container container) {
        container.add(piirtoalusta);
        frame.addKeyListener(nappaimistonkuuntelija);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Paivitettava getPaivitettava() {
        return piirtoalusta;
    }

    /**
     * Luodaan ja käynnistetään uusi peli.
     */
    public void uusiPeli() {
        peli = new Pacman();
        peli.setPaivitettava(this.getPaivitettava());
        peli.start();
    }

    public Pacman getPeli() {
        return this.peli;
    }

    public Highscore getHighscore() {
        return this.highscore;
    }

    /**
     * Muodostaa uuden ikkunan, jossa kerrotaan parametrina saatu virheilmoitus.
     * Ok nappia painamalla peli sulkeutuu kokonaa.
     *
     * @param virhe String, jossa kerrotaan missä virhe on tapahtunut.
     */
    public void virheilmoitus(String virhe) {
        peli.stop();
        if (!ikkuna) {
            ikkuna = true;
            JFrame virheFrame = new JFrame("Virheilmoitus");
            virheFrame.setPreferredSize(new Dimension(300, 100));

            luoVirheenKomponentit(virheFrame.getContentPane(), virhe);

            virheFrame.pack();
            virheFrame.setVisible(true);
        }

    }

    /**
     * Luodaan komponentit virheilmoitus frameen.
     *
     * @param container Container, johon komponentit lisätään.
     * @param virhe String, jossa kerrotaan missä virhe on tapahtunut.
     */
    private void luoVirheenKomponentit(Container container, String virhe) {
        container.setLayout(new BorderLayout());
        JButton ok = new JButton("OK");
        JLabel teksti = new JLabel(virhe);
        container.add(teksti);
        container.add(ok, BorderLayout.SOUTH);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.dispatchEvent(new WindowEvent(frame, Event.WINDOW_DESTROY));
            }
        });

    }
}