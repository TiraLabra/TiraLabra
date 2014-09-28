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
 * HiirenKuuntelija eli MouseListener, joka vastaa labyrintissa
 * tehtävistä komennoista.
 *
 * @author Mikael Parvamo
 */
public class HiirenKuuntelija implements MouseListener {

    private int koko;
    private Nappula[][] nappulat;
    private Maapala[][] maapalat;
    private JButton kaynnistys;
    private boolean alkuAsetettu;
    private boolean loppuAsetettu;
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
        this.alkuAsetettu = false;
        this.loppuAsetettu = false;
        this.alkuja = 0;
        this.loppuja = 0;
        this.lyhinReitti = lyhinReitti;
    }
    /**
     * Tämä metodi kattaa hiiren kuuntelun, kun sitä klikataan kerran.
     * Metodissa käydään läpi, mikä komponentti kutsun tekee, ja millä hiiren näppäimellä.
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
                for (int i = 0; i < koko; i++) {
                    for (int j = 0; j < koko; j++) {
                        nappulat[j][i].setEnabled(false);
                    }
                }
                Maapala maapala = maapalarekisteri.getLoppu();

                while (true) {
                    nappulat[maapala.getX()][maapala.getY()].setBackground(Color.GREEN);
                    maapala = maapala.getVanhempi();
                    if (maapala == null) {
                        break;
                    }
                }
            } else {
                for (int i = 0; i < koko; i++) {
                    for (int j = 0; j < koko; j++) {
                        nappulat[j][i].setEnabled(false);
                        if (!maapalat[j][i].onkoSeina()) {
                            nappulat[j][i].setBackground(Color.LIGHT_GRAY);
                        }
                    }
                }
            }
        }
        if (e.getSource() != kaynnistys && e.getButton() == MouseEvent.BUTTON1) {
            Nappula nappula = (Nappula) e.getSource();
            if (!maapalat[nappula.getXKoordinaatti()][nappula.getYKoordinaatti()].onkoSeina()) {
                maapalat[nappula.getXKoordinaatti()][nappula.getYKoordinaatti()].asetaSeinaksi();
                nappula.setBackground(Color.BLACK);
            } else {
                maapalat[nappula.getXKoordinaatti()][nappula.getYKoordinaatti()].asetaLapaistavaksi();
                nappula.setBackground(null);
            }

        }
        if (e.getSource() != kaynnistys && e.getButton() == MouseEvent.BUTTON3) {
            Nappula nappula = (Nappula) e.getSource();
            if (nappula.getText().isEmpty() && !alkuAsetettu) {
                maapalarekisteri.setAlkuX(nappula.getXKoordinaatti());
                maapalarekisteri.setAlkuY(nappula.getYKoordinaatti());
                nappula.setText("A");
                alkuja++;
                this.alkuAsetettu = true;
                kaynnistys.setEnabled(voikoKaynnistaa());
            } else if (nappula.getText().equals("A")) {
                nappula.setText("");
                alkuAsetettu = false;
                alkuja--;
                kaynnistys.setEnabled(voikoKaynnistaa());
            } else if (nappula.getText().isEmpty() && alkuAsetettu && !loppuAsetettu) {
                maapalarekisteri.setLoppuX(nappula.getXKoordinaatti());
                maapalarekisteri.setLoppuY(nappula.getYKoordinaatti());
                nappula.setText("L");
                loppuja++;
                this.loppuAsetettu = true;
                kaynnistys.setEnabled(voikoKaynnistaa());
            } else if (nappula.getText().equals("L")) {
                nappula.setText("");
                loppuAsetettu = false;
                loppuja--;
                kaynnistys.setEnabled(voikoKaynnistaa());
            }




        }

    }
    
    /**
     * Metodi tarkistaa, että aloitus ja lopetus pisteitä on vain yksi.
     * @return boolean true/false
     */

    public boolean voikoKaynnistaa() {
        if (alkuja == 1 && loppuja == 1) {
            return true;
        }
        return false;
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
