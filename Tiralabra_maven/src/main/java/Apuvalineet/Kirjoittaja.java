package Apuvalineet;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Luokan tarkoituksena on kirjoittaa tiedostoon teksti‰.
 * Se kapseloi "DataOutputStream" -olion, jonka avulla voidaan kirjoittaa teksti‰
 * tavuina. Kirjoittaminen on t‰ll‰ tavoin eritt‰in hidasta, mutta se oli ainoa
 * tapa, jolla sain ohjelmani kirjoittamaan pakkaukseen oikein.
 */

public class Kirjoittaja {
    private DataOutputStream kirjoittaja;
    
    public Kirjoittaja(String polku) throws IOException {
        this(polku, false);
    }
    
    /**
     * Luo uuden tiedoston jos konstruktoria kutsuttiin arvoilla (polku, true) ja t‰m‰n
     * j‰lkeen luo kirjoittajan.
     * @param polku
     * @param tiedostoOlemassa
     * @throws IOException 
     */
    
    public Kirjoittaja(String polku, boolean tiedostoOlemassa) throws IOException {
        if (!tiedostoOlemassa) {
            luoUusiTiedosto(polku);
        }
        
        this.kirjoittaja = new DataOutputStream(new FileOutputStream(polku));
    }
    
    /**
     * Kirjoittaa tekstin tiedostoon tavuina. Jos n‰in ei teht‰isi, osa
     * kirjoitetuista merkeist‰ k‰‰ntyisi joko 0x3F:ksi ('?') tai tulisi
     * muita ongelmia. *monet eri mahdollisuudet testattu, ainoa mink‰ olen
     * saanut toimimaan oikein*
     * @param teksti
     * @throws IOException 
     */
    
    public void kirjoita(String teksti) throws IOException {
        kirjoittaja.writeBytes(teksti);
        kirjoittaja.close();
    }
    
    /**
     * Luo uuden tyhj‰n tiedoston tai heitt‰‰ poikkeuksen jos samanniminen tiedosto on jo olemassa.
     * @param polku
     * @throws IOException
     */
    
    private void luoUusiTiedosto(String polku) throws IOException {
        File tiedosto = new File(polku);
        
        if (! tiedosto.exists()) {
            tiedosto.createNewFile();
            tiedosto.setWritable(true);
        }
    }
}
