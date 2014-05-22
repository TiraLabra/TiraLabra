package Tietorakenteet;

public class MinKeko {
    private Solmu[] keko;
    private int koko;
    
    public MinKeko(int hashSize) {
        this.keko = new Solmu[hashSize];
        this.koko = 0;
    }
    
    public Solmu[] getKeko() {
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
    
    public void lisaa(Solmu solmu) {
        this.koko++;
        if (this.koko > this.keko.length) { 
            tuplaaKoko();                   // ei pitäisi koskaan tapahtua Huffman-algoritmin yhteydessä
        }
        
        int i = this.koko - 1;
        
        while (i > 0 && keko[vanh(i)].getEsiintymat() > solmu.getEsiintymat()) {
            keko[i] = keko[vanh(i)];
            i = vanh(i);
        }
        keko[i] = solmu;
    }
    
    public Solmu poistaHuippuSolmu() {
        Solmu poistettava = keko[0];
        
        this.koko--;
        keko[0] = keko[this.koko];                      //    1
        heapify(0);                                     //  2   3
                                                        // 5 6 7 8
        return poistettava;
    }
    
    private void heapify(int i) {
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
    
    private void vaihda(int i, int j) {
        Solmu k = keko[i];
        
        keko[i] = keko[j];
        keko[j] = k;
    }
    
    
//    public void paivitaSolmujenOsoittimet() {
//        for (int i = 0; i < koko; i++) {
//            paivitaOsoittimet(i);
//        }
//    }
//    
//    private void paivitaOsoittimet(int i) {
//        Solmu solmu = keko[i];
//        
//        if (i > 0) {
//            solmu.setVanh(keko[vanh(i)]);
//        }
//        if (vasen(i) < koko) {
//            solmu.setVasen(keko[vasen(i)]);
//        }
//        if (oikea(i) < koko) {
//            solmu.setOikea(keko[oikea(i)]);
//        }
//    }
    
    private void tuplaaKoko() {
        Solmu[] uusi = new Solmu[this.keko.length * 2];
        System.arraycopy(keko, 0, uusi, 0, this.keko.length);
        keko = uusi;
    }
}
