package tira.list;

/**
 *
 * @author joonaslaakkonen
 * Linkitettyyn listaan talletettava yksittäinen olio.
 */
public class ListObject {
    
    private ListObject next;
    private ListObject prev;
    private Object olio;

    public ListObject(Object o) {
        this.olio = o;
        this.next = null;
        this.prev = null;
    }
    
    public void setNext(ListObject n) {
        this.next = n;
    }
    
    public void setPrev(ListObject p) {
        this.prev = p;
    }
    
    public Object getOlio() {
        return this.olio;
    }
    
    public ListObject getNext() {
        return this.next;
    }
}
