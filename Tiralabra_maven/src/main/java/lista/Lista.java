package lista;

import java.util.Iterator;

/**
 *
 * @author Ilkimys
 */
public class Lista implements Iterable<Object>, Iterator<Object> {

    private Linkki ensimmainen;
    private int koko;

    public Lista() {
        koko = 0;
    }

    public void lisaaListaan(Object o) {
        if (ensimmainen == null) {
            ensimmainen = new Linkki(o);
        } else {
            Linkki seuraava = ensimmainen.getSeuraava();
            while (seuraava != null) {
                seuraava = seuraava.getSeuraava();
            }
            seuraava.setSeuraava(new Linkki(o));
        }
        koko++;
    }

    public int getListanKoko() {
        return koko;
    }



    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object next() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Iterator<Object> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
