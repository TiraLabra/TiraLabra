/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiByteEntities;

/**
 * Abstracts the use of a multiple-byte single entity.
 *
 * @author virta
 */
public abstract class MultiByteAbstraction {

    /**
     * Data to be stored.
     */
    protected byte[] bytes;
    /**
     * Index to which new data is loaded.
     */
    protected int index;
    /**
     * The byte-width of current multiple-byte entity.
     */
    protected int width;

    public MultiByteAbstraction(int width) {
        this.width = width;
        this.index = 0;
        this.bytes = new byte[width];
    }

    /**
     *
     * @return returns the data carried by this entity.
     */
    public byte[] getBytes() {
        return this.bytes;
    }

    /**
     * Adds data to the array at the location marked by the index, which is then
     * incremented.
     *
     * @param data
     * @return true, if the array was not full, false if no more room in array.
     */
    public boolean addData(byte data) {
        if (index < width) {
            bytes[index] = data;
            index++;
            return true;
        }
        return false;
    }

    /**
     * The implementation checks the class of supplied object and then its'
     * bytes for a match.
     *
     * @param o
     * @return true if bytes match, false if not the same class or bytes
     * do not match.
     */
    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            MultiByte match = (MultiByte) o;
            return this.checkBytes(match);
        } else {
            return false;
        }
    }

    /**
     * Recoded to a generic hashCode generator.
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 11;
        for (int i = 0; i < bytes.length; i++) {
            
            hash += (((bytes[i])+11) * 97);
            
        }
     
        return hash;
    }
    
    /**
     * Checks for internal matches on the byte level.
     * @param toMatch
     * @return true if all bytes match.
     */
    private boolean checkBytes(MultiByte toMatch){
        byte[] match = toMatch.getBytes();
        if (match.length != this.bytes.length){
            return false;
        }
        for (int i = 0; i < match.length; i++) {
            if (match[i] != this.bytes[i]){
                return false;
            }
        }
        return true;
    }
}
