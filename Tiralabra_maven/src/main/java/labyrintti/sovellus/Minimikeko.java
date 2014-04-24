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
     * Ruudut-taulukon keon koko. Huom, voi olla pienempi kuin taulukonKoko.
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
        return (solmu - 1) / 2;
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
        return 2 * (solmu + 1);
    }

    /**
     * Huolehtii, että minimikeon ehto on voimassa annetusta solmusta alaspäin.
     *
     * @param solmu jota siirretään alaspäin keossa, kunnes kekoehto on taas
     * voimassa
     */
    private void heapify(int solmu) {
        while (true) {
            int vasen = vasenLapsi(solmu);
            if (vasen >= keonKoko) {
                break;
            }
            int oikea = oikeaLapsi(solmu);
            if (oikea < keonKoko) { // jos solmulla on oikea lapsi
                int pienin = etsiPienempiLapsi(vasen, oikea);
                if (ruudut[solmu].getEtaisyyksienSumma() > ruudut[pienin].getEtaisyyksienSumma()) {
                    vaihdaSolmujenPaikkaa(solmu, pienin);
                    solmu = pienin;
                } else {
                    break;
                }
            } else if (vasen == keonKoko - 1 && ruudut[solmu].getEtaisyyksienSumma() > ruudut[vasen].getEtaisyyksienSumma()) {
                vaihdaSolmujenPaikkaa(solmu, vasen);
                break;
            } else {
                break;
            }
        }
    }

    /**
     * Palauttaa indeksin, jonka solmun alun ja lopun etäisyyksien summa on
     * pienempi.
     *
     * @param vasen solmun vasemman lapsen indeksi
     * @param oikea solmun oikean lapsen indeksi
     * @return indeksi, jonka solmun arvo on pienempi
     */
    private int etsiPienempiLapsi(int vasen, int oikea) {
        int pienin = oikea;
        if (ruudut[vasen].getEtaisyyksienSumma() < ruudut[oikea].getEtaisyyksienSumma()) {
//        if (ruudut[vasen].getArvo() < ruudut[oikea].getArvo()) {
            pienin = vasen;
        }
        return pienin;
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
        ruudut[eka].setIndeksi(eka);
        ruudut[toka] = r;
        ruudut[toka].setIndeksi(toka);
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
                pohja.getRuutu(i, j).setIndeksi(i * pohja.getLeveys() + j);
            }
        }
    }

    /**
     * Tulostaa keon alkioiden koordinaatit. Huom, taulukossa voi olla enemmän
     * alkioita, kuin keossa.
     */
    public void tulosta() {
        for (int i = 0; i < keonKoko; i++) {
            System.out.println(ruudut[i].koordinaatit());
        }
    }

    /**
     *
     * @return merkkijono, johon on tallennettu keon alkiot
     */
    public String getAlkiot() {
        String koordinaatit = "";
        for (int i = 0; i < keonKoko - 1; i++) {
            koordinaatit += ruudut[i].koordinaatit() + " ";
        }
        koordinaatit += ruudut[keonKoko - 1].koordinaatit();
        return koordinaatit;
    }

    /**
     * Rakentaa minimikeon niin että kekoehto tulee voimaan.
     */
    public void rakennaKeko() {
        for (int i = keonKoko / 2 - 1; i >= 0; i--) {
            heapify(i);
//            heapify1(i);
        }
    }

    /**
     * Päivittää keon, kun jonkin ruudun etäisyysarvio muuttuu.
     *
     * @param paivitettava ruutu jonka etäisyysarvio on muuttunut.
     */
    public void paivitaRuutuKekoon(Ruutu paivitettava) {
        int solmu = paivitettava.getIndeksi();
        while (solmu > 0 && ruudut[vanhempi(solmu)].getEtaisyyksienSumma() > ruudut[solmu].getEtaisyyksienSumma()) {
            vaihdaSolmujenPaikkaa(solmu, vanhempi(solmu));
            solmu = vanhempi(solmu);
        }
    }

    public int getKeonKoko() {
        return keonKoko;
    }

    public Ruutu[] getRuudut() {
        return ruudut;
    }
//    /**
//     * Apumetodi suorituskykytestaukseen.
//     * @param solmu 
//     */
//    private void heapify1(int solmu) {
//         while (true) {
//            int vasen = vasenLapsi(solmu);
//            if(vasen>=keonKoko){
//                break;
//            }
//            int oikea = oikeaLapsi(solmu);
//            if (oikea < keonKoko) { // jos solmulla on oikea lapsi
//                int pienin = etsiPienin(vasen, oikea);
//                if (ruudut[solmu].getArvo() > ruudut[pienin].getArvo()) {
//                    vaihdaSolmujenPaikkaa(solmu, pienin);
//                    solmu = pienin;
//                } else {
//                    break;
//                }
//            } else if (vasen == keonKoko - 1 && ruudut[solmu].getArvo() > ruudut[vasen].getArvo()) {
//                vaihdaSolmujenPaikkaa(solmu, vasen);
//                break;
//            } else {
//                break;
//            }
//        }
//    }
}
