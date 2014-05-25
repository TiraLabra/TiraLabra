/**
 * Huffmanin puuhun kuuluva solmu. Voi olla lehti tai sisäsolmu.
 */
class HuffmanSolmu implements Comparable<HuffmanSolmu>{
    /**
     * Viite solmun vasempaan lapseen.
     */
    private HuffmanSolmu vasen;
    /**
     * Viite solmun oikeaan lapseen.
     */
    private HuffmanSolmu oikea;
    /**
     * Solmun sisältämä merkki. Vain lehtisolmuilla.
     */
    private int merkki;
    /**
     * Solmusta lähtevän puun lehtien merkkien esiintymiskerrat tiedostossa.
     */
    private int maara;

    /**
     * Luo uuden solmun. Jos vasen ja oikea ovat null, on kyseessä lehti. Sisäsolmuilla merkkikoodi alustetaan negatiiviseksi.
     * @param merkkikoodi Merkin ASCII-koodi.
     * @param maara Merkin määrä tiedostossa.
     * @param vasen Solmun vasen lapsi.
     * @param oikea  Solmun oikea lapsi.
     */
    public HuffmanSolmu(int merkkikoodi, int maara, HuffmanSolmu vasen, HuffmanSolmu oikea) {
        this.merkki = merkkikoodi;
        this.maara = maara;
        this.vasen = vasen;
        this.oikea = oikea;
    }
    
    public int getMerkki() {
        return this.merkki;
    }
    
    public int getMaara() {
        return this.maara;
    }
    
    public HuffmanSolmu getVasen() {
        return this.vasen;
    }
    
    public HuffmanSolmu getOikea() {
        return this.oikea;
    }
    
    /**
     * Verrataan solmuja ensin merkkien määrän suhteen, jos nämä samat sitten merkin ASCII-koodin suhteen. 
     */

    @Override
    public int compareTo(HuffmanSolmu o) {
        if (this.maara - o.maara == 0) {
            return this.merkki - o.merkki;
        }
        return this.maara - o.maara;
    }
    
    
}
