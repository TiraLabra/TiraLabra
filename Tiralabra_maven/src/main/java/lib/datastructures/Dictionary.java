package lib.datastructures;

import java.util.HashMap;
import lib.utils.ArrayUtils;

/**
 * LZW-algoritmien käyttämä hakemisto.
 * @author Iiro
 */
public class Dictionary {
    private final HashMap<String, String> dictionary; //Integer on boolean-arrayn hashCode(sisällön mukaan).
    private final int dictionarySize = 4095;
    private int nextIndex = 0;
    private boolean dictionaryFull;
    private boolean key;
    
    /**
     * @param key Mikäli true, avaimena käytetään tavutaulukon String-esitystä, muuten järjestysnumeroa. 
     */
    public Dictionary(boolean key){
        dictionaryFull = false;
        dictionary = new HashMap();
        this.key = key;
        for(int b=-128; b <= 127; b++){
            addToDictionary(new byte[]{(byte)b});
        }
    }
    /**
     * Lisää tavujonon hakemistoon.
     * @param bytes tavujono.
     */
    public void addToDictionary(byte[] bytes){
        if(!dictionaryFull){
            if(nextIndex < dictionarySize){
                if(key){
                    dictionary.put(ArrayUtils.byteArrayToString(bytes), String.valueOf(nextIndex));
                } else {
                    dictionary.put(String.valueOf(nextIndex),ArrayUtils.byteArrayToString(bytes));
                }    
                nextIndex++;
            } else {
                dictionaryFull = true;
            }
        }
    }
    
    public boolean contains(byte[] w){
        return dictionary.containsKey(ArrayUtils.byteArrayToString(w));
    }
    
    public boolean contains(int i){
        return dictionary.containsKey(String.valueOf(i));
    }
    
    public int get(byte[] w){
        return Integer.parseInt(dictionary.get(ArrayUtils.byteArrayToString(w)));        
    }
    
    public byte[] get(int i){
        return ArrayUtils.stringToByteArray(dictionary.get(String.valueOf(i)));
    }
    
    
}
