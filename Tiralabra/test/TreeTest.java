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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    /**
     * Testaa löytyykö oikea lapsi
     */
    @Test
    public void findRightChild() {
        
        assertEquals("1", tree.find('c'));
    }
    /**
     * löytyykö vasemman lapsen oikea lapsi
     */
    @Test
    public void findLeftRightChild() {
        
        assertEquals("01", tree.find('d'));
    }
    /**
     * löytyykö vasemman lapsen vasen lapsi
     */
    @Test
    public void findLeftLeftChild() {
        
        assertEquals("00", tree.find('e'));
    }
}
