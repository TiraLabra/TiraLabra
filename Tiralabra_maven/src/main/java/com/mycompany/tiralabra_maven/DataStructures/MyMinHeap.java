package com.mycompany.tiralabra_maven.DataStructures;

import com.mycompany.tiralabra_maven.Node;

/**
 * Minimum heap for the implementation of priority queue
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

    /** Insert item into the heap */
    public void insert(Node insert) {
        if (size == table.length) {
            return;
        }

        int i = size;

        while ((i > 0) && table[parent(i)].compareTo(insert) > 0) {
            table[i] = table[parent(i)];
            i = parent(i);
            table[i] = insert;
        }

        size += 1;

    }

    /** Returns the smallest item from the heap */
    public Node min() {
        return table[0];
    }

    /** Deletes the smallest item from the heap */
    public Node deleteMinimum() {
        Node min = min();
        table[0] = table[size-1];
        size -= 1;
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


    /** Tests if the table is empty */
    public boolean isEmpty() {
        return table.length == 0;
    }

    /** Checks if the queue contains a node: */
    public boolean contains(Node node) {



        return false;
    }




}
