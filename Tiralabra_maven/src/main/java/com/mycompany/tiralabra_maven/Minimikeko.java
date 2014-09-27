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
     * Aikavaativuus: vakio
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
     * Aikavaativuus: vakio
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
     * Aikavaativuus: vakio
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
     * Pahin tapaus: kekoehto rikki ensimmäisessä solmussa ja solmu siirretään
     * puun viimeiseksi alkioksi. 
     * Solmu siirretään joko vasemman tai oikean lapsen tilalle ja jokaisella 
     * siirrolla valitsemattoman lapsen alkiot jätetään käsittelemättä. Täten 
     * käsiteltäviä alkioita on vain log(2, kekokoko).
     * Aikavaativuus: logaritminen keon alkioiden lukumäärän suhteen
     * Tilavaativuus: rekursion vuoksi logaritminen
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
     * Aikavaativuus: vakio
     * 
     * @param    kohta1  ensimmäisen kohdan indeksi
     * @param    kohta2  toisen kohdan indeksi
    */
    public void vaihdaKeskenaan(int kohta1, int kohta2) {
        Solmu vanha = solmut[kohta1];
        solmut[kohta1] = solmut[kohta2];
        solmut[kohta2] = vanha;
        if(solmut[kohta1] != null) {
            solmut[kohta1].setIndeksi(kohta1);
        }
        if(solmut[kohta2] != null) {
            solmut[kohta2].setIndeksi(kohta2);
        }
    }
    
    /**
     * Palauttaa keon pienimmän solmun poistamatta sitä keosta
     * 
     * Aikavaativuus: vakio
     * 
     * @return keon pienin solmu
    */
    public Solmu pienin() {
        return solmut[0];
    }
    
    /**
     * Palauttaa keon pienimmän solmun ja poistaa sen keosta
     * 
     * Aikavaativuus: logaritminen keon alkioiden suhteen (heapify:n vuoksi)
     * 
     * @return keon pienin solmu
    */
    public Solmu poistaPienin() {
        Solmu pienin = solmut[0];
        vaihdaKeskenaan(0, kekokoko); //solmut[0] = solmut[kekokoko];
        kekokoko--;
        heapify(0);
        return pienin;
    }
    
    /**
     * Lisää solmun kekoon sellaiseen paikkaan, että keko ei mene sekaisin
     * 
     * Pahin tapaus: lisättävä solmu on keon pienin solmu ja se täytyy kuljettaa
     * puun alhaalta ylös asti. Koska keko on binääripuu, käsiteltäviä alkioita 
     * on vain log(2, kekokoko) kuten heapify:ssä.
     * Aikavaativuus: logaritminen keon alkioiden lukumäärän suhteen
     * 
     * @param    lisattava  kekoon lisättävä solmu
    */
    public void lisaa(Solmu lisattava) {
        kekokoko++;
        int i = kekokoko;
        while(i>0 && solmut[vanhempi(i)] == null || i>0 && solmut[vanhempi(i)].getAlkuunLoppuunSumma() > lisattava.getAlkuunLoppuunSumma()) {
            vaihdaKeskenaan(i, vanhempi(i)); //solmut[i] = solmut[vanhempi(i)];
            i = vanhempi(i);
        }
        solmut[i] = lisattava;
        lisattava.setIndeksi(i);
    }
    
    /**
     * Pienentää keossa olevan solmun arvoa (Astar-etäisyysarvio) ja nostaa sen 
     * oikealle paikalle
     * 
     * Pahin tapaus: solmu on muutoksen jälkeen keon pienin solmu ja se täytyy 
     * kuljettaa puun alhaalta ylös asti. Koska keko on binääripuu, käsiteltäviä
     * alkioita on vain log(2, kekokoko) kuten heapify:ssä. Valitettavasti
     * solmun etsintään kuluu aikaa lineaarinen määrä
     * Aikavaativuus: n log n, missä n = keon alkioiden lukumäärä
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
     * Palauttaa solmun indeksin taulukossa
     * 
     * Aikavaativuus: vakio
     * 
     * @param    etsittava  solmu, jonka indeksi taulukossa halutaan tietää
     * @return etsittävän solmun indeksi taulukossa
    */
    public int etsiSolmunIndeksi(Solmu etsittava) { 

        if(etsittava == null) {
            return -1;
        }
        
        return etsittava.getIndeksi();

    }
}
