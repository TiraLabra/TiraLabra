
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
    private int etaisyys;
    
    public Labyrintti(String laby){
        try {
           labyrintti = ImageIO.read(new File("src/"+laby+".bmp")); 
        } catch (Exception e){
            System.out.println("Kuvaa ei löytynyt");
        }
        
    }
    
    public BufferedImage haeLaby(){
        return labyrintti;
    }
    
    public void asetaLattiaJaSeina(){
        lattia = -1;
        seina = -16777216;
    }
    
    public void etaisyys(int a, int b, int a1, int b1) {
        if(labyrintti.getRGB(a, b) == seina || labyrintti.getRGB(a1, b1) == seina) {
            return;
        }
        
        if (labyrintti.getRGB(a, b)==lattia && labyrintti.getRGB(a1, b1)==lattia) {
            etaisyys = 1;
        } else {
            etaisyys = 1000000;
        }
    }
    
    
}
