/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman;

/**
 * A class that collects all the frequencies and stores them in a table that uses heap as a data structure. Heap ensures that the max value is always the root value
 * (going to be changed to minheap)
 * @author robertvalta
 */
public class FrequencyHeap {
    private int[] table;
    private int n;
    
    public FrequencyHeap(int[] table) {
        this.table = table;
    }
    
    /**
     * Builds the heap from a table
     * @param table 
     */
    
    public void buildHeap(int[] table) {
        n = table.length - 1;
        for (int i = n / 2; i >= 0; i--) {
            heapify(table, i);
        }
    }
    
    /**
     * swaps two values
     * @param i
     * @param j 
     */

    public void swap(int i, int j) {
        int k = i;
        i = j;
        j = k;
    }
    
    /**
     * Basic heapify algorithm.
     * @param table
     * @param i 
     */

    private void heapify(int[] table, int i) {
        int left = 1 + 2 * i;
        int right = 2 + 2 * i;
        int parent = i / 2;
        int largest = i;
        if (right <= n) {
            if (table[left] > table[right]) {
                largest = left;
            } else {
                largest = right;
            }
            if (table[i] < table[largest]) {
                swap(table[i], table[largest]);
                heapify(table, largest);
            }
        } else if (left == n && table[i] < table[left]) {
            swap(table[i], table[left]);
        }
    }

    public void printTable(int[] table) {
        for (int i : table) {
            System.out.println("Luotu: " + i);
        }
    }

    public void printHeap(int[] table) {
        for (int i : table) {
            System.out.println("Järjestetty: " + i);
        }
    }
    
    /**
     * Sorts the table by using heap data structure.
     * @param table 
     */

    public void heapSort(int[] table) {
        int[] heap = table;
        buildHeap(heap);

        for (int i = n; i > 0; i--) {
            swap(heap[0], heap[i]);
            n--;
            heapify(heap, 0);

        }
    }
}
