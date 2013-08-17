/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiralabra.Encoding;

import Encoding.MultiByteEncoder;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author virta
 */
public class MultiByteEncoderTest {

    MultiByteEncoder encoder;
    Thread encoderThread;
    byte[] encodingData;
    byte[] randomData;

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
        Random r = new Random(2719);
        randomData = new byte[20000];
        r.nextBytes(randomData);
        encodingData = new byte[]{ //1                                                         2                                                           3                                                           4                                                           5
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

    @After
    public void tearDown() {
    }

    @Test
    public void testEncodingNonZeroKeysAndData() {
        encoder = new MultiByteEncoder(encodingData, 4);
        encoderThread = new Thread(encoder);
        assertEquals("Incorrect status", MultiByteEncoder.StatusEnum.NULL, encoder.getStatus());
        encoderThread.start();
        while (encoder.getStatus() != MultiByteEncoder.StatusEnum.DONE) {
            //do nothing
        }
        byte[] keys = encoder.getEncodedKeys();
        assertTrue("Zero keys found", keys.length > 0);
        byte[] data = encoder.getEncodedData();
        assertTrue("Zero data found", data.length > 0);
        byte[] combined = encoder.getCombinedEncodedKeysAndData();
        assertTrue("Zero length comined data and keys", combined.length > 0);

    }

    @Test
    public void testKeysAndDataValidity() {
        encoder = new MultiByteEncoder(encodingData, 4);
        encoderThread = new Thread(encoder);
        encoderThread.start();
        while (encoder.getStatus() != MultiByteEncoder.StatusEnum.DONE) {
            //do nothing
        }
        byte[] keys = encoder.getEncodedKeys();
        assertEquals("More than four keys found", 16, keys.length);
        byte[] data = encoder.getEncodedData();
        assertTrue("Larger encoded data than original data", data.length < encodingData.length);

    }
    
    @Test
    public void testInterruption(){
        encoder = new MultiByteEncoder(randomData, 4);
        encoderThread = new Thread(encoder);
        assertEquals("Thread already running", MultiByteEncoder.StatusEnum.NULL, encoder.getStatus());
        encoderThread.start();
        MultiByteEncoder.StatusEnum status = encoder.getStatus();
        if (status == MultiByteEncoder.StatusEnum.BUILDING || status == MultiByteEncoder.StatusEnum.ENCODING){
            encoder.interrupt();
            assertFalse("Encoder was not interrupted", encoderThread.isAlive());
        }
    }
    
    
}