package structures;

import map.Node;

/**
 * OrderedStack is linked list of nodes which are ordered by the f value of the nodes.
 * The stack works like a linked list.
 * 
 */
public class OrderedStack {

    /**
     * The first node in the stack.
     */
    private Node head;
    
    public OrderedStack(Node first) {
        this.head = first;
    }
    
    public OrderedStack() {
    }

    /**Adds a new node to the stack, ordered by the f value.
     * 
     * @param node 
     */
    public void add(Node node) {
        if (head == null) {
            head = node;
            return;
        }
        
        Node temp = node;
        Node prev = head;
        Node next = head.getNext();
        while (next != null) {
            if (next.getF() > temp.getF()) {
                break;
            }
            prev = next;
            next = next.getNext();
        }
        
        prev.setNext(temp);
        temp.setNext(next);
    }
    
    /**Pops the head of the stack.
     * 
     * @return 
     */
    public Node pop() {
        Node temp = head;
        head = head.getNext();
        temp.setNext(null);
        return temp;
    }
    
    /**Checks if there is a node in the stack with the given coordinates.
     * 
     * @param x
     * @param y
     * @return 
     */
    public boolean nodeInStack(int x, int y) {
        if (isEmpty()) {
            return false;
        }
        Node temp = head;
        while (temp != null) {
            if (temp.getX() == x && temp.getY() == y) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }
    
    /**Returns the node from the stack with the given coordinates if it exists.
     * Does not remove the node from the stack.
     * 
     * @param x
     * @param y
     * @return 
     */
    public Node get(int x, int y) {
        if (isEmpty()) {
            return null;
        }
        Node temp = head;
        while (temp != null) {
            if (temp.getX() == x && temp.getY() == y) {
                return temp;
            }
            temp = temp.getNext();
        }
        return null;
    }
    
    /**
     * Returns true if the stack is empty.
     * @return 
     */
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }
    
    /**
     * @param first the first to set
     */
    public void setFirst(Node first) {
        this.head = first;
    }
    
    /**Prints the nodes in the stack in the order they're in the stack.
     * 
     * @return 
     */
    public String printStack() {
        String output = "";
        Node temp = head;
        while(temp != null) {
            output = output + "(X:" + temp.getX() + "Y:" + temp.getY() + "F:" + temp.getF() + ") -> ";
            temp = temp.getNext();
        }
        output += "null";
        return output;
    }
}
