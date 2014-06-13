/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Structures;

import Hike.Graph.Node;

/**
 *
 * MinHeap containing Nodes. Nodes also contain a index value, so that nodes can
 * be found in constant time. In this Heap, index 0 is used by the first node.
 */
public class MinHeap {

    private Node[] table;
    private int last;

    /*
     * Last is the index of the last node, -1 when list is empty.
     */
    public MinHeap(int area) {
        last = -1;
        table = new Node[area + 1];



    }

    /**
     * Inserts a node into the heap and fixed the heap. Also changed the index
     * values of the node.
     *
     * @param node
     */
    public void insert(Node node) {
        last++;
        node.setHeapIndex(last);
        table[last] = node;
        int i = last;
        while (i > 0 && table[parent(i)].getHeapValue() > node.getHeapValue()) {
            int indexI = table[i].getHeapIndex();
            int indexP = table[parent(i)].getHeapIndex();
            table[i].setHeapIndex(indexP);
            table[parent(i)].setHeapIndex(indexI);
            table[i] = table[parent(i)];

            i = parent(i);
            table[i] = node;

        }
        table[i] = node;




    }

    /**
     * Fixed the heap rules if they have been broken.
     *
     * @param i
     */
    public void heapify(int i) {
        int r = right(i);
        int l = left(i);
        int smallest;

        if (r <= last) {
            if (table[l].getHeapValue() > table[r].getHeapValue()) {
                smallest = r;
            } else {
                smallest = l;
            }
            if (table[i].getHeapValue() > table[smallest].getHeapValue()) {
                swap(i, smallest);
                heapify(smallest);
            } else if (l == last && table[i].getHeapValue() > table[l].getHeapValue()) {
                swap(i, l);
            }
        }
    }

    private int right(int i) {
        return (2 * i) + 2;
    }

    private int left(int i) {
        return (2 * i) + 1;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Swaps two nodes in the heap. Also swaps the index value of the nodes.
     *
     * @param i
     * @param smallest
     */
    private void swap(int i, int smallest) {
        int indexi = table[smallest].getHeapIndex();
        int indexs = table[i].getHeapIndex();
        Node temp = table[i];

        table[i] = table[smallest];
        table[smallest] = temp;
        table[i].setHeapIndex(indexs);
        table[smallest].setHeapIndex(indexi);
    }

    public void printHeap() {
        for (int i = 0; i <= last; i++) {
            System.out.println("D: " + table[i].getDistance() + " I: " + table[i].getHeapIndex());
        }
    }

    /**
     * Pops the smallest value and removes it from heap.
     *
     * @return
     */
    public Node removeMin() {
        Node min = table[0];
        table[0] = table[last];
        table[0].setHeapIndex(0);
        last--;
        heapify(0);
        return min;
    }

    /**
     * Decreases the distance value of a node, if the new value is smaller than
     * the old one. Node's heapIndex can be used to quickly find a nodes
     * position in heap.
     *
     * @param i
     * @param newValue
     */
    public void decHeap(int i, int newValue, int distanceGoal) {
        if (i > last) {

            return;
        }
        if (newValue <= table[i].getDistance()) {
            table[i].setDistance(newValue);
            
            while (i > 0 && table[parent(i)].getHeapValue() > table[i].getHeapValue()) {
                swap(i, parent(i));
                i = parent(i);
            }
        }

    }

    public Node getNode(int i) {
        return table[i];
    }

    public int getLast() {
        return last;
    }

    public boolean empty() {
        if (last == -1) {
            return true;
        }
        return false;
    }

    public int size() {
        return last + 1;
    }
}
