/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrintti.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import labyrintti.logiikka.LyhinReitti;
import labyrintti.logiikka.Maapala;
import labyrintti.logiikka.Maapalarekisteri;

/**
 * HiirenKuuntelija eli MouseListener, joka vastaa labyrintissa tehtävistä
 * komennoista.
 *
 * @author Mikael Parvamo
 */
public class HiirenKuuntelija implements MouseListener {

    private int koko;
    private Nappula[][] nappulat;
    private Maapala[][] maapalat;
    private JButton kaynnistys;
    private Maapalarekisteri maapalarekisteri;
    private int alkuja;
    private int loppuja;
    private LyhinReitti lyhinReitti;

    public HiirenKuuntelija(int koko, Nappula[][] nappulat, JButton kaynnistys, Maapalarekisteri maapalarekisteri, LyhinReitti lyhinReitti) {
        this.koko = koko;
        this.nappulat = nappulat;
        this.maapalarekisteri = maapalarekisteri;
        maapalarekisteri.luoMaapalat();
        this.maapalat = maapalarekisteri.getLabyrintti();
        this.kaynnistys = kaynnistys;
        kaynnistys.setEnabled(false);
        this.alkuja = 0;
        this.loppuja = 0;
        this.lyhinReitti = lyhinReitti;
    }

    /**
     * Tämä metodi kattaa hiiren kuuntelun, kun sitä klikataan kerran. Metodissa
     * käydään läpi, mikä komponentti kutsun tekee, ja millä hiiren näppäimellä.
     * Nämä kertovat, mikä toimenpide labyrintille pitää tehdä.
     *
     * @param MouseEvent e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == kaynnistys) {

            maapalarekisteri.alustaMaapalat();
            lyhinReitti = new LyhinReitti(maapalarekisteri);
            lyhinReitti.etsiLyhinReitti();

            if (lyhinReitti.onkoLoppuLoytynyt()) {
                naytaReitti();
            } else {
                eiReittia();
            }
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            seinanAlustus(e);

        } else if (e.getButton() == MouseEvent.BUTTON3) {
            alustaAlkuJaLoppu(e);
        }
    }

    /**
     * Metodi tarkistaa, että aloitus- ja lopetuspisteitä on vain yksi.
     *
     * @return boolean true/false
     */
    public boolean voikoKaynnistaa() {
        return (alkuja == 1 && loppuja == 1);
    }
    
    /**
     * Loppu on löytynyt, joten lyhin reitti näytetään värittämällä se vihreäksi
     * ja kaikki nappulat kytketään pois päältä.
     */

    public void naytaReitti() {
        Maapala maapala = maapalarekisteri.getLoppu().getVanhempi();

        while (true) {
            nappulat[maapala.getX()][maapala.getY()].setBackground(Color.GREEN);
            maapala = maapala.getVanhempi();
            if (maapala == null) {
                break;
            }
        }
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                nappulat[j][i].setEnabled(false);
                nappulat[j][i].avaa();
            }
        }
    }
    
    /**
     * Labyrintistä ei ole reittiä loppuun, joten palat väritetään harmaiksi
     * ja kytketään pois päältä.
     */

    public void eiReittia() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                nappulat[j][i].setEnabled(false);
                nappulat[j][i].avaa();
                if (!(maapalat[j][i].onkoSeina() || maapalat[j][i].equals(maapalarekisteri.getAlku()) || maapalat[j][i].equals(maapalarekisteri.getLoppu()))) {
                    nappulat[j][i].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }
    
    /**
     * Nappulaa on painettu hiiren vasemmalla.
     * Jos nappulan koordinaateissa ei ole aloitus- tai loppupistettä, asetetaan
     * nappulan koordinaateissa oleva maapala seinäksi.
     * 
     * @param MouseEvent e 
     */

    public void seinanAlustus(MouseEvent e) {
        Nappula nappula = (Nappula) e.getSource();
        int x = nappula.getXKoordinaatti();
        int y = nappula.getYKoordinaatti();
        if (maapalat[x][y].equals(maapalarekisteri.getAlku()) || maapalat[x][y].equals(maapalarekisteri.getLoppu())) {
        } else if (!nappula.getAvattu()) {
            if (!maapalat[nappula.getXKoordinaatti()][nappula.getYKoordinaatti()].onkoSeina()) {
                maapalat[nappula.getXKoordinaatti()][nappula.getYKoordinaatti()].asetaSeinaksi();
                nappula.setBackground(Color.BLACK);
            } else {
                maapalat[nappula.getXKoordinaatti()][nappula.getYKoordinaatti()].asetaLapaistavaksi();
                nappula.setBackground(null);
            }
        }
    }
    /**
     * Nappulaa on painettu hiiren oikealla näppäimellä.
     * Metodi asettaa maapalasta alun tai lopun, riippuen nappulan toteuttamista ehdoista.
     * 
     * @param MouseEvent e 
     */

    public void alustaAlkuJaLoppu(MouseEvent e) {
        Nappula nappula = (Nappula) e.getSource();
        int x = nappula.getXKoordinaatti();
        int y = nappula.getYKoordinaatti();

        if (!nappula.getAvattu()) {
            if (!(maapalat[x][y].onkoSeina()) && alkuja == 0 && nappula.getBackground() != Color.red) {  // maapala ei ole seinä eikä loppu eikä alkua ole vielä alustettu, joten
                maapalarekisteri.setAlkuX(nappula.getXKoordinaatti());                                   // alku alustetaan
                maapalarekisteri.setAlkuY(nappula.getYKoordinaatti());
                nappula.setBackground(Color.green);
                alkuja++;
                kaynnistys.setEnabled(voikoKaynnistaa());
            } else if (nappula.getBackground() == Color.GREEN) {               //nappula on jo asetettu seinäksi, joten aloituspala alustetaan
                nappula.setBackground(null);
                alkuja--;
                kaynnistys.setEnabled(voikoKaynnistaa());
            } else if (!(maapalat[x][y].onkoSeina()) && loppuja == 0 && alkuja == 1) {       //maapala ei ole seinä, aloituspala on jo määritelty ja
                maapalarekisteri.setLoppuX(nappula.getXKoordinaatti());                      //loppupistettä ei ole vielä määritelty
                maapalarekisteri.setLoppuY(nappula.getYKoordinaatti());       
                nappula.setBackground(Color.red);
                loppuja++;
                kaynnistys.setEnabled(voikoKaynnistaa());
            } else if (nappula.getBackground() == Color.red) {                //maapala on jo alustettu lopetuspisteeksi, joten lopetus alustetaan
                nappula.setBackground(null);
                loppuja--;
                kaynnistys.setEnabled(voikoKaynnistaa());
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
