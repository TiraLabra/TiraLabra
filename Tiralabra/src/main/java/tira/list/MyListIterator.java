package tira.list;

import java.util.Iterator;

/**
 *
 * @author joonaslaakkonen
 */
public class MyListIterator implements Iterator<Object> {
    
    private ListObject current;
    private ListObject last;

    public MyListIterator(ListObject head, ListObject tail) {
        this.current = head;
        this.last = tail;
    }

    @Override
    public boolean hasNext() {
        return this.current != null;
    }

    @Override
    public Object next() {
        ListObject a = this.current;
        this.current = a.getNext();
        return a.getOlio();
    }
    
}