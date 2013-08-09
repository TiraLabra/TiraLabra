/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import MultiByteEntities.MultiByte;

/**
 * Simple list like data structure for storing multi-byte data.
 *
 * @author virta
 */
public class MultiByteHashedTable {

    /**
     * The actual data.
     */
    private MultiByte[][] data;
    /**
     * The current size of the array.
     */
    private int size;
    /**
     * Statistics to keep, whether is deleted or not.
     */
    private int[] stats;

    /**
     * Initialize with default value equal to specified number of fields.
     */
    public MultiByteHashedTable(int size) {
        this.size = size;
        this.data = new MultiByte[size][1000];
        this.stats = new int[size];
    }

    /**
     * Puts the specified multi-byte entity to the index position, if the table
     * is full, a new table twice the size is created with the previous data.
     *
     * @param multiByte
     * @return true if the operation succeeded, false if the table grows too big
     * and is not allowed.
     */
    public boolean put(MultiByte multiByte) {

        int hash = this.getHash(multiByte.hashCode());

        if (hash < size) {
            for (int i = 0; i < data[hash].length; i++) {
                if (this.data[hash][i] == null) {
                    this.data[hash][i] = multiByte;
                    
                    return true;
                }
            }
        } 

        return false;
    }

    private int getHash(int hashCode) {
        int hash = ((11 * hashCode + 13) % size);
        return hash;
    }

    /**
     * Checks the contents of the subtable at the hash location for a matching
     * multibyte.
     *
     * @param multiByte
     * @return
     */
    public boolean contains(MultiByte multiByte) {
        int hash = this.getHash(multiByte.hashCode());
        if (this.data[hash][0] != null) {
            for (int i = 0; i < data[hash].length; i++) {
                if (this.data[hash][i].equals(multiByte)) {
                    return true;
                }
            }
        }
        return false;
    }
}
