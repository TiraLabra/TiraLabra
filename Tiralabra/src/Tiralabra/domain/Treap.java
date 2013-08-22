package Tiralabra.domain;

/** Toteuttaa keko/puu-Treap rakenteellisen puun.
 *
 * @author Pia Pakarinen
 */
public class Treap implements Puu{

    /**
     * Juurisolmu.
     */
    private SolmuTreap juuri;
    
    /**
     * Luo uuden puun, jolle luodaan juurisolmu annetulla arvolla.
     * @param emo juurisolmun arvo
     */
    public Treap(int emo) {
    }

    /**
     * Luo uuden tyhjän puun.
     */
    public Treap() {
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
    public boolean search(int key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
