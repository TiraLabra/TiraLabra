package blokus.kaynnistin;

import blokus.gui.Kayttoliittyma;
import blokus.logiikka.Blokus;


/**
 * Käynnistää ohjelman
 * @author Simo Auvinen
 */
public class Main 
{
    /**
     *
     * @param args
     */
    public static void main( String[] args )
    {
        Blokus peli = new Blokus();    
        peli.aloitaVuoro();
        Kayttoliittyma g = new Kayttoliittyma(peli);
        
    }
}
