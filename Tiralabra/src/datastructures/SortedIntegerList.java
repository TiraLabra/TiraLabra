package datastructures;

/**
 * A list of integers that is always sorted from smallest to largest.
 * 
 * The list is implemented as a doubly linked list.
 * 
 * There is no method for removing elements from the list, since this
 * isn't needed.
 * 
 * @author Sebastian Björkqvist
 */
public class SortedIntegerList {

    private IntegerNode smallestNode;
    private IntegerNode largestNode;
    private int size;

    public SortedIntegerList() {
        this.smallestNode = null;
        this.largestNode = null;
        this.size = 0;
    }

    /**
     * Returns the smallest node of the list.
     * 
     * This does not remove the node from the list.
     * 
     * @return Smallest node.
     */
    public IntegerNode getSmallestNode() {
        return smallestNode;
    }

    /**
     * Returns the largest node of the list.
     * 
     * This does not remove the node from the list.
     * 
     * @return Largest node.
     */    
    public IntegerNode getLargestNode() {
        return largestNode;
    }

    /**
     * Returns the size (number of nodes) of the list.
     * 
     * @return The number of nodes in the list.
     */
    
    public int getSize() {
        return size;
    }
    
    /**
     * Adds an integer to the list.
     * 
     * By changing the value of the boolean beginTraversalFromSmallest it is possible
     * to determine how the list adds the new node, and thus make the insertion
     * faster.
     * 
     * @param value Integer to add
     * @param beginTraversalFromSmallest If true, starts traversing the list
     * from the smallest element. If false, starts from the largest.
     */
    public void addIntegerToList(Integer value, boolean beginTraversalFromSmallest) {
        size++;        
        if (smallestNode == null) {
            IntegerNode newNode = new IntegerNode(value, null, null);
            smallestNode = newNode;
            largestNode = newNode;
            return;
        }
        if (beginTraversalFromSmallest) {
            addIntegerToListFromSmallest(value);
        } else {
            addIntegerToListFromLargest(value);
        }
    }

    private void addIntegerToListFromSmallest(Integer value) {
        if (value <= smallestNode.getValue()) {
            IntegerNode newSmallest = new IntegerNode(value, null, smallestNode);
            smallestNode.setPrev(newSmallest);
            smallestNode = newSmallest;
            return;
        }
        IntegerNode smaller = smallestNode;
        IntegerNode larger = smallestNode.getNext();      
        
        while (larger != null && larger.getValue() < value) {
            IntegerNode newNext = larger.getNext();
            smaller = larger;
            larger = newNext;
        }
        
        // If we reached the end of the list, we've got a new largest element.
        if (larger == null) {
            IntegerNode newLargest = new IntegerNode(value, smaller, null);
            smaller.setNext(newLargest);
            largestNode = newLargest;
        } else {
            IntegerNode newNode = new IntegerNode(value, smaller, larger);
            smaller.setNext(newNode);
            larger.setPrev(newNode);
        }
    }

    private void addIntegerToListFromLargest(Integer value) {
        if (value >= largestNode.getValue()) {
            IntegerNode newLargest = new IntegerNode(value, largestNode, null);
            largestNode.setNext(newLargest);
            largestNode = newLargest;
            return;
        }
        IntegerNode larger = largestNode;
        IntegerNode smaller = largestNode.getPrev();      
        
        while (smaller != null && smaller.getValue() > value) {
            IntegerNode newSmaller = smaller.getPrev();
            larger = smaller;
            smaller = newSmaller;
        }
        
        // If we reached the end of the list, we've got a new smallest element.
        if (smaller == null) {
            IntegerNode newSmallest = new IntegerNode(value, null, larger);
            larger.setPrev(newSmallest);
            smallestNode = newSmallest;
        } else {
            IntegerNode newNode = new IntegerNode(value, smaller, larger);
            larger.setPrev(newNode);
            smaller.setNext(newNode);
        }        
    }
}
