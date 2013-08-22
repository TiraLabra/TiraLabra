package logiikka;

import osat.Laatikko;
import osat.Nelikulmio;

/**
 * Luokka, joka huolehtii laatikoiden parhaan asettelutavan laskemisesta.
 *
 * @author albis
 */
public class Laskuri {
    /**
     * Tämän hetkellä kokeilussa oleva laatikoiden asettelutapa.
     */
    private int[][] asettelu;
    
    /**
     * Tähän mennessä paras tapa asetella laatikot.
     */
    private int[][] parasAsettelu;
    
    /**
     * Lavalle mahtuvien laatikoiden lukumäärä.
     */
    private int montakoParhaallaLavalla;
    
    public Laskuri() {
    }
    
    /**
     * Metodi, joka käynnistää laatikoiden asettelun kokeilun ja loppujenlopuksi palauttaa
     * parhaan asettelutavan.
     * 
     * @param laatikko Laatikko, jolle asettelutapa lasketaan.
     * @param lava Lava, jolle laatikot on aseteltava
     * @return Palauttaa kaksiulotteisen taulukon, jossa laatikot on kuvattu koordinaatistoon
     * numeroin. Numerot osoittavat aina monenteenko asetettuun laatikkoon kyseinen ruutu
     * kuuluu ja 0 kertoo tyhjästä ruudusta.
     */
    public int[][] laske(Laatikko laatikko, Nelikulmio lava) {
        asettelu = new int[lava.getPituus()][lava.getLeveys()];
        
        parasAsettelu = new int[lava.getPituus()][lava.getLeveys()];
        montakoParhaallaLavalla = 0;
        
        kokeileAsettelutavat(laatikko, lava, 1);
        
        return parasAsettelu;
    }
    
    /**
     * Metodi, joka asettaa hakee ensimmäiset vapaat paikat aloittaen haun ensin leveyssuunnasta
     * ja sitten pituusssuunnasta ja asettaa laatikon vuorotellen eri päin, jotta saadaan kokeiltua
     * eri tavat asetella laatikot.
     * 
     * @param laatikko Laatikko, jota lavalle asetellaan.
     * @param lava Lava, jolle laatikkoa asetellaan.
     * @param monesko Kokonaisluku, joka kertoo monesko laatikko seuraavaksi lavalle laitetaan.
     */
    private void kokeileAsettelutavat(Laatikko laatikko, Nelikulmio lava, int monesko) {
        kokeileLeveyshaunVaaka(laatikko, lava, monesko);
        if (laatikko.getLeveys() != laatikko.getPituus()) {
            kokeileLeveyshaunPysty(laatikko, lava, monesko);
        }
        kokeilePituushaunVaaka(laatikko, lava, monesko);
        if (laatikko.getLeveys() != laatikko.getPituus()) {
            kokeilePituushaunPysty(laatikko, lava, monesko);
        }
    }
    
    /**
     * Metodi aloittaa hakemisen leveyssuunnassa ja etsii ensimmäisen vastaantulevan
     * paikan, johon laatikko mahtuu poikittain. Tämän löydyttyä vapaan paikan
     * alkupiste, eli vasen yläkulma asetetaan arvoon -1, jotta se löytyy helposti.
     * 
     * @param laatikko Laatikko, jota lavalle asetellaan.
     * @param lava Lava, jolle laatikkoa asetellaan.
     * @param monesko Kokonaisluku, joka kertoo monesko laatikko seuraavaksi lavalle laitetaan.
     */
    private void kokeileLeveyshaunVaaka(Laatikko laatikko, Nelikulmio lava, int monesko) {
        if (merkitseSeuraavaVaakapaikkaLeveyssuunnassa(laatikko.getLeveys(), laatikko.getPituus())) {
            asetaLaatikko(laatikko.getLeveys(), laatikko.getPituus(), monesko);
            
            onkoUusiParas(monesko);
            
            kokeileAsettelutavat(laatikko, lava, monesko+1);
            poistaLaatikko(monesko);
        }
    }
    
    /**
     * Metodi aloittaa hakemisen leveyssuunnassa ja etsii ensimmäisen vastaantulevan
     * paikan, johon laatikko mahtuu pitkittäin. Tämän löydyttyä vapaan paikan
     * alkupiste, eli vasen yläkulma asetetaan arvoon -1, jotta se löytyy helposti.
     * 
     * @param laatikko Laatikko, jota lavalle asetellaan.
     * @param lava Lava, jolle laatikkoa asetellaan.
     * @param monesko Kokonaisluku, joka kertoo monesko laatikko seuraavaksi lavalle laitetaan.
     */
    private void kokeileLeveyshaunPysty(Laatikko laatikko, Nelikulmio lava, int monesko) {
        if (merkitseSeuraavaPystypaikkaLeveyssuunnassa(laatikko.getLeveys(), laatikko.getPituus())) {
            asetaLaatikko(laatikko.getPituus(), laatikko.getLeveys(), monesko);
            
            onkoUusiParas(monesko);
            
            kokeileAsettelutavat(laatikko, lava, monesko+1);
            poistaLaatikko(monesko);
        }
    }
    
