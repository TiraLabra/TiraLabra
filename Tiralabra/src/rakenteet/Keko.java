
package rakenteet;

/**
 *
 * @author maef
 * Ei varsinaisesti keko sanan todellisessa merkityksessä, mutta jotain vastaavaa.
 */
public class Keko<E> extends Lista{
     
    /**
     * 
     * @return Palauttaa päällimmäisen 
     */
    public E poll(){
        if (koko == 0) {
            return null;
        }
        E palautettava = (E) lista[koko - 1];
        koko--;

        return palautettava; 
    }
    
    /**
     * 
     * @param e
     * Lisää parametrin keon päälle.
     */
    public void put(E e){
        add(e);
    }
    
//    /**
//     * 
//     * @return Palauttaa alimmaisen. 
//     */
//    public E getFirst(){
//        return (E) get(0);
//    }
    
}
