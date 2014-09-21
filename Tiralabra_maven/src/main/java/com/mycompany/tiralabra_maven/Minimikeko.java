package com.mycompany.tiralabra_maven;

/**
* Minimikeko-tietorakenne. Keon pienin alkio säilytetään taulukon ensimmäisessä
* paikassa. Keon koko täytyy tietää etukäteen.
* 
* Keko esitetään binääripuuna ja metodien termistö vastaa sitä käsitystä.
* Keko on tallennettu taulukkona.
*/
public class Minimikeko {
    private Solmu[] solmut;
    private int kekokoko;

    /** 
     * @param    maksimikoko  kekoon mahtuvien solmujen määrä
    */
    public Minimikeko(int maksimikoko) {
        solmut = new Solmu[maksimikoko];
        this.kekokoko = -1;
    }

    public Solmu[] getSolmut() {
        return solmut;
    }
    
    /**
     * Palauttaa kysytyn kohdan vanhemman (parent) indeksin taulukossa
     * 
     * @param    kohta  indeksi, jonka vanhempi halutaan tietää
     * @return vanhemman indeksi
    */
    public int vanhempi (int kohta) {
        int vanhempi = (int) Math.floor(kohta / 2);
        return vanhempi;
    }
    
    /**
     * Palauttaa kysytyn kohdan vasemman lapsen indeksin taulukossa
     * 
     * @param    kohta  indeksi, jonka vasen lapsi halutaan tietää
     * @return vasemman lapsen indeksi
    */
    public int vasen (int kohta) {
        return 2*kohta;
    }
    
    /**
     * Palauttaa kysytyn kohdan oikean lapsen indeksin taulukossa
     * 
     * @param    kohta  indeksi, jonka oikea lapsi halutaan tietää
     * @return oikean lapsen indeksi
    */
    public int oikea (int kohta) {
        return 2*kohta+1;
    }
    
    /**
     * Heapify korjaa keon, jos se on sekaisin parametrina annetusta kohdasta.
     * Oletuksena parametrin kohdan lapset ovat ehjiä.
     * 
     * @param    kohta  indeksi, josta keko korjataan
    */
    public void heapify (int kohta) {
        int vasen = vasen(kohta);
        int oikea = oikea(kohta);
        int pienin;
        
        if(oikea <= kekokoko) { //molemmat lapset olemassa
            if (solmut[oikea] == null || solmut[vasen].getAlkuunLoppuunSumma() < solmut[oikea].getAlkuunLoppuunSumma()) { //valitaan lapsista pienempi 
                pienin = vasen;
            } else {
                pienin = oikea;
            }
            
            if (solmut[kohta].getAlkuunLoppuunSumma() > solmut[pienin].getAlkuunLoppuunSumma()) { //jos käsiteltävä solmu on suurempi kuin pienin lapsi, nostetaan pienempi lapsi käsiteltävän tilalle
                vaihdaKeskenaan(kohta, pienin);
                heapify(pienin);
            }
        } else if (vasen == kekokoko && solmut[vasen] != null && solmut[kohta].getAlkuunLoppuunSumma() > solmut[vasen].getAlkuunLoppuunSumma()) { //vain vasen lapsi olemassa ja sen arvo on pienempi kuin käsiteltävän solmun, joten nostetaan se käsiteltävän paikalle
            vaihdaKeskenaan(kohta, vasen);
        }
    }
    
    /**
     * Vaihtaa kahden solmun paikkaa keskenään taulukossa
     * 
     * @param    kohta1  ensimmäisen kohdan indeksi
     * @param    kohta2  toisen kohdan indeksi
    */
    public void vaihdaKeskenaan(int kohta1, int kohta2) {
        Solmu vanha = solmut[kohta1];
        solmut[kohta1] = solmut[kohta2];
        solmut[kohta2] = vanha;
    }
    
    /**
     * Palauttaa keon pienimmän solmun poistamatta sitä keosta
     * 
     * @return keon pienin solmu
    */
    public Solmu pienin() {
        return solmut[0];
    }
    
    /**
     * Palauttaa keon pienimmän solmun ja poistaa sen keosta
     * 
     * @return keon pienin solmu
    */
    public Solmu poistaPienin() {
        Solmu pienin = solmut[0];
        solmut[0] = solmut[kekokoko];
        kekokoko--;
        heapify(0);
        return pienin;
    }
    
    /**
     * Lisää solmun kekoon sellaiseen paikkaan, että keko ei mene sekaisin
     * 
     * @param    lisattava  kekoon lisättävä solmu
    */
    public void lisaa(Solmu lisattava) {
        kekokoko++;
        int i = kekokoko;
        while(i>0 && solmut[vanhempi(i)] == null || i>0 && solmut[vanhempi(i)].getAlkuunLoppuunSumma() > lisattava.getAlkuunLoppuunSumma()) {
            solmut[i] = solmut[vanhempi(i)];
            i = vanhempi(i);
        }
        solmut[i] = lisattava;
    }
    
    /**
     * Pienentää keossa olevan solmun arvoa (Astar-etäisyysarvio) ja nostaa sen 
     * oikealle paikalle
     * 
     * @param    solmu  solmu, jonka arvoa pienennetään
    */
    public void pienennaArvoa(Solmu solmu) {
        int kohta = etsiSolmunIndeksi(solmu);
        while(kohta>0 && solmut[vanhempi(kohta)].getAlkuunLoppuunSumma() > solmut[kohta].getAlkuunLoppuunSumma()) {
            vaihdaKeskenaan(kohta, vanhempi(kohta));
            kohta = vanhempi(kohta);
        }
    }
    
    /**
     * Etsii solmun indeksin taulukossa
     * 
     * @param    etsittava  solmu, jonka indeksi taulukossa halutaan tietää
     * @return etsittävän solmun indeksi taulukossa
    */
    public int etsiSolmunIndeksi(Solmu etsittava) { 
        int indeksi = -1;
        for(Solmu solmu : solmut) { //vie hirveästi aikaa
            indeksi++;
            if(solmu == etsittava) {
                return indeksi;
            }
        }
        return -1;
    }
}
