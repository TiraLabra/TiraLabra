/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dictionary;

import MultiByteEntities.MultiByte;

/**
 * A class to assign specific integer values to MultiBytes for encoding.
 * @author virta
 */
public class SinlgeMultiByteIndexing {
    
    private MultiByteHashedTable hashTable;
    private MultiByte[] table;
    private final int singleSetSize = 250;
    
    private MultiByte[] primaryKeys;
    private MultiByte[][] secondaryKeys;
    private MultiByte[][][] tertiaryKeys;
    private MultiByte[][][][] quatenaryKeys;
    
    private int levels;
    
    public SinlgeMultiByteIndexing(MultiByteHashedTable table){
        this.hashTable = table;
    }
    
    public void generateIndexing(){
        
        this.table = hashTable.getArray();
        
        int keys = table.length;
        levels = 1;
        while (keys > 251){
            keys /= singleSetSize;
            levels++;
        }
        
        switch (levels){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
        
        
        
        primaryKeys = new MultiByte[250]; // 250 keys
        secondaryKeys = new MultiByte[250][250];  // 250 x 250 = 62 500
        tertiaryKeys = new MultiByte[250][250][250];  // 62 500 x 250 = 15 625 000
        quatenaryKeys = new MultiByte[250][250][250][250]; // 15 625 000 x 250 = 3 906 250 000
        
    }
    
}
