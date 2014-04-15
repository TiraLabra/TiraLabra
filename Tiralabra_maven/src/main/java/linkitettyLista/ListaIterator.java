package linkitettyLista;

import java.util.Iterator;


/**
 *
 * @param <T> 
 * @author Ilkimys
 */
public class ListaIterator<T> implements Iterator<T> {
    private Linkki nykyinen;

    /**
     *
     * @param nykyinen
     */
    public ListaIterator(Linkki<T> nykyinen) {
        this.nykyinen = nykyinen;
    }
    
    

    public boolean hasNext() {
        return nykyinen.getSeuraava() != null;
    }

    public T next() {
        if(!hasNext()) { return null; }
	        nykyinen = nykyinen.getSeuraava();
	        return (T) nykyinen.getData();
	    }

    public void remove() {
        
    }
    }


  

    
    

