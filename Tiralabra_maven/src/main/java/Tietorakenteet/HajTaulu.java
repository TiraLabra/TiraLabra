package Tietorakenteet;

/**
 * Riitt��k� ett� "hajautetaan" siten ett� jokainen ascii-merkki saa sit� vastaavan numeron joka on toinen ascii merkki?
 * T�ll�in ei tarvitsisi hajautusta laisinkaan mutta homma pelitt�isi silti.
 */
public class HajTaulu {
    private char[] merkit;
    private String[] arvot;
    
    public HajTaulu() {
        this.merkit = new char[32];
        this.arvot = new String[32];
    }
    
    public void lisaa(char merkki, String arvo) {}
    
    public void poista(char merkki, String arvo) {}
    
}
