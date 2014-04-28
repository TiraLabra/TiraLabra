/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.benchmarks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sanapuuro.datastructures.PrimeNumberUtils;
import sanapuuro.hashfunctions.DJB2ForStrings;
import sanapuuro.hashfunctions.FNVOneForStrings;
import sanapuuro.hashfunctions.GeneralHashFuncForStrings;
import sanapuuro.hashfunctions.GeneralHashFuncForStrings2;
import sanapuuro.hashfunctions.HashFunction;
import sanapuuro.hashfunctions.MurmurHash3ForStrings;
import sanapuuro.fileio.FileIO;
import sanapuuro.hashfunctions.CRC32ForStrings;
import sanapuuro.hashfunctions.JavaHashForStrings;

/**
 *
 * @author skaipio
 */
public class HashCollisionsWithModuloAndAbs { 
    private final List<String> words;

    public HashCollisionsWithModuloAndAbs() {
        FileIO fileIO = new FileIO();
        this.words = fileIO.readInWordsFromFile("words/english_words", 8);
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
    public void duplicatesWithJavaHash() {
        float[] collisionData = this.getCollisionsWithFunction(words, new JavaHashForStrings());
        System.out.println("java hash");
        this.printCollisionData(collisionData);
    }
    
    @Test
    public void duplicatesWithGeneralHashFunctionWithBigInts() {
        float[] collisionData = this.getCollisionsWithFunction(words, new GeneralHashFuncForStrings2());
        System.out.println("General hash function using BigInts");
        this.printCollisionData(collisionData);
    }
    
    @Test
    public void duplicatesWithGeneralHashFunctionWithInts() {
        float[] collisionData = this.getCollisionsWithFunction(words, new GeneralHashFuncForStrings());
        System.out.println("General hash function using integers");
        this.printCollisionData(collisionData);
    }
    
    @Test
    public void duplicatesWithDJB2() {
        float[] collisionData = this.getCollisionsWithFunction(words, new DJB2ForStrings());
        System.out.println("djb2");
        this.printCollisionData(collisionData);
    }
    
    @Test
    public void duplicatesWithFNVOne() {
        float[] collisionData = this.getCollisionsWithFunction(words, new FNVOneForStrings());
        System.out.println("FNV-1a");
        this.printCollisionData(collisionData);
    }
    
    @Test
    public void duplicatesWithMurmurHash3() {
        float[] collisionData = this.getCollisionsWithFunction(words, new MurmurHash3ForStrings());
        System.out.println("MurmurHash3");
        this.printCollisionData(collisionData);
    }
    
    @Test
    public void duplicatesWithCRC32() {
        float[] collisionData = this.getCollisionsWithFunction(words, new CRC32ForStrings());
        System.out.println("CRC32");
        this.printCollisionData(collisionData);
    }
    
    private void printCollisionData(float[] collisionData){
        System.out.println("Total collisions: " + (int)collisionData[0]);
        System.out.println("Average collision chain length: " + collisionData[1]);
        System.out.println("Max collisions for same hash: " + (int)collisionData[2]);
        System.out.println("");
    }

    private float[] getCollisionsWithFunction(List<String> words, HashFunction<String> function){
        
        int m = this.getM(words.size(), 0.75);
        
        Map<Integer, Integer> collisionCounts = new HashMap<>();
        for (String word : words) {
            int hash = Math.abs(function.getHash(word)%m);
            if (!collisionCounts.containsKey(hash)) {
                collisionCounts.put(hash, 0);
            } else {
                int count = collisionCounts.get(hash);
                collisionCounts.put(hash, count+1);
            }
        }
        int max = 0;
        int collisions = 0;
        for (int count : collisionCounts.values()) {
            collisions += count;
            max = Math.max(count+1, max);
        }
        float average = 1.0f*(collisions+words.size())/collisionCounts.size();
        return new float[]{collisions, average, max};
    }
    
    private int getM(int numberOfKeys, double desiredLoadRate){
        int estimatedTableSize = (int) (numberOfKeys / desiredLoadRate);
        int[] primesCloseToTableSize = PrimeNumberUtils.findPrimesCloseTo(estimatedTableSize);
        return PrimeNumberUtils.pickNumberThatIsFarthestFromPowerOfTwo(primesCloseToTableSize);
    }
}
