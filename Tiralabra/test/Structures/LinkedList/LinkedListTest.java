package Structures.LinkedList;

import Utils.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class LinkedListTest {
    private LinkedList<String> ll;
    public LinkedListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.ll=new LinkedList<String>();
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void randomTest(){
        int[] numbers = new int[30];
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int i=0; i<numbers.length; i++){
            int rnd = (int)Math.round(Math.random()*100);
            numbers[i]= rnd;
            list.add(rnd);
        }
        Iterator<Integer> j = new Iterator<Integer>(list);
        for(int t=0; t<numbers.length; t++){
            assertEquals(numbers[t],(int)j.getNext());
        }
    }
    @Test
    public void standartTest() {
        this.ll.add("Kalle");
        this.ll.add("Pekka");
        this.ll.add("Pirkko");
        assertEquals("Kalle",(String)this.ll.getTail().getData());
        this.ll.removeTail();
        assertEquals("Pekka",(String)this.ll.getTail().getData());
        assertEquals(false, this.ll.isEmpty());
        this.ll.removeTail();
        assertEquals("Pirkko",(String)this.ll.getTail().getData());
        this.ll.removeTail();
        assertEquals(true, this.ll.isEmpty());
    }
}