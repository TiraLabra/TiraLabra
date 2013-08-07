
package kolmiopeli.UI;

import kolmiopeli.UI.napit.KolmioNappi;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kolmiopeli.domain.Kolmio;

/**
 * Jokaiseen KolmioNappiin liitetty oma TapahtumaKuuntelija joka hoitaa napin siirtamista
 * @author Eemi
 */
class TapahtumaKuuntelija implements ActionListener {

    private Peliruudukko peliruudukko;
    private int korkeus;
    private int leveys;


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
        
        // Kaydaan lapi eri suuntiin mahdollisesti tapahtuvat siirrot
        if (edellinen == viimeisin) {
            this.peliruudukko.setValittuKolmio(viimeisin);
        }
        if (edellinen == null) {
            this.peliruudukko.setValittuKolmio(nappi.getNapinKolmio());
            return;
        }
        if (edellinen.getSijaintiRivi() + 1 == viimeisin.getSijaintiRivi()) {
            this.peliruudukko.getSiirrot().siirraKolmioAlas(edellinen.getSijaintiRivi(), edellinen.getSijaintiSarake());
            this.peliruudukko.setValittuKolmio(null);
            this.peliruudukko.taytaKolmiot(this.korkeus, this.leveys);
        }
        else if (edellinen.getSijaintiRivi() - 1 == viimeisin.getSijaintiRivi()) {
            this.peliruudukko.getSiirrot().siirraKolmioYlos(edellinen.getSijaintiRivi(), edellinen.getSijaintiSarake());
            
            this.peliruudukko.setValittuKolmio(null);
            this.peliruudukko.taytaKolmiot(this.korkeus, this.leveys);
        }
        else  if (edellinen.getSijaintiSarake() + 1 == viimeisin.getSijaintiSarake()) {
            this.peliruudukko.getSiirrot().siirraKolmioOikealle(edellinen.getSijaintiRivi(), edellinen.getSijaintiSarake());
            this.peliruudukko.setValittuKolmio(null);
            this.peliruudukko.taytaKolmiot(this.korkeus, this.leveys);
        }
        else  if (edellinen.getSijaintiSarake() - 1 == viimeisin.getSijaintiSarake()) {
            this.peliruudukko.getSiirrot().siirraKolmioVasemmalle(edellinen.getSijaintiRivi(), edellinen.getSijaintiSarake());
            this.peliruudukko.setValittuKolmio(null);
            this.peliruudukko.taytaKolmiot(this.korkeus, this.leveys);
        }
        
        // Paivitetaan pisteet ja siirrot
        this.peliruudukko.getPeliFrame().getInfoPaneeli().paivita();
        if (this.peliruudukko.getPeliFrame().getInfoPaneeli().getSiirtoja() == 0) {
            CardLayout paneelit = (CardLayout) this.peliruudukko.getPeliFrame().getNakymat().getLayout();
            this.peliruudukko.getPeliFrame().getGameover().paivita();
            paneelit.last(this.peliruudukko.getPeliFrame().getNakymat());
        }
    }
}
