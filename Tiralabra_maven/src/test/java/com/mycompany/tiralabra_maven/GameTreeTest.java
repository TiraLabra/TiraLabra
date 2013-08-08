/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven;

import com.mycompany.tiralabra_maven.data_structures.GameTreeNode;
import com.mycompany.tiralabra_maven.data_structures.StackNode;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jonu
 */
public class GameTreeTest extends TestCase{
    private GameTreeNode tree;
    
    
    public GameTreeTest() {
        
    }
    

    
    @Before
    @Override
    public void setUp() {
        tree = new GameTreeNode(-2, -2);
    }
    
    
    
    @Test
    public void testAdding(){
        tree.addChild(new StackNode(0, -1));
        tree.addChild(new StackNode(0, 0));
        tree.addChild(new StackNode(0, 1));
        
        tree.addChild(new StackNode(1, -1));
        tree.addChild(new StackNode(1, 0));
        tree.addChild(new StackNode(1, 1));
        
        tree.addChild(new StackNode(2, -1));
        tree.addChild(new StackNode(2, 0));
        tree.addChild(new StackNode(2, 1));
        
        assertEquals(tree.getChild(0, 0), new GameTreeNode(0, 0));
        assertEquals(tree.getChildren()[5], new GameTreeNode(1, 1));
        assertEquals(tree.getChild(new StackNode(2, 0)), tree.getChildren()[7]);
    }
    
    @Test
    public void testGoingThrough(){
        StackNode sn = new StackNode(0, -1);
        tree.addChild(sn);
        assertEquals(tree.getChild(sn), new GameTreeNode(sn.getMove(), sn.getResult()));
        tree = tree.getChild(sn);
        
        sn = new StackNode(0, 0);
        tree.addChild(sn);
        assertEquals(tree.getChild(sn), new GameTreeNode(sn.getMove(), sn.getResult()));
        tree = tree.getChild(sn);
        
        sn = new StackNode(0, 1);
        tree.addChild(sn);
        assertEquals(tree.getChild(sn), new GameTreeNode(sn.getMove(), sn.getResult()));
        tree = tree.getChild(sn);
        
        sn = new StackNode(1, -1);
        tree.addChild(sn);
        assertEquals(tree.getChild(sn), new GameTreeNode(sn.getMove(), sn.getResult()));
        tree = tree.getChild(sn);
        
        sn = new StackNode(1, 0);
        tree.addChild(sn);
        assertEquals(tree.getChild(sn), new GameTreeNode(sn.getMove(), sn.getResult()));
        tree = tree.getChild(sn);
        
        sn = new StackNode(1, 1);
        tree.addChild(sn);
        assertEquals(tree.getChild(sn), new GameTreeNode(sn.getMove(), sn.getResult()));
        tree = tree.getChild(sn);
        
        sn = new StackNode(2, -1);
        tree.addChild(sn);
        assertEquals(tree.getChild(sn), new GameTreeNode(sn.getMove(), sn.getResult()));
        tree = tree.getChild(sn);
        
        sn = new StackNode(2, 0);
        tree.addChild(sn);
        assertEquals(tree.getChild(sn), new GameTreeNode(sn.getMove(), sn.getResult()));
        tree = tree.getChild(sn);
        
        sn = new StackNode(2, 1);
        tree.addChild(sn);
        assertEquals(tree.getChild(sn), new GameTreeNode(sn.getMove(), sn.getResult()));
        tree = tree.getChild(sn);
    }

}
