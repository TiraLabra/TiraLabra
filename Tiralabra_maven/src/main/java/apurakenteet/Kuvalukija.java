
package apurakenteet;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import tietorakenteet.*;

/**
 *
 */
public class Kuvalukija {
    
    String filenimi = "esim.bmp";
    
    BufferedImage kuva;

    public Kuvalukija() {
        try {
            kuva = ImageIO.read(new File(filenimi));
            System.out.println("Kuva luettu, kooltaan: " + kuva.getHeight() + "x" + kuva.getWidth());
        }
        catch (IOException e) {
            System.out.println("Virhe kuvatiedostoa luettaessa!");
        }
    }

    public Node[][] muodostaAlue() {
        Node[][] nodet = new Node[kuva.getHeight()][kuva.getWidth()];
        for (int i =0; i < kuva.getHeight(); i++) {
            for (int j=0; j < kuva.getWidth(); j++) {
                int kustannus = laskekustannus(kuva.getRGB(i, j));
                nodet[i][j] = new Node(i, j, kustannus);
                
                //System.out.println("Lisätty node " + i + ", " + j);
            }
        }
        
        return nodet;
    }
    
    public int getKorkeus() {
        return kuva.getHeight();
    }
    
    public int getLeveys() {
        return kuva.getWidth();
    }

    /**
     * Laskee halutun rgb-värin perusteella kustannuksen kyseistä pistettä kohden.
     * TODO: Kesken, tarkoitus tunnistaa eri "tummuusasteita, ja luokitella sen mukaan"...
     * @param rgb
     * @return 
     */
    private int laskekustannus(int rgb) {
        int kustannus = 0;
        if (rgb == Color.BLACK.getRGB()) {
            kustannus = 9;
        }
        return kustannus;
    }
    
    
    
}