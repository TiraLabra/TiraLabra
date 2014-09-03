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
    
    /**
     * Lisätään olio listaan luomalla uusi ListObject olio.
     * @param o lisättävä olio.
     */
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
    
    /**
     * Metodi hakee listalta parametrina annettua oliota.
     * @param o haettava olio.
     * @return tieto löytyikö.
     */
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
    
    /**
     * 
     * @return kertoo listan alkioiden määrän.
     */
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
    
    /**
     * Haetaan listalta viite olioon tietyllä paikalla.
     * @param index monesko listan objekti halutaan.
     * @return viite olioon mikäli lista ei ole tyhjä.
     * Kutsu koon yli aiheuttaa tutun Null Pointerin.
     */
    public Object get(int index) {
        if (this.empty()) {
            return null;
        }
        ListObject a = this.head;
        
        for (int i=0; i < index; i++) {
            a=a.getNext();
        }
        return a.getOlio();
    }
    
    /**
     * Tutkitaan onko haluttu olio listalla.
     * @param o haluttu olio.
     * @return tieto löytyykö olio vai ei. 
     */
    public boolean contains(Object o) {
        if (this.empty()) {
            return false;
        }
        ListObject p = this.head;
        while (p != null) {
            if (p.getOlio().equals(o)) {
                return true;
            } else {
                p = p.getNext();
            }
        }
        return false;
    }
    
    /**
     * Iteraattori listan läpikänytiin.
     * @return palauttaa uuden iteraattorin kutsujalle.
     */
    @Override
    public Iterator iterator() {
        return new MyListIterator(this.head, this.tail);
    }
}