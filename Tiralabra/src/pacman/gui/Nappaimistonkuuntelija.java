package pacman.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pacman.hahmot.Suunta;

/**
 * Pacmanin näppäimistönkuuntelija, joka muuttaa manin suuntaa pelaajan
 * näppäimistönpainallusten mukaisesti.
 *
 * @author hhkopper
 */
public class Nappaimistonkuuntelija implements KeyListener {

    /**
     * Käyttoliittymä, jonka kautta päästään käynnistämään uusipeli.
     */
    private Kayttoliittyma kayttis;

    /**
     * Kunstruktorissa asetetaan nappaimistonkuuntelijalle tarvittavat arvot.
     * 
     * @param kayttis Kayttoliittyma, jotta päästään käsiksi tarvittaviin tietoihin pelistä.
     */
    public Nappaimistonkuuntelija(Kayttoliittyma kayttis) {
        this.kayttis = kayttis;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Muutetaan manin suuntaa painamalla nuolinäppäimiä, tarkistetaan onko
     * uudessa suunnassa seinä.
     * Painamalla enter, kun peli on päättynyt saadaan käynnistettyä uusi peli.
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        Suunta vanhaSuunta = kayttis.getPeli().getMan().getSuunta();
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            kayttis.getPeli().getMan().setSuunta(Suunta.VASEN);
            tarkistaOnkoSuunnassaSeina(vanhaSuunta);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            kayttis.getPeli().getMan().setSuunta(Suunta.OIKEA);
            tarkistaOnkoSuunnassaSeina(vanhaSuunta);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            kayttis.getPeli().getMan().setSuunta(Suunta.YLOS);
            tarkistaOnkoSuunnassaSeina(vanhaSuunta);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            kayttis.getPeli().getMan().setSuunta(Suunta.ALAS);
            tarkistaOnkoSuunnassaSeina(vanhaSuunta);
        }

        if (!kayttis.getPeli().getJatkuu()) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                this.kayttis.uusiPeli();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Tarkistetaan onko uudessa suunnassa vastassa seinä, jos on muutetaan
     * suunnaksi vanha suunta, joka oli ennen kuin painettiin nuolinäppäintä.
     *
     * @param suunta kertoo vanhan suunnan
     */
    public void tarkistaOnkoSuunnassaSeina(Suunta suunta) {
        if (onkoSuunnassaSeina()) {
            kayttis.getPeli().getMan().setSuunta(suunta);
        }
    }

    /**
     * Katsotaan onko seuraava ruutu, johon man on liikkumassa, seinä. Jos on,
     * palautetaan true, jos ei, palautetaan false.
     * @return palautetaan boolean arvo.
     */
    public boolean onkoSuunnassaSeina() {
        Suunta suunta = kayttis.getPeli().getMan().getSuunta();
        int x = kayttis.getPeli().getMan().getX();
        int y = kayttis.getPeli().getMan().getY();

        if (kayttis.getPeli().getAlusta().getPeliruutu(x + suunta.getX(), y + suunta.getY()).getRuudunTyyppi() == 0) {
            return true;
        } else {
            return false;
        }
    }
}