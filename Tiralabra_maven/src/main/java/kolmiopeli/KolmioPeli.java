package kolmiopeli;

import java.awt.Color;
import kolmiopeli.UI.Kayttoliittyma;
import kolmiopeli.domain.Kolmio;
import kolmiopeli.logiikka.tiralabraAlgoritmit.Nopeustestaus;

/**
 * @author eemihauk
 */
public class KolmioPeli {

    public static void main(String[] args) {


        // Nopeustesti
//            Nopeustestaus n = new Nopeustestaus(100, 100);
//            n.algoritminKeskiarvoNopeus(500);
            
            
            
        // Peli    
        Kayttoliittyma k = new Kayttoliittyma();
        k.run();
        









    }
    static Kolmio[][] testausPelilauta = {{new Kolmio(Color.BLUE, 0, 0), new Kolmio(Color.BLUE, 0, 1), new Kolmio(Color.YELLOW, 0, 2), new Kolmio(Color.RED, 0, 3), new Kolmio(Color.RED, 0, 4)},
        {new Kolmio(Color.YELLOW, 1, 0), new Kolmio(Color.BLUE, 1, 1), new Kolmio(Color.BLUE, 1, 2), new Kolmio(Color.YELLOW, 1, 3), new Kolmio(Color.YELLOW, 1, 4)},
        {new Kolmio(Color.BLUE, 2, 0), new Kolmio(Color.YELLOW, 2, 1), new Kolmio(Color.RED, 2, 2), new Kolmio(Color.BLUE, 2, 3), new Kolmio(Color.GREEN, 2, 4)},
        {new Kolmio(Color.RED, 3, 0), new Kolmio(Color.YELLOW, 3, 1), new Kolmio(Color.GREEN, 3, 2), new Kolmio(Color.BLUE, 3, 3), new Kolmio(Color.BLUE, 3, 4)}};
}
