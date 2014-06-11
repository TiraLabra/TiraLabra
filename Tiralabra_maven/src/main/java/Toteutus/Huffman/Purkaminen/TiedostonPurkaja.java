package Toteutus.Huffman.Purkaminen;

import Apuvalineet.BinaariMuuntaja;
import Apuvalineet.Kirjoittaja;
import Apuvalineet.Lukija;
import Tietorakenteet.HajautusTaulu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * Luokka suorittaa tiedoston purkamisen k�ytt�en BinaariMuuntaja luokkaa apuna.
 */

public class TiedostonPurkaja {
    private BinaariMuuntaja muuntaja;
    
    public TiedostonPurkaja() {
        this.muuntaja = new BinaariMuuntaja();
    }
    
    /**
     * Tarkistaa onko tiedoston polku validi. Jos on, hakee pakkauksen ja muodostaa sit� vastaavan tiedoston (ilman
     * pakkauksen loppup��tett�). T�m�n j�lkeen purkaa pakkauksen.
     * @param polku
     * @throws IOException 
     */
    
    public void pura(String polku) throws IOException, Exception {
        String kirjoitettava = muodostaTeksti(polku);
        kirjoitaTeksti(luotavanTiedostonPolku(polku), kirjoitettava);
    }
    
    protected String muodostaTeksti(String polku) throws IOException, Exception {
        tarkistaOnkoPolkuValidi(polku);
        File pakkaus = haePakkaus(polku);
        
        return puretunTiedostonSisalto(pakkaus);
        
    }
    
    /**
     * Kirjoittaa puretun tiedoston sis�ll�n.
     * @param polku
     * @param teksti
     * @throws IOException
     */
    
    protected void kirjoitaTeksti(String polku, String teksti) throws IOException {
        Kirjoittaja kirjoittaja = new Kirjoittaja(polku);
        kirjoittaja.kirjoita(teksti);
    }
    
    /**
     * Tarkistaa onko pakkauksen p��te ".hemi".
     * @param polku
     * @throws IOException 
     */
    
    protected void tarkistaOnkoPolkuValidi(String polku) throws IOException {
        if (! polku.toLowerCase().endsWith(".hemi")) {
            throw new IOException("Tiedosto ei ole tyyppi� '.hemi' ja sit� ei voida t�ll� ohjelmalla purkaa.\nOhjelma suljetaan.");
        }
    }
    
    /**
     * Hakee pakkauksen ja heitt�� poikkeuksen jos se ei ole olemassa.
     * @param polku
     * @return
     * @throws IOException 
     */
    
    protected File haePakkaus(String polku) throws IOException {
        File pakkaus = new File(polku);
        if (! pakkaus.exists()) {
            throw new IOException("Valitsemasi tiedosto ei ole olemassa.\nOhjelma suljetaan.");
        }
        
        return pakkaus;
    }
    
    /**
     * Purkaa pakkauksen sis�ll�n tiedostoon hakemalla ensin pakkauksen sis�ll�n (ascii merkkein�), k�yden t�st�
     * Huffman puun l�pi ja ker�ten siit� "bittijonot ja niit� vastaavat merkit", muodostaen String -olion, jossa
     * on sis�ll�n bin��ritekstiosa ja muodostaen t�st� sitten puretun tiedoston sis�ll�n.
     * @param pakkaus
     * @throws IOException 
     * @return
     */
    
    protected String puretunTiedostonSisalto(File pakkaus) throws IOException, Exception {
        String teksti = lueTeksti(pakkaus);
        HajautusTaulu bittijonotJaMerkit = new HajautusTaulu();
        
        int binaariTekstinAlku = kayPuuLapi(teksti, bittijonotJaMerkit);
        String tekstiBinaarina = tekstiBinaarina(teksti, binaariTekstinAlku);
        return kirjoitettavaTeksti(tekstiBinaarina, bittijonotJaMerkit);
    }
    
    /**
     * Muodostaa bin��ritekstist� (010110010...) ja haj. taulusta, jossa on bittijonoja ja niit� vastaavia ascii -merkkej�,
     * puretun tiedoston sis�ll�n.
     * @param tekstiBinaarina
     * @param bittijonotJaMerkit
     * @return 
     */
    
    protected String kirjoitettavaTeksti(String tekstiBinaarina, HajautusTaulu bittijonotJaMerkit) throws Exception {
        long ennen = System.currentTimeMillis();
        
        StringBuilder kirjoitettava = new StringBuilder();
        StringBuilder bittijono = new StringBuilder();
        
        for (int i = 0; i < tekstiBinaarina.length(); i++) {
            bittijono.append(tekstiBinaarina.charAt(i));
            
            if (bittijonotJaMerkit.sisaltaaAvaimen(bittijono.toString())) {
                kirjoitettava.append(bittijonotJaMerkit.getArvo(bittijono.toString()));
                bittijono = new StringBuilder();
            }
        }
        
        long jalkeen = System.currentTimeMillis();
        System.out.println("Kirjoitettavan tekstin aikaansaamiseen kului " + (jalkeen - ennen) + "ms");
        
        return kirjoitettava.toString();
    }
    
