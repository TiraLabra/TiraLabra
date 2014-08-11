package huffmanKoodaaja.kasittely;



import huffmanKoodaaja.kasittely.tietorakenteet.Frekvenssitaulu;
import huffmanKoodaaja.kasittely.tietorakenteet.Puu;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Tiedosto {

    private BufferedInputStream lahde;
    private BufferedOutputStream kohde;
    private boolean pakkaus;
    private String sijainti;
    private String tallennus;

    public Tiedosto(String sijainti) {
        this(sijainti, sijainti);
    }

    public Tiedosto(String sijainti, String tallennus) {
        this.sijainti = sijainti;
        this.pakkaus = pakataanko(sijainti);
        if (pakkaus) {
            this.tallennus = tallennus + ".huff";
        } else {
            this.tallennus = tallennus.substring(0, tallennus.length() - 4);
        }
        try {
            FileInputStream lahdetiedosto = new FileInputStream(this.sijainti);
            FileOutputStream kohdetiedosto = new FileOutputStream(this.tallennus);
            this.lahde = new BufferedInputStream(lahdetiedosto);
            this.kohde = new BufferedOutputStream(kohdetiedosto);
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
    
    public boolean isPakkaus() {
        return pakkaus;
    }

    public String getSijainti() {
        return sijainti;
    }

    public String getTallennus() {
        return tallennus;
    }

}
