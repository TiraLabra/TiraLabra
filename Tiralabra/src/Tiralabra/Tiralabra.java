package Tiralabra;


/**
 *
 * @author virta
 */
import UI.UIscreen;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.SwingUtilities;

public class Tiralabra {

    public static void main(String[] args) throws IOException {
        
        UIscreen screen = new UIscreen();
        SwingUtilities.invokeLater(screen);

//        File f = new File("joku");
//        Path d = f.toPath();
//        String uri = f.getCanonicalPath();
//        Path e = Paths.get(uri);
//        File a = new File(uri);
        
    }

}
