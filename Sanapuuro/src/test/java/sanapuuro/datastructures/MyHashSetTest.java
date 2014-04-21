/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.datastructures;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sanapuuro.fileio.FileIO;

/**
 *
 * @author skaipio
 */
public class MyHashSetTest {

    private MyHashSet mySet;

    public MyHashSetTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.mySet = new MyHashSet(5, new HashFuncStub<String>());
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hashSetSetsTableSizeToMValueOfHashFunc() {
        MyHashSet<String> set = new MyHashSet<>(1, new HashFuncStub<String>());
        assertEquals(3, set.tableSize);
    }

    @Test
    public void hashSetSizeIncreasesByAddingObjectsToIt() {
        this.mySet.add("abc");
        this.mySet.add("bcd");
        this.mySet.add("aaa");
        assertEquals(3, this.mySet.size());
    }

    @Test
    public void hashSetSizeDecreasesByRemovingObjectsFromIt() {
        this.mySet.add("abc");
        this.mySet.add("bcd");
        this.mySet.add("aaa");
        this.mySet.remove("abc");
        this.mySet.remove("bcd");
        assertEquals(1, this.mySet.size());
    }

    @Test
    public void hashSetContainsObjectAfterHavingAddedIt() {
        String inSet = "abc";
        assertTrue(this.mySet.add(inSet));
        assertTrue(this.mySet.contains(inSet));
    }

    @Test
    public void hashSetContainsMultipleObjectsAfterHavingAddedThem() {
        String inSet = "abc";
        String inSet2 = "abcd";
        String inSet3 = "abcde";
        this.mySet.add(inSet);
        this.mySet.add(inSet2);
        this.mySet.add(inSet3);
        assertTrue(this.mySet.contains(inSet));
        assertTrue(this.mySet.contains(inSet2));
        assertTrue(this.mySet.contains(inSet3));
    }

    @Test
    public void hashSetContainsTwoDifferentObjectsWithSameHashValues() {
        String inSet = "abc";
        String inSet2 = "cba";
        this.mySet.add(inSet);
        this.mySet.add(inSet2);
        assertTrue(this.mySet.contains(inSet));
        assertTrue(this.mySet.contains(inSet2));
    }

    @Test
    public void hashSetDoesNotContainARemovedObject() {
        String inSet = "abc";
        this.mySet.add(inSet);
        assertTrue(this.mySet.remove(inSet));
        assertFalse(this.mySet.contains(inSet));
    }

    @Test
    public void hashSetContainsSecondObjectAfterRemovingFirstObjectWithSameHash() {
        this.mySet.add("abc");
        this.mySet.add("cba");
        this.mySet.remove("abc");
        assertTrue(this.mySet.contains("cba"));
    }

    @Test
    public void hashSetRemovesBothObjectsWithSameHashWhenRemovedInDifferentOrderThanAdded() {
        this.mySet.add("abc");
        this.mySet.add("cba");
        this.mySet.remove("abc");
        this.mySet.remove("cba");
        assertFalse(this.mySet.contains("abc"));
        assertFalse(this.mySet.contains("cba"));
    }

    @Test
    public void hashSetDoesNotRemoveAnObjectWithSameHashValueButNotEqual() {
        String inSet = "abc";
        String inSet2 = "cba";
        this.mySet.add(inSet);
        this.mySet.add(inSet2);
        this.mySet.remove(inSet2);
        assertTrue(this.mySet.contains(inSet));
        assertFalse(this.mySet.contains(inSet2));
    }

    @Test
    public void hashSetDoesNotAddToAFullTable() {
        MyHashSet<String> set = new MyHashSet<>(1, new HashFuncStub<String>());
        String inSet = "abc";
        String inSet2 = "cba";
        String inSet3 = "hhh";
        String notInSet = "bbb";
        set.add(inSet);
        set.add(inSet2);
        set.add(inSet3);
        assertFalse(set.add(notInSet));
        assertFalse(set.contains(notInSet));
    }

    @Test
    public void hashSetShouldNotAnObjectItAlreadyContains() {
        String inSet = "abc";
        assertTrue(this.mySet.add(inSet));
        assertFalse(this.mySet.add(inSet));
    }

    private class HashFuncStub<String> extends HashFunction<String> {

        @Override
        public int getHash(String o, int m, int numberOfTry) {
            return (o.toString().length() + numberOfTry) % m;
        }

        @Override
        public int getNormalHash(String o, int m) {
            return o.hashCode();
        }

        @Override
        public int calculateM(int numberOfKeys, double desiredLoadRate) {
            return numberOfKeys * 3;
        }
    }
}