    /**
     * Metodi aloittaa hakemisen pituussuunnassa ja etsii ensimmäisen vastaantulevan
     * paikan, johon laatikko mahtuu poikittain. Tämän löydyttyä vapaan paikan
     * alkupiste, eli vasen yläkulma asetetaan arvoon -1, jotta se löytyy helposti.
     * 
     * @param laatikko Laatikko, jota lavalle asetellaan.
     * @param lava Lava, jolle laatikkoa asetellaan.
     * @param monesko Kokonaisluku, joka kertoo monesko laatikko seuraavaksi lavalle laitetaan.
     */
    private void kokeilePituushaunVaaka(Laatikko laatikko, Nelikulmio lava, int monesko) {
        if (merkitseSeuraavaVaakapaikkaPituussuunnassa(laatikko.getLeveys(), laatikko.getPituus())) {
            asetaLaatikko(laatikko.getLeveys(), laatikko.getPituus(), monesko);
            
            onkoUusiParas(monesko);
            
            kokeileAsettelutavat(laatikko, lava, monesko+1);
            poistaLaatikko(monesko);
        }
    }
    
    /**
     * Metodi aloittaa hakemisen pituussuunnassa ja etsii ensimmäisen vastaantulevan
     * paikan, johon laatikko mahtuu pitkittäin. Tämän löydyttyä vapaan paikan
     * alkupiste, eli vasen yläkulma asetetaan arvoon -1, jotta se löytyy helposti.
     * 
     * @param laatikko Laatikko, jota lavalle asetellaan.
     * @param lava Lava, jolle laatikkoa asetellaan.
     * @param monesko Kokonaisluku, joka kertoo monesko laatikko seuraavaksi lavalle laitetaan.
     */
    private void kokeilePituushaunPysty(Laatikko laatikko, Nelikulmio lava, int monesko) {
        if (merkitseSeuraavaPystypaikkaPituussuunnassa(laatikko.getLeveys(), laatikko.getPituus())) {
            asetaLaatikko(laatikko.getPituus(), laatikko.getLeveys(), monesko);
            
            onkoUusiParas(monesko);
            
            kokeileAsettelutavat(laatikko, lava, monesko+1);
            poistaLaatikko(monesko);
        }
    }
    
    /**
     * Vertaa tällä hetkellä lavalla olevaa laatikkomäärää tähän asti suurimpaan
     * laatikkomäärään, joka lavalle on saatu mahtumaan. Jos tämänhetkinen määrä on
     * suurempi kuin tähän asti paras, asetetaan nykyinen asettelutapa parhaaksi
     * asettelutavaksi ja parhaan laatikkomäärän kertova kokonaisluku lavalla tällä
     * hetkellä olevien laatikoiden määrän mukaan.
     * 
     * @param monesko Kokonaisluku, joka kertoo montako laatikkoa lavalla tällä
     * hetkellä on.
     */
    private void onkoUusiParas(int monesko) {
        if (monesko > montakoParhaallaLavalla) {
                montakoParhaallaLavalla = monesko;
                kopioiTaulukko(asettelu, parasAsettelu);
        }
    }
    
    /**
     * Poistaa asetetun laatikon lavalta, jotta päästään kokeilemaan seuraavaa
     * asettelutapaa.
     * 
     * @param monesko Kertoo monesko laatikko poistetaan, jotta tiedetään mitkä arvot
     * taulukosta asetetaan nollaan.
     */
    private void poistaLaatikko(int monesko) {
        for (int i = 0; i < asettelu.length; i++) {
            for (int j = 0; j < asettelu[0].length; j++) {
                if (asettelu[i][j] == monesko) {
                    asettelu[i][j] = 0;
                }
            }
        }
    }
    
    /**
     * Metodi etsii ensin paikan, joka on merkitty asetettavan laatikon vasemmaksi
     * yläkulmaksi luvulla -1. Tämän jälkeen se vaihtaa siitä alkaen annettujen mittojen
     * mukaisen määrän ruutuja kokonaisluvuksi, joka kertoo monesko laatikko on kyseessä.
     * 
     * @param leveys Asetettavan laatikon viemä tila leveyssuunnassa.
     * @param pituus Asetettavan laatikon viemä tila pituussuunnassa.
     * @param monesko Kertoo monesko laatikko lavalle ollaan asettamassa.
     */
    private void asetaLaatikko(int leveys, int pituus, int monesko) {
        int x = 0;
        int y = 0;
        
        for (int i = 0; i < asettelu.length; i++) {
            for (int j = 0; j < asettelu[0].length; j++) {
                if (asettelu[i][j] == -1) {
                    x = j;
                    y = i;
                }
            }
        }
        
        int loppuX = x + leveys;
        int loppuY = y + pituus;
        
        for (int i = y; i < loppuY; i++) {
            for (int j = x; j < loppuX; j++) {
                asettelu[i][j] = monesko;
            }
        }
    }
    
