package kolmiopeli.UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JPanel;
import kolmiopeli.domain.Ruudukko;
import kolmiopeli.logiikka.PistesiirtojenEtsija;
import kolmiopeli.logiikka.Siirrot;
import kolmiopeli.logiikka.KolmioTayttaja;

/**
 * Kayttoliittyman keskeisin osa, luo pelitilanteen ja vaihtaa pelin loppuessa GameOver nakyman
 * @author Eemi
 */
public class PeliFrame {

    private Ruudukko ruudukko;
    private PistesiirtojenEtsija etsija;
    private Siirrot siirrot;
    private InfoPaneeli infoPaneeli;
    private GameOverPanel gameover;
    private Container nakymat;
    private KolmioTayttaja tayttaja;
    private Peliruudukko peliruudukko;


    public PeliFrame(int rivit, int sarakkeet, Container container) {
        this.ruudukko = new Ruudukko(rivit, sarakkeet);
        this.tayttaja = new KolmioTayttaja(this.ruudukko);
        tayttaja.taytaKaikkiRuudut();

        this.etsija = new PistesiirtojenEtsija(ruudukko);
        this.siirrot = new Siirrot(ruudukko);
        this.siirrot.setEtsija(etsija);
        
        
        
        GameOverPanel gameOverPaneeli = new GameOverPanel(this);
        this.gameover = gameOverPaneeli;
        
        
        JPanel pelipaneeli = new JPanel(new BorderLayout());
        this.luoPeliKomponentit(pelipaneeli);
        
        JPanel nakymat = new JPanel(new CardLayout());
        nakymat.add(pelipaneeli);
        nakymat.add(gameover);
        this.nakymat = nakymat;
        
        container.add(nakymat);
        

        
    }

    private void luoPeliKomponentit(Container container) {
        container.setBackground(Color.WHITE);


        InfoPaneeli infoPanel = new InfoPaneeli(this);
        infoPanel.setBackground(Color.WHITE);
        this.infoPaneeli = infoPanel;
        container.add(infoPanel, BorderLayout.NORTH);


        this.peliruudukko = new Peliruudukko(this);
        peliruudukko.setBackground(Color.WHITE);
        container.add(peliruudukko);

    }

    public Ruudukko getPeliruudukko() {
        return ruudukko;
    }

    public PistesiirtojenEtsija getEtsija() {
        return etsija;
    }

    public InfoPaneeli getInfoPaneeli() {
        return infoPaneeli;
    }

    public GameOverPanel getGameover() {
        return gameover;
    }

    public Container getNakymat() {
        return nakymat;
    }

    public void uusiPeli() {
        this.tayttaja.taytaKaikkiRuudut();
        this.infoPaneeli.nollaaPisteet();
        this.infoPaneeli.paivita();
        this.peliruudukko.taytaKolmiot(this.ruudukko.getRuudukko().length, this.ruudukko.getRuudukko()[0].length);
        
    }
    
    public Siirrot getSiirrot() {
        return this.siirrot;
    }
}
