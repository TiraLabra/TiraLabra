/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiralabra.Encoding;

import Encoding.MultiByteDecoder;
import Encoding.MultiByteEncoder;
import Encoding.StatusEnum;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sun.audio.AudioPlayer;

/**
 *
 * @author virta
 */
public class MultiByteDecoderTest {

    private byte[] encodedDataType1;
    private byte[] encodingDataType1;
    private byte[] encodingDataType2;
    private byte[] encodingDataType3;
    private byte[] encodingDataType4;

    public MultiByteDecoderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testDecodingDataType1() {
        setUpEndoedDataType1();
        MultiByteDecoder decoder = new MultiByteDecoder(encodedDataType1);
        Thread decoderThread = new Thread(decoder);
        assertEquals("Incorrect status", Encoding.StatusEnum.NULL, decoder.getStatus());

//        decoder.run();
        decoderThread.start();

        boolean threadIsAlive = decoderThread.isAlive();

        while (decoder.getStatus() != Encoding.StatusEnum.DONE) {
            assertTrue("Decoder is not running", threadIsAlive);      //if debugging with decoder.run() and thread has not been started.
        }

        byte[] decodedData = decoder.getDecodedData();

        assertEquals("Incorrect byte found at index 1", 2, decodedData[0]);
    }
    
    @Test
    public void testDecoderInterruption() throws InterruptedException{
        setUpEndoedDataType1();
        MultiByteDecoder decoder = new MultiByteDecoder(encodedDataType1);
        Thread decoderThread = new Thread(decoder);
        decoderThread.start();
        decoder.interrupt();
        Thread.sleep(100);
        assertEquals("Decoder was not interrupted", StatusEnum.INTERRUPTED, decoder.getStatus());
    }

    @Test
    public void testEncodingAndDecodingDataType1() {
        setupEncodingDataType1();
        MultiByteEncoder encoder = new MultiByteEncoder(encodingDataType1, 4);
        Thread encoderThread = new Thread(encoder);
        encoderThread.start();
//        encoder.run();

        while (encoder.getStatus() != StatusEnum.DONE) {
            //do nothing
        }

        byte[] encodedData = encoder.getCombinedEncodedKeysAndData();

        MultiByteDecoder decoder = new MultiByteDecoder(encodedData);
        Thread decoderThread = new Thread(decoder);
        decoderThread.start();

        while (decoder.getStatus() != StatusEnum.DONE) {
            //do nothing
        }

        byte[] decodedData = decoder.getDecodedData();
        
        assertEquals("Different length in original and decoded data", encodingDataType1.length, decodedData.length);

        for (int i = 0; i < decodedData.length; i++) {
            assertEquals("Error in decoded data", encodingDataType1[i], decodedData[i]);
        }
    }

    @Test
    public void testEncodingAndDecodingDataType2() {
        setupEncodingDataType2();
        MultiByteEncoder encoder = new MultiByteEncoder(encodingDataType2, 4);
        Thread encoderThread = new Thread(encoder);
        encoderThread.start();
//        encoder.run();

        while (encoder.getStatus() != StatusEnum.DONE) {
            //do nothing
        }

        byte[] encodedData = encoder.getCombinedEncodedKeysAndData();

        MultiByteDecoder decoder = new MultiByteDecoder(encodedData);
        Thread decoderThread = new Thread(decoder);
        decoderThread.start();

        while (decoder.getStatus() != StatusEnum.DONE) {
            //do nothing
        }

        byte[] decodedData = decoder.getDecodedData();
        
        assertEquals("Different length in original and decoded data", encodingDataType2.length, decodedData.length);

        for (int i = 0; i < decodedData.length; i++) {
            assertEquals("Error in decoded data", encodingDataType2[i], decodedData[i]);
        }
    }

    @Test
    public void testEncodingAndDecodingDataType3() {
        setupEncodingDataType3();
        MultiByteEncoder encoder = new MultiByteEncoder(encodingDataType3, 4);
        Thread encoderThread = new Thread(encoder);
        encoderThread.start();
//        encoder.run();

        while (encoder.getStatus() != StatusEnum.DONE) {
            //do nothing
        }

        byte[] encodedData = encoder.getCombinedEncodedKeysAndData();

        MultiByteDecoder decoder = new MultiByteDecoder(encodedData);
        Thread decoderThread = new Thread(decoder);
        decoderThread.start();

        while (decoder.getStatus() != StatusEnum.DONE) {
            //do nothing
        }

        byte[] decodedData = decoder.getDecodedData();
        
        assertEquals("Different length in original and decoded data", encodingDataType3.length, decodedData.length);

        for (int i = 0; i < decodedData.length; i++) {
            assertEquals("Error in decoded data", encodingDataType3[i], decodedData[i]);
        }
    }

    @Test
    public void testEncodingAndDecodingDataType4() {
        setupEncodingDataType4();
        MultiByteEncoder encoder = new MultiByteEncoder(encodingDataType4, 4);
        Thread encoderThread = new Thread(encoder);
        encoderThread.start();
//        encoder.run();

        while (encoder.getStatus() != StatusEnum.DONE) {
            //do nothing
        }

        byte[] encodedData = encoder.getCombinedEncodedKeysAndData();

        MultiByteDecoder decoder = new MultiByteDecoder(encodedData);
        Thread decoderThread = new Thread(decoder);
//        decoderThread.start();
        decoder.run();
        while (decoder.getStatus() != StatusEnum.DONE) {
            //do nothing
        }

        byte[] decodedData = decoder.getDecodedData();
        
        assertEquals("Different length in original and decoded data", encodingDataType4.length, decodedData.length);

        for (int i = 0; i < decodedData.length; i++) {
            assertEquals("Error in decoded data", encodingDataType4[i], decodedData[i]);
        }
    }

    private void setUpEndoedDataType1() {
        encodedDataType1 = new byte[]{
            new Byte("4"), new Byte("16"), new Byte("0"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("2"),
            new Byte("3"), new Byte("8"),
            new Byte("11"), new Byte("1"),
            new Byte("12"), new Byte("2"), new Byte("2"),
            new Byte("-85"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("6"), new Byte("6"), new Byte("6"), new Byte("6"),
            new Byte("19"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("13"), new Byte("0"), new Byte("0"), new Byte("0")
        };
    }

    private void setupEncodingDataType1() {
        encodingDataType1 = new byte[]{
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1")
        };
    }

    private void setupEncodingDataType2() {
        encodingDataType2 = new byte[]{
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5")
        };
    }

    private void setupEncodingDataType3() {
        encodingDataType3 = new byte[]{
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1")
        };
    }

    private void setupEncodingDataType4() {
        encodingDataType4 = new byte[]{
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("5"), new Byte("5"), new Byte("5"), new Byte("5"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"), new Byte("3"),
            new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"), new Byte("2"),
            new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"), new Byte("4"),
            new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"), new Byte("1"),
            new Byte("2"), new Byte("3")
        };
    }
}