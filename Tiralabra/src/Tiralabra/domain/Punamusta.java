package Tiralabra.domain;

/** Toteuttaa punamustan puun.
 * Punamustapuu on itsestään tasapainottuva binäärihakupuu, joka käyttää hyväksen solmujen värjäämistä.
 * @author Pia Pakarinen
 */
public class Punamusta extends Puu{

    public Punamusta(int emo) {
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
