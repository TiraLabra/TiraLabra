package pacman.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pacman.hahmot.Haamu;
import pacman.hahmot.Suunta;

/**
 * Piirtoalusta piirtää pelikentän, haamut, manin, pisteet ja elämät sekä pelin
 * päättymisen jälkeisen tekstikentän.
 * 
* @author hhkopper
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    /**
     * Kertoo minkä kokoiseksi halutaan pelialustan yhtä ruutua kuvaavan ruudun
     * sivun pituus.
     */
    private int ruudunSivu;
    /**
     * JFrame, johon kuvat liitetään.
     */
    private JFrame frame;
    /**
     * Kayttoliittyma, jonka kautta päästään käsiksi tarvittaviin tietoihin.
     */
    private Kayttoliittyma kayttis;

    /**
     * Konstruktorissa asetetaan kaikki tarvittavat arvot piirtoalustalle.
     *
     * @param sivu int arvoinen luku.
     * @param frame JFrame, johon kuvat halutaan.
     * @param kayttis Kayttoliittyma, jonka kaytta tarvittavat tiedot kutsutaan.
     */
    public Piirtoalusta(int sivu, JFrame frame, Kayttoliittyma kayttis) {
        this.ruudunSivu = sivu;
        super.setBackground(Color.BLACK);
        this.frame = frame;
        this.kayttis = kayttis;
    }

    /**
     * Piirtää pelialustan ja sen komponentit.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (kayttis.getPeli().getJatkuu()) {
            piirraPeliKaynnissa(g);
        } else {
            piirraPeliPaattynyt(g);
        }
    }

    /**
     * Piirtää uudelleen kentän
     */
    @Override
    public void paivita() {
        repaint();
    }

    /**
     * Jokaiselle haamulle annetaan kuva nimen perusteella. Jos haamujen tyyppi
     * on heikko piirretään haamujen heikot kuvat.
     *
     * @param g
     */
    private void kuvitaHaamut(Graphics g) {

        for (int i = 0; i < kayttis.getPeli().getHaamuLista().koko(); i++) {
            Haamu haamu = (Haamu) kayttis.getPeli().getHaamuLista().getAlkio(i);

            if (haamu.getTyyppi().equals("vahva")) {
                if (haamu.getNimi().equals("red")) {
                    ImageIcon kuva = new ImageIcon(this.getClass().getResource("red.png"));
                    piirraHaamunKuva(g, kuva, haamu);
                } else if (haamu.getNimi().equals("green")) {
                    ImageIcon kuva = new ImageIcon(this.getClass().getResource("green.png"));
                    piirraHaamunKuva(g, kuva, haamu);
                } else if (haamu.getNimi().equals("magenta")) {
                    ImageIcon kuva = new ImageIcon(this.getClass().getResource("magenta.png"));
                    piirraHaamunKuva(g, kuva, haamu);
                } else if (haamu.getNimi().equals("cyan")) {
                    ImageIcon kuva = new ImageIcon(this.getClass().getResource("cyan.png"));
                    piirraHaamunKuva(g, kuva, haamu);
                }
            } else {
                piirraHeikotHaamut(g, haamu);
            }
        }
    }

    /**
     * Piirretään haamut joiden tyyppi on heikko omalla tyylillä.
     *
     * @param g
     * @param haamu haamu joka piirretään.
     */
    private void piirraHeikotHaamut(Graphics g, Haamu haamu) {
        ImageIcon kuva = new ImageIcon(this.getClass().getResource("heikkoHaamu.png"));
        piirraHaamunKuva(g, kuva, haamu);
    }

    /**
     * Piirretään man siten, että jokaista suuntaa kuvaa oma kuvansa.
     *
     * @param g
     */
    private void piirraMan(Graphics g) {
        if (kayttis.getPeli().getMan().getSuunta() == Suunta.OIKEA) {
            ImageIcon kuva = new ImageIcon(this.getClass().getResource("pacmanOikea.png"));
            piirraManinKuva(g, kuva);
        } else if (kayttis.getPeli().getMan().getSuunta() == Suunta.VASEN) {
            ImageIcon kuva = new ImageIcon(this.getClass().getResource("pacmanVasen.png"));
            piirraManinKuva(g, kuva);
        } else if (kayttis.getPeli().getMan().getSuunta() == Suunta.YLOS) {
            ImageIcon kuva = new ImageIcon(this.getClass().getResource("pacmanYlos.png"));
            piirraManinKuva(g, kuva);
        } else if (kayttis.getPeli().getMan().getSuunta() == Suunta.ALAS) {
            ImageIcon kuva = new ImageIcon(this.getClass().getResource("pacmanAlas.png"));
            piirraManinKuva(g, kuva);
        }

    }

    /**
     * Jos ruutu on tyyppiä seinä, piirretään seinä. Jos taas ruudussa on
     * pistepallo tai ekstrapistepallo piirretään palloa kuvaava ympyrä.
     *
     * @param x
     * @param y koordinaatti Y
     * @param g koordinaatti X
     */
    private void piirraSeinatJaPallot(int x, int y, Graphics g) {
        if (kayttis.getPeli().getAlusta().getPeliruutu(x, y).getRuudunTyyppi() == 0) {
            piirraSeinat(g, x, y);
        } else if (kayttis.getPeli().getAlusta().getPeliruutu(x, y).getOnkoPallo()) {
            piirraPistepallot(g, x, y);
        } else if (kayttis.getPeli().getAlusta().getPeliruutu(x, y).getOnkoExtraPallo()) {
            piirraExtraPallo(g, x, y);
        }
    }

    /**
     * Piirretään pistepalloa kuvaava ympyrä.
     *
     * @param g
     * @param x koordinaatti X
     * @param y koordinaatti Y
     */
    private void piirraPistepallot(Graphics g, int x, int y) {
        int luku = this.ruudunSivu / 3;
        g.setColor(Color.yellow);
        g.fillOval((x * this.ruudunSivu) + luku, (y * this.ruudunSivu) + luku, this.ruudunSivu / 3, this.ruudunSivu / 3);
    }

    /**
     * Piirretään ekstrapistepalloa kuvaava ympyrä.
     *
     * @param g
     * @param x koordinaatti X
     * @param y koordinaatti Y
     */
    private void piirraExtraPallo(Graphics g, int x, int y) {
        int luku = this.ruudunSivu / 5;
        g.setColor(Color.yellow);
        g.fillOval((x * this.ruudunSivu) + luku, (y * this.ruudunSivu) + luku, this.ruudunSivu / 2, this.ruudunSivu / 2);
    }

    /**
     * Piirretään seinää kuvaava neliö.
     *
     * @param g
     * @param x koordinaatti X
     * @param y koordinaatti Y
     */
    private void piirraSeinat(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.fillRect(kayttis.getPeli().getAlusta().getPeliruutu(x, y).getX() * this.ruudunSivu, kayttis.getPeli().getAlusta().getPeliruutu(x, y).getY() * this.ruudunSivu,
                this.ruudunSivu, this.ruudunSivu);
    }

    /**
     * Piirretään pelaajan pistemäärä.
     *
     * @param g
     */
    private void piirraPisteet(Graphics g) {
        Font peliFontti = new Font("Candara", Font.BOLD, 30);
        g.setFont(peliFontti);
        g.setColor(Color.GREEN);
        g.drawString(Integer.toString(kayttis.getPeli().getLaskuri().getPisteet()), 600, 60);
    }

    /**
     * Piirretään elämiä kuvaavat sydämet.
     *
     * @param g
     */
    private void piirraElamat(Graphics g) {
        for (int i = 0; i < kayttis.getPeli().getMan().getElamat(); i++) {
            ImageIcon kuva = new ImageIcon(this.getClass().getResource("sydan.png"));
            g.drawImage(kuva.getImage(), 580 + (i * (kuva.getIconWidth() + 2)), 60 + kuva.getIconHeight(), frame);
        }
    }

    /**
     * Piirretään hedelmää kuvaava kirsikan kuva.
     *
     * @param g
     */
    private void piirraHedelma(Graphics g) {
        if (kayttis.getPeli().getLaskuri().getPisteet() > 400) {
            ImageIcon kuva = new ImageIcon(this.getClass().getResource("kirsikka.png"));
            g.drawImage(kuva.getImage(), kayttis.getPeli().getHedelmanPaikka().getX() * this.ruudunSivu, kayttis.getPeli().getHedelmanPaikka().getY() * this.ruudunSivu, frame);
        }
    }

    /**
     * Piirretään teksti, joka kertoo onko peli voitettu vai hävitty.
     *
     * @param g
     */
    private void piirretaanTilanne(Graphics g) {
        if (kayttis.getPeli().getTilanne()) {
            g.drawString("Voitit! Onneksi olkoon!", 200, 300);
        } else {
            g.drawString("Hävisit...", 200, 300);
        }
    }

    /**
     * Piirretään pelilauta kun peli on käynnissä.
     *
     * @param g
     */
    private void piirraPeliKaynnissa(Graphics g) {
        piirraElamat(g);
        piirraMan(g);
        for (int y = 0; y < kayttis.getPeli().getAlusta().getKorkeus(); y++) {
            for (int x = 0; x < kayttis.getPeli().getAlusta().getLeveys(); x++) {
                piirraSeinatJaPallot(x, y, g);
            }
        }
        piirraHedelma(g);
        kuvitaHaamut(g);
        piirraPisteet(g);
    }

    /**
     * Tarkistetaan onko uusi pistemäärä ennätys.
     */
    private void onkoEnnatys() {
        try {
            if (kayttis.getHighscore().tarkistaOnkoEnnatys(kayttis.getPeli().getLaskuri().getPisteet())) {
                kayttis.getHighscore().kirjaaEnnatys(kayttis.getPeli().getLaskuri().getPisteet());
            }
        } catch (FileNotFoundException ex) {
            kayttis.virheilmoitus("Scannerin luonnissa tapahtui virhe.");
        } catch (IOException ex) {
            kayttis.virheilmoitus("FileWriterin luonnissa tapahtui virhe.");
        }
    }

    /**
     * Piirretään pelialusta, kun peli on päättynyt.
     *
     * @param g
     */
    private void piirraPeliPaattynyt(Graphics g) {
        g.setColor(Color.RED);
        Font peliFontti = new Font("Candara", Font.BOLD, 25);
        g.setFont(peliFontti);
        piirretaanTilanne(g);
        g.drawString("Pisteesi: " + kayttis.getPeli().getLaskuri().getPisteet(), 200, 330);
        onkoEnnatys();
        g.drawString("Ennätyspisteet: " + kayttis.getHighscore().getParas(), 200, 390);
        g.drawString("Paina ENTER aloittaaksesi uuden pelin", 80, 500);
        paivita();
    }

    /**
     * Piirretään kuva.
     *
     * @param g
     * @param kuva kuva, mikä halutaan piirtää.
     */
    private void piirraManinKuva(Graphics g, ImageIcon kuva) {
        g.drawImage(kuva.getImage(), kayttis.getPeli().getMan().getX() * this.ruudunSivu, kayttis.getPeli().getMan().getY() * this.ruudunSivu, frame);
    }

    /**
     * Piirretään haamun kuva.
     *
     * @param g
     * @param kuva haamun oma kuva
     * @param haamu haamu, josta kuva piirretään
     */
    private void piirraHaamunKuva(Graphics g, ImageIcon kuva, Haamu haamu) {
        g.drawImage(kuva.getImage(), haamu.getX() * this.ruudunSivu, haamu.getY() * this.ruudunSivu, frame);
    }
}
