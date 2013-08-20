package Tiralabra;


/**
 *
 * @author virta
 */
import UI.UIscreen;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class Tiralabra {

    public static void main(String[] args) throws IOException {
        
        UIscreen screen = new UIscreen();
        SwingUtilities.invokeLater(screen);

    }

}
