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
            seuraava.setSeuraava(new Linkki(o));
        
        koko++;
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
