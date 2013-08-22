
package Tiedostokasittely;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Rajapinta joka määrittää kirjoitusoperaatiot. Mahdollistaa valeobjektien käytön yksikkötesteissä.
 * 
 */
public interface ITiedostoKirjoittaja {

    void avaaTiedosto() throws FileNotFoundException;

    void kirjoita(byte[] kirjoitusPuskuri) throws IOException;

    long koko();

    void suljeTiedosto() throws IOException;
    
}
