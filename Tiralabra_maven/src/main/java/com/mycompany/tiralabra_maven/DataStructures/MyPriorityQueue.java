package com.mycompany.tiralabra_maven.DataStructures;

import com.mycompany.tiralabra_maven.Node;


/** Implementation of priority queue
 *  Used for the open queue in the Astar algorithm.
 */
public class MyPriorityQueue {
    /** Table where entries are saved */
    private Node[] table;
    /** Maximum heap size of the minimum heap */
    private int heapsize;
    /** Checker for the first item */
    private boolean first;

    /** Constructor for the minimum heap */
    public MyPriorityQueue(int heapsize) {
        this.heapsize = 0;
        this.table = new Node[heapsize +1];
        this.first = true;
    }

    /**
     * Inserts item into the heap
     * key should be cost of the node
     * @param insert the node to be inserted into heap
     */
    public void insert(Node insert) {
        if (first) {
            table[0] = insert;
            first = false;
        }

        heapsize += 1;
        int i = heapsize;
        //table[parent(i)] > insert
        while ((i > 0) && table[parent(i)].compareTo(insert) == 1) {
            table[i] = table[parent(i)];
            i = parent(i);
        }
        table[i] = insert;

    }

    /** Returns the smallest item from the heap */
    public Node min() {
        return table[0];
    }

    /** Removes and returns the smallest item from the heap */
    public Node deleteMinimum() {
        System.out.println("minimum" + table[0].getX() + " nextmin" + table[1].getX());
        Node min = table[0];
        if (!isEmpty()) {
            table[0] = table[heapsize-1];
            heapsize -= 1;
            heapify(0);
        }
        return min;
    }


    /** Returns the parent of the current node */
    public int parent(int i) {
        return (i-1)/2;
    }

    /** Returns the left child of the current node */
    public int left(int i) {
        return 2*i+1;
    }
    /** Returns the right child of the current node */
    public int right(int i) {
        return 2*i+2;
    }

    /** A recursive method to heapify the heap: corrects the binary tree formation
     *  until there is nothing to be corrected
     *  @param i index of the table */
    public void heapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest;
        if (right <= heapsize-1) {
            //table[left] < table[right]
            if (table[right].compareTo(table[left]) == 1) {
                smallest = left;
            } else {
                smallest = right;
            }
            //table[i] > table[smallest]
            if (table[i].compareTo(table[smallest]) == 1 ) {
                /** manual swap: */
                Node help = table[i];
                table[i] = table[smallest];
                table[smallest] = help;
                heapify(smallest);
            }
        //table[i] < table[left]
        } else if (left == heapsize-1 && table[left].compareTo(table[i]) == 1) {
            /** manual swap: */
            Node help = table[i];
            table[i] = table[left];
            table[left] = help;
        }

    }
    /** A method which decreases cost of a specific item in the heap
     *
     * */
    public void decreaseCost(int i, int newCost) {
        if (newCost < table[i].getCost()) {
            table[i].setCost(newCost);
            //System.out.println("new cost " + table[i].getCost());
            //print();
            heapify(0);
        }
    }

    /** Tests if the table is empty */
    public boolean isEmpty() {
        return heapsize == 0;
    }

    /** Checks if the heap contains the element */
    public boolean contains(Node node) {
        return indexOf(node) != -1;
    }


    /** Finds the node's index in heap
     * @return index of the node
     * */
    private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i <= heapsize; i++)
                if (o.equals(table[i]))
                    return i;
        }
        return -1;
    }

    /**
     * A method to get the nodes
     * @return table
     */
    public Node[] getTable() {
        return table;
    }

    /** Remove a specific node from the heap by decreasing it's cost and then removing it as the minimum item on the heap
     *
     *
     * @param node Node to be removed
     */
    public void removeNode(Node node) {
        int i = indexOf(node);
        //System.out.println("indexof node " + i );
        //print();
        decreaseCost(i, 0);
        //print();
        //Node min = deleteMinimum();
        //print();
        //System.out.println("deleted node " + min.getX());

    }

    /**
     *
     * @return Size of the heap
     */
    public int getHeapSize() {
        return heapsize;
    }

    /**
     * Visual representation of the priority queue
     */
    public void print() {
        for (int i = 0; i < heapsize; i++) {
            System.out.print(table[i].getCost() + " ");
        }
        System.out.println("");

    }

}
