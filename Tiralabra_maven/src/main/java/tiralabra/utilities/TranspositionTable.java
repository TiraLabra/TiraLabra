/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.math.BigInteger;

/**
 * TODO
 *
 * @author atte
 */
public class TranspositionTable {

    private BoardValues[] table;
    private int size;

    public TranspositionTable() {
        table = new BoardValues[10267];
        size = 0;
    }

    public TranspositionTable(int initialCapacity) {
        table = new BoardValues[initialCapacity];
        size = 0;
    }

    public boolean isPrimeNumber(int n) {
        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean put(BoardValues values) {
        return put(values, true);
    }

    private boolean put(BoardValues values, boolean increaseSize) {
        int i = 0;
        while (i < table.length) {
            int j = hashKey(values.hash, i);

            if (table[j] == null) {
                table[j] = values;

                if (increaseSize) {
                    size++;
                    growCapacity();
                }
                return true;
            } else if (table[j].equals(values)) {
                if (table[j].depth > values.depth) {
                    table[j] = values;
                }
                return true;
            }
            i++;
        }
        return false;
    }
    
    public BoardValues get(BigInteger hash) {
        int i = 0;
        
        while (i < table.length) {
            int j = hashKey(hash, i);
            
            if (table[j] != null && table[j].hash.equals(hash)) {
                return table[j];
            }
            i++;
        }
        
        
        return null;
    }

    private int hashKey(BigInteger hash, int i) {
        return (hashKeyOne(hash) + i * hashKeyTwo(hash)) % table.length;
    }

    private int hashKeyOne(BigInteger hash) {
        return Math.abs(hash.intValue() % table.length);
    }

    private int hashKeyTwo(BigInteger hash) {
        return Math.abs(1 + hash.intValue() % (table.length - (table.length / 10)));
    }

    public void growCapacity() {
//        System.out.println("size: " + size + ", length: " + table.length);
        if (size < table.length * 0.60) {
            return;
        }
        BoardValues[] newTable = new BoardValues[table.length * 2 + 1];
        BoardValues[] oldTable = table;

        this.table = newTable;

        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                put(oldTable[i], false);
            }
        }
    }

    public BoardValues[] getTable() {
        return table;
    }
}
