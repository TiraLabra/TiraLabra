import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;


/**
 *
 * @author maef
 */
public class LabyrintissaSuunnistus {
    
    
    public static void main(String[] args) throws FileNotFoundException {
        Kayttoliittyma kl = new Kayttoliittyma();
        SwingUtilities.invokeLater(kl);
        
    }
}
