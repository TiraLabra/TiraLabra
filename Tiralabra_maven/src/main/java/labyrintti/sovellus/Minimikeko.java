package labyrintti.sovellus;

import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;

/**
 * Minimikeossa ensimmäisen Ruutu-alkion alun ja lopun etäisyyksien summa on
 * pienin.
 *
 * @author heidvill
 */
public class Minimikeko {

    /**
     * Ruudut-taulukon koko.
     */
    private int taulukonKoko;
    /**
     * Ruudut-talukon keon koko. Huom, voi olla pienempi kuin taulukonKoko.
     */
    private int keonKoko;
    /**
     * Taulukko, johon keon alkiot on tallennettu.
     */
    private Ruutu[] ruudut;

    /**
     *
     * @param talukonKoko pohjakartan leveys*korkeus
     */
    public Minimikeko(int talukonKoko) {
        this.taulukonKoko = talukonKoko;
        this.keonKoko = taulukonKoko;
        ruudut = new Ruutu[talukonKoko];
    }

    /**
     * Hakee solmun vanhemman indeksin.
     *
     * @param solmu indeksi, jonka vanhempi haetaan
     * @return vanhemman indeksi
     */
    private int vanhempi(int solmu) {
        return solmu / 2;
    }

    /**
     * Hakee solmun vasemman lapsen indeksin.
     *
     * @param solmu indeksi, jonka vasen lapsi haetaan
     * @return vasemman lapsen indeksi
     */
    private int vasenLapsi(int solmu) {
        return 2 * solmu + 1;
    }

    /**
     * Hakee solmun oikean lapsen indeksin.
     *
     * @param solmu indeksi, jonka oikea lapsi haetaan
     * @return oikean lapsen indeksi
     */
    private int oikeaLapsi(int solmu) {
        return 2 * solmu + 2;
    }

    /**
     * Huolehtii, että minimikeon ehto on voimassa annetusta solmusta alaspäin.
     *
     * @param solmu jota siirretään alaspäin keossa, kunnes kekoehto on taas
     * voimassa
     */
    private void heapify(int solmu) {
        int pienin = 0;
        int vasen = vasenLapsi(solmu);
        int oikea = oikeaLapsi(solmu);
        if (oikea < keonKoko) {
            if (ruudut[vasen].getEtaisyyksienSumma() < ruudut[oikea].getEtaisyyksienSumma()) {
                pienin = vasen;
            } else {
                pienin = oikea;
            }
            if (ruudut[solmu].getEtaisyyksienSumma() > ruudut[pienin].getEtaisyyksienSumma()) {
                vaihdaSolmujenPaikkaa(solmu, pienin);
                heapify(pienin);
            }
        } else if (vasen == keonKoko - 1 && ruudut[solmu].getEtaisyyksienSumma() > ruudut[vasen].getEtaisyyksienSumma()) {
            vaihdaSolmujenPaikkaa(solmu, vasen);
        }
    }

    /**
     * Vaihtaa parametrina annettujen solmujen paikkaa keossa.
     *
     * @param eka ensimmäisen solmun indeksi
     * @param toka toisen solmun indeksi
     */
    private void vaihdaSolmujenPaikkaa(int eka, int toka) {
        Ruutu r = ruudut[eka];
        ruudut[eka] = ruudut[toka];
        ruudut[toka] = r;
    }

    /**
     * Palauttaa keon juuren, joka on ruutu, jolla alun ja lopun etäisyyksien
     * summa on pienin. Poistaa palautetun alkion keosta ja järjestää keon
     * uudelleen.
     *
     * @return keon juuri
     */
    public Ruutu pollPienin() {
        Ruutu pienin = ruudut[0];
        ruudut[0] = ruudut[keonKoko - 1];
        keonKoko--;
        heapify(0);
        return pienin;
    }

    /**
     * Tallentaa 2-ulotteisen pohjataulukon ruudut 1-ulotteiseen keon talukkoon.
     *
     * @param pohja pohjakartta, jonka ruudut tallennetaan kekoon
     */
    public void alustaTaulukko(Pohja pohja) {
        for (int i = 0; i < pohja.getKorkeus(); i++) {
            for (int j = 0; j < pohja.getLeveys(); j++) {
                ruudut[i * pohja.getLeveys() + j] = pohja.getRuutu(i, j);
            }
        }
    }

    /**
     * Tulostaa keon alkiot. Huom, taulukossa voi olla enemmän alkioita, kuin keossa.
     */
    public void tulosta() {
        for (int i = 0; i < keonKoko; i++) {
            System.out.println(ruudut[i].koordinaatit());
        }
    }
    
    /**
     * 
     * @return merkkinon, johon on tallennettu keon alkiot
     */
    public String getAlkiot(){
        String koordinaatit = "";
        for (int i = 0; i < keonKoko-1; i++) {
            koordinaatit += ruudut[i].koordinaatit() + " ";
        }
        koordinaatit += ruudut[keonKoko-1].koordinaatit();
        return koordinaatit;
    }

    /**
     * Rakentaa minimikeon niin että kekoehto tulee voimaan.
     */
    public void rakennaKeko() {
        for (int i = keonKoko / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

//    /**
//     * Päivittää keon,
//     * @param paivitettava 
//     */
//    public void paivitaRuutuKekoon(Ruutu paivitettava) {
//        for (int i = 0; i < ruudut.length; i++) {
//            if (ruudut[i].equals(paivitettava)) {
//                ruudut[i] = paivitettava;
//            }
//        }
//        rakennaKeko();
//        int solmu = 0;
//        for (int i = 0; i < ruudut.length; i++) {
//            if (ruudut[i].equals(paivitettava)) {
//                solmu = i;
//                break;
//            }
//        }
//        while (solmu > 0 && ruudut[vanhempi(solmu)].getEtaisyyksienSumma() > ruudut[solmu].getEtaisyyksienSumma()) {
//            vaihdaSolmujenPaikkaa(solmu, vanhempi(solmu));
//            solmu = vanhempi(solmu);
//        }
//    }
    public int getKeonKoko() {
        return keonKoko;
    }

    public Ruutu[] getRuudut() {
        return ruudut;
    }

}
