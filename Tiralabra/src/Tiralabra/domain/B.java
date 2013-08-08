package Tiralabra.domain;

/** Toteuttaa B-puun.
 * B-puussa solmuilla voi olla useampia lapsia.
 * @author Pia Pakarinen
 */
public class B extends Puu{

    public B(int emo) {
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
