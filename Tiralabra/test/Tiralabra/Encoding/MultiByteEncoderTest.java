/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiralabra.Encoding;

import Encoding.MultiByteDecoder;
import Encoding.MultiByteEncoder;
import Encoding.StatusEnum;
import java.util.Random;
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
public class MultiByteEncoderTest {

    MultiByteEncoder encoder;
    Thread encoderThread;
    byte[] encodingDataType1;
    byte[] randomData;
    byte[] encodingDataType2;
    byte[] encodingDataType3;
    byte[] encodingDataType4;

    public MultiByteEncoderTest() {
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

    private void setupRandomData() {
        Random r = new Random(2719);
        randomData = new byte[20000];
        r.nextBytes(randomData);
    }

    @Test
    public void testEncodingNonZeroKeysAndData() {
        setupEncodingDataType1();
        encoder = new MultiByteEncoder(encodingDataType1, 4);
        encoderThread = new Thread(encoder);
        assertEquals("Incorrect status", Encoding.StatusEnum.NULL, encoder.getStatus());
        encoderThread.start();
        StatusEnum status = encoder.getStatus();
        while (status != Encoding.StatusEnum.DONE) {
            status = encoder.getStatus();
        }
        byte[] keys = encoder.getEncodedKeys();
        assertTrue("Zero keys found", keys.length > 0);
        byte[] data = encoder.getEncodedData();
        assertTrue("Zero data found", data.length > 0);
        byte[] combined = encoder.getCombinedEncodedKeysAndData();
        assertTrue("Zero length comined data and keys", combined.length > 0);

    }

    @Test
    public void testKeysAndDataValiditySimple() {
        setupEncodingDataType1();
        encoder = new MultiByteEncoder(encodingDataType1, 4);
        encoderThread = new Thread(encoder);
        encoderThread.start();
        while (encoder.getStatus() != Encoding.StatusEnum.DONE) {
            //do nothing
        }
        byte[] keys = encoder.getEncodedKeys();
        assertEquals("More than four keys found", 16, keys.length);
        byte[] data = encoder.getEncodedData();
        assertTrue("Larger encoded data than original data", data.length < encodingDataType1.length);

    }

    @Test
    public void testInterruption() {
        setupRandomData();
        encoder = new MultiByteEncoder(randomData, 4);
        encoderThread = new Thread(encoder);
        assertEquals("Thread already running", Encoding.StatusEnum.NULL, encoder.getStatus());
        encoderThread.start();
        Encoding.StatusEnum status = encoder.getStatus();
        if (status == Encoding.StatusEnum.BUILDING || status == Encoding.StatusEnum.ENCODING) {
            encoder.interrupt();
            assertFalse("Encoder was not interrupted", encoderThread.isAlive());
        }
    }

    @Test
    public void testCombinedDataAndKeysValidityType1() {
        setupEncodingDataType1();
        encoder = new MultiByteEncoder(encodingDataType1, 4);
        encoderThread = new Thread(encoder);
//        encoder.run();            //disable one or the other for debugging
        encoderThread.start();
        while (encoder.getStatus() != Encoding.StatusEnum.DONE) {
            //do nothing
        }

        byte[] combined = encoder.getCombinedEncodedKeysAndData();

        byte headerByteWidth = combined[0];
        assertEquals("Wrong bytewidth in header", 4, Utilities.IntegerConverter.ByteToInteger(new byte[]{headerByteWidth}));

        byte headerKeyCount = combined[1];
        assertEquals("Wrong number of keys", 4*4, Utilities.IntegerConverter.ByteToInteger(new byte[]{headerKeyCount}));

        byte zeroAfterHeader = combined[2];
        assertEquals("Zero byte not found after header", 0, Utilities.IntegerConverter.ByteToInteger(new byte[]{zeroAfterHeader}));

        byte zeroRemainderLength = combined[19];
        assertEquals("Zero byte not found as remainder", 0, Utilities.IntegerConverter.ByteToInteger(new byte[]{zeroRemainderLength}));

        byte prefixForRawData = combined[20];
        assertEquals("Wrong prefix for raw data", 170, Utilities.IntegerConverter.ByteToInteger(new byte[]{prefixForRawData}));

        byte firstByteOfRawData = combined[21];
        byte five = new Byte("5");
        assertEquals("Wrong data found", Utilities.IntegerConverter.ByteToInteger(new byte[]{five}), Utilities.IntegerConverter.ByteToInteger(new byte[]{firstByteOfRawData}));

        byte firstKeyPrefix = combined[25];
        int prefixInt = Utilities.IntegerConverter.ByteToInteger(new byte[]{firstKeyPrefix});
        int keyWidth = prefixInt / 10;
        int runlength = prefixInt - (keyWidth * 10);
        assertEquals("Wrong width for key", 1, keyWidth);
        assertEquals("Wrong runlength", 9, runlength);
    }

    @Test
    public void testCombinedDataAndKeysValidityType2() {
        setupEncodingDataType2();
        encoder = new MultiByteEncoder(encodingDataType2, 4);
        encoderThread = new Thread(encoder);
//        encoder.run();            //disable one or the other for debugging
        encoderThread.start();
        while (encoder.getStatus() != Encoding.StatusEnum.DONE) {
            //do nothing
        }
        byte[] combined = encoder.getCombinedEncodedKeysAndData();

        byte headerByteWidth = combined[0];
        assertEquals("Wrong bytewidth in header", 4, Utilities.IntegerConverter.ByteToInteger(new byte[]{headerByteWidth}));

        byte headerKeyCount = combined[1];
        assertEquals("Wrong number of keys", 4*4, Utilities.IntegerConverter.ByteToInteger(new byte[]{headerKeyCount}));

        byte zeroAfterHeader = combined[2];
        assertEquals("Zero byte not found after header", 0, Utilities.IntegerConverter.ByteToInteger(new byte[]{zeroAfterHeader}));

        byte zeroRemainderLength = combined[19];
        assertEquals("Zero byte not found as remainder", 0, Utilities.IntegerConverter.ByteToInteger(new byte[]{zeroRemainderLength}));

        byte firstPrefix = combined[20];
        assertEquals("Wrong first prefix", 11, Utilities.IntegerConverter.ByteToInteger(new byte[]{firstPrefix}));

        byte secondPrefix = combined[22];
        assertEquals("Wrong next prefix", 19, Utilities.IntegerConverter.ByteToInteger(new byte[]{secondPrefix}));
    }

    @Test
    public void testCombinedDataAndKeysValidityType3() {
        setupEncodingDataType3();
        encoder = new MultiByteEncoder(encodingDataType3, 4);
        encoderThread = new Thread(encoder);
//        encoder.run();            //disable one or the other for debugging
        encoderThread.start();
        while (encoder.getStatus() != Encoding.StatusEnum.DONE) {
            //do nothing
        }
        byte[] combined = encoder.getCombinedEncodedKeysAndData();

        byte headerByteWidth = combined[0];
        assertEquals("Wrong bytewidth in header", 4, Utilities.IntegerConverter.ByteToInteger(new byte[]{headerByteWidth}));

        byte headerKeyCount = combined[1];
        assertEquals("Wrong number of keys", 4*4, Utilities.IntegerConverter.ByteToInteger(new byte[]{headerKeyCount}));

        byte zeroAfterHeader = combined[2];
        assertEquals("Zero byte not found after header", 0, Utilities.IntegerConverter.ByteToInteger(new byte[]{zeroAfterHeader}));

        byte zeroRemainderLength = combined[19];
        assertEquals("Zero byte not found as remainder", 0, Utilities.IntegerConverter.ByteToInteger(new byte[]{zeroRemainderLength}));

        byte firstPrefix = combined[20];
        assertEquals("Wrong first prefix", 170, Utilities.IntegerConverter.ByteToInteger(new byte[]{firstPrefix}));

        byte secondPrefix = combined[25];
        assertEquals("Wrong second prefix", 171, Utilities.IntegerConverter.ByteToInteger(new byte[]{secondPrefix}));
        
        byte thirdPrefix = combined[34];
        assertEquals("Wrong third prefix", 19, Utilities.IntegerConverter.ByteToInteger(new byte[]{thirdPrefix}));
    }

    @Test
    public void testCombinedDataAndKeysValidityType4() {
        setupEncodingDataType4();
        encoder = new MultiByteEncoder(encodingDataType4, 4);
        encoderThread = new Thread(encoder);
//        encoder.run();            //disable one or the other for debugging
        encoderThread.start();
        while (encoder.getStatus() != Encoding.StatusEnum.DONE) {
            //do nothing
        }
        byte[] combined = encoder.getCombinedEncodedKeysAndData();

        byte headerByteWidth = combined[0];
        assertEquals("Wrong bytewidth in header", 4, Utilities.IntegerConverter.ByteToInteger(new byte[]{headerByteWidth}));

        byte headerKeyCount = combined[1];
        assertEquals("Wrong number of keys", 4*4, Utilities.IntegerConverter.ByteToInteger(new byte[]{headerKeyCount}));

        byte zeroAfterHeader = combined[2];
        assertEquals("Zero byte not found after header", 0, Utilities.IntegerConverter.ByteToInteger(new byte[]{zeroAfterHeader}));

        byte remainderLength = combined[19];
        assertEquals("Zero byte as remainder length", 2, Utilities.IntegerConverter.ByteToInteger(new byte[]{remainderLength}));
        
        byte firstPrefix = combined[22];
        assertEquals("Wrong first prefix", 170, Utilities.IntegerConverter.ByteToInteger(new byte[]{firstPrefix}));
        
        byte secondPrefix = combined[27];
        assertEquals("Wrong secong prefix", 171, Utilities.IntegerConverter.ByteToInteger(new byte[]{secondPrefix}));
        
        byte thirdPrefix = combined[36];
        assertEquals("Wrong third prefix", 19, Utilities.IntegerConverter.ByteToInteger(new byte[]{thirdPrefix}));
    }
    
    @Test
    public void testEncodingAndDecodingDataType1(){
        setupEncodingDataType1();
        encoder = new MultiByteEncoder(encodingDataType1, 3);
        encoderThread = new Thread(encoder);
        encoderThread.start();
        
        while (encoder.getStatus() != StatusEnum.DONE){
            //do nothing
        }
        
        byte[] combined = encoder.getCombinedEncodedKeysAndData();
        
        MultiByteDecoder byteDecoder = new MultiByteDecoder(combined);
        Thread decodingThread = new Thread(byteDecoder);
        decodingThread.start();
        
        while (byteDecoder.getStatus() != StatusEnum.DONE){
            //do nothing
        }
        
        byte[] decodedData = byteDecoder.getDecodedData();
        
        for (int i = 0; i < decodedData.length; i++) {
            assertEquals("Decoded data did not match", encodingDataType1[i], decodedData[i]);
        }
        
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