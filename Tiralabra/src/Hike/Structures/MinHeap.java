/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Hike.Structures;

import Hike.Graph.Node;

/**
 *
 * @author petri
 */
public class MinHeap {

    private HeapElement[] table;
    private int last;

    public MinHeap(int area) {
        last = -1;
        table = new HeapElement[area + 1];



    }

    public void insert(Node node) {
        last++;
        table[last] = new HeapElement(node, last);
        int i = last;
        while (i > 0 && table[parent(i)].getNode().getDistance() > node.getDistance()) {
            table[i] = table[parent(i)];
            i = parent(i);
        }
        table[i].setNode(node);



    }

    public void heapify(int i) {
        int r = right(i);
        int l = left(i);
        int smallest;

        if (r >= last) {
            if (table[l].getNode().getDistance() > table[r].getNode().getDistance()) {
                smallest = r;
            } else {
                smallest = l;
            }
            if (table[i].getNode().getDistance() > table[smallest].getNode().getDistance()) {
                swap(i, smallest);
                heapify(smallest);
            } else if (l == last && table[i].getNode().getDistance() > table[l].getNode().getDistance()) {
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

    private void swap(int i, int smallest) {
        HeapElement temp = table[i];
        table[i] = table[smallest];
        table[smallest] = temp;
    }

    public void printHeap() {
        for (int i = 0; i <= last; i++) {
            System.out.println(table[i].getNode().getDistance());
        }
    }

    public HeapElement removeMin() {
        HeapElement min = table[0];
        table[0] = table[last];
        last--;
        heapify(0);
        return min;
    }

    public void decHeap(int newValue, int i) {
        if (newValue < table[i].getNode().getDistance()) {
            table[i].getNode().setDistance(newValue);
            while (i > 0 && table[parent(i)].getNode().getDistance() > table[i].getNode().getDistance()) {
                swap(i, parent(i));
                i = parent(i);
            }
        }

    }

    public HeapElement getElement(int i) {
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
}
