/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Encoding;

import MultiByteEntities.MultiByte;
import Utilities.*;

/**
 * This decoding mechanism takes apart the encoded data and produces a machine
 * readable byte array. Implements the runnable interface for multithreading.
 *
 * @author virta
 */
public class MultiByteDecoder implements Runnable {

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
     * Where in the decoded data array data is put.
     */
    private int writeToIndex;
    /**
     * Used to interrupt operations.
     */
    public boolean interrupt;
    /**
     * Used to query the status of current operations.
     */
    private StatusEnum status;

    /**
     * Constructs a decoder with the specified data.
     *
     * @param data
     */
    public MultiByteDecoder(byte[] data) {
        this.status = StatusEnum.NULL;
        this.interrupt = false;
        this.combinedDataAndKeys = data;
        this.writeToIndex = 0;
    }
    
    public StatusEnum getStatus(){
        return this.status;
    }
    
    public void interrupt(){
        this.interrupt = true;
    }
    
    public boolean isInterrupted(){
        return this.interrupt;
    }
    
    public MultiByte[] getKeys() {
        return this.keys;
    }

    public byte[] getDecodedData() {
        return this.decodedData;
    }
    /**
     * Enables multiple instances of decoding to take place in separate threads.
     */
    @Override
    public void run() {
        this.status = StatusEnum.EXTRACTING;

        splitDataAndKeys();

        if (interrupt) {
            this.status = StatusEnum.INTERRUPTED;
            return;
        }

        decodeKeys();

        if (interrupt) {
            this.status = StatusEnum.INTERRUPTED;
            return;
        }

        extractRemainder();

        if (interrupt) {
            this.status = StatusEnum.INTERRUPTED;
            return;
        }

        this.status = StatusEnum.DECODING;

        decodeData();
        
        this.status = StatusEnum.DONE;
    }

    /**
     * Decodes from the encoded data all data with the encoded keys and remainder into 'decodedData'.
     */
    private void decodeData() {
        this.decodedData = new byte[combinedDataAndKeys.length];
        int readFromIndex = remainder.length + 1;

        while (readFromIndex < encodedData.length) {

            if (interrupt) {
                this.status = StatusEnum.INTERRUPTED;
                return;
            }

            byte[] prefixByte = new byte[]{encodedData[readFromIndex]};
            int prefixInteger = IntegerConverter.ByteToInteger(prefixByte);
            readFromIndex++;

            if (prefixInteger < 170) {
                int keyWidth = prefixInteger / 10;
                int runLength = prefixInteger - (keyWidth * 10);
                readFromIndex = decodeAtIndex(readFromIndex, runLength, keyWidth, true);

            } else {
                int runLength = prefixInteger - 169;
                readFromIndex = decodeAtIndex(readFromIndex, runLength, byteWidth, false);
            }
        }

        if (interrupt) {
            this.status = StatusEnum.INTERRUPTED;
            return;
        }

        writeToIndex = ArrayUtilities.encodeIntoArray(remainder, decodedData, writeToIndex);
        
        decodedData = ArrayUtilities.removeTrailingZeros(decodedData, writeToIndex);
    }

    /**
     * Decodes runLength number of data entries of 'width' starting at the
     * dataIndex in the encoded data. Calls inserting method to insert the
     * decoded data into the array.
     *
     * @param dataIndex
     * @param runLength
     * @param width
     * @return next position to read data from encoded array.
     */
    private int decodeAtIndex(int dataIndex, int runLength, int width, boolean isKey) {
        for (int i = dataIndex; i < dataIndex + (runLength * width); i += width) {

            if (interrupt) {
                this.status = StatusEnum.INTERRUPTED;
                return -1;
            }

            byte[] dataArray = new byte[width];

            for (int j = 0; j < width; j++) {
                dataArray[j] = encodedData[i + j];
            }

            byte[] decodedSubData;

            if (isKey) {
                int key = IntegerConverter.ByteToInteger(dataArray);
                decodedSubData = keys[key].getBytes();
            } else {
                decodedSubData = dataArray;
            }

            insertIntoDecodedData(decodedSubData);
        }

        return dataIndex + (runLength * width) ;
    }

