/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import MultiByteEntities.MultiByte;

/**
 * A Hashmap like structure with collision handling. Puts the given multibyte
 * entity into a bucket using its hashcode as a reference for the internal
 * hashing.
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
    
    private int[][] references;

    /**
     * Initialize the table to contain the specified number of buckets and a
     * 1000 bucket overflow for same hashes. This structure makes it very much
     * faster to locate a specific multibyte although it implies a list
     * structure as overflow.
     *
     */
    public MultiByteHashedTable(int size) {
        this.size = size;
        this.data = new MultiByte[size][1000];
        this.stats = new int[size];
        this.references = new int[size][1000];
    }
    
    public int[][] getReferences(){
        return this.references;
    }

    /**
     * A method to recall the statistics for hashings.
     *
     * @return
     */
    public int[] getStats() {
        int[] reduced = new int[3];
        for (int i = 0; i < stats.length; i++) {
            if (stats[i] != 0) {
                reduced[0]++;
                reduced[1] += stats[i];
                if (stats[i] > reduced[2]) {
                    reduced[2] = stats[i];
                }
            }
        }
        return reduced;
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
            if (!this.contains(multiByte)) {
                for (int i = 0; i < data[hash].length; i++) {
                    if (this.data[hash][i] == null) {
                        this.data[hash][i] = multiByte;
                        stats[hash]++;
                        references[hash][i]=1;
                        return true;
                    }
                }
            } else {
                this.references[hash][getIndex(hash, multiByte)]++;
            }
        }

        return false;
    }

    /**
     * Generic method for single hashing.
     * @param hashCode
     * @return 
     */
    private int getHash(int hashCode) {
        int hash = ((11 * hashCode + 13) % size);
        return Math.abs(hash);
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
        for (int i = 0; i < data[hash].length; i++) {
            if (this.data[hash][i] != null && this.data[hash][i].equals(multiByte)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns an index for the given multibyte in the subtable.
     * @param hash
     * @param mb
     * @return index for the multibyte or -1 if not found, which should never happen.
     */
    private int getIndex(int hash, MultiByte mb){
        for (int i = 0; i < data[hash].length; i++) {
            if (mb.equals(data[hash][i])){
                return i;
            }
        }
        return -1;
    }
}
