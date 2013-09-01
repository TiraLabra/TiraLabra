package Structures.Queue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    private Queue<String> q;
    public QueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.q=new Queue<String>();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEnqueue() {
        this.q.enqueue("Kalle");
        assertEquals("Kalle",this.q.dequeue());
        assertEquals(true,this.q.isEmpty());
        this.q.enqueue("Satu");
        this.q.enqueue("Pirkko");
        assertEquals(false,this.q.isEmpty());
        assertEquals("Pirkko",this.q.dequeue());
        assertEquals("Satu",this.q.dequeue());
        assertEquals(true,this.q.isEmpty());
    }
}