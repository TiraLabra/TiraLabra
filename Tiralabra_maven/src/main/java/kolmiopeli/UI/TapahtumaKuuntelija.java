package kolmiopeli.UI;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;
import kolmiopeli.UI.napit.KolmioNappi;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Koordinaatti;

/**
 * Jokaiseen KolmioNappiin liitetty oma TapahtumaKuuntelija joka hoitaa napin
 * siirtamista
 *
 * @author Eemi
 */
class TapahtumaKuuntelija implements ActionListener {

    private Peliruudukko peliruudukko;
    private int korkeus;
    private int leveys;
    private ArrayList<Koordinaatti> tuhoutuvat;

    TapahtumaKuuntelija(Peliruudukko peliruudukko) {
        this.peliruudukko = peliruudukko;
        this.korkeus = peliruudukko.getPeliruudukko().length;
        this.leveys = peliruudukko.getPeliruudukko()[0].length;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        KolmioNappi nappi = (KolmioNappi) e.getSource();
        Kolmio edellinen = this.peliruudukko.getValittuKolmio();
        Kolmio viimeisin = nappi.getNapinKolmio();
        tuhoutuvat = null;

        // Kaydaan lapi eri suuntiin mahdollisesti tapahtuvat siirrot
        if (edellinen == viimeisin) {
            this.peliruudukko.setValittuKolmio(viimeisin);
        }
        if (edellinen == null) {
            this.peliruudukko.setValittuKolmio(nappi.getNapinKolmio());
            return;
        }
        if (edellinen.getSijaintiRivi() + 1 == viimeisin.getSijaintiRivi()) {
            tuhoutuvat = (ArrayList<Koordinaatti>) this.peliruudukko.getSiirrot().siirraKolmioAlas(edellinen.getSijaintiRivi(), edellinen.getSijaintiSarake());

        } else if (edellinen.getSijaintiRivi() - 1 == viimeisin.getSijaintiRivi()) {
            tuhoutuvat = (ArrayList<Koordinaatti>) this.peliruudukko.getSiirrot().siirraKolmioYlos(edellinen.getSijaintiRivi(), edellinen.getSijaintiSarake());

        } else if (edellinen.getSijaintiSarake() + 1 == viimeisin.getSijaintiSarake()) {
            tuhoutuvat = (ArrayList<Koordinaatti>) this.peliruudukko.getSiirrot().siirraKolmioOikealle(edellinen.getSijaintiRivi(), edellinen.getSijaintiSarake());

        } else if (edellinen.getSijaintiSarake() - 1 == viimeisin.getSijaintiSarake()) {
            tuhoutuvat = (ArrayList<Koordinaatti>) this.peliruudukko.getSiirrot().siirraKolmioVasemmalle(edellinen.getSijaintiRivi(), edellinen.getSijaintiSarake());
        }


        if (tuhoutuvat != null) {
            this.peliruudukko.setValittuKolmio(null);
            
            // Siirretaan ei-tuhoutuneen kolmion koordinaatti pois muistiin ettei sita tuhota
            Koordinaatti siirtyneenKolmionKoordinaatti = tuhoutuvat.get(0);
            tuhoutuvat.remove(0);
            
            
            this.peliruudukko.getSiirrot().getPisteenlaskija().lisaaTuhoutuneistaPisteet(tuhoutuvat);
            this.peliruudukko.getPeliFrame().getPeliruudukko().poistaKolmiotKohdista(tuhoutuvat);
            
            // Lisataan siirtynyt kolmio piirtamista varten listaan
            tuhoutuvat.add(0, siirtyneenKolmionKoordinaatti);
            this.peliruudukko.taytaKolmiot(tuhoutuvat);
            
            // Poistetaan siirretty kolmio listasta jotta sen paalle ei arvota uutta
            this.tuhoutuvat.remove(0);

            ActionListener teePiirto = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    peliruudukko.getPeliFrame().getTayttaja().taytaTietytRuudut(tuhoutuvat);
                    peliruudukko.taytaKolmiot(korkeus, leveys);
                    // Paivitetaan pisteet ja siirrot
                    peliruudukko.getPeliFrame().getInfoPaneeli().paivita();
                    if (peliruudukko.getPeliFrame().getInfoPaneeli().getSiirtoja() == 0) {
                        CardLayout paneelit = (CardLayout) peliruudukko.getPeliFrame().getNakymat().getLayout();
                        peliruudukko.getPeliFrame().getGameover().removeAll();
                        peliruudukko.getPeliFrame().getGameover().revalidate();
                        peliruudukko.getPeliFrame().getGameover().paivita();
                        paneelit.last(peliruudukko.getPeliFrame().getNakymat());
                    }
                }
            };
            Timer timer = new Timer(300, teePiirto);
            timer.setRepeats(false);
            timer.start();




        }




    }
}