    /**
     * Kelaa pakatussa tiedostossa olevan Huffman -puun l�pi ja ker�� siit� tietona;
     * 1) Puuta seuraavan alkion osoitteen
     * 2) Bin��riesitykset kutakin ascii -merkki� varten.
     
     * @param teksti
     * @param bittijonotJaMerkit
     * @return 
     */
    
    protected int kayPuuLapi(String teksti, HajautusTaulu bittijonotJaMerkit) throws Exception {
        if (puuOnKelattuLoppuun(teksti, 0)) {
            return 2;
        }
        
        return kelaaPuuLapi(teksti, bittijonotJaMerkit);
    }
    
    private int kelaaPuuLapi(String teksti, HajautusTaulu bittijonotJaMerkit) throws Exception {
        StringBuilder bittiEsitys = new StringBuilder();
        char kirjain = teksti.charAt(0);

        int i = 1;
        while (true) {
            char seuraava = teksti.charAt(i);
            if (! lisaaMerkkiJosSeOn0Tai1(seuraava, bittiEsitys)) {
                
                if (josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun(kirjain, bittiEsitys.toString(), bittijonotJaMerkit)) {
                    kirjain = seuraava;
                    bittiEsitys = new StringBuilder();
                }
                
                if (puuOnKelattuLoppuun(teksti, i)) {
                    return i + 2;
                }
            }

            i++;
        }
    }
    
    protected boolean josBittiEsitysEpaTyhjaLisataanSeHajautusTauluun(char kirjain, String bittiEsitys, HajautusTaulu bittijonotJaMerkit) throws Exception {
        if (bittiEsitys.isEmpty()) {
            return false;
        }
        
        bittijonotJaMerkit.lisaa(bittiEsitys, kirjain + "");
        return true;
    }
    
    protected boolean lisaaMerkkiJosSeOn0Tai1(char merkki, StringBuilder bittiEsitys) {
        if (merkki == 0 || merkki == 1) {
            String lisattava = merkki + "";
            bittiEsitys.append(lisattava);
            return true;
        }
        return false;
    }
    
    protected boolean puuOnKelattuLoppuun(String teksti, int i) {
        return teksti.charAt(i) == (char) 127 && teksti.charAt(i + 1) == (char) 127;
    }
    
    /**
     * 
     * @param teksti
     * @param poistettavienEtuNollienOsoite
     * @return 
     */
    
    /**
     * Muodostaa bin��riesityksen pakkauksen sis�ll�st� "poistettavienEtuNollienOsoite":een j�lkeen.
     * @param teksti
     * @param poistettavienEtuNollienOsoite
     * @return 
     */
    
    protected String tekstiBinaarina(String teksti, int poistettavienEtuNollienOsoite) {
        StringBuilder binaarina = new StringBuilder();
        binaarina.append(tavuIlmanEtuNollia(teksti, poistettavienEtuNollienOsoite));
        binaarina.append(lisaaMuuTeksti(teksti, poistettavienEtuNollienOsoite));       
        
        return binaarina.toString();
    }
    
    /**
     * Poistaa "poistettavienEtuNollienOsoite":tta seuraavasta tavusta ko. osoitteessa olevan
     * ascii -merkin m��r�n verran etunollia.
     * @param teksti
     * @param poistettavienEtuNollienOsoite
     * @return 
     */
    
    protected String tavuIlmanEtuNollia(String teksti, int poistettavienEtuNollienOsoite) {
        int arvo = teksti.charAt(poistettavienEtuNollienOsoite + 1);
        int poistettavia = teksti.charAt(poistettavienEtuNollienOsoite);
        
        String binaarina = muuntaja.binaariEsitysEtuNollilla8Bit(arvo);
        return binaarina.substring(poistettavia);
    }
    
    /**
     * Palauttaa pakkauksen sis�ll�st� bin��riesityksen os. "poistettavienEtuNollienOsoite + 2" alkaen.
     * @param teksti
     * @param poistettavienEtuNollienOsoite
     * @return 
     */
    
    protected String lisaaMuuTeksti(String teksti, int poistettavienEtuNollienOsoite) {
        StringBuilder lisaaja = new StringBuilder();
        
        for (int i = poistettavienEtuNollienOsoite + 2; i < teksti.length(); i++) {
            int arvo = teksti.charAt(i);
            lisaaja.append(muuntaja.binaariEsitysEtuNollilla8Bit(arvo));
        }
        return lisaaja.toString();
    }
         
    /**
     * Palauttaa pakkauksen sis�ll�n.
     * @param pakkaus
     * @return
     * @throws FileNotFoundException 
     * @throws UnsupportedEncodingException
     */
    
    protected String lueTeksti(File pakkaus) throws FileNotFoundException, UnsupportedEncodingException, IOException, Exception {
        Lukija lukija = new Lukija(pakkaus.getPath());
        lukija.lue();
        
        return lukija.getTeksti();
    }

    /**
     * Muodostaa polun puretulle tiedostolle (ts. poistaa ".hemi" p��tteen).
     * @param polku
     * @return 
     */
    
    protected String luotavanTiedostonPolku(String polku) {
        return polku.substring(0, polku.length() - 5);
    }
}
