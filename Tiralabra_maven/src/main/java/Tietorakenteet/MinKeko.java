package Tietorakenteet;

/**
 * Tietorakenne kuvaa nimens‰ mukaisesti tietorakennetta "Minimikeko".
 * Keko on j‰rjestetty siten ett‰ keon huipulla on se solmu, joka esiintyi luetussa tekstitiedostossa v‰hiten.
 * 
 * Keko liittyy Huffman -algoritmin toteutuksessa etenkin HuffmanPuuhun.
 */

public class MinKeko {
    private Solmu[] keko;
    private int koko;
    
    /**
     * Keolle annetaan solmutaulukon pituudeksi se, kuinka monta erilaista merkki‰ luetussa (teksti)-tiedostossa
     * on ollut.
     * @param merkkeja 
     */
    
    public MinKeko(int merkkeja) {
        this.keko = new Solmu[merkkeja];
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
     * Metodi joka lis‰‰ uuden solmun minimikekoon.
     * Solmu sijoitetaan keon viimeiseksi alkioksi, jos sen esiintymien m‰‰r‰ (tekstitiedostossa) on suurempi kuin
     * viimeisen paikan vanhemman solmun esiintymien m‰‰r‰. Muussa tapauksessa solmua tulee "valuttaa" ylˆsp‰in
     * kohti keon huippua (koska kyseess‰ minimikeko solmujen esiintymism‰‰rien suhteen).
     * 
     * @param solmu 
     */
    
    public void lisaa(Solmu solmu) {
        this.koko++;
        if (this.koko > this.keko.length) { 
            tuplaaMahtuvienSolmujenMaara();
        }
        
        int i = selvitaLisattavanPaikanIndeksi(this.koko-1, solmu);
        keko[i] = solmu;
    }
    
    protected int selvitaLisattavanPaikanIndeksi(int i, Solmu solmu) {
        while (i > 0 && keko[vanh(i)].getEsiintymat() > solmu.getEsiintymat()) {
            keko[i] = keko[vanh(i)];
            i = vanh(i);
        }
        return i;
    }
    
    /**
     * Poistaa keosta sen huippusolmun pienent‰en samalla keon kokoa.
     * T‰m‰n j‰lkeen kutsutaan heapify-operaatiota,
     * jotta saadaan tietorakenne pidettyn‰ minimikekona poiston j‰lkeenkin.
     
     * @return "huippusolmu"
     */
    
    public Solmu poistaHuippuSolmu() {
        if (keko.length == 0) {
            throw new ArrayIndexOutOfBoundsException("Tyhj‰st‰ keosta ei voi poistaa alkiota.");
        }
        
        Solmu poistettava = keko[0];
        
        this.koko--;
        keko[0] = keko[this.koko];
        heapify(0);

        return poistettava;
    }
    
    /**
     * heapify -operaatio joka valuttaa indekss‰ i olevaa alkiota alasp‰in, jos sen lapsista ainakin toisella on pienempi
     * esiintymien m‰‰r‰. Funktio toimii rekursiivisesti.
     * @param i 
     */
    
    protected void heapify(int i) {
        int vasen = vasen(i);
        int oikea = oikea(i);
        
        if (oikea < koko) {
            int pienempi = pienempi(vasen, oikea);

            if (keko[i].getEsiintymat() > keko[pienempi].getEsiintymat()) {
                vaihda(i, pienempi);
                heapify(pienempi);
            }
        }
        
        else if (vasen == koko - 1 && keko[i].getEsiintymat() > keko[vasen].getEsiintymat()) {
            vaihda(i, vasen);
        }
    }
    
    /**
     * Valitsee pienemm‰ksi joko indeksin vasen tai oikea riippuen siit‰, kummassa paikassa kekoa
     * solmun esiintymien m‰‰r‰ on pienempi.
     * @param vasen
     * @param oikea
     * @return - solmun indeksi jonka esiintymien m‰‰r‰ pienempi
     */
    
    protected int pienempi(int vasen, int oikea) {
        int pienempi = vasen;
        if (keko[oikea].getEsiintymat() < keko[vasen].getEsiintymat()) {
            pienempi = oikea;
        }
        return pienempi;
    }
    
    /**
     * Vaihtaa solmut p‰itt‰in.
     * @param i
     * @param j 
     */
    
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
