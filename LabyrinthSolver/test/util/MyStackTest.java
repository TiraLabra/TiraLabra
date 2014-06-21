package util;

import util.MyStack;
import java.util.EmptyStackException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyStackTest {

    MyStack testStack;

    @Before
    public void setUp() {
        testStack = new MyStack();
    }

    @Test(expected = EmptyStackException.class)
    public void poppingFromEmptyStack() {
        testStack.pop();
    }

    @Test
    public void pushing() {
        for (int i = 1; i < 6; i++) {
            testStack.push(i);
            assertEquals(i, testStack.size());
        }
    }

    @Test
    public void poppingRetainsElementOrder() {
        pushing();
        for (int i = 5; i >= 1; i--) {
            assertEquals(i, testStack.pop());
        }
    }
    
    @Test
    public void massivePushingAndPopping() {
        for(int i = 0; i < 100; i++) {
            testStack.push(i);
            assertEquals(i + 1, testStack.size());
        }
        for(int i = 99; i > 0; i--) {
            assertEquals(i, testStack.pop());
            assertEquals(i, testStack.size());
        }
    }
    
    @Test
    public void differentDataTypes() {
        testStack = new MyStack<String>();
        testStack.push("test");
        testStack.push("testing");
        testStack.push("testingtesting");
        assertEquals(3, testStack.size());
        assertEquals("testingtesting", testStack.pop());
        assertEquals("testing", testStack.pop());
    }
}
