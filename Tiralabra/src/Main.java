
import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import logiikka.Kontinpurkaja;
/**
 *
 * @author albis
 * 
 * Kontinpurkaja 2000-ohjelman käynnistävä pääluokka. Ohjelmaa käytetään laskemaan
 * paras tapa asettaa laatikot lavalle ja tallentamaan saatu tulos muistiin.
 */
public class Main {
    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(new Kontinpurkaja());
        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
