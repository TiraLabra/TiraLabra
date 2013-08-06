package Tiralabra.MultiByteEntities;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import MultiByteEntities.MultiByte;
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
public class MultiByteTest {

    byte[] incrementalData;
    byte[] sameData;
    byte[] randomData;

    public MultiByteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        this.incrementalData = new byte[]{
            new Byte("1"),
            new Byte("2"),
            new Byte("3"),
            new Byte("4"),
            new Byte("5"),
            new Byte("6"),
            new Byte("7"),
            new Byte("8"),};

        this.sameData = new byte[]{
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"),
            new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7"), new Byte("7")
        };

        this.randomData = new byte[2000000];
        Random randomByteGenerator = new Random(2719);
        randomByteGenerator.nextBytes(randomData);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRandomDataForSameHashesShouldFail(){
        int[] doubleByteHashArray = new int[incrementalData.length];
        int[] tripleByteHashArray = new int[incrementalData.length];
        int[] quadByteHashArray = new int[incrementalData.length];

        indtroduceHashCodes(doubleByteHashArray, incrementalData, 2);
        indtroduceHashCodes(tripleByteHashArray, incrementalData, 3);
        indtroduceHashCodes(quadByteHashArray, incrementalData, 4);

        checkForMatchingHashes(doubleByteHashArray, false);
        checkForMatchingHashes(tripleByteHashArray, false);
        checkForMatchingHashes(quadByteHashArray, false);

    }
    
    @Test
    public void predefinedMultiByteHashTestNoSameHashes() {
        int[] doubleByteHashArray = new int[incrementalData.length];
        int[] tripleByteHashArray = new int[incrementalData.length];
        int[] quadByteHashArray = new int[incrementalData.length];

        indtroduceHashCodes(doubleByteHashArray, incrementalData, 2);
        indtroduceHashCodes(tripleByteHashArray, incrementalData, 3);
        indtroduceHashCodes(quadByteHashArray, incrementalData, 4);

        checkForMatchingHashes(doubleByteHashArray, false);
        checkForMatchingHashes(tripleByteHashArray, false);
        checkForMatchingHashes(quadByteHashArray, false);

    }

    @Test
    public void predefinedLargeTestSetWithSameHashes() {
        int[] doubleByteHashArray = new int[sameData.length];
        int[] tripleByteHashArray = new int[sameData.length];
        int[] quadByteHashArray = new int[sameData.length];

        indtroduceHashCodes(doubleByteHashArray, sameData, 2);
        indtroduceHashCodes(tripleByteHashArray, sameData, 3);
        indtroduceHashCodes(quadByteHashArray, sameData, 4);

        checkForMatchingHashes(doubleByteHashArray, true);
        checkForMatchingHashes(tripleByteHashArray, true);
        checkForMatchingHashes(quadByteHashArray, true);
    }

    private void indtroduceHashCodes(int[] multiByteHashArray, byte[] dataArray, int width) {
        int hashArrayIndex = 0;
        for (int i = 0; i < dataArray.length; i++) {
            if (i + width < dataArray.length) {

                MultiByte multiByte = new MultiByte(width);

                for (int j = 0; j < width; j++) {
                    multiByte.addData(dataArray[i + j]);

                }

                multiByteHashArray[hashArrayIndex] = multiByte.hashCode();
                hashArrayIndex++;
            }
        }
    }

    private void checkForMatchingHashes(int[] multiByteHashArray, boolean matching) {
        for (int i = 0; i < multiByteHashArray.length; i++) {
            int j = multiByteHashArray[i];

            if (multiByteHashArray[i] == 0) {
                break;
            }

            for (int k = 0; k < multiByteHashArray.length; k++) {
                if (k == i) {
                    k++;
                }

                if (multiByteHashArray[k] == 0) {
                    break;
                }
                assertEquals("Matching hashcodes" + i, matching, multiByteHashArray[k] == multiByteHashArray[i]);
            }
        }
    }
}