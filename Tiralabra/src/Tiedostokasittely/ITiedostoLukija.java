
package Tiedostokasittely;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Rajapinta joka määrittää  lukuoperaatiot. Mahdollistaa valeobjektien käytön yksikkötesteissä.
 * 
 */
public interface ITiedostoLukija {

    void avaaTiedosto() throws FileNotFoundException;

    long koko();

    int lue(byte[] puskuri) throws IOException;

    void suljeTiedosto() throws IOException;
    
}
