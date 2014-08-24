package pakkaaja;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import pakkaaja.Tree;
import pakkaaja.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joonaskylliainen
 */
public class TreeTest {
    
    Tree tree;
    
    public TreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Node root = new Node('a',1);
        Node left = new Node('b', 2);
        Node right = new Node('c', 3);
        Node leftright = new Node('d', 5);
        Node leftleft = new Node('e',6);
        root.setLeft(left);
        root.setRight(right);
        left.setRight(leftright);
        left.setLeft(leftleft);
        tree = new Tree(root);
    }
    
    @After
    public void tearDown() {
    }
    /**
     * testaa toimiiko makeDirectory metodi. Jos toimii, niin puukin rakentuu oikein.
     */
    @Test
    public void doesMakeDirectoryWork() {      
        String[] list = tree.makeDirectory();
        assertEquals("1", list['c']);
        assertEquals("01", list['d']);
        assertEquals("00", list['e']);
        assertTrue("sisäsolmut eivät voi olla listassa", list['b']==null);
    }
    
    
}
