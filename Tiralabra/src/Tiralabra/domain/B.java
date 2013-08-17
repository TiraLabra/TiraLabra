package Tiralabra.domain;

/** Toteuttaa 2-3 B-puun.
 * 2-3 B-puussa solmulla voi olla 1-2 arvoa, ja 0-3 lasta.
 * @author Pia Pakarinen
 */
public class B implements Puu{

    /**
     * Puun juurisolmu.
     */
    private SolmuB juuri;
    
    /**
     * Luo uuden 2-3 B-puun ja tälle juurisolmun annetulla arvolla.
     * @param emo juurisolmun arvo
     */
    public B(int emo) {
        juuri = new SolmuB(emo);
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
