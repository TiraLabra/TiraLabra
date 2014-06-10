package apurakenteet;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import tietorakenteet.*;

/**
 * Luokka joka tulkitsee annetun bittikarttakuvatiedoston ja luo siitä hakualueen.
 */
public class Kuvalukija {
    
    private static final String oletusnimi = "esim.bmp";
    
    BufferedImage kuva;
    
    /**
     * Luo Kuvalukijan ja tekee tarvittavat alustukset.
     * @param tiedostonimi tiedosto, josta kuva haetaan. 
     */
    public Kuvalukija(String tiedostonimi) {
        try {
            kuva = ImageIO.read(new File(tiedostonimi));
            System.out.println("Kuva luettu, kooltaan: " + kuva.getHeight() + "x" + kuva.getWidth());
        }
        catch (IOException e) {
            System.out.println("Virhe kuvatiedostoa luettaessa!");
        }
    }
    
    /**
     * Konstruktori, joka luo Kuvalukijan oletustiedostonimellä.
     */
    public Kuvalukija() {
        this(oletusnimi);
    }
    
    /**
     * Varsinainen kuvan läpikäyvä metodi, joka muodostaa Node-taulukon pikseleistä.
     * @return 
     */
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
     * TODO: Kustannusarvojen rajat ovat vielä vähän summittaisia, voisi säätää kohdalleen...
     * @param rgb
     * @return 
     */
    protected int laskekustannus(int rgb) {
        int kustannus = 0;
        int kirkkaus = kirkkausarvo(rgb);
        if (kirkkaus < 50)
            kustannus = 9;
        else if (kirkkaus < 100)
            kustannus = 5;
        else if (kirkkaus < 150)
            kustannus = 4;
        else if (kirkkaus < 175)
            kustannus = 3;
        else if (kirkkaus < 200)
            kustannus = 2;
        else if (kirkkaus < 225)
            kustannus = 1;
        else
            kustannus = 0;
        
//        if (rgb == Color.BLACK.getRGB()) {
//            kustannus = 9;
//        }
        
        return kustannus;
    }
    
    /**
     * Laskee annetulle värille jonkinlaisen arvion kirkkaudesta.
     * Tätä tietoa voidaan käyttää kustannuksen määrittelyyn.
     * Laskennassa käytetty kaava on useista nettilähteistä yhdistelemällä saatu arvio.
     * @param rgb väriarvo javan rbg-inttinä
     * @return kirkkausarvo väliltä 0-255, 0 = musta, 255=täyskirkkaus
     */
    protected int kirkkausarvo(int rgb) {
        Color c = new Color(rgb);
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();

        return (int) Math.sqrt(r*r*0.241 + g*g*0.691 + b*b*0.068);
    }
    
}