    /**
     * Aloittaa haun vaakasuunnassa ja merkitsee ensimmäisen vastaan tulevan paikan,
     * johon aseteltava laatikko mahtuu vaakasuunnassa.
     * 
     * @param laatikko Aseteltava laatikko.
     * @return Palauttaa true jos paikka löytyi ja false jos lavalla ei ole enää tilaa.
     */
    private boolean merkitseSeuraavaVaakapaikkaLeveyssuunnassa(int leveys, int pituus) {
        for (int i = 0; i < asettelu.length; i++) {
            for (int j = 0; j < asettelu[0].length; j++) {
                if (asettelu[i][j] == 0) {
                    if (onkoTilaa(i, j, leveys, pituus)) {
                        asettelu[i][j] = -1;
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Aloittaa haun vaakasuunnassa ja merkitsee ensimmäisen vastaan tulevan paikan,
     * johon aseteltava laatikko mahtuu pystysuunnassa.
     * 
     * @param laatikko Aseteltava laatikko.
     * @return Palauttaa true jos paikka löytyi ja false jos lavalla ei ole enää tilaa.
     */
    private boolean merkitseSeuraavaPystypaikkaLeveyssuunnassa(int leveys, int pituus) {
        for (int i = 0; i < asettelu.length; i++) {
            for (int j = 0; j < asettelu[0].length; j++) {
                if (asettelu[i][j] == 0) {
                    if (onkoTilaa(i, j, pituus, leveys)) {
                        asettelu[i][j] = -1;
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Aloittaa haun pituussuunnassa ja merkitsee ensimmäisen vastaan tulevan paikan,
     * johon aseteltava laatikko mahtuu vaakasuunnassa.
     * 
     * @param laatikko Aseteltava laatikko.
     * @return Palauttaa true jos paikka löytyi ja false jos lavalla ei ole enää tilaa.
     */
    private boolean merkitseSeuraavaVaakapaikkaPituussuunnassa(int leveys, int pituus) {
        for (int i = 0; i < asettelu[0].length; i++) {
            for (int j = 0; j < asettelu.length; j++) {
                if (onkoTilaa(j, i, leveys, pituus)) {
                    asettelu[j][i] = -1;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Aloittaa haun pituussuunnassa ja merkitsee ensimmäisen vastaan tulevan paikan,
     * johon aseteltava laatikko mahtuu pystyssuunnassa.
     * 
     * @param laatikko Aseteltava laatikko.
     * @return Palauttaa true jos paikka löytyi ja false jos lavalla ei ole enää tilaa.
     */
    private boolean merkitseSeuraavaPystypaikkaPituussuunnassa(int leveys, int pituus) {
        for (int i = 0; i < asettelu[0].length; i++) {
            for (int j = 0; j < asettelu.length; j++) {
                if (onkoTilaa(j, i, pituus, leveys)) {
                    asettelu[j][i] = -1;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Metodi, joka kertoo onko annetussa paikassa tarvittava määrä tilaa laatikolle.
     * 
     * @param y Kokeiltavan paikan vasemman yläkulman sijainti y-akselilla.
     * @param x Kokeiltavan paikan vasemman yläkulman sijainti x-akselilla.
     * @param leveys Riittävän tilan pituus x-akselilla.
     * @param pituus Riittävän tilan pituus y-akselilla
     * @return Palauttaa totuusarvon true, jos tilaa on ja false jos ei ole.
     */
    private boolean onkoTilaa(int y, int x, int leveys, int pituus) {
        int yLoppu = y + pituus;
        int xLoppu = x + leveys;
        
        if (yLoppu > asettelu.length || xLoppu > asettelu[0].length) {
            return false;
        }
        
        for (int i = y; i < yLoppu; i++) {
            for (int j = x; j < xLoppu; j++) {
                if (asettelu[i][j] != 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Kopioi ensimmäisenä parametrinä annetun taulukon arvot toisena parametrinä
     * annettuun taulukkoon.
     * 
     * @param lahde Taulukko, josta arvot kopioidaan.
     * @param kohde Taulukko, johon arvot kopioidaan.
     */
    private void kopioiTaulukko(int[][] lahde, int[][] kohde) {
        for (int i = 0; i < lahde.length; i++) {
            for (int j = 0; j < lahde[0].length; j++) {
                kohde[i][j] = lahde[i][j];
            }
        }
    }
    
    /**
     * Laskee kuinka monta laatikkoa lavalle voidaan asettaa päällekkäin annetun
     * korkeusrajan puitteissa.
     * 
     * @param laatikko Lavalle aseteltava laatikko.
     * @param lava Lava, jolle laatikot asetellaan.
     * @return Palauttaa kokonaisluvun, joka kertoo sallitun kerrosten määrän.
     */
    public int laskeKerrokset(Laatikko laatikko, Nelikulmio lava) {
        return lava.getKorkeus() / laatikko.getKorkeus();
    }
}
