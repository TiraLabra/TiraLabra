package kolmiopeli.UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JPanel;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.domain.Ruudukko;
import kolmiopeli.logiikka.KolmioTayttaja;
import kolmiopeli.logiikka.PistesiirtojenEtsija;
import kolmiopeli.logiikka.Siirrot;
import kolmiopeli.logiikka.tiralabraAlgoritmit.KomboEtsija;

/**
 * Kayttoliittyman keskeisin osa, luo pelitilanteen ja vaihtaa pelin loppuessa
 * GameOver nakyman
 *
 * @author Eemi
 */
public class PeliFrame {

    private Ruudukko ruudukko;
    private Siirrot siirrot;
    private InfoPaneeli infoPaneeli;
    private GameOverPanel gameover;
    private Container nakymat;
    private KolmioTayttaja tayttaja;
    private Peliruudukko peliruudukko;
    private PistesiirtojenEtsija etsija;
    private KomboEtsija komboEtsija;
    static Kolmio[][] test1 = {{new Kolmio(Color.MAGENTA, 0, 0), new Kolmio(Color.RED, 0, 1), new Kolmio(Color.BLUE, 0, 2), new Kolmio(Color.RED, 0, 3), new Kolmio(Color.RED, 0, 4)},
        {new Kolmio(Color.GREEN, 1, 0), new Kolmio(Color.CYAN, 1, 1), new Kolmio(Color.MAGENTA, 1, 2), new Kolmio(Color.MAGENTA, 1, 3), new Kolmio(Color.RED, 1, 4)},
        {new Kolmio(Color.CYAN, 2, 0), new Kolmio(Color.CYAN, 2, 1), new Kolmio(Color.CYAN, 2, 2), new Kolmio(Color.MAGENTA, 2, 3), new Kolmio(Color.BLUE, 2, 4)},
        {new Kolmio(Color.CYAN, 3, 0), new Kolmio(Color.MAGENTA, 3, 1), new Kolmio(Color.MAGENTA, 3, 2), new Kolmio(Color.MAGENTA, 3, 3), new Kolmio(Color.CYAN, 3, 4)},};

    public PeliFrame(int rivit, int sarakkeet, Container container) {
        this.ruudukko = new Ruudukko(rivit, sarakkeet);
//        this.ruudukko.setRuudukko(test1);
        this.tayttaja = new KolmioTayttaja(this.ruudukko);
        tayttaja.taytaKaikkiRuudut();
        
        
//         Seuraava rivi on aikatestaukseen tarkoitettu osa
//        tayttaja.taytaKaikkiRuudutRajoittamatta();

        this.etsija = new PistesiirtojenEtsija(ruudukko);
        this.siirrot = new Siirrot(ruudukko);
        this.siirrot.setEtsija(etsija);
        this.komboEtsija = new KomboEtsija(ruudukko.getRuudukko(), true);



        GameOverPanel gameOverPaneeli = new GameOverPanel(this);
        this.gameover = gameOverPaneeli;


        JPanel pelipaneeli = new JPanel(new BorderLayout());
        this.luoPeliKomponentit(pelipaneeli);

        JPanel nakymat = new JPanel(new CardLayout());
        nakymat.add(pelipaneeli);
        nakymat.add(gameover);
        this.nakymat = nakymat;

        container.add(nakymat);
        
        
        // Nama on myos aikatestauksen alkuosia
//        KomboEtsijaAnimointiSatunnaisessaRuudukossa a = new KomboEtsijaAnimointiSatunnaisessaRuudukossa(this.peliruudukko);
//        a.animoiKomboEtsinta();



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

    public Ruudukko getPelilauta() {
        return ruudukko;
    }

    public KolmioTayttaja getTayttaja() {
        return tayttaja;
    }

    public KomboEtsija getKomboEtsija() {
        return komboEtsija;
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
