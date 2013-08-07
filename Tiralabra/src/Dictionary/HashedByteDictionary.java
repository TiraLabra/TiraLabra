/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import MultiByteEntities.MultiByte;

/**
 *
 * @author virta
 */
public class HashedByteDictionary {
    
    private MultiByte[] data;
//    private int byteWidth;
    
    public HashedByteDictionary(){
        this.data = new MultiByte[10000];
    }
    
    public boolean put(MultiByte multiByte){
        int hashCode = multiByte.hashCode();
        if (hashCode<data.length) {
            if (this.data[hashCode] == null){
                this.data[hashCode] = multiByte;
                return true;
            } else {
                return false;
            }
        } else {
            MultiByte[] newArray = new MultiByte[hashCode+1];
            System.arraycopy(this.data, 0, newArray, 0, this.data.length);
            this.data = newArray;
            this.data[hashCode] = multiByte;
            return true;
        }
        
    }
    
    public int contains(MultiByte multiByte){
        int hashCode = multiByte.hashCode();
        if (hashCode<this.data.length && this.data[hashCode] != null){
            return checkForByteMatch(hashCode, multiByte);
        }
        return -1;
    }
    
    public MultiByte fetch(int index){
        return this.data[index];
    }
    
    public MultiByte[] fetchArray(){
        return this.data;
    }

    private int checkForByteMatch(int hashCode, MultiByte multiByte) {
        byte[] stored = this.data[hashCode].getBytes();
        byte[] toMatch = multiByte.getBytes();
        
        for (int i = 0; i < toMatch.length; i++) {
            if (stored[i] != toMatch[i]){
                return 1;
            }
        }
        return 0;
    }
    
}
