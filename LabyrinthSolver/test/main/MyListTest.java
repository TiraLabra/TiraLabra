package main;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyListTest {

    public MyList testList;

    @Before
    public void setUp() {
        testList = new MyList();
    }

    @Test
    public void addingAndGetting() {
        testList.add(5);
        assertEquals(1, testList.size());
        testList.add(55);
        testList.add(555);
        assertEquals(3, testList.size());
        assertEquals(5, testList.get(0));
        assertEquals(55, testList.get(1));
        assertEquals(555, testList.get(2));
    }

    @Test
    public void addingPastListMaxSize() {
        for (int i = 0; i < 64; i++) {
            testList.add(i);
            assertEquals(i + 1, testList.size());
            assertEquals(i, testList.get(i));
        }
    }

    @Test
    public void removing() {
        testList.add(5);
        testList.add(55);
        testList.add(555);
        testList.add(5555);
        testList.remove(1);
        assertEquals(3, testList.size());
        assertEquals(5555, testList.get(1));
    }

    @Test
    public void massiveRemoval() {
        addingPastListMaxSize();
        for (int i = 0; i < 100; i++) {
            testList.remove();
            assertTrue(testList.size() >= 0);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void tryingToRetrieveOutOfBoundsKey() {
        removing();
        testList.get(4);
    }

    @Test
    public void joiningTwoLists() {
        for (int i = 0; i < 4; i++) {
            testList.add(i);
        }
        MyList newList = new MyList();
        for (int i = 0; i < 4; i++) {
            newList.add(i);
        }
        testList.join(newList);
        assertEquals(8, testList.size());
        for (int i = 0; i < 8; i++) {
            assertEquals(i % 4, testList.get(i));
        }
    }

    @Test
    public void joiningManyLists() {
        MyList[] lists = new MyList[10];
        for (int i = 0; i < 10; i++) {
            lists[i] = new MyList();
            for (int j = 0; j < 10; j++) {
                lists[i].add(j);
            }
        }
        for (int i = 1; i < 10; i++) {
            lists[0].join(lists[i]);
            assertEquals(10 + i * 10, lists[0].size());
            for (int j = 0; j < lists[0].size(); j++) {
                assertEquals(j % 10, lists[0].get(j) % 10);
            }
        }
    }
}
