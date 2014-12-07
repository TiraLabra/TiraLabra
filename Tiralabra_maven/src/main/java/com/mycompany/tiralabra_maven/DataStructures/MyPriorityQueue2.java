package com.mycompany.tiralabra_maven.DataStructures;

import com.mycompany.tiralabra_maven.Node;

/**
 * Created by riena on 11/30/14.
 */
public class MyPriorityQueue2 {
    /** Table where entries are saves */
    private Node[] table;
    /** Maximum heapd of the minimum heap */
    private int heapsize;

    /** Constructor for the minimum heap */
    public MyPriorityQueue2(int heapsize) {
        this.heapsize = 0;
        this.table = new Node[heapsize +1];
    }

    /**
     * Inserts item into the heap
     * key should be cost of the node
     * @param insert the node to be inserted into heap
     */
    public void insert(Node insert) {
        if (heapsize == 0) {
            heapsize +=1;
            table[0] = insert;
            return;
        }

        //if (heapsize == table.length) {
        //    return;
        //}
        heapsize += 1;
        int i = heapsize;
        //parent[i] < insert
        //System.out.println(table.toString() + " " + table.length + " heapsize" + i);

        if (table[parent(i)] == null) {
            System.out.println("Null :D");
            //System.out.println("table[parent(i)] " + table[parent(i)].toString() + "insert " + insert );
        }
        if (table[parent(i)] != null) {
            //System.out.println("Null :D");
            System.out.println("table[parent(i)] " + table[parent(i)].getX() + "insert " + insert.getX() );
        }
        while ((i > 0) && table[parent(i)] != null && (table[parent(i)].compareTo(insert) == -1)) {
            table[i] = table[parent(i)];
            i = parent(i);
        }
        table[i-1] = insert;

    }

    /** Returns the smallest item from the heap */
    public Node min() {
        return table[0];
    }

    /** Removes and returns the smallest item from the heap */
    public Node deleteMinimum() {
        Node min = min();
        if (!isEmpty()) {
            System.out.println(heapsize);
            table[0] = table[heapsize -1];
            heapsize -= 1;
        }
        if (heapsize > 0) {
            heapify(heapsize);
        }
        return min;
    }

    /** Returns the parent of the current node */
    public int parent(int i) {
        return i/2;
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
        int smallest=i;
        // left < index
        System.out.println("heapify " + i);
        if (heapsize > left && (table[left].compareTo(table[i]) == -1)) {
            smallest = left;
        }
        if (heapsize > right && (table[right].compareTo(table[i]) == -1)) {
            smallest = right;
        }



        /**
         * if r ≤ A.heap-size
            if A[l] > A[r] largest = l
            else largest = r
            if A[i] < A[largest]
                vaihda A[i] ja A[largest]
                heapify(A,largest)
            elsif l == A.heap-size and A[i]<A[l]
                vaihda A[i] ja A[l]
         */

        /**
        if (right <= heapsize) {
            // left > right
            if (table[left].compareTo(table[right]) > 0) {
                smallest = left;
            } else {
                smallest = right;
            }
        }
        */

        if (smallest != i) {
            swap(table[i], table[smallest]);
            heapify(smallest);
        }



        /**
        if (table[i].compareTo(table[smallest]) > 0) {
            swap(table[i], table[smallest]);

            heapify(smallest);
        } else if (left == heapsize && table[i].compareTo(table[left]) > 0 ) {
            swap(table[i], table[left]);
        }*/

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
        return heapsize == 0;
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
            for (int i = 0; i < heapsize; i++)
                if (o.equals(table[i]))
                    return i;
        }
        return -1;
    }
/*




 */




}
