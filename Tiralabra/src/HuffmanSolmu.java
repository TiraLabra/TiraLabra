
class HuffmanSolmu implements Comparable<HuffmanSolmu>{
    private HuffmanSolmu vasen;
    private HuffmanSolmu oikea;
    private int merkki;
    private int maara;

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

    @Override
    public int compareTo(HuffmanSolmu o) {
        if (this.maara - o.maara == 0) {
            return this.merkki - o.merkki;
        }
        return this.maara - o.maara;
    }
    
    
}
