
import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import logiikka.Kontinpurkaja;
/**
 *
 * @author albis
 */
public class Main {
    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(new Kontinpurkaja());
        SwingUtilities.invokeLater(kayttoliittyma);
    }
}
