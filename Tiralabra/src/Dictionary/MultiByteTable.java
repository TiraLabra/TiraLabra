/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import MultiByteEntities.MultiByte;

/**
 * Simple list like data structure for storing multi-byte data.
 * @author virta
 */
public class MultiByteTable {

    /**
     * The actual data.
     */
    private MultiByte[] data;
    
    /**
     * 
     * The index to which new data is added, also the count of items.
     */
    private int putIndex;

    /**
     * Initialize with default value 16 fields.
     */
    public MultiByteTable() {
        this.putIndex = 0;
        this.data = new MultiByte[16];
    }
    
    public int size(){
        return this.putIndex;
    }

    /**
     * Puts the specified multi-byte entity to the index position,
     * if the table is full, a new table twice the size is created with the previous data.
     * @param multiByte
     * @return true if the operation succeeded, false if the table grows too big and is not allowed.
     */
    public boolean put(MultiByte multiByte) {
        if (putIndex < data.length) {
            data[putIndex] = multiByte;
            putIndex++;
            return true;
        } else {
            try {
                MultiByte[] newTable = new MultiByte[data.length * 2];
                System.arraycopy(data, 0, newTable, 0, data.length);
                data = newTable;
                data[putIndex] = multiByte;
                putIndex++;
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * Checks the entire contents of the table for a matching multibyte.
     * @param multiByte
     * @return 
     */
    public boolean contains(MultiByte multiByte) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                MultiByte multiByte1 = data[i];
                if (multiByte1.equals(multiByte)) {
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    public MultiByte fetch(int index) {
        return this.data[index];
    }

    public MultiByte[] fetchArray() {
        return this.data;
    }
    
}