    /**
     * Inserts into the decoded data array the data given as parameter.
     *
     * @param data
     */
    private void insertIntoDecodedData(byte[] data) {
        if (writeToIndex + data.length >= decodedData.length) {
            decodedData = ArrayUtilities.expandByteArray(decodedData);
        }
        writeToIndex = ArrayUtilities.encodeIntoArray(data, decodedData, writeToIndex);
    }

    /**
     * Extracts from the encoded data array the remainder of data, that is
     * unencoded.
     */
    private void extractRemainder() {
        byte[] remainderLengthArray = new byte[]{encodedData[0]};
        int remainderLength = IntegerConverter.ByteToInteger(remainderLengthArray);
        remainder = new byte[remainderLength];

        for (int i = 0; i < remainder.length; i++) {
            remainder[i] = encodedData[i + 1];
        }
    }

    /**
     * Decodes the set of keys that was used to encode the data.
     */
    private void decodeKeys() {
        keys = new MultiByte[keyCount];
        int keyPutIndex = 0;
        for (int i = 0; i < encodedKeys.length; i += byteWidth) {

            if (interrupt) {
                this.status = StatusEnum.INTERRUPTED;
                return;
            }

            keys[keyPutIndex] = ArrayUtilities.makeMultiByte(i, encodedKeys, byteWidth);
            keyPutIndex++;
        }
    }

    /**
     * Calls appropriate methods to split the header, keys and data from the
     * combined data.
     */
    private void splitDataAndKeys() {
        byte[] header = extractHeader();
        byteWidth = Utilities.IntegerConverter.ByteToInteger(new byte[]{header[0]});
        keyCount = extractKeyCount(header);

        extractEncodedKeys(header, keyCount);

        extractEncodedData(header, keyCount);
    }

    /**
     * Extracts from the combined data the header which contains information
     * about bytewidth and number of keys originally used to encode the data.
     *
     * @return
     */
    private byte[] extractHeader() {
        int headerSize = 0;
        for (int i = 0; i < combinedDataAndKeys.length; i++) {
            if (combinedDataAndKeys[i] == 0) {
                headerSize = i;
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
     * Extracts from the header the number of keys used for encoding.
     *
     * @param header
     * @return
     */
    private int extractKeyCount(byte[] header) {
        byte[] keyCountArray = new byte[header.length - 1];
        for (int i = 0; i < keyCountArray.length; i++) {
            keyCountArray[i] = header[i + 1];
        }
        return IntegerConverter.ByteToInteger(keyCountArray);
    }

    /**
     * Extracts from the combined keys and data the actual keys into a new array
     * for easier manipulation.
     *
     * @param header
     * @param keyCount
     */
    private void extractEncodedKeys(byte[] header, int keyCount) {
        encodedKeys = new byte[keyCount];
        int keyPutIndex = 0;
        for (int i = header.length + 1; i < (header.length+keyCount+1); i++) {

            if (interrupt) {
                this.status = StatusEnum.INTERRUPTED;
                return;
            }

            encodedKeys[keyPutIndex] = combinedDataAndKeys[i];
            keyPutIndex++;
        }
    }

    /**
     * Extracts from the combined keys and data the encoded data into a new
     * array for easier manipulation.
     *
     * @param header
     * @param keyCount
     */
    private void extractEncodedData(byte[] header, int keyCount) {
        encodedData = new byte[combinedDataAndKeys.length - header.length - keyCount - 1];
        int dataPutIndex = 0;
        for (int i = header.length + keyCount + 1; i < combinedDataAndKeys.length; i++) {
            
            if (interrupt) {
                this.status = StatusEnum.INTERRUPTED;
                return;
            }
            
            encodedData[dataPutIndex] = combinedDataAndKeys[i];
            dataPutIndex++;
        }
    }
}
