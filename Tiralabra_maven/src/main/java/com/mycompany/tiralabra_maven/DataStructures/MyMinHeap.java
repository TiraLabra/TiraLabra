package com.mycompany.tiralabra_maven.DataStructures;

import com.mycompany.tiralabra_maven.Node;

/**
 * Minimum heap for the implementation of priority queue:
 * methods are added but is not currently working properly.
 * Minimikeko
 *
 */
public class MyMinHeap {
    /** Table where entries are saves */
    private Node[] table;
    /** Maximum size of the minimum heap */
    private int size;

    /** Constructor for the minimum heap */
    public MyMinHeap(int size) {
        this.size = 0;
        this.table = new Node[size+1];
    }

    /**
     * Inserts item into the heap
     * @param insert the node to be inserted into heap
     */
    public void insert(Node insert) {
        //if (size == table.length) {
        //    return;
        //}
        size += 1;
        int i = size;
        //parent[i] < insert
        while ((i > 0) && table[parent(i)].compareTo(insert) == 0) {
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
        Node min = min();
        if (!isEmpty()) {
            System.out.println(size);
            table[0] = table[size-1];
            size -= 1;
        }
        if (size > 0) {
            heapify(size);
        }
        return min;
    }

    /** Returns the parent of the current node */
    public int parent(int i) {
        return (int)Math.floor(i/2);
    }

    /** Returns the left child of the current node */
    public int left(int i) {
        return 2*i;
    }
    /** Returns the right child of the current node */
    public int right(int i) {
        return (2*i)+1;
    }

    /** A recursive method to heapify the heap: corrects the binary tree formation
     *  until there is nothing to be corrected
     *  @param i index of the table */
    public void heapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest=0;
        if (right <= size) {
            if (table[left].compareTo(table[right]) > 0) {
                smallest = left;
            } else {
                smallest = right;
            }
        }

        if (table[i].compareTo(table[smallest]) > 0) {
            swap(table[i], table[smallest]);

            heapify(smallest);
        } else if (left == size && table[i].compareTo(table[left]) > 0 ) {
            swap(table[i], table[left]);
        }

    }

    /** Swaps two items */
    private void swap(Node i, Node j) {
        Node help = i;
        i = j;
        j = help;
    }
    private void swap(int i, int j) {
        int help = i;
        i = j;
        j = help;
    }


    /** Tests if the table is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Decreases the key value of the node
     * @param node to be decreased
     */
    public void heapDecKey(Node node) {
        int i = indexOf(node);

        if (i == -1) {
            return;
        }

        while(i > 0 && table[parent(i)].compareTo(table[i])>0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }


    /** Checks if the heap contains the element */
    public boolean contains(Node node) {
        return indexOf(node) != -1;
    }


    /** Finds the node's index in heap */
    private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++)
                if (o.equals(table[i]))
                    return i;
        }
        return -1;
    }
}
