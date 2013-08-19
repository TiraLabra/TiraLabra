/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Encoding;

import Utilities.*;
import MultiByteEntities.MultiByte;

/**
 * This decoding mechanism takes apart the encoded data and produces a machine readable byte array.
 * Implements the runnable interface for multithreading.
 * @author virta
 */
public class MultiByteDecoder implements Runnable{

    /**
     * The entirety of data given as parameter to be decoded.
     */
    private byte[] combinedDataAndKeys;
    
    /**
     * The keys extracted from the encoded data.
     */
    private byte[] encodedKeys;
    
    /**
     * The data that is encoded.
     */
    private byte[] encodedData;
    
    /**
     * Contains the last sequence of data that was unencoded.
     */
    private byte[] remainder;
    
    /**
     * The keys that have been decoded.
     */
    private MultiByte[] keys;
    
    /**
     * The bytewidth of encoded data.
     */
    private int byteWidth;
    
    /**
     * How many keys were encoded in total.
     */
    private int keyCount;
    
    /**
     * The final decoded data.
     */
    private byte[] decodedData;
    
    /**
     * Where in the encoded data array to start the decoding process.
     */
    private int encodedDataStartingIndex;
    
    private int decodingIndex;
    
    /**
     * Constructs a decoder with the specified data.
     * @param data 
     */
    public MultiByteDecoder(byte[] data){
        this.combinedDataAndKeys = data;
        this.encodedDataStartingIndex = 0;
        this.decodingIndex = 0;
    }

    /**
     * Enables multiple instances of decoding to take place in separate threads.
     */
    @Override
    public void run() {
        splitDataAndKeys();
        decodeKeys();
        extractRemainder();
        decodeData();
    }
    
    public MultiByte[] getKeys(){
        return this.keys;
    }
    
    public byte[] getDecodedData(){
        return this.decodedData;
    }
    
    /**
     * Decodes from the encoded data all data with the encoded keys.
     */
    private void decodeData(){
        this.decodedData = new byte[combinedDataAndKeys.length];
        int decodeIndex = 1 + remainder.length;
        int encodingIndex = encodedDataStartingIndex;
        while (encodingIndex < encodedData.length){
            byte[] prefixByte = new byte[] { encodedData[encodingIndex] };
            int prefixInteger = IntegerConverter.ByteToInteger(prefixByte);
            
            if (prefixInteger<170){
                int keyWidth = prefixInteger/10;
                int runLength = prefixInteger - keyWidth;
                
            } else {
                int runLength = prefixInteger-169;
                
            }
        }
    }
    
    private void decodeAtIndex(int dataIndex, int runLength, int keyWidth){
        for (int i = dataIndex; i < dataIndex+runLength; i+=keyWidth) {
            byte[] keyArray = new byte[keyWidth];
            for (int j = 0; j < keyWidth; j++) {
                keyArray[j] = encodedData[i+j];
            }
            int key = IntegerConverter.ByteToInteger(keyArray);
            MultiByte mb = keys[key];
        }
    }
    
    private void insertIntoDecodedData(byte[] data){
        if (decodingIndex+data.length>=decodedData.length){
            decodedData = ArrayUtilities.expandArray(decodedData);
        }
        ArrayUtilities.encodeIntoArray(data, decodedData, decodingIndex);
    }
    
    /**
     * Extracts from the encoded data array the remainder of data, that is unencoded.
     */
    private void extractRemainder(){
        byte[] remainderLengthArray = new byte[]{ encodedData[0] };
        int remainderLength = IntegerConverter.ByteToInteger(remainderLengthArray);
        remainder = new byte[remainderLength];
        
        for (int i = 0; i < remainderLengthArray.length; i++) {
            remainder[i] = encodedData[i+1];
        }
        encodedDataStartingIndex += 1 + remainderLength;
    }
    
    /**
     * Decodes the set of keys that was used to encode the data.
     */
    private void decodeKeys(){
        keys = new MultiByte[keyCount];
        int keyPutIndex = 0;
        for (int i = 0; i < encodedKeys.length; i+=byteWidth) {
            keys[keyPutIndex] = ArrayUtilities.makeMultiByte(i, encodedKeys, byteWidth);
            keyPutIndex++;
        }
    }
    
    /**
     * Calls appropriate methods to split the header, keys and data from the combined data.
     */
    private void splitDataAndKeys(){
        byte[] header = extractHeader();
        byteWidth = extractByteWidth(header);
        keyCount = extractKeyCount(header);
        encodedDataStartingIndex = header.length;
        extractEncodedKeys(header, keyCount);
        encodedDataStartingIndex += encodedKeys.length;
        extractEncodedData(header, keyCount);
    }

    /**
     * Extracts from the combined data the header which contains information about bytewidth and number of keys originally used to encode the data.
     * @return 
     */
    private byte[] extractHeader() {
        int headerSize = 0 ;
        for (int i = 0; i < combinedDataAndKeys.length; i++) {
            if (combinedDataAndKeys[i] == 0) {
                headerSize = i-1;
                break;
            }
        }
        byte[] header = new byte[headerSize];
        for (int i = 0; i < header.length; i++) {
            header[i] = combinedDataAndKeys[i];
        }
        return header;
    }

    /**
     * Extracts from the header the bytewidth for encoded keys.
     * @param header
     * @return 
     */
    private int extractByteWidth(byte[] header) {
        byte[] byteWidthArray = new byte[]{header[0]};
        return IntegerConverter.ByteToInteger(byteWidthArray);
    }

    /**
     * Extracts from the header the number of keys used for encoding.
     * @param header
     * @return 
     */
    private int extractKeyCount(byte[] header) {
        byte[] keyCountArray = new byte[header.length-1];
        for (int i = 0; i < keyCountArray.length; i++) {
            keyCountArray[i] = header[i+1];
        }
        return IntegerConverter.ByteToInteger(keyCountArray);
    }

    /**
     * Extracts from the combined keys and data the actual keys into a new array for easier manupulation.
     * @param header
     * @param keyCount 
     */
    private void extractEncodedKeys(byte[] header, int keyCount) {
        encodedKeys = new byte[keyCount];
        int keyPutIndex = 0;
        for (int i = header.length; i < keyCount; i++) {
            encodedKeys[keyPutIndex] = header[i];
            keyPutIndex++;
        }
    }

    /**
     * Extractracts from the combined keys and data the encoded data into a new array for easier manipulation.
     * @param header
     * @param keyCount 
     */
    private void extractEncodedData(byte[] header, int keyCount) {
        encodedData = new byte[combinedDataAndKeys.length-header.length-keyCount];
        int dataPutIndex = 0;
        for (int i = header.length + keyCount; i < combinedDataAndKeys.length; i++) {
            encodedData[dataPutIndex] = combinedDataAndKeys[i];
        }
    }
    
}
