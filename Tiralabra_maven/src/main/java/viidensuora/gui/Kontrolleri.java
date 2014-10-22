package viidensuora.gui;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import viidensuora.ai.Etsintametodi;
import viidensuora.ai.Hakutulos;
import viidensuora.logiikka.Pelimerkki;
import viidensuora.logiikka.Ristinolla;

/**
 * Pyörittää peliä välittämällä tekoälyn etsimät ja hiirellä klikatut siirrot
 * Ristinollalle. Päivittää myös hiiren kursorin pelitilanteeseen sopivaksi.
 *
 * @author juha
 */
public class Kontrolleri implements MouseListener, ActionListener {

    /**
     * Kursori, joka näytetään Ristin vuorolla.
     */
    private final Cursor X_KURSORI;

    /**
     * Kursori, joka näytetään Nollan vuorolla.
     */
    private final Cursor O_KURSORI;

    /**
     * Kursori, joka näytetään Tekoälyn miettiessä siirtoaan.
     */
    private final Cursor AI_KURSORI;

    /**
     * Järjestelmän normaali kursori.
     */
    private final Cursor DEF_KURSORI;

    /**
     * Tekoälyn hakusyvyys
     */
    private final int AI_HAKUSYVYYS = 4;

    private final Kayttoliittyma kayttoliittyma;
    private final Peliruutu peliruutu;
    private final Ristinolla ristinolla;
    private final Etsintametodi ristinAI;
    private final Etsintametodi nollanAI;

    /**
     * Tekoälyn laukaisemiseen käytettävä ajastin.
     */
    private final Timer aiAjastin;

    public Kontrolleri(Kayttoliittyma kali, Ristinolla rn, Etsintametodi rAI, Etsintametodi nAI) {
        this.kayttoliittyma = kali;
        this.peliruutu = kali.getPeliruutu();
        this.ristinolla = rn;
        this.ristinAI = rAI;
        this.nollanAI = nAI;

        // Ajatin ajetaan aina vain kerran. ei toistuvasti
        this.aiAjastin = new Timer(500, this);
        this.aiAjastin.setRepeats(false);

        this.X_KURSORI = luoKursori("risti.png", "Risti");
        this.O_KURSORI = luoKursori("nolla.png", "Nolla");
        this.AI_KURSORI = new Cursor(Cursor.WAIT_CURSOR);
        this.DEF_KURSORI = new Cursor(Cursor.DEFAULT_CURSOR);

        // Jos peli aloitetaan tekoälyn siirrolla..
        if (ristinAI != null) {
            aiAjastin.start();
        }
    }

    /**
     * Lukee hiiren klikkaukset ja välittää klikkausta vastaavan siirron
     * Ristinollalle mikäli on ihmispelaajan vuoro.
     *
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
        if ((ristinolla.onRistinVuoro() && ristinAI != null)
                || (!ristinolla.onRistinVuoro() && nollanAI != null)) {
            return;
        }

        int x = e.getX() / Peliruutu.RUUDUN_KOKO;
        int y = e.getY() / Peliruutu.RUUDUN_KOKO;
        if (!ristinolla.ruutuOnTyhja(x, y)) {
            return;
        }
        ristinolla.pelaaVuoro(x, y);

        // Laukaistaan seuraavan vuoron tekoäly, jos vastustaja ei ole ihminen.
        if ((ristinolla.onRistinVuoro() && ristinAI != null)
                || (!ristinolla.onRistinVuoro() && nollanAI != null)) {
            aiAjastin.restart();
        }
        tarkistaTulos();
        asetaKursori();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Päivittää kursorin kun hiiri tuodaan peliruudulle.
     *
     * @param e
     */
    public void mouseEntered(MouseEvent e) {
        asetaKursori();
    }

    /**
     * Asettaa normaalin kursorin kun hiiri poistuu peliruudulta.
     *
     * @param e
     */
    public void mouseExited(MouseEvent e) {
        peliruutu.setCursor(DEF_KURSORI);
    }

    /**
     * Ajatimen lakaisema tapahtuma, jossa käynnistetään tekoäly. Tekoälyä
     * kutsutaan ajastimen avulla, jotta peliruutu keretään piirtää vuorojen
     * välissä.
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        Hakutulos hakutulos;
        if (ristinolla.onRistinVuoro()) {
            hakutulos = ristinAI.etsiRistinSiirto(AI_HAKUSYVYYS);
            // Päivitetään InfoPaneelia tarvittaessa
            if (kayttoliittyma.getRistinInfo() != null) {
                kayttoliittyma.getRistinInfo().lisaaTekstia("\n" + hakutulos.toString());
            }
            if (nollanAI != null) {
                aiAjastin.restart();
            }
        } else {
            hakutulos = nollanAI.etsiNollanSiirto(AI_HAKUSYVYYS);
            if (kayttoliittyma.getNollanInfo() != null) {
                kayttoliittyma.getNollanInfo().lisaaTekstia("\n" + hakutulos.toString());
            }
            if (ristinAI != null) {
                aiAjastin.restart();
            }
        }
        ristinolla.pelaaVuoro(hakutulos.parasSiirto.x, hakutulos.parasSiirto.y);
        tarkistaTulos();
        asetaKursori();
    }

    /**
     * Luo uuden kursorin.
     *
     * @param kuvatiedosto Kursorissa näyettävän kuvan sijainti.
     * @param nimi Kursorin nimi.
     * @return Luotu kursori.
     */
    private Cursor luoKursori(String kuvatiedosto, String nimi) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image kuva = toolkit.createImage(this.getClass().getResource("/" + kuvatiedosto));
        Point hotSpot = new Point(12, 12);
        return toolkit.createCustomCursor(kuva, hotSpot, nimi);
    }

    /**
     * Näyttää tuloksen mikäli peli päättyi.
     */
    private void tarkistaTulos() {
        if (ristinolla.getVoittaja() != null) {
            aiAjastin.stop();
            String voittaja = ristinolla.getVoittaja() == Pelimerkki.RISTI ? "Risti" : "Nolla";
            JOptionPane.showMessageDialog(null, voittaja + " voitti.");
        } else if (ristinolla.lautaTaynna()) {
            aiAjastin.stop();
            JOptionPane.showMessageDialog(null, "Tasapeli.");
        }
    }

    /**
     * Asettaa pelilanteeseen sopivan kursorin.
     */
    private void asetaKursori() {
        if (ristinolla.onRistinVuoro()) {
            if (ristinAI == null) {
                peliruutu.setCursor(X_KURSORI);
            } else {
                peliruutu.setCursor(AI_KURSORI);
            }
        } else {
            if (nollanAI == null) {
                peliruutu.setCursor(O_KURSORI);
            } else {
                peliruutu.setCursor(AI_KURSORI);
            }
        }
    }

}
