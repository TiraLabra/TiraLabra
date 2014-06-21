package util;

import util.MyList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyListTest {

    MyList testList;

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
    public void removingByIndex() {
        testList.add(5);
        testList.add(55);
        testList.add(555);
        testList.add(5555);
        testList.removeByIndex(1);
        assertEquals(3, testList.size());
        assertEquals(5, testList.get(0));
        assertEquals(5555, testList.get(1));
        assertEquals(555, testList.get(2));
    }

    @Test
    public void massiveRemoval() {
        addingPastListMaxSize();
        for (int i = 0; i < 100; i++) {
            testList.removeLast();
            assertTrue(testList.size() >= 0);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void tryingToRetrieveOutOfBoundsKey() {
        removingByIndex();
        testList.get(4);
    }

    @Test
    public void removingByValue() {
        for (int i = 0; i < 100; i++) {
            testList.add(i);
        }
        testList.removeByValue(37);
        testList.removeByValue(58);
        testList.removeByValue(87);
        assertEquals(97, testList.size());
        int j = -1;
        for (int i = 0; i < 97; i++) {
            j++;
            if (i == 37) {
                assertEquals(99, testList.get(i));
                continue;
            } else if (i == 58) {
                assertEquals(98, testList.get(i));
                continue;
            } else if (i == 87) {
                assertEquals(97, testList.get(i));
                continue;
            }
            assertEquals(j, testList.get(i));

        }
    }

    @Test
    public void joiningNullList() {
        int size = testList.size();
        testList.join(null);
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
                assertEquals(j % 10, (int) lists[0].get(j) % 10);
            }
        }
    }

    @Test
    public void reversingList() {
        for (int i = 0; i < 100; i++) {
            testList.add(i);
        }
        testList.reverseList();
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, testList.get(99 - i));
        }
    }

    @Test
    public void swapping() {
        testList.add(5);
        testList.add(15);
        testList.add(25);
        testList.swap(0, 2);
        assertEquals(25, testList.get(0));
        assertEquals(5, testList.get(2));
        testList.swap(0, 3);
        assertEquals(25, testList.get(0));
        assertEquals(5, testList.get(2));
    }

    @Test
    public void removingReturnsItem() {
        for (int i = 0; i < 100; i++) {
            testList.add(i);
        }
        assertEquals(99, testList.removeLast()); // Remove list[99] = 99.
        int removed = (int) testList.removeByIndex(5); // Remove list[5] = 5.
        assertEquals(98, testList.get(5)); // Swap operation made list[5] = 98.
        assertEquals(5, removed);
        assertEquals(98, testList.removeByValue(98)); // 98 is found at [5].
        assertEquals(97, testList.removeByValue(97)); // List[5] becomes 97.
        assertEquals(null, testList.removeByValue(5)); // Removed items can't be found anymore.
        assertEquals(null, testList.removeByValue(98)); // Removed items can't be found anymore.
    }

    @Test
    public void abstractDataTypes() {
        testList = new MyList<String>();
        testList.add("test");
        testList.add("testing");
        testList.add("testingtesting");
        assertEquals(3, testList.size());
        assertEquals("test", testList.get(0));
        assertEquals("testing", testList.removeByValue("testing"));
    }
}
