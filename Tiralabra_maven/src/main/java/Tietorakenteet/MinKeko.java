package Tietorakenteet;

/**
 * Tietorakenne kuvaa nimensä mukaisesti tietorakennetta "Minimikeko".
 * Keko on järjestetty siten että keon huipulla on se solmu, joka esiintyi luetussa tekstitiedostossa vähiten.
 * 
 * Keko liittyy Huffman -algoritmin toteutuksessa etenkin HuffmanPuuhun.
 */

public class MinKeko {
    private Solmu[] keko;
    private int koko;
    
    /**
     * Keolle annetaan solmutaulukon pituudeksi se, kuinka monta erilaista merkkiä luetussa (teksti)-tiedostossa
     * on ollut.
     * @param hashSize 
     */
    
    public MinKeko(int hashSize) {
        this.keko = new Solmu[hashSize];
        this.koko = 0;
    }
    
    public Solmu[] getSolmut() {
        return this.keko;
    }
    
    public int getKoko() {
        return this.koko;
    }

    public int vanh(int i) {
        return (i - 1) / 2;
    }
    
    public int vasen(int i) {
        return 2 * i + 1;
    }
    
    public int oikea(int i) {
        return vasen(i) + 1;
    }
    
    /**
     * Metodi joka lisää uuden solmun minimikekoon.
     * Solmu sijoitetaan keon viimeiseksi alkioksi, jos sen esiintymien määrä (tekstitiedostossa) on suurempi kuin
     * viimeisen paikan vanhemman solmun esiintymien määrä. Muussa tapauksessa solmua tulee "valuttaa" ylöspäin
     * kohti keon huippua (koska kyseessä minimikeko solmujen esiintymismäärien suhteen).
     * 
     * @param solmu 
     */
    public void lisaa(Solmu solmu) {
        this.koko++;
        if (this.koko > this.keko.length) { 
            tuplaaMahtuvienSolmujenMaara();                   // ei pitäisi koskaan tapahtua Huffman-algoritmin yhteydessä
        }
        
        int i = this.koko - 1;
        
        while (i > 0 && keko[vanh(i)].getEsiintymat() > solmu.getEsiintymat()) {
            keko[i] = keko[vanh(i)];
            i = vanh(i);
        }
        keko[i] = solmu;
    }
    
    /**
     * Poistaa keosta sen huippusolmun pienentäen samalla keon kokoa.
     * Tämän jälkeen kutsutaan heapify-operaatiota,
     * jotta saadaan tietorakenne pidettynä minimikekona poiston jälkeenkin.
     
     * @return "huippusolmu"
     */
    
    public Solmu poistaHuippuSolmu() {
        Solmu poistettava = keko[0];
        
        this.koko--;
        keko[0] = keko[this.koko];
        heapify(0);

        return poistettava;
    }
    
    /**
     * heapify -operaatio joka valuttaa indekssä i olevaa alkiota alaspäin, jos sen lapsista ainakin toisella on pienempi
     * esiintymien määrä. Funktio toimii rekursiivisesti.
     * @param i 
     */
    
    protected void heapify(int i) {
        int vasen = vasen(i);
        int oikea = oikea(i);
        
        if (oikea < koko) {
            int pienempi = vasen;
            
            if (keko[oikea].getEsiintymat() < keko[vasen].getEsiintymat()) {
                pienempi = oikea;
            }
            
            if (keko[i].getEsiintymat() > keko[pienempi].getEsiintymat()) {
                vaihda(i, pienempi);
                heapify(pienempi); 
            }
        }
        
        else if (vasen == koko - 1 && keko[i].getEsiintymat() > keko[vasen].getEsiintymat()) {
            vaihda(i, vasen);
        }
    }
    
    protected void vaihda(int i, int j) {
        Solmu k = keko[i];
        
        keko[i] = keko[j];
        keko[j] = k;
    }
    
    protected void tuplaaMahtuvienSolmujenMaara() {
        Solmu[] uusi = new Solmu[this.keko.length * 2];
        System.arraycopy(keko, 0, uusi, 0, this.keko.length);
        keko = uusi;
    }
}
