
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


/**
 *
 * @author maef
 * 
 * Muutetaan labyrintin sisältävä kuvatiedosto javalle luettavaan muotoon
 * ja määrätään etäisyydet solmujen (kuvan koordinaattien) välille.
 */
public class Labyrintti {

    private BufferedImage labyrintti;
    private int lattia;
    private int seina;
    
    public Labyrintti(String laby){
        try {
           labyrintti = ImageIO.read(new File("src/"+laby+".bmp")); 
        } catch (Exception e){
            System.out.println("Kuvaa ei löytynyt");
        }
        asetaLattiaJaSeina();
    }
    
    public BufferedImage haeLaby(){
        return labyrintti;
    }
    
    private void asetaLattiaJaSeina(){
        lattia = -1; //Valkoinen
        seina = -16777216; //Musta
    }
    
    /**
     * 
     * @param x
     * Metodilla määritetään pisteen etäisyysarvo, eli kuinka pitkä matka on
     * kuljettava, että siihen voi päästä.
     */
    public int etaisyys(Solmu x) {
        if(labyrintti.getRGB(x.getA(), x.getB()) == lattia) {
            return 1;
        } else {
            return 1000000;
        }
    }

    public int getLattia() {
        return lattia;
    }

    public int getSeina() {
        return seina;
    }
    
    public int getWidth() {
        return labyrintti.getWidth();
    }
    
    public int getHeight() {
        return labyrintti.getHeight();
    }
}
