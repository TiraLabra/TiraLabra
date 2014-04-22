package linkitettyLista;

import java.util.Iterator;

/**
 *
 * @param <T> Listaan talletettavan datan tyyppi
 * @author Ilkimys
 */
public class Lista<T> implements Iterable<T> {

    private Linkki<T> ensimmainen;
    private int koko;

    /**
     * Luo listan ja sille tyhjän solmun ensimmäiseksi solmuksi
     */
    public Lista() {
        ensimmainen = new Linkki(null);
        koko = 0;
    }

    /**
     * Lisää listan jatkoksi halutun objektin
     * @param o
     */
    public void lisaaListaan(T o) {
            Linkki seuraava = ensimmainen;
            while (seuraava.getSeuraava() != null) {
                seuraava = seuraava.getSeuraava();
            }
            Linkki<T> lisattava = new Linkki<T>(o);
            lisattava.setEdellinen(seuraava);
            seuraava.setSeuraava(lisattava);
        
        koko++;
    }
    
    
    public T poll() {
        Linkki<T> palautettava = ensimmainen.getSeuraava();
        
        if (palautettava != null) {
            if (palautettava.getSeuraava() != null) {
                palautettava.getSeuraava().setEdellinen(ensimmainen);
            }
            
            ensimmainen.setSeuraava(palautettava.getSeuraava());
        }
        
        if (palautettava == null) {
            return null;
        } else {
            koko--;
            return palautettava.getData();
        }
        
        
    }

    /**
     * Palauttaa listan koon
     * @return kokonaisluku
     */
    public int getListanKoko() {
        return koko;
    }
    
    public boolean isEmpty() {
        return koko == 0;
    }



    public Iterator<T> iterator() {
        return new ListaIterator(ensimmainen);
       
    }
    
    
}
