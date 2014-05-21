package Tietorakenteet;

public class Solmu {
    private String avain;
    private int esiintymiskerrat;
    private Solmu vanh;
    private Solmu vasen;
    private Solmu oikea;
    
    public Solmu(String avain, int esiintymiskerrat) {
        this.avain = avain;
        this.esiintymiskerrat = esiintymiskerrat;
    }
    
    public String getAvain() {
        return this.avain;
    }
    
    public int getEsiintymat() {
        return this.esiintymiskerrat;
    }
    
    public void setVanh(Solmu solmu) {
        this.vanh = solmu;
    }
    
    public void setVasen(Solmu solmu) {
        this.vasen = solmu;
    }
    
    public void setOikea(Solmu solmu) {
        this.oikea = solmu;
    }
    
    public Solmu getVanh() {
        return this.vanh;
    }
    
    public Solmu getVasen() {
        return this.vasen;
    }
                    
    public Solmu getOikea() {
        return this.oikea;
    }
}
