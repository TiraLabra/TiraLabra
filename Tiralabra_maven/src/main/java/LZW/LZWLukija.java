package LZW;

import Apuvalineet.BinaariMuuntaja;
import Tietorakenteet.HajautusTaulu;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Luokka joka suorittaa tiedoston lukemisen luoden samanaikaisesti
 * "laajennetttua sanastoa". Laajennetun sanaston (laaj) k‰yttˆ
 * mahdollistaa merkkien koodaamisen lyhyemmin kuin "tavu/merkki".
 */

public class LZWLukija {
    private BinaariMuuntaja muuntaja;
    private DataInputStream lukija;
    private StringBuilder teksti;
    private HajautusTaulu ascii;
    private HajautusTaulu laaj;
    private int merkkienPituus;
    
    public LZWLukija() {
        this.teksti = new StringBuilder();
        this.muuntaja = new BinaariMuuntaja();
        this.laaj = new HajautusTaulu();
        this.ascii = new YleisMetodeja().alustaAscii();
        this.merkkienPituus = 9;
    }
    
    public String getTeksti() {
        return this.teksti.toString();
    }
    
    protected HajautusTaulu getAscii() {
        return this.ascii;
    }
    
    protected HajautusTaulu getLaaj() {
        return this.laaj;
    }
    
    /**
     * Lukee tiedoston, jonka polun se saa parametrina.
     * Tiedostoa luetaan merkki kerrallaan ja samanaikaisesti
     * kirjoitetaan sen pakattavaa esityst‰ StringBuilder -oliolle teksti.
     * @param polku
     * @throws IOException 
     */
    
    public void lue(String polku) throws IOException {
        this.lukija = new DataInputStream(new FileInputStream(polku));
        StringBuilder lisattava = new StringBuilder();
        
        while (true) {
            lisattava = kasitteleArvo(lukija.read(), lisattava);
            
            if (lisattava == null) {
                break;
            }
            
        }
        lukija.close();
    }
    
    /**
     * K‰sittelee seuraavan merkin joka on parametrina arvo.
     * Jos arvo = -1, on koko luettava teksti jo kelattu l‰pi ja muodostettavaan
     * tekstiin lis‰t‰‰n ainoastaan nykyinen. Muuten lis‰t‰‰n uusi merkki.
     * @param arvo
     * @param lisattava
     * @return 
     */
    
    protected StringBuilder kasitteleArvo(int arvo, StringBuilder lisattava) {
        String nykyinen = lisattava.toString();
        
        if (arvo == -1) {
            lisaaBittijonoTekstiin(nykyinen);
            return null;
        }

        return kasitteleMerkki((char) arvo + "", nykyinen, lisattava);
    }
    
    /**
     * lisattava sis‰lt‰‰ merkkijonon joka on joko tyhj‰ (alussa) tai
     * sis‰lt‰‰ edelt‰vien luettujen merkkien yhdistelm‰n.
     * 
     * Metodi palauttaa joko lisattavan arvona "merkki" tai "seuraava" riippuen
     * siit‰, sis‰lt‰‰kˆ laajennettu sanasto jo "seuraavan" vai ei. Jos ei
     * sis‰ll‰, tekstiin lis‰t‰‰n nykyinen ja seuraava vied‰‰n aakkostoon.
     * @param merkki
     * @param nykyinen
     * @param lisattava
     * @return 
     */
    
    protected StringBuilder kasitteleMerkki(String merkki, String nykyinen, StringBuilder lisattava) {
        String seuraava = nykyinen + merkki;
        
        if (! nykyinen.isEmpty() && ! laaj.sisaltaaAvaimen(seuraava)) {

            lisaaBittijonoTekstiin(nykyinen);
            lisaaKoodistoon(seuraava);
                
            lisattava = new StringBuilder();
        }
        
        lisattava.append(merkki);
        return lisattava;
    }
    
    /**
     * Lis‰‰ koodistoon (laaj) uuden merkkijonon.
     * "merkkienPituus" kasvatetaan jos lis‰tt‰v‰n merkkijonon arvon pituus on pidempi.
     * @param avain 
     */
    
    protected void lisaaKoodistoon(String avain) {
        YleisMetodeja yleis = new YleisMetodeja();
        
        String arvo = yleis.koodistoonLisattavaArvo(ascii, laaj);
        merkkienPituus = yleis.merkkienPituus(merkkienPituus, arvo.length());
        
        laaj.lisaa(avain, arvo);
    }
    
    /**
     * Kasvattaa teksti‰ lis‰ten siihen uuden bittijonon joka on parametrina
     * saatavan olion arvo.
     * @param nykyinen 
     */
    
    protected void lisaaBittijonoTekstiin(String nykyinen) {
        if (laaj.sisaltaaAvaimen(nykyinen)) {
            lisaaBittijono(laaj, nykyinen);
        }
        else {
            lisaaBittijono(ascii, nykyinen);
        }
    }
    
    /**
     * Ottaa koodistosta arvon merkille/merkkijonolle ja lis‰‰ t‰h‰n
     * niin monta etunollaa kuin tarvitsee jotta sen pituus on "merkkienPituus".
     * 
     * Lis‰‰ t‰m‰n j‰lkeen arvon tekstiin.
     * @param koodisto
     * @param avain 
     */
    
    protected void lisaaBittijono(HajautusTaulu koodisto, String avain) {
        String arvo = koodisto.getArvo(avain);
        teksti.append(bittijonona(arvo));
    }
    
    /**
     * Lis‰‰ arvon eteen 00-tavuja kunnes sen pituus on merkkienPituus. 
     * @param arvo
     * @return 
     */
    
    protected String bittijonona(String arvo) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < merkkienPituus - arvo.length(); i++) {
            builder.append((char) 0);
        }
        
        builder.append(arvo);
        return builder.toString();
    }
}
