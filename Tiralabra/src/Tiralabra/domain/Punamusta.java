package Tiralabra.domain;

/** Toteuttaa punamustan puun.
 * Punamustapuu on itsestään tasapainottuva binäärihakupuu, joka käyttää hyväksen solmujen värjäämistä.
 * @author Pia Pakarinen
 */
public class Punamusta implements Puu{
    
    /**
     * Puun juurisolmu.
     */
    SolmuPunamusta juuri;

    /** Luodaan uusi puu.
     * Juurisolmuksi asetetaan musta, annetun arvon sisältävä solmu.
     * @param emo juurisolmun arvo
     */
    public Punamusta(int emo) {
      juuri = new SolmuPunamusta(emo, false);
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
