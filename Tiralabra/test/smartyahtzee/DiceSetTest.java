    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smartyahtzee;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author essalmen
 */
public class DiceSetTest {
    
    public DiceSetTest() {
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

    /**
     * Test of throwDice method, of class DiceSet.
     */
    @Test
    public void testThrowDice() {
        System.out.println("throwDice");
        DiceSet instance = new DiceSet();
        
        int[] results = new int[6];
        for (int i = 0; i<10000; i++)
        {
            instance.throwDice();
            for (int j = 0; j < 5; j++)
            {
                int result = instance.getDie(j).getNumber();
                results[result-1]++;
                
            }
        }
        int difference = Math.abs(results[0]-results[2]);
        System.out.println(difference);
        assertTrue(difference < 500); 
    }

    /**
     * Test of toggleLock method, of class DiceSet.
     */
    @Test
    public void testToggleLock() {
        System.out.println("toggleLock");
        int index = 0;
        DiceSet instance = new DiceSet();
        instance.toggleLock(index);
        assertTrue(instance.getDie(index).isLocked());
    }

    /**
     * Test of unlockAll method, of class DiceSet.
     */
    @Test
    public void testUnlockAll() {
        System.out.println("unlockAll");
        DiceSet instance = new DiceSet();
        instance.toggleLock(1);
        instance.unlockAll();
        
        assertFalse(instance.getDie(1).isLocked());
    
    }

    /**
     * Test of asArray method, of class DiceSet.
     */
    @Test
    public void testAsArray() {
        System.out.println("asArray");
        DiceSet instance = new DiceSet();
        instance.getDie(0).setNumber(3);
        instance.getDie(1).setNumber(2);
        instance.getDie(2).setNumber(1);
        instance.getDie(3).setNumber(5);
        instance.getDie(4).setNumber(4);
        int[] expResult = {1, 2, 3, 4, 5};
        int[] result = instance.asArray();
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of toString method, of class DiceSet.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DiceSet instance = new DiceSet();
        for (Die d : instance.getDice())
        {
            d.setNumber(1);
        }
        instance.getDie(1).lock();
        String expResult = "1 (1) 1 1 1 ";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
    @Test
    public void testLockMany()          
    {
        System.out.println("lockMany");
        DiceSet instance = new DiceSet();
        for (int i = 0; i<5; i++)
        {
            instance.getDie(i).setNumber(i+2);
        }
        int[] lock = {2, 6};
        instance.lockMany(lock);
        String expResult = "(2) 3 4 5 (6) ";
        assertEquals(instance.toString(), expResult);

    }
    
    @Test
    public void testLockMany2()          
    {
        System.out.println("lockMany");
        DiceSet instance = new DiceSet();
        for (int i = 0; i<5; i++)
        {
            instance.getDie(i).setNumber(i+1);
        }
        int[] lock = {3, 4};
        instance.lockMany(lock);
        String expResult = "1 2 (3) (4) 5 ";
        assertEquals(instance.toString(), expResult);

    }
    
    @Test
    public void testLockManyRepeatedDigits()          
    {
        System.out.println("lockMany");
        DiceSet instance = new DiceSet();
        for (int i = 0; i<5; i++)
        {
            if (i < 3)
            {
                instance.getDie(i).setNumber(6);
            } else {
                instance.getDie(i).setNumber(5);
            }
            
        }
        int[] lock = {6, 6};
        instance.lockMany(lock);
        String expResult = "(6) (6) 6 5 5 ";
        assertEquals(instance.toString(), expResult);

    }
    
    @Test
    public void testLockManyRepeatedDigits2()          
    {
        System.out.println("lockMany");
        DiceSet instance = new DiceSet();
        for (int i = 0; i<5; i++)
        {
            instance.getDie(i).setNumber(6);
            
        }
        int[] lock = {6, 6};
        instance.lockMany(lock);
        String expResult = "(6) (6) 6 6 6 ";
        assertEquals(instance.toString(), expResult);

    }
    
    
    /**
     * Testaa että konstruktori luo viisi eikä enempää toisiinsa linkitettyä noppaa
     */
    @Test
    public void testConstructor()
    {
        System.out.println("dicesetConstructor");
        DiceSet instance = new DiceSet();
        Die die = instance.getDie(0);
        for (int i = 0; i < 4; i++)
        {
            assertTrue(die.nextDie() != null);
            die = die.nextDie();
        }
        assertTrue(die.nextDie() == null);
    }
    
}
