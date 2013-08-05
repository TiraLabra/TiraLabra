
import java.io.FileNotFoundException;


/**
 *
 * @author maef
 */
public class LabyrintissaSuunnistus {
    
    
    public static void main(String[] args) throws FileNotFoundException {
        
        Labyrintti labyrintti = new Labyrintti("labyrintti");
        int a = labyrintti.haeLaby().getRGB(0,0);
        
        System.out.println(a);
    }
}
