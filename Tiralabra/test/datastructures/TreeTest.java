package datastructures;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import datastructures.Tree;
import datastructures.Node;
import java.util.Arrays;
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
    
    @Test
    public void doesSizeWork() {
        assertEquals(5, tree.size());
    }
    
    @Test
    public void doesTreeToBinaryWork() {
        byte[] kissa = new byte[8];
        kissa[0] = 7;
        kissa[1] = 46;
        kissa[2] = 115;
        kissa[3] = 46;
        kissa[4] = 107;
        kissa[5] = 46;
        kissa[6] = 97;
        kissa[7] = 105;
        
        Node node1 = new Node('*',1);
        Node node2 = new Node('s',1);
        Node node3 = new Node('*',1);
        Node node4 = new Node('k',1);
        Node node5 = new Node('*',1);
        Node node6 = new Node('a',1);
        Node node7 = new Node('i',1);
        
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setLeft(node4);
        node3.setRight(node5);
        node5.setLeft(node6);
        node5.setRight(node7);
        
        Tree tree2 = new Tree(node1);
        
        assertFalse("treeToBinary ei toimi", Arrays.equals(kissa,tree.treeToBinary()));
    }
    
}
