package tira.list;

import java.util.Iterator;

/**
 *
 * @author joonaslaakkonen
 * Iteraattori-luokka listan läpikäyntiin.
 */
public class MyListIterator implements Iterator<Object> {
    
    private ListObject current;
    private ListObject last;

    public MyListIterator(ListObject head, ListObject tail) {
        this.current = head;
        this.last = tail;
    }
    
    /**
     * Metodi tarkistaa onko listalla käsittelemättömiä olioita jäljellä.
     * @return tieto onko valmista.
     */
    @Override
    public boolean hasNext() {
        return this.current != null;
    }
    
    /**
     * Metodi hakee seuraavan käsiteltävän olio.
     * @return olio.
     */
    @Override
    public Object next() {
        ListObject a = this.current;
        this.current = a.getNext();
        return a.getOlio();
    } 
}