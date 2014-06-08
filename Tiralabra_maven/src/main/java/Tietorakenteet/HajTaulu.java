package Tietorakenteet;

/**
 * Riittääkö että "hajautetaan" siten että jokainen ascii-merkki saa sitä vastaavan numeron joka on toinen ascii merkki?
 * Tällöin ei tarvitsisi hajautusta laisinkaan mutta homma pelittäisi silti.
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
