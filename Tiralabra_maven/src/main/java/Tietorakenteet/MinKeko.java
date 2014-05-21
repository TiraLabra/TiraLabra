package Tietorakenteet;

public class MinKeko {
    private Solmu[] keko;
    private int koko;
    
    public MinKeko(int hashSize) {
        this.keko = new Solmu[hashSize];
        this.koko = 0;
    }
    
    public int vanh(int i) {
        return (i/2);
    }
    
    public int vasen(int i) {
        return 2 * i;
    }
    
    public int oikea(int i) {
        return 2 * i + 1;
    }
    
    public void lisaa(Solmu solmu) {
        this.koko++;
        if (this.koko > this.keko.length) { 
            tuplaaKoko();                   // ei pitäisi koskaan tapahtua
        }
        
        int i = this.koko - 1;
        
        while (i > 0 && keko[vanh(i)].getEsiintymat() > solmu.getEsiintymat()) {
            keko[i] = keko[vanh(i)];
            i = vanh(i);
        }
        keko[i] = solmu;
    }
    
    public void paivitaSolmujenOsoittimet() {
        for (int i = 0; i < koko; i++) {
            paivitaOsoittimet(i);
        }
    }
    
    private void paivitaOsoittimet(int i) {
        Solmu solmu = keko[i];
        
        if (i > 0) {
            solmu.setVanh(keko[vanh(i)]);
        }
        if (vasen(i) < koko) {
            solmu.setVasen(keko[vasen(i)]);
        }
        if (oikea(i) < koko) {
            solmu.setOikea(keko[oikea(i)]);
        }
    }
    
    private void tuplaaKoko() {
        Solmu[] uusi = new Solmu[this.keko.length * 2];
        System.arraycopy(keko, 0, uusi, 0, this.keko.length);
        keko = uusi;
    }
}
