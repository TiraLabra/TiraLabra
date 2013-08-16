/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kolmiopeli.UI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;

/**
 * KOKEILUA
 * @author Eemi
 */
public class KomboEtsijaAnimointiSatunnaisessaRuudukossa {

    private Peliruudukko peliruudukko;
    private int moneskoTimerToisto;
    private ArrayList<Koordinaatti> kombot;
    private final int korkeus;
    private final int leveys;
    private final ArrayList<Koordinaatti> tuhoutuvat;

    public KomboEtsijaAnimointiSatunnaisessaRuudukossa(Peliruudukko peliruudukko) {
        this.peliruudukko = peliruudukko;
        this.korkeus = peliruudukko.getPeliruudukko().length;
        this.leveys = peliruudukko.getPeliruudukko()[0].length;
        this.tuhoutuvat = new ArrayList<Koordinaatti>();
        taytaTuhoutuvat();
    }

    public void animoiKomboEtsinta() {


        // Saada tassa kuinka nopeasti animaatiot nakyvat
        final Timer timer = new Timer(2000, null);
        this.moneskoTimerToisto = 1;
        this.kombot = new ArrayList<Koordinaatti>();

        // Animaatio tapahtuu tassa metodissa timerin avustuksella, jatkuu kunnes komboja ei enaa loydy
        ActionListener teePiirto;
        teePiirto = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (moneskoTimerToisto == 1) {
                    kombot.clear();
                    kombot.addAll(peliruudukko.getPeliFrame().getKomboEtsija().etsiKombot(tuhoutuvat));

                    if (kombot.isEmpty()) {
                        System.out.println("KOMBOT LOPPUIVAT");
                        timer.stop();

                        // Paivitetaan pisteet ja siirrot
                        peliruudukko.getPeliFrame().getInfoPaneeli().paivita();

                        // Jos siirrot loppuvat, peli loppuu
                        if (peliruudukko.getPeliFrame().getInfoPaneeli().getSiirtoja() == 0) {
                            CardLayout paneelit = (CardLayout) peliruudukko.getPeliFrame().getNakymat().getLayout();
                            peliruudukko.getPeliFrame().getGameover().removeAll();
                            peliruudukko.getPeliFrame().getGameover().revalidate();
                            peliruudukko.getPeliFrame().getGameover().paivita();
                            paneelit.last(peliruudukko.getPeliFrame().getNakymat());
                        }

                    } else {
                        tuhoutuvat.clear();
                        tuhoutuvat.addAll(kombot);
                        peliruudukko.getSiirrot().getPisteenlaskija().lisaaTuhoutuneistaPisteet(tuhoutuvat);
                        peliruudukko.getPeliFrame().getPelilauta().poistaKolmiotKohdista(tuhoutuvat);
                        peliruudukko.taytaKolmiot(tuhoutuvat);
                        peliruudukko.getPeliFrame().getInfoPaneeli().paivitaPisteet();
                        moneskoTimerToisto = 2;
                    }

                } else if (moneskoTimerToisto == 2) {
                    peliruudukko.getPeliFrame().getTayttaja().taytaTietytRuudutRajoittamatta(tuhoutuvat);
                    peliruudukko.taytaKolmiot(tuhoutuvat);
                    moneskoTimerToisto = 1;
                }
            }
        };

        timer.addActionListener(teePiirto);
        timer.start();

    }

    private void taytaTuhoutuvat() {
        for (int rivi = 0; rivi < this.korkeus; rivi++) {
            for (int sarake = 0; sarake < this.leveys; sarake++) {
                this.tuhoutuvat.add(new Koordinaatti(rivi, sarake));
            }
        }
    }
}
