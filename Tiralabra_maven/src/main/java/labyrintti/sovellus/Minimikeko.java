package labyrintti.sovellus;

import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;

/**
 *
 * @author heidvill@cs
 */
public class Minimikeko {

    private int taulukonKoko;
    private int keonKoko;
    private Ruutu[] ruudut;
    private int pienin;
    private int[] lukuja;

    public Minimikeko(int talukonKoko) {
        this.taulukonKoko = talukonKoko;
        this.keonKoko = taulukonKoko;
        ruudut = new Ruutu[talukonKoko];
        lukuja = new int[taulukonKoko];
    }

    private int vanhempi(int solmu) {
        return solmu / 2;
    }

    private int vasenLapsi(int solmu) {
        return 2 * solmu+1;
    }

    private int oikeaLapsi(int solmu) {
        return 2 * solmu + 2;
    }

    private void heapify(int solmu) {
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
        } else if (vasen == keonKoko-1 && ruudut[solmu].getEtaisyyksienSumma() > ruudut[vasen].getEtaisyyksienSumma()) {
            vaihdaSolmujenPaikkaa(solmu, vasen);
        }
    }

    private void heapify1(int solmu) {
        int vasen = vasenLapsi(solmu);
        int oikea = oikeaLapsi(solmu);
        if (oikea < keonKoko) {
            if (lukuja[vasen] < lukuja[oikea]) {
                pienin = vasen;
            } else {
                pienin = oikea;
            }
            if (lukuja[solmu] > lukuja[pienin]) {
                int apu = lukuja[solmu];
                lukuja[solmu] = lukuja[pienin];
                lukuja[pienin] = apu;
                heapify1(pienin);
            }
        } else if (vasen == keonKoko-1 && lukuja[solmu] > lukuja[vasen]) {
            int apu = lukuja[solmu];
            lukuja[solmu] = lukuja[vasen];
            lukuja[vasen] = apu;
        }
    }

    public int poistaEka() {
        int pienin = lukuja[0];
        lukuja[0] = lukuja[keonKoko-1];
        keonKoko--;
        heapify1(0);
        return pienin;
    }

    public void setLukuja(int[] luvut) {
        for (int i = 0; i < luvut.length; i++) {
            lukuja[i] = luvut[i];
        }
    }

    public void rakennaKeko1() {
        for (int i = taulukonKoko / 2 - 1; i >= 0; i--) {
            heapify1(i);
        }
    }

    public void tulostaLuvut() {
        for (int i = 0; i < lukuja.length; i++) {
            System.out.println(lukuja[i]);
        }
    }

    private void vaihdaSolmujenPaikkaa(int eka, int toka) {
        Ruutu r = ruudut[eka];
        ruudut[eka] = ruudut[toka];
        ruudut[toka] = r;
    }

    public Ruutu pollPienin() {
        Ruutu pienin = ruudut[0];
        ruudut[0] = ruudut[keonKoko-1];
        keonKoko--;
        heapify(0);
        return pienin;
    }

    public void alustaTaulukko(Pohja pohja) {
        for (int i = 0; i < pohja.getKorkeus(); i++) {
            for (int j = 0; j < pohja.getLeveys(); j++) {
                ruudut[i * pohja.getLeveys() + j] = pohja.getRuutu(i, j);
            }
        }
    }

    public void setRuudut(Ruutu[] ruudut) {
        this.ruudut = ruudut;
    }

    public void tulosta() {
        for (int i = 0; i < ruudut.length; i++) {
            System.out.println(ruudut[i].koordinaatit());
        }
    }

    public void rakennaKeko() {
        for (int i = taulukonKoko / 2-1; i >= 0; i--) {
            heapify(i);
        }
    }
    
    public void paivitaRuutuKekoon(Ruutu paivitettava){
        for (int i = 0; i < ruudut.length; i++) {
            if(ruudut[i].equals(paivitettava)){
                ruudut[i]=paivitettava;
            }
        }
        rakennaKeko();
    }

    public int getKeonKoko() {
        return keonKoko;
    }
}
