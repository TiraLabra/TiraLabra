package datastructures;

/**
 * Represents a node in SortedIntegerList.
 * 
 * @author Sebastian Björkqvist
 */
public class IntegerNode {

    private Integer value;
    private IntegerNode prev;
    private IntegerNode next;

    IntegerNode(Integer value, IntegerNode prev, IntegerNode next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    public Integer getValue() {
        return value;
    }

    public IntegerNode getPrev() {
        return prev;
    }

    public IntegerNode getNext() {
        return next;
    }

    void setValue(Integer value) {
        this.value = value;
    }

    void setPrev(IntegerNode prev) {
        this.prev = prev;
    }

    void setNext(IntegerNode next) {
        this.next = next;
    }
    
    
}
