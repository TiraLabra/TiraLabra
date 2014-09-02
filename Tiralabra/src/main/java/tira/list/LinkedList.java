package tira.list;

import java.util.Iterator;

/**
 *
 * @author joonaslaakkonen
 * Luokka korvaa Javan ArrayListin. Listaa käytetään oliosäilönä.
 *
 */
public class LinkedList <T> implements Iterable<T> {
    
    private ListObject head;
    private ListObject tail;
    
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }
    
    public boolean empty() {
        return this.head == null;
    }
    
    public void add (Object o) {
        
        ListObject added = new ListObject(o);
        
        if (this.empty()) {
            
            this.head = added;
            this.tail = added;
            
        } else {
            added.setNext(this.head);
            this.head.setPrev(added);
            this.head = added;
        }      
    }
    
    public ListObject search (Object o) {
        if (this.empty()) {
            return null;
        }
        ListObject p = this.head;
        while (p != null && !o.equals(p.getOlio())) {
            p = p.getNext();
        }
        return p;
    }
    
    public int size() {
        int i = 0;
        if (this.empty()) {
            return i;
        }
        ListObject p = this.head;
        while (p != null) {
            p = p.getNext();
            i++;
        }
        return i;
    }

    @Override
    public Iterator iterator() {
        return new MyListIterator(this.head, this.tail);
    }
}