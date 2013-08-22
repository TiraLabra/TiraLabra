package suunnistajat;


import java.awt.Graphics;
import rakenteet.*;
import verkko.Solmu;



/**
 *
 * @author maef
 */
public interface Suunnistaja {
    
    Lista<Solmu> etsi(Graphics g);
    
}
