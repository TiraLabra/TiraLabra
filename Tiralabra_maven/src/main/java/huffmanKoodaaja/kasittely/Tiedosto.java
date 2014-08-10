package huffmanKoodaaja.kasittely;

import huffmanKoodaaja.pakkaus.Frekvenssitaulu;
import huffmanKoodaaja.pakkaus.Puu;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Tiedosto {

    private BufferedInputStream tiedosto;
    private BufferedOutputStream tallennus;
    private boolean pakkaus;

    public Tiedosto(String sijainti) {
        this(sijainti, sijainti);
    }

    public Tiedosto(String sijainti, String tallennus) {
        this.pakkaus = pakataanko(sijainti);
        if (pakkaus) {
            tallennus = tallennus + ".huff";
        } else {
            tallennus = tallennus.substring(0, tallennus.length() - 4);
        }
        try {
            FileInputStream lahde = new FileInputStream(sijainti);
            FileOutputStream kohde = new FileOutputStream(tallennus);
            this.tiedosto = new BufferedInputStream(lahde);
            this.tallennus = new BufferedOutputStream(kohde);
        } catch (FileNotFoundException e) {
            System.out.println("Tiedostoa ei löytynyt.");
        }
        
    }

    private boolean pakataanko(String sijainti) {
        if (sijainti.length() < 6 || !sijainti.substring(sijainti.length() - 5).equals(".huff")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPakkaus() {
        return pakkaus;
    }

    public void lueTaulukoksi(Frekvenssitaulu taulukko) {
        
    }

    public void kirjoitaTaulukko(Frekvenssitaulu taulukko) {
        
    }

    public void kirjoitaPakattu(Puu puu) {
        
    }

    public void lueTaulukko(Frekvenssitaulu taulukko) {
        
    }

    public void kirjoitaPurettu(Puu puu) {
        
    }

}
