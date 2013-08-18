/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Encoding.MultiByteEncoder;
import MultiByteEntities.MultiByte;

/**
 * Contains useful methods for byte manipulation which are used by the encoder
 * and decoder alike.
 *
 * @author virta
 */
public class ArrayUtilities {

    /**
     * Encodes any supplied data into the supplied data-array beginning at the
     * supplied index.
     *
     * @param data
     * @param dataArray
     * @param dataIndex
     * @return the next position for data.
     */
    public static int encodeIntoArray(byte[] data, byte[] dataArray, int dataIndex) {
        for (int i = 0; i < data.length; i++) {
            dataArray[dataIndex] = data[i];
            dataIndex++;
        }
        return dataIndex;
    }

    /**
     * Makes a multibyte out of the data-array beginning at the supplied index.
     *
     * @param dataIndex
     * @param dataArray
     * @param byteWidth
     * @return a multibyte with the appropriate data if there was enough data in
     * the array.
     */
    public static MultiByte makeMultiByte(int dataIndex, byte[] dataArray, int byteWidth) {
        if (dataIndex + byteWidth - 1 < dataArray.length) {
            MultiByte mb = new MultiByte(byteWidth);
            for (int j = 0; j < byteWidth; j++) {
                mb.addData(dataArray[dataIndex + j]);
            }
            return mb;
        }
        return null;
    }

    /**
     * Removes trailing zeros from the supplied array ending at the last index.
     *
     * @param encodedData
     * @param lastIndex
     * @return
     */
    public static byte[] removeTrailingZeros(byte[] encodedData, int lastIndex) {
        byte[] trimmed = new byte[lastIndex];
        for (int i = 0; i < trimmed.length; i++) {
            if (MultiByteEncoder.interrupt) {
                break;
            }

            trimmed[i] = encodedData[i];
        }
        return trimmed;
    }

    /**
     * Expands the given array to twice its original size, might have to be
     * recoded.
     *
     * @param array
     * @return
     */
    public static byte[] expandArray(byte[] array) {
        byte[] newArray = new byte[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    /**
     * Contracts the given array into the size specified.
     *
     * @param array
     * @param toSize
     * @return
     */
    public static byte[] contractArray(byte[] array, int toSize) {
        byte[] newArray = new byte[toSize];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    
}
