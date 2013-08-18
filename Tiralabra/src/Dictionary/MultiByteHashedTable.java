/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import Encoding.MultiByteEncoder;
import MultiByteEntities.MultiByte;

/**
 * A Hashmap like structure with collision handling. Puts the given multibyte
 * entity into a bucket using its hashcode as a reference for the internal
 * hashing. Is interruptible from the MultiByteEncoder class.
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
     * Statistics for counting collisions.
     */
    private int[] stats;
    /**
     * Statistics about reference count for each multi-byte entry.
     */
    private int[][] references;
    /**
     * Kept updated so it doesn't have to be searched from data.
     */
    private int keyCount;
    /**
     * When rehashing do not call rehashing methods recursively.
     */
    private boolean rehashing;

    /**
     * Initialize the hashtable to contain 16 by 16 buckets.
     *
     */
    public MultiByteHashedTable() {
        this.size = 16;
        this.data = new MultiByte[16][16];
        this.stats = new int[16];
        this.references = new int[16][16];
        this.keyCount = 0;
        this.rehashing = false;
    }

    public int[][] getReferences() {
        return this.references;
    }

    public int getKeyCount() {
        return this.keyCount;
    }

    /**
     * A method to recall the statistics for hashings. Useful for evaluating the
     * size and number of the internal table.
     *
     * @return
     */
    public int[] getStats() {
        int[] reduced = new int[3];
        for (int i = 0; i < stats.length; i++) {
            if (MultiByteEncoder.interrupt) {
                break;
            }

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
     * Puts the specified multi-byte entity to a bucket in the table. If the
     * given multibyte is already in the table its reference count is increased.
     *
     * @param multiByte
     * @return true if the operation succeeded, false if the internal hashing
     * function returned an invalid hash which should never happen.
     */
    public boolean put(MultiByte multiByte) {

        int hash = this.getHash(multiByte.hashCode());

        if (hash < size) {
            if (!this.contains(multiByte)) {
                for (int i = 0; i < data[hash].length; i++) {
                    if (MultiByteEncoder.interrupt) {
                        break;
                    }

                    if (this.data[hash][i] == null) {
                        return insertIntoTable(hash, i, multiByte);
                    }
                }
                if (!rehashing) {
                    return rehashToGreaterSize(multiByte);
                }
            } else {
                this.references[hash][getIndex(hash, multiByte)]++;
                return true;
            }
        }

        return false;
    }

    /**
     * Rehashes the table to twice its original size with the new multibyte. If
     * the table size grows too big, throws an out of memory error.
     */
    private boolean rehashToGreaterSize(MultiByte mb) {
        rehashing = true;
        MultiByte[][] oldData = this.data;

        try {
            this.data = new MultiByte[size * 2][size * 2];
            this.references = new int[size * 2][size * 2];
            this.stats = new int[size * 2];
            this.size = size * 2;
            this.keyCount = 0;
        } catch (Error e) {
            throw new OutOfMemoryError("Hashtable too big, not enough memory.");
        }

        for (int i = 0; i < oldData.length; i++) {
            if (MultiByteEncoder.interrupt) {
                break;
            }

            for (int j = 0; j < oldData[i].length; j++) {
                if (oldData[i][j] != null) {
                    this.put(oldData[i][j]);
                } else {
                    break;
                }
            }
        }
        this.put(mb);
        rehashing = false;
        return true;
    }

    /**
     * Generic method for single hashing.
     *
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
            if (MultiByteEncoder.interrupt) {
                break;
            }

            if (this.data[hash][i] != null && this.data[hash][i].equals(multiByte)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an index for the given multibyte in the subtable.
     *
     * @param hash
     * @param mb
     * @return index for the multibyte or -1 if not found, which should never
     * happen.
     */
    private int getIndex(int hash, MultiByte mb) {
        for (int i = 0; i < data[hash].length; i++) {
            if (MultiByteEncoder.interrupt) {
                break;
            }

            if (mb.equals(data[hash][i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Use with caution. Purges from the data all multibytes that have not been
     * referenced the given amount. Rehashes according to that data.
     *
     * This method should always be run prior to requesting an array for
     * encoding to attain meaningful results.
     *
     * @param refCount
     */
    public void purgeAndClean(int byteWidth) {
        int minRefCount = byteWidth + 1;

        MultiByte[] multiBytesToKeep = new MultiByte[16];
        int toKeepIndex = 0;
        for (int i = 0; i < references.length; i++) {
            if (MultiByteEncoder.interrupt) {
                return;
            }

            if (references[i][0] != 0) {
                for (int j = 0; j < references[i].length; j++) {
                    if (MultiByteEncoder.interrupt) {
                        return;
                    }

                    if (references[i][j] >= minRefCount) {
                        multiBytesToKeep[toKeepIndex] = data[i][j];
                        toKeepIndex++;
                    } else if (references[i][j] == 0) {
                        break;
                    }
                    multiBytesToKeep = toKeepIndex == multiBytesToKeep.length ? enlargeTable(toKeepIndex, multiBytesToKeep) : multiBytesToKeep;
                }
            }
        }

        multiBytesToKeep = removeTrailingEmptySpace(multiBytesToKeep, toKeepIndex);

        rehashToData(multiBytesToKeep);
    }

    private MultiByte[] removeTrailingEmptySpace(MultiByte[] table, int index) {
        MultiByte[] newTable = new MultiByte[index];
        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = table[i];
        }
        return newTable;
    }

    /**
     * Builds a one-dimensional array of the contained multibyte entities, sorts
     * the array descending by referencecount, ie the number of times a
     * particular multibyte has been "called". Ignores data, the keys of which
     * would take more space than data itself and rehashes the formed data.
     *
     * @return an array of multibytes sorted descending to reference count.
     */
    public MultiByte[] getArray(int byteWidth) {
        int maxKeyCount = (int) Math.pow(2, ((byteWidth - 2) * 8));

        Object[][] objectArray = new Object[keyCount][2];
        int[] referenceCounts = new int[keyCount];
        int arrayIndex = 0;
        for (int i = 0; i < data.length; i++) {
            if (MultiByteEncoder.interrupt) {
                break;
            }

            if (data[i][0] != null) {
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i][j] != null) {
                        objectArray[arrayIndex][0] = data[i][j];
                        objectArray[arrayIndex][1] = references[i][j];
                        arrayIndex++;
                    } else {
                        break;
                    }
                }
            }
        }

        mergeSort(objectArray);

        MultiByte[] returnArray = extractMultiByteArray(objectArray, maxKeyCount);

        rehashToData(returnArray);

        return returnArray;
    }

    /**
     * Rehashes and rebuilds the entire table with the data in the array.
     *
     * @param array
     */
    private void rehashToData(MultiByte[] array) {
        this.data = new MultiByte[array.length][array.length];
        this.references = new int[array.length][array.length];
        this.stats = new int[array.length];
        this.size = array.length;
        this.keyCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (MultiByteEncoder.interrupt) {
                return;
            }

            this.put(array[i]);
        }
    }

    /**
     * Extracts a one-dimensional MultiByte table from the two-dimensional
     * Object array given as parameter.
     *
     * @param source
     * @return
     */
    private MultiByte[] extractMultiByteArray(Object[][] source, int maxCount) {
        int arraySize = maxCount < source.length ? maxCount : source.length;
        MultiByte[] array = new MultiByte[arraySize];
        for (int i = 0; i < array.length; i++) {
            array[i] = (MultiByte) source[i][0];
        }
        return array;
    }

    /**
     * Merge-sorts the given array, where sorting is done descending according
     * to the reference count. This method requires a two-dimensional array
     * where the first dimension dictates the size of the array, and the
     * subsequent subarrays 0 and 1 contain multibyte data and its reference
     * count respectively.
     *
     * @param source
     * @return
     */
    private Object[][] mergeSort(Object[][] source) {

        if (source.length < 2) {
            return source;
        }

        int modulo = source.length % 2;
        Object[][] firstPart = new Object[source.length / 2][2];
        Object[][] secondPart = new Object[source.length / 2 + modulo][2];

        cloneFrom(source, firstPart, 0, source.length / 2, 0);
        cloneFrom(source, secondPart, source.length / 2, source.length, 0);

        firstPart = mergeSort(firstPart);
        secondPart = mergeSort(secondPart);

        conquer(firstPart, secondPart, source);

        return source;
    }

    /**
     * Merges two arrays given as parameters 'firstPart' and 'secondPart' into
     * one sorted array, also given as a parameter 'destination'.
     *
     * @param firstPart
     * @param secondPart
     * @param destination
     */
    private void conquer(Object[][] firstPart, Object[][] secondPart, Object[][] destination) {
        int firstIndex = 0;
        int secondIndex = 0;
        int dataIndex = 0;

        while (firstIndex < firstPart.length && secondIndex < secondPart.length) {
            if (MultiByteEncoder.interrupt) {
                return;
            }

            if ((Integer) firstPart[firstIndex][1] > (Integer) secondPart[secondIndex][1]) {
                destination[dataIndex][0] = firstPart[firstIndex][0];
                destination[dataIndex][1] = firstPart[firstIndex][1];
                firstIndex++;
                dataIndex++;
            } else {
                destination[dataIndex][0] = secondPart[secondIndex][0];
                destination[dataIndex][1] = secondPart[secondIndex][1];
                secondIndex++;
                dataIndex++;
            }
        }

        if (firstIndex < firstPart.length) {
            cloneFrom(firstPart, destination, firstIndex, firstPart.length, dataIndex);
        } else if (secondIndex < secondPart.length) {
            cloneFrom(secondPart, destination, secondIndex, secondPart.length, dataIndex);
        }

    }

    /**
     * Clones objects from the 'source' array into the 'destination' array
     * starting in the source array at index 'from' and ending in 'until' and
     * starting at 'destIndex' in the destination array. The number of items
     * cloned must never exceed the capacity of the destination array, ie the
     * final value of destIndex must always end no later than the capacity of
     * destination -1.
     *
     * @param source
     * @param destination
     * @param from
     * @param until
     * @param destIndex
     */
    private void cloneFrom(Object[][] source, Object[][] destination, int from, int until, int destIndex) {
        for (int i = from; i < until; i++) {
            if (MultiByteEncoder.interrupt) {
                return;
            }

            destination[destIndex][0] = source[i][0];
            destination[destIndex][1] = source[i][1];
            destIndex++;
        }
    }

    /**
     * Inserts the given multibyte into the internal table at the hashed
     * location by subindex and updates references.
     *
     * @param hash
     * @param subIndex
     * @param multiByte
     * @return
     */
    private boolean insertIntoTable(int hash, int subIndex, MultiByte multiByte) {
        this.data[hash][subIndex] = multiByte;
        stats[hash]++;
        references[hash][subIndex] = 1;
        keyCount++;
        return true;
    }

    private MultiByte[] enlargeTable(int toKeepIndex, MultiByte[] multiBytesToKeep) {
        MultiByte[] newTable = new MultiByte[toKeepIndex * 2];
        for (int k = 0; k < multiBytesToKeep.length; k++) {
            newTable[k] = multiBytesToKeep[k];
        }
        multiBytesToKeep = newTable;
        return multiBytesToKeep;
    }
}
