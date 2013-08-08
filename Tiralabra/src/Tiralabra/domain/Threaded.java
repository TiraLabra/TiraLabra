package Tiralabra.domain;

/** Toteuttaa threaded-mallisen binääripuun.
 * Puun lehtien normaalisti null-arvoiset lapsi-pointterit osoittavat solmun seuraajaan (oikea) tai edeltäjään (vasen) sisäjärjestyksessä. 
 * @author Pia Pakarinen
 */
public class Threaded extends Puu{

    public Threaded(int emo) {
        super(emo);
    }
    
    @Override
    public String tulostaArvot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void insert(int key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(int key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Solmu search(int key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
