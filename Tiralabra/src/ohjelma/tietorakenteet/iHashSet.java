/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohjelma.tietorakenteet;


/**
 *
 * @author kkivikat
 */
public class iHashSet<Kaari> {

    private iHashMap<Kaari, Object> taulu;
    private static final Object nykyinen = new Object();

    public iHashSet() {
        taulu = new iHashMap<Kaari, Object>();
    }

     /**
     * Lisää tauluun elementin oletuksella ettei se löydy jo taulusta.
     */
    public boolean add(Kaari e) {
        return taulu.put(e, nykyinen) == null;
    }

     /**
     * Palauttaa arvojen määrän.
     */
    public int size() {
        return taulu.getArvojenMaara();
    }
}
