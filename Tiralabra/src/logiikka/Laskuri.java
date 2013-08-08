package logiikka;

import osat.Laatikko;
import osat.Lava;
import tyokalut.KasvavaLista;

/**
 * Luokka, joka huolehtii laatikoiden parhaan asettelutavan laskemisesta.
 *
 * @author albis
 */
public class Laskuri {
    /**
     * Laatikko-olio, jonka kuvaaman laatikon asettelutapa lasketaan.
     */
    private Laatikko laatikko;
    
    /**
     * Lava-olio, jonka kuvaamalle lavalle asetettavat laatikot on laitettava.
     */
    private Lava lava;
    
    /**
     * Lista, jota käytetään laatikon asettelutavan kuvaamiseen.
     */
    private KasvavaLista parasJarjestys;
    
    public Laskuri() {
    }
    
    /**
     * Metodi, joka huolehtii laatikoiden asettelutavan laskemisesta.
     * 
     * @param laatikko Laatikko, jonka asettelutapa lasketaan.
     * @param lava Lava, jolle laatikko asetellaan.
     * @return Palauttaa parhaan asettelutavan listana.
     */
    public KasvavaLista laske(Laatikko laatikko, Lava lava) {
        parasJarjestys = new KasvavaLista();
        this.laatikko = laatikko;
        this.lava = lava;
        
        asetaLaatikko(0, 0, lava, new KasvavaLista());
        
        return parasJarjestys;
    }
    
    /**
     * Rekursiivinen metodi, joka asettaa laatikoita lavalle ja näin kokeilemalla selvittää parhaan
     * tavan asetella laatikot.
     * 
     * @param x Asetettavan laatikon vasemman yläkulman sijainti x-akselilla.
     * @param y Asetettavan laatikon vasemman yläkulman sijainti y-akselilla.
     * @param vanhaLava Lava-olio, joka sisältää tiedon jo asetetuista ruuduista ja näin myös siitä,
     * mihin laatikoita voidaan vielä asettaa.
     * @param jarjestysTahanAsti Lavalle tällä hetkellä asetettujen laatikoiden asennot.
     */
    private void asetaLaatikko(int x, int y, Lava vanhaLava, KasvavaLista jarjestysTahanAsti) {
        if (jarjestysTahanAsti.length() > parasJarjestys.length()) {
            parasJarjestys = jarjestysTahanAsti.kopioi();
        }
        
        if (x + laatikko.getLeveys() <= lava.getLeveys() && y + laatikko.getKorkeus() <= lava.getKorkeus()) {
            Lava leveysSivulleLava = vanhaLava.kopioi();
            leveysSivulleLava.merkitseLaatikko(x, y, laatikko.getLeveys(), laatikko.getPituus());
            Lava leveysEteenLava = leveysSivulleLava.kopioi();

            KasvavaLista leveysSivulleJarjestys = jarjestysTahanAsti.kopioi();
            leveysSivulleJarjestys.lisaa("VAAKA");
            KasvavaLista leveysEteenJarjestys = leveysSivulleJarjestys.kopioi();

            asetaLaatikko(x+laatikko.getLeveys(), y, leveysSivulleLava, leveysSivulleJarjestys);
            asetaLaatikko(x, y+laatikko.getPituus(), leveysEteenLava, leveysEteenJarjestys);
        }
        
        if (x + laatikko.getPituus() <= lava.getLeveys() && y + laatikko.getLeveys() <= lava.getPituus()) {
            Lava pituusSivulleLava = vanhaLava.kopioi();
            pituusSivulleLava.merkitseLaatikko(x, y, laatikko.getPituus(), laatikko.getLeveys());
            Lava pituusEteenLava = pituusSivulleLava.kopioi();

            KasvavaLista pituusSivulleJarjestys = jarjestysTahanAsti.kopioi();
            pituusSivulleJarjestys.lisaa("PYSTY");
            KasvavaLista pituusEteenJarjestys = pituusSivulleJarjestys.kopioi();

            asetaLaatikko(x+laatikko.getPituus(), y, pituusSivulleLava, pituusSivulleJarjestys);
            asetaLaatikko(x, y+laatikko.getLeveys(), pituusEteenLava, pituusEteenJarjestys);
        }
    }
}
