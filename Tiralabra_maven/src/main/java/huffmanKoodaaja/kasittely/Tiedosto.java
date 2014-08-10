package huffmanKoodaaja.kasittely;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Tiedosto {

    private BufferedInputStream tiedosto;
    private BufferedOutputStream tallennus;
    private boolean toimenpide;

    public Tiedosto(String sijainti) {
        
    }

    public Tiedosto(String sijainti, String tallennus) {
        this.toimenpide = pakataanko(sijainti);
        try {
            FileInputStream lahde = new FileInputStream(sijainti);
            FileOutputStream kohde = new FileOutputStream(tallennus + ".huff");
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

}